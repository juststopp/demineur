package utils.Tiles;

import java.util.ArrayList;

public enum TileType {

    MINE("X"),
    EMPTY("");

    private String icon;

    TileType(String icon) {
        this.icon = icon;
    }

    public static ArrayList<TileType> getTileTypes() {
        ArrayList<TileType> tileTypes = new ArrayList<>();
        tileTypes.add(MINE);
        tileTypes.add(EMPTY);

        return tileTypes;
    }

    public String getIcon() { return icon; }
}
