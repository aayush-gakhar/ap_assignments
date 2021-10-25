package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final Player player;
    private final Dice dice = new Dice(2);
    private final List<Floor> floors = new LinkedList<>();
    private boolean started = false;
    private final Scanner scanner;

    Game(String name, Scanner scanner) {
        this.player = new Player(name, this);
        this.scanner=scanner;
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the player name and hit enter");
        String name =scanner.nextLine();
        Game game = new Game(name,scanner);
        game.bonus();
        while (true) {
            System.out.print("Hit enter to roll the dice");
            game.pressEnter();
//            int roll=scanner.nextInt();
            int roll = game.dice.roll();
            game.playAMove(roll);
            if (game.gameOver()) {
                System.out.println("Game over");
                System.out.println(game.player + " accumulated " + game.player.getPoints() + " points");
                break;
            }
        }
    }

    public void bonus(){
        System.out.print("Want bonus?[yN]: ");
        String in=scanner.next().toLowerCase();
        if(in.equals("n")){
            return;
        }else if(in.equals("y")){
            _bonus();
        }else {
            bonus();
        }
    }

    public void _bonus(){
        System.out.println("Add new floors!!!");
        System.out.print("Enter no of floors you want to add: ");
        int newFloors=scanner.nextInt();
        System.out.println("floor menu:\n1.Empty floor\n2.Normal ladder floor\n3.Elevator floor\n4.Normal snake floor\n5.King cobra floor");
        for (int i = 0; i < newFloors; i++) {
            int floorId=scanner.nextInt();
            if(floorId<1||floorId>5){
                System.out.println("Invalid input");
                i--;
                continue;
            }
            if(floorId==1){
                floors.add(new EmptyFloor());
                System.out.println("Added empty floor");
            }else if(floorId==2){
                if(newFloors-i<5){
                    System.out.println("can't add ladder in last 4 floors");
                    i--;
                    continue;
                }
                floors.add(new NormalLadderFloor());
                System.out.println("Added normal ladder floor");
            }else if(floorId==3){
                if(newFloors-i<9){
                    System.out.println("can't add Elevator in last 8 floors");
                    i--;
                    continue;
                }
                floors.add(new ElevatorFloor());
                System.out.println("Added elevator floor");
            }else if(floorId==4){
                if(floors.get(floors.size()-1).getClass()==NormalSnakeFloor.class ||
                        floors.get(floors.size()-1).getClass()==KingCobraSnakeFloor.class){
                    System.out.println("can't add snake floors continuously");
                    i--;
                    continue;
                }
                floors.add(new NormalSnakeFloor());
                System.out.println("Added normal snake floor");
            }else{
                if(floors.get(floors.size()-1).getClass()==NormalSnakeFloor.class ||
                        floors.get(floors.size()-1).getClass()==KingCobraSnakeFloor.class){
                    System.out.println("can't add snake floors continuously");
                    i--;
                    continue;
                }
                floors.add(new KingCobraSnakeFloor());
                System.out.println("Added King cobra snake floor");
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

    public boolean gameOver(){
        return player.getPosition()==floors.size()-1;
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
        if (player.getPosition() + roll > floors.size()-1) {
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
