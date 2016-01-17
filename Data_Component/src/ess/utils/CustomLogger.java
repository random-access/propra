package ess.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import ess.exc.PropertyException;
import ess.strings.CustomErrorMessages;

/**
 * This class provides a customized global <code>Logger</code> object. 
 * The minimum log level and the output target can be set in the config.properties file.
 * To format the log output, an instance {@link CustomLogFormatter} is used
 * 
 * @author Monika Schrenk
 *
 */
public final class CustomLogger {
    
    // constant for log file names
    private static final String LOGFILE_NAME = "log_%g.txt";
    
    // constants for log rotation & file size
    private static final int MB = 1048576;
    private static final int LOG_FILE_SIZE = 100 * MB;
    private static final int NO_OF_LOGFILES = 5;
    
    // the global logger
    private static Logger logger;

    // prevents instantiation
    private CustomLogger() { }
    
    /**
     * Method for obtaining a customized <code>Logger</code>, using the settings
     * from the configuration file.
     * 
     * @return a customized logger
     * @throws PropertyException PropertyException if there is an error in the properties file, invalid property values
     * or if logs cannot be written to the given destination.
     */
    public static synchronized Logger getLogger() {
        if (logger == null) {
            logger = new CustomLogger().setup();
        }
        return logger;
    }
    
    
    /**
     * Configures the global logger by configuring:
     * <ul>
     *      <li>The log level: Only messages with an equal or higher {@link Level} get logged</li>
     *      <li>Console output: Can be turned off or on</li>
     *      <li>Logging to file: If a log directory is specified, log messages will be written
     *          to files (with Log Rotation)</li>
     * </ul>
     * These 3 parameters can be controlled through a properties file that is placed in the workspace.
     */
    private Logger setup() {
            ProPraProperties properties;
            try {
                properties = ProPraProperties.getInstance();
                
                // Get the global logger object
                logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
                
                // set log level
                setLogLevel(properties.getValue(ProPraProperties.KEY_LOG_LEVEL));
                
                // Configure log output
                handleLoggingToConsole(properties.getValue(ProPraProperties.KEY_LOG_CONSOLE));
                handleLoggingToFile(properties.getValue(ProPraProperties.KEY_LOG_FILE));
                
                // Customize formatting
                Logger rootLogger = Logger.getLogger("");
                for (Handler h : rootLogger.getHandlers()) {
                    h.setFormatter(new CustomLogFormatter());
                }
                
                logger.log(logger.getLevel(), "Initialized logging, log level: " + logger.getLevel());
                
            } catch (PropertyException e) {
                logger.setLevel(Level.OFF);
            }
            return logger;
    }

    /**
     * Parses the path to a directory where the logs should be stored.
     * Create the necessary directory structure if it doesn't exist yet and
     * setup a file handler to manage writing to file. 
     * The logs are rotated.
     * @param path log directory
     * @throws PropertyException if log directory cannot be created due to insufficient permissions
     * or other I/O errors
     */
    private void handleLoggingToFile(String path) {
        try {
            // do not log, if no pathname is specified
            if (path == null || path.isEmpty()) {
                return;
            }
            
            // create necessary directory hierarchy, if directory doesn't exist yet
            if (!Files.isDirectory(Paths.get(path))) {
                Files.createDirectory(Paths.get(path));
            }
            
            // create a file handler to manage logging to file and add it to the logger
            FileHandler fileHandler = new FileHandler(path + LOGFILE_NAME, LOG_FILE_SIZE, NO_OF_LOGFILES, false);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (SecurityException | IOException e) {
            logger.warning(CustomErrorMessages.ERROR_LOG_CREATE);
        }

    }

    /**
     * Parses the value of log_console from config.properties to determine if
     * logging should be displayed.
     * This method treats any invalid output as if log_console was set to false.
     * 
     * @param consoleOutput
     *            a String containing "true" if there should be log output, else
     *            "false".
     */
    private void handleLoggingToConsole(String consoleOutput) {
        
        // warn user on invalid input
        if (!consoleOutput.equalsIgnoreCase("true") && !(consoleOutput.equalsIgnoreCase("false"))) {
            logger.warning(CustomErrorMessages.ERROR_INVALID_VALUE_LOG_CONSOLE);
        }
        
        // if there should be console output, there is nothing to do
        if (Boolean.parseBoolean(consoleOutput)) {
            return;
        }
        
        // remove the ConsoleHandler from the root logger to disable console output
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers.length > 0 && handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }
    }

    /**
     * Parses the value of log_level from config.properties. Every log with level
     * equal or above the given log level will pass the filter and be displayed
     * or written to file.
     * If no valid Level is specified in config.properties, the log level is set to OFF
     * (usually I would output a short warning message about invalid logger configuration,
     * but in the final version there should be no logging output at all, therefore
     * I decided to use this solution).
     * 
     * @param logLevelName
     *            should be one of the 7 LogLevels defined in {@link Level}. The
     *            values OFF (no logging at all) and ALL (all messages get
     *            logged) are accepted as well.
     * @see Level
     */
    private void setLogLevel(String logLevelName) {
        Level logLevel;
        try {
            logLevel = Level.parse(logLevelName.toUpperCase());
        } catch (IllegalArgumentException e) {
            logLevel = Level.OFF;
        }
        logger.setLevel(logLevel);
        Logger rootLog = Logger.getLogger("");
        rootLog.setLevel(logLevel);
        Handler[] handlers = rootLog.getHandlers();
        if (handlers.length > 0) {
            handlers[0].setLevel(logLevel);
        }
        
    }

}
