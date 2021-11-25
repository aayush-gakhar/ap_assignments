package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private final Player player=new Player();
    private final List<Tile> tiles=new ArrayList<Tile>();
    private final Generator generator=new Generator();
    private final Calculator<String> strCalculator=new Calculator<String>();
    private final Calculator<Integer> intCalculator=new Calculator<Integer>();
    private final Random random = new Random();
    private final Scanner scanner;
    private int counter=0;

    public Game(Scanner scanner) {
        this.scanner = scanner;
        tiles.add(new Tile(new Toy("Teddy Bear")));
        tiles.add(new Tile(new Toy("Donald Duck")));
        tiles.add(new Tile(new Toy("Puppy")));
        tiles.add(new Tile(new Toy("Mickey Mouse")));
        tiles.add(new Tile(new Toy("Jerry")));
        tiles.add(new Tile(new Toy("Tom")));
        tiles.add(new Tile(new Toy("Scooby Doo")));
        tiles.add(new Tile(new Toy("SpongeBob")));
        tiles.add(new Tile(new Toy("Winnie the Pooh")));
        tiles.add(new Tile(new Toy("Pink Panther")));
        tiles.add(new Tile(new Toy("Perry the Platypus")));
        tiles.add(new Tile(new Toy("Batman")));
        tiles.add(new Tile(new Toy("Popeye")));
        tiles.add(new Tile(new Toy("Iron Man")));
        tiles.add(new Tile(new Toy("Pikachu")));
        tiles.add(new Tile(new Toy("Superman")));
        tiles.add(new Tile(new Toy("Optimus Prime")));
        tiles.add(new Tile(new Toy("Captain America")));
        tiles.add(new Tile(new Toy("Doraemon")));
        tiles.add(new Tile(new Toy("Minion")));
        System.out.println("Game is ready");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Hit enter to initialize the game");
        pressEnter();
        Game game = new Game(scanner);
        game.start();
    }

    public static void pressEnter() {
//        try {
//            System.in.read();
//        } catch (Exception e) {
//
//        }
        System.out.println();
    }

    public void start() {
        while (counter<5){
            System.out.printf("Hit enter for your %s hop",strTurn());
            pressEnter();
            int tile=hop();
            if(tile>20){
                System.out.println("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!");
            }else{
                System.out.println("You landed on tile "+tile);
                if(tile%2==0){
                    System.out.println("Question answer round. Integer or string?");
                    String type = scanner.next();
                    if (type.equals("integer")) {
                        Integer[] arr= generator.generateIntegers();
                        System.out.printf("Calculate the result of %d divided by %d\n",arr[0],arr[1]);
                        int correctAnswer=strCalculator.calculate(arr);
                        int answer=scanner.nextInt();
                        if(answer==correctAnswer){
                            System.out.println("Correct answer");
                        }else {
                            continue;
                        }
                    }else if(type.equals("string")){
                        String[] arr=generator.generateStrings();
                        System.out.printf("Calculate the concatenation of strings %s and %s",arr[0],arr[1]);
                        String correctAnswer=strCalculator.calculate(arr);
                        String answer=scanner.next();
                        if(correctAnswer.equals(answer)){
                            System.out.println("Correct answer");
                        }else {
                            continue;
                        }
                    }
                }
                System.out.printf("You won a %s soft toy\n",tiles.get(tile-1).getToy());
                player.addToy(tiles.get(tile-1).getToy().clone());
            }
            counter++;
        }
        System.out.println("Game over");
        System.out.println("Soft toys won by you are:");
        boolean first = true;
        for(Toy toy:player.getBucket().getToys()){
            if (first) {
                System.out.print(toy);
                first = false;
            } else {
                System.out.print(", "+toy);
            }
        }
    }

    public String strTurn(){
        return switch (counter) {
            case 0 -> "first";
            case 1 -> "second";
            case 2 -> "third";
            case 3 -> "fourth";
            case 4 -> "fifth";
            default -> "";
        };
    }

    public int hop(){
        return 1+random.nextInt(21);
    }

}
// doubts:
// 1. integer division?
// 2. generic, exceptions,
