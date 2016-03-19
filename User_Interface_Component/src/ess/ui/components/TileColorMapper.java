package ess.ui.components;

import java.awt.Color;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import ess.data.Tile;

public class TileColorMapper {

    private static TileColorMapper instance;
    private Random random;
    private HashMap<Tile, Color> colorMap;
    
    private TileColorMapper(ArrayList<Tile> tileSorts) {
        colorMap = new HashMap<>();
        random = new Random();
        initialize(tileSorts);
    }

    public static TileColorMapper getInstance(ArrayList<Tile> tileSorts) {
        if (instance == null) {
            instance = new TileColorMapper(tileSorts);
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
        for (Tile t : tileSorts) {
            colorMap.put(t, generateRandomColor());
        }   
    }

    private Color generateRandomColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }
}
