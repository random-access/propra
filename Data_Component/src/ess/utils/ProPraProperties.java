package ess.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class ProPraProperties {
	
	// logging keys
	public static final String KEY_LOG_LEVEL = "log_level";
	public static final String KEY_LOG_FILE = "log_file";
	public static final String KEY_LOG_CONSOLE = "log_console";
	
	// rules keys
	public static final String RULES_PACKAGE = "ess.rules.explicit.";
	public static final String KEY_MAX_LINE_LENGTH = "MaxLineLengthRule";
	public static final String KEY_CROSSINGS = "CrossingsRule";
	public static final String KEY_REPLACEABLE_TILE= "ReplaceableTileRule";
	public static final String KEY_SAME_TILE = "SameTileRule";
	
	// heuristics keys
	public static final String HEURISTICS_PACKAGE = "ess.algorithm.modules.";
	public static final String KEY_POSITION_FINDER = "position_finder";
	public static final String KEY_TILE_CHOOSER = "tile_chooser";
	public static final String KEY_TILE_CHOOSER_STRATEGY = "tile_chooser_strategy";
	
	private static ProPraProperties instance;
	private Properties properties;
	private static final String PATH_TO_PROPERTIES = "../Rules_Component/config/config.properties";

	// TODO handle parameter parsing in config file
	
	private ProPraProperties() throws PropertyException {
		try {
			properties = loadProperties();
		} catch (IOException e) {
			throw new PropertyException("Error: Property file is not existing or cannot be read.", e);
		}
	}
	
	public static synchronized ProPraProperties getInstance() throws PropertyException {
		if (instance == null) {
			instance = new ProPraProperties();
		}
		return instance;
	}
	
	/**
	 * Load properties from config.properties.
	 * @return a Properties object holding the values from the config file.
	 * @throws PropertyException
	 *             if the file is not existing or cannot be read.
	 */
	private Properties loadProperties() throws FileNotFoundException, IOException {
		File propertiesFile = new File(PATH_TO_PROPERTIES);
		Properties properties = new Properties();
		properties.load(new FileReader(propertiesFile));
		return properties;
	}
	
	public ArrayList<String> getActiveRuleNames() throws PropertyException {
		ArrayList<String> rules = new ArrayList<>();
		if (parseBoolean(KEY_MAX_LINE_LENGTH)) {
			rules.add(RULES_PACKAGE + KEY_MAX_LINE_LENGTH);
		}
		if (parseBoolean(KEY_CROSSINGS)) {
			rules.add(RULES_PACKAGE + KEY_CROSSINGS);
		}
		if (parseBoolean(KEY_REPLACEABLE_TILE)) {
			rules.add(RULES_PACKAGE + KEY_REPLACEABLE_TILE);
		}
		if (parseBoolean(KEY_SAME_TILE)) {
			rules.add(RULES_PACKAGE + KEY_SAME_TILE);
		}
		return rules;
	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}
	
	private boolean parseBoolean(String key) throws PropertyException {
		if (!properties.getProperty(key).equals("true") && !properties.getProperty(key).equals("false")) {
			throw new PropertyException("Error");
		}
		return Boolean.parseBoolean(properties.getProperty(key));
	}

}
