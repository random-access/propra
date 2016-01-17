package ess.io;

/**
 * This class holds String values used by {@link ess.io.XMLDataExchanger}.
 * 
 * @author Monika Schrenk
 *
 */
public final class XMLValues {
	
	// prevents instantiation
	private XMLValues() { }
	
	/**
	 * Path to the DTD file for XML validation, this path works in the whole project
	 */
	public static final String PATH_TO_DTD = "/resources/DataModel.dtd";
	
	/**
	 * Integer used for converting Tiles to their internal representation.
	 * If <code>CONVERSION_UNIT</code> = x, we assume that all tile sorts and the surfaces width and length 
	 * are multiples of x, and can be divided by x for internal data representation.
	 */
	public static final int CONVERSION_UNIT = 20;
	
	
	/*********************************** XML tags & attributes ****************************************/
	
	/**
	 * XML attribute / tag for lenght1 of tile sorts / surface
	 */
	public static final String LENGTH_1 = "length1";
	
	/**
	 * XML attribute / tag for lenght2 of tile sorts / surface
	 */
	public static final String LENGTH_2 = "length2";
	
	/**
	 * XML tag for tile sorts
	 */
	public static final String FLIESENTYPEN = "Fliesentypen";
	
	/**
	 * XML tag for single tile sort
	 */
	public static final String FLIESENTYP = "Fliesentyp";
	
	/**
	 * XML tag for composite
	 */
	public static final String VERLEGUNGSPLAN = "Verlegungsplan";
	
	/**
	 * XML tag for tile in composite
	 */
	public static final String PLATTE = "Platte";
	
	/**
	 * XML attribute for tile sort id's
	 */
	public static final String IDENT = "ident";
	
	/**
	 * XML tag for tile id
	 */
	public static final String FLIESEN_ID = "fliesenId";
	
	/**
	 * XML tag for tile number in composite
	 */
	public static final String NR = "Nr";	
}
