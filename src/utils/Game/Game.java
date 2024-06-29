package utils.Game;

import utils.DemineurMap;

import java.util.Scanner;

public class Game {

    private GameType type;
    private DemineurMap map;

    public Game() {
        this.type = GameType.WAITING;
        this.map = new DemineurMap(10);
    }

    public GameType getType() { return type; }
    public void setType(GameType type) { this.type = type; }

    public DemineurMap getMap() { return map; }

    public void start() {
        this.map.print(false);
        this.type = GameType.RUNNING;

        while (this.type == GameType.RUNNING) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("[ + ] Ligne: ");
            String ligne = scanner.nextLine();

            System.out.print("[ + ] Colone: ");
            String colone = scanner.nextLine();

            try {
                if(this.map.getMap()[Integer.parseInt(ligne)][Integer.parseInt(colone)].getShow()){
                    throw new Exception();
                }

                this.type = this.map.getMap()[Integer.parseInt(ligne)][Integer.parseInt(colone)].setShow(true, this.getMap(), false);
                this.map.print(false);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("[ - ] Coordonnées incorrecte.");
                this.map.print(false);
            }
        }

        if(this.type == GameType.WON) {
            System.out.println("[ + ] You won!");
        } else {
            System.out.println("[ - ] You lost!");
            System.out.println("[ + ] Here is the solution:");
            System.out.println(" ");

            this.map.print(true);
        }
    }
}
