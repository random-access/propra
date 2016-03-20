package ess.ui.components;

import java.awt.Color;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import ess.data.Tile;
import ess.strings.CustomErrorMessages;
import ess.ui.components.CompositePanel.ColorStrategy;

/**
 * This class maps tiles to colors, implementing the Singleton pattern.
 * 
 * @author Monika Schrenk
 *
 */
public class TileColorMapper {

    private static TileColorMapper instance;
    private Random random;
    private HashMap<Tile, Color> colorMap;
    private ColorStrategy strategy;
    
    // step size for color shades
    private static final int COLOR_STEP = 70;
    
    // private constructor, executed only once
    private TileColorMapper(ArrayList<Tile> tileSorts, ColorStrategy strategy) {
        this.strategy = strategy;
        colorMap = new HashMap<>();
        random = new Random();
        initialize(tileSorts);
    }

    /**
     * Method for obtaining an instance of TileColorMapper
     * @param tileSorts list of tiles to be colored
     * @param strategy color strategy, parsed from XML
     * @return an instance of TileColorMapper
     */
    public static TileColorMapper getInstance(ArrayList<Tile> tileSorts, ColorStrategy strategy) {
        if (instance == null) {
            instance = new TileColorMapper(tileSorts, strategy);
        }
        return instance;
    }
    
    /**
     * Method for for getting the color to use for a certain tile.
     * @param tile the tile that should be colored.
     * @return a color
     */
    public Color getColor(Tile tile) {
        Color color = colorMap.get(tile);
        if (color == null) {
            throw new InvalidParameterException(CustomErrorMessages.ERROR_COLOR_NOT_FOUND);
        }
        return color;
    }
    
    // method for filling the tile-color map
    private void initialize(ArrayList<Tile> tileSorts) {
        for (int i = 0; i < tileSorts.size(); i++) {
            switch(strategy) {
                case LARGEST_TILE_RED:
                    colorMap.put(tileSorts.get(i), generateColorShades(Color.RED, i));
                    break;
                case SMALLEST_TILE_GREEN:
                    colorMap.put(tileSorts.get(i), generateColorShades(Color.GREEN, i));
                    break;
                case RANDOM_COLOR:
                    colorMap.put(tileSorts.get(i), generateRandomColor());
                    break;
                case NO_COLOR:
                    // do nothing
            }
            
        }   
    }

    // Method for generating a random color
    private Color generateRandomColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }
    
    // Method for generating a shade of a color, the higher the index, the more it
    // differs from the original color
    private Color generateColorShades(Color color, int index) {
        int r = modifyColorComponent(color.getRed(), index);
        int g = modifyColorComponent(color.getGreen(), index);
        int b = modifyColorComponent(color.getBlue(), index);
        return new Color(r, g, b);
    }
    
    // Method for modifying a single r/g/b value for generating color shades
    private int modifyColorComponent(int c, int index) {
        if (c == 0) {
            return 0;
        }
        int modified = c - index * COLOR_STEP;
        while (modified < 0) {
            modified += 256;
        }
        return modified;
    }
}
