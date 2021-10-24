package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final Player player;
    private final Dice dice = new Dice(2);
    private final List<Floor> floors = new LinkedList<>();
    private boolean started = false;

    Game(String name) {
        this.player = new Player(name, this);
        floors.add(new EmptyFloor());
        floors.add(new EmptyFloor());
        floors.add(new ElevatorFloor());
        floors.add(new EmptyFloor());
        floors.add(new EmptyFloor());
        floors.add(new NormalSnakeFloor());
        floors.add(new EmptyFloor());
        floors.add(new EmptyFloor());
        floors.add(new NormalLadderFloor());
        floors.add(new EmptyFloor());
        floors.add(new EmptyFloor());
        floors.add(new KingCobraSnakeFloor());
        floors.add(new EmptyFloor());
        floors.add(new EmptyFloor());
        System.out.println("The game setup is ready");
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the player name and hit enter");
        String name =scanner.nextLine();
        Game game = new Game(name);
        while (true) {
            System.out.print("Hit enter to roll the dice");
            game.pressEnter();
//            int roll=scanner.nextInt();
            int roll = game.dice.roll();
            game.playAMove(roll);
            if (game.player.getPosition() == 13) {
                System.out.println("Game over");
                System.out.println(game.player + " accumulated " + game.player.getPoints() + " points");
//                Path fileName = Path.of("/Users/aayushgakhar/Desktop/score.txt");
//                int max=Integer.parseInt(Files.readString(fileName));
//                if(game.player.getPoints()>max)
//                    Files.writeString(fileName,String.valueOf(game.player.getPoints()));
                break;
            }
        }
    }

    public void pressEnter() {
        try {
            System.in.read();
        } catch (Exception e) {

        }
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void playAMove(int roll) {
        if (!isStarted()) {
            if (roll == 1) {
                setStarted(true);
            } else {
                System.out.println("Game cannot start until you get 1");
                return;
            }
        }
        if (player.getPosition() + roll > 13) {
            System.out.println("Player cannot move");
            return;
        }
        player.movePosition(roll);
        player.addPoints(player.getCurrentFloor().getPoints());
        System.out.println("Player position Floor-" + player.getPosition());
        System.out.println(player + " has reached an " + floors.get(player.getPosition()));
        System.out.println("Total points " + player.getPoints());
        if (player.getCurrentFloor().getJump() == 0) {
            return;
        }
        playAMove(player.getCurrentFloor().getJump());
    }
}
