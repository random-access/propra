package ess.ui.components;

import java.awt.Color;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import ess.data.Tile;
import ess.ui.components.CompositePanel.ColorStrategy;

public class TileColorMapper {

    private static TileColorMapper instance;
    private Random random;
    private HashMap<Tile, Color> colorMap;
    private ColorStrategy strategy;
    
    private static final int COLOR_STEP = 70;
    
    private TileColorMapper(ArrayList<Tile> tileSorts, ColorStrategy strategy) {
        this.strategy = strategy;
        colorMap = new HashMap<>();
        random = new Random();
        initialize(tileSorts);
    }

    public static TileColorMapper getInstance(ArrayList<Tile> tileSorts, ColorStrategy strategy) {
        if (instance == null) {
            instance = new TileColorMapper(tileSorts, strategy);
        }
        return instance;
    }
    
    public Color getColor(Tile tile) {
        Color color = colorMap.get(tile);
        if (color == null) {
            throw new InvalidParameterException("Color not in map"); // TODO
        }
        return color;
    }
    
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

    private Color generateRandomColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }
    
    private Color generateColorShades(Color color, int index) {
        int r = modifyColorComponent(color.getRed(), index);
        int g = modifyColorComponent(color.getGreen(), index);
        int b = modifyColorComponent(color.getBlue(), index);
        return new Color(r, g, b);
    }
    
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
