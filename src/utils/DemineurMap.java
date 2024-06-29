package utils;

import utils.Tiles.Tile;
import utils.Tiles.TileType;

import java.util.*;

public class DemineurMap {

    private int size;
    private Tile[][] map;

    public DemineurMap(int size) {
        this.size = size;
        this.map = this.generate();
    }

    public Tile[][] generate() {
        Tile[][] map = new Tile[this.size][this.size];

        for (int ligne = 0; ligne < size; ligne++) {
            for (int colone = 0; colone < size; colone++) {
                Random rand = new Random();
                TileType type;

                if(rand.nextInt(100) >= 80) type = TileType.MINE;
                else type = TileType.EMPTY;

                map[ligne][colone] = new Tile(type, ligne, colone);
            }
        }

        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                map[x][y].minesArround = map[x][y].calculateMinesArround(map, size, x, y);
            }
        }

        return map;
    }

    public void discover(int ligne, int colone, boolean first) {
        if(colone < 0 || ligne < 0 || colone >= this.size || ligne >= this.size) return;

        if((first && map[ligne][colone].minesArround == 0) || (map[ligne][colone].getType() == TileType.EMPTY && map[ligne][colone].minesArround == 0 && !map[ligne][colone].getShow())) {
            map[ligne][colone].setShow(true, this, true);

            if(ligne > 0) {
                if(colone > 0 && map[ligne - 1][colone - 1].getType() == TileType.EMPTY && !map[ligne - 1][colone - 1].getShow()) discover(ligne - 1, colone - 1, false);
                if(map[ligne - 1][colone].getType() == TileType.EMPTY && !map[ligne - 1][colone].getShow()) discover(ligne - 1, colone, false);
                if(colone < size - 1 && map[ligne - 1][colone + 1].getType() == TileType.EMPTY && !map[ligne - 1][colone + 1].getShow()) discover(ligne - 1, colone + 1, false);
            }

            if(colone > 0 && map[ligne][colone - 1].getType() == TileType.EMPTY && !map[ligne][colone - 1].getShow()) discover(ligne, colone - 1, false);
            if(colone < size - 1 && map[ligne][colone + 1].getType() == TileType.EMPTY && !map[ligne][colone + 1].getShow()) discover(ligne, colone + 1, false);

            if(ligne < size - 1) {
                if(colone > 0 && map[ligne + 1][colone - 1].getType() == TileType.EMPTY && !map[ligne + 1][colone - 1].getShow()) discover(ligne + 1, colone - 1, false);
                if(map[ligne + 1][colone].getType() == TileType.EMPTY && !map[ligne + 1][colone].getShow()) discover(ligne + 1, colone, false);
                if(colone < size - 1 && map[ligne + 1][colone + 1].getType() == TileType.EMPTY && !map[ligne + 1][colone + 1].getShow()) discover(ligne + 1, colone + 1, false);
            }

        }
    }

    public void print(boolean showSolution) {
        System.out.println(" ");
        System.out.println("    0 1 2 3 4 5 6 7 8 9");
        System.out.println("    ___________________");

        for (int ligne = 0; ligne < size; ligne++) {
            StringBuilder line = new StringBuilder();
            line.append(ligne);
            line.append(" |");

            for (int colone = 0; colone < size; colone++) {
                String icon = map[ligne][colone].getType().getIcon();
                line.append(" ");

                if(map[ligne][colone].getShow() || showSolution) {
                    if(map[ligne][colone].getType() == TileType.EMPTY) {
                        line.append(map[ligne][colone].minesArround);
                    } else {
                        line.append(icon);
                    }
                }
                else { line.append("-"); }
            }

            System.out.println(line);
        }

        System.out.println(" ");
    }

    public Tile[][] getMap() {
        return map;
    }

    public int getSize() {
        return size;
    }
}
