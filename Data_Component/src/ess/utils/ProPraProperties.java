package ess.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import ess.exc.PropertyException;
import ess.strings.CustomErrorMessages;

/**
 * This class reads all values from config.properties, stores them in a Property
 * object and provides a basic method to retrieve the value from a certain key
 * and a customized method for getting a list of explicit rules. This class
 * implements the Singleton pattern. It relies on a valid config.properties file
 * with all the values below.
 * 
 * @author Monika Schrenk
 *
 */
public final class ProPraProperties {

	/********************** logging keys **********************************/

	/** Property key for the log level. */
	public static final String KEY_LOG_LEVEL = "log_level";

	/** Property key for the log file. */
	public static final String KEY_LOG_FILE = "log_file";

	/** Property key for the console logging. */
	public static final String KEY_LOG_CONSOLE = "log_console";

	/********************** rules keys ***********************************/

	/** Name of the explicit rules package. */
	public static final String EXPLICIT_RULES_PKG = "ess.rules.explicit.";

	/** Property key for the max line length rule. */
	public static final String KEY_MAX_LINE_LENGTH = "MaxLineLengthRule";

	/** Property key for the crossings rule. */
	public static final String KEY_CROSSINGS = "CrossingsRule";

	/** Property key for the replaceable tile rule. */
	public static final String KEY_REPLACEABLE_TILE = "ReplaceableTileRule";

	/** Property key for the same tile rule. */
	public static final String KEY_SAME_TILE = "SameTileRule";

	/********************** heuristics keys *******************************/

	/** Name of the heuristics package. */
	public static final String HEURISTICS_PACKAGE = "ess.algorithm.modules.";

	/** Property key for the position finder */
	public static final String KEY_POSITION_FINDER = "position_finder";

	/** Property key for the tile chooser */
	public static final String KEY_TILE_CHOOSER = "tile_chooser";

	/** Property key for the tile chooser strategy */
	public static final String KEY_TILE_CHOOSER_STRATEGY = "tile_chooser_strategy";

	// ProPraProperties singleton
	private static ProPraProperties instance;

	// A map, holding the actual property key-value pairs
	private Properties properties;

	// path to properties file
	private static final String PATH_TO_PROPERTIES = "/resources/config.properties";

	// private constructor - prevent instantiation from outside this class
	private ProPraProperties() throws PropertyException {
		try {
			properties = loadProperties();
		} catch (IOException e) {
			throw new PropertyException(
					CustomErrorMessages.ERROR_PROPERTY_READ, e);
		}
	}

	/**
	 * Returns a singleton of ProPraProperties. This method is synchronized to
	 * make sure that every caller receives the same data, synchronization is not 
	 * yet needed because the config file only gets read.
	 * 
	 * @return an instance of ProPraProperties
	 * @throws PropertyException
	 *             if the properties file cannot be loaded
	 */
	public static synchronized ProPraProperties getInstance()
			throws PropertyException {
		if (instance == null) {
			instance = new ProPraProperties();
		}
		return instance;
	}

	/**
	 * Load properties from config.properties.
	 * 
	 * @return a Properties object holding the values from the config file.
	 * @throws PropertyException
	 *             if the file is not existing or cannot be read.
	 */
	private Properties loadProperties() throws FileNotFoundException,
			IOException {
		Properties p = new Properties();
		p.load(getClass().getResourceAsStream(PATH_TO_PROPERTIES));
		return p;
	}

	/**
	 * Returns a list with the full-qualified class names of all explicit rules
	 * that are activated in config.properties.
	 * 
	 * @return a list of all active explicit rules
	 * @throws PropertyException
	 *             when parsing an invalid value for a rule (neither true nor
	 *             false)
	 */
	public ArrayList<String> getActiveRuleNames() throws PropertyException {
		ArrayList<String> rules = new ArrayList<>();
		if (parseBoolean(KEY_MAX_LINE_LENGTH)) {
			rules.add(EXPLICIT_RULES_PKG + KEY_MAX_LINE_LENGTH);
		}
		if (parseBoolean(KEY_CROSSINGS)) {
			rules.add(EXPLICIT_RULES_PKG + KEY_CROSSINGS);
		}
		if (parseBoolean(KEY_REPLACEABLE_TILE)) {
			rules.add(EXPLICIT_RULES_PKG + KEY_REPLACEABLE_TILE);
		}
		if (parseBoolean(KEY_SAME_TILE)) {
			rules.add(EXPLICIT_RULES_PKG + KEY_SAME_TILE);
		}
		return rules;
	}

	/**
	 * Returns the value that is associated with key, or null if key doesn't
	 * match any value in config.properties
	 * 
	 * @param key
	 *            the property key
	 * @return the value associated with this key in config.properties or null
	 *         if no such key exists
	 */
	public String getValue(String key) {
		return properties.getProperty(key);
	}

	// parse boolean values - accept no other input than "true" or "false"
	// (ignoring case),
	// otherwise a typo in "true" could cause an accidental reading of "false"
	// without any warning
	private boolean parseBoolean(String key) throws PropertyException {
		if (!properties.getProperty(key).equalsIgnoreCase("true")
				&& !properties.getProperty(key).equalsIgnoreCase("false")) {
			throw new PropertyException("Error");
		}
		return Boolean.parseBoolean(properties.getProperty(key));
	}

}
