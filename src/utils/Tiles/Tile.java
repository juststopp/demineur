package utils.Tiles;

import utils.DemineurMap;
import utils.Game.GameType;

public class Tile {

    private TileType type;
    private Boolean show;

    public int minesArround;

    private int ligne;
    private int colone;

    public Tile(TileType type, int ligne, int colone) {
        this.type = type;
        this.show = false;

        this.ligne = ligne;
        this.colone = colone;
    }

    public TileType getType() { return type; }

    public Boolean getShow() { return show; }
    public GameType setShow(Boolean show, DemineurMap map, Boolean ignoreDiscover) {
        this.show = show;
        if(this.show && this.getType() == TileType.MINE) return GameType.LOOSE;

        int total = 0;
        int showed = 0;

        if(!ignoreDiscover) map.discover(this.ligne, this.colone, true);

        for (int ligne = 0; ligne < map.getSize(); ligne++) {
            for (int colone = 0; colone < map.getSize(); colone++) {
                if(map.getMap()[ligne][colone].getType() == TileType.EMPTY) {
                    total++;
                    if(map.getMap()[ligne][colone].getShow()) {
                        showed++;
                    }
                }
            }
        }

        return showed == total ? GameType.WON : GameType.RUNNING;
    }

    public int calculateMinesArround(Tile[][] map, int size, int ligne, int colone) {
        int minesArround = 0;

        if(ligne > 0) {
            if(colone > 0 && map[ligne - 1][colone - 1].getType() == TileType.MINE) minesArround++;
            if(map[ligne - 1][colone].getType() == TileType.MINE) minesArround++;
            if(colone < size - 1 && map[ligne - 1][colone + 1].getType() == TileType.MINE) minesArround++;
        }

        if(colone > 0 && map[ligne][colone - 1].getType() == TileType.MINE) minesArround++;
        if(colone < size - 1 && map[ligne][colone + 1].getType() == TileType.MINE) minesArround++;

        if(ligne < size - 1) {
            if(colone > 0 && map[ligne + 1][colone - 1].getType() == TileType.MINE) minesArround++;
            if(map[ligne + 1][colone].getType() == TileType.MINE) minesArround++;
            if(colone < size - 1 && map[ligne + 1][colone + 1].getType() == TileType.MINE) minesArround++;
        }

        return minesArround;
    }

    public int getLigne() { return ligne; }
    public int getColone() { return colone; }
}
