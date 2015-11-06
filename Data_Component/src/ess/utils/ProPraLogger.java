package ess.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ProPraLogger {
	
	private static final String KEY_LOG_LEVEL = "log_level";
	private static final String KEY_LOG_FILE = "log_file";
	private static final String KEY_LOG_CONSOLE = "log_console";
	
	private static final String PATH_TO_PROPERTIES = "../Rules_Component/config/config.properties";
	private static final int MB = 1048576;
	
	private static Logger logger;
	
	// prevents instatiation
	private ProPraLogger() {}
	
	public static void setup() throws FileNotFoundException, IOException, IllegalArgumentException {
		// load properties file
		Properties properties = loadProperties();
		
		// Get the global logger object & set log level
	    logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	    
	    setLogLevel(properties.getProperty(KEY_LOG_LEVEL));
	    handleLoggingToConsole(properties.getProperty(KEY_LOG_CONSOLE));
	    handleLoggingToFile(properties.getProperty(KEY_LOG_FILE));
	}
	
	private static void handleLoggingToFile(String path) throws SecurityException, IOException {
		if (path == null) {
			return;
		}
		FileHandler fileHandler = new FileHandler(path + "log_%g.txt", 100 * MB, 5, false);
		fileHandler.setFormatter(new SimpleFormatter());
		logger.addHandler(fileHandler);
	}

	
	private static void handleLoggingToConsole(String consoleOutput) {
		if (Boolean.parseBoolean(consoleOutput)) {
			return;
		}
		Logger rootLogger = Logger.getLogger("");
	    Handler[] handlers = rootLogger.getHandlers();
	    if (handlers[0] instanceof ConsoleHandler) {
	      rootLogger.removeHandler(handlers[0]);
	    }
	}
	
	private static void setLogLevel(String logLevelName) {
		Level logLevel = Level.parse(logLevelName);
	    logger.setLevel(logLevel);
	}
	
	private static Properties loadProperties() throws FileNotFoundException, IOException {
		File propertiesFile = new File(PATH_TO_PROPERTIES);
		Properties properties = new Properties();
		properties.load(new FileReader(propertiesFile));
		return properties;
	}
}
