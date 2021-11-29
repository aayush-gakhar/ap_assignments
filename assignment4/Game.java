import java.util.*;

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
        try {
            System.in.read();
        } catch (Exception e) {

        }
//        System.out.println();
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
                    if(!askQuestion()){
                        continue;
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

    public boolean askQuestion(){
        System.out.println("Question answer round. Integer or string?");
        String type = scanner.next().toLowerCase();
        if (type.equals("integer") || type.equals("i")) {
            Integer[] arr = generator.generateIntegers();
            System.out.printf("Calculate the result of %d divided by %d\n", arr[0], arr[1]);
            int answer;
            while(true) {
                try {
                    answer= scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Wrong input");
                }
            }

            if(intCalculator.calculate(arr,answer)){
                System.out.println("Correct answer");
                return true;
            }else {
                System.out.println("Incorrect answer");
                return false;
            }
        }else if(type.equals("string") || type.equals("s")) {
            String[] arr = generator.generateStrings();
            System.out.printf("Calculate the concatenation of strings %s and %s\n", arr[0], arr[1]);
            String answer = scanner.next();
            if (strCalculator.calculate(arr, answer)) {
                System.out.println("Correct answer");
                return true;
            } else {
                System.out.println("Incorrect answer");
                return false;
            }
        }else {
            System.out.println("Invalid input");
            return askQuestion();
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

class Player {
    private final Bucket bucket=new Bucket();

    public Bucket getBucket(){
        return bucket;
    }

    public void addToy(Toy toy){
        bucket.getToys().add(toy);
    }
}

class Bucket {
    private final List<Toy> toys=new ArrayList<>();

    public List<Toy> getToys() {
        return toys;
    }
}

class Generator {
    private final Random random = new Random();

    public Integer[] generateIntegers() {
        Integer[] array = new Integer[2];
        array[1] = random.nextInt(Integer.MAX_VALUE/16);
        int a=random.nextInt(15)+1;
        array[0] = a*array[1];
        return array;
    }

    public String[] generateStrings() {
        String[] array = new String[2];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append((char)(random.nextInt(26) + 'A'+random.nextInt(2)*32));
        }
        array[0] = sb.toString();
        sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append((char)(random.nextInt(26) + 'A'+random.nextInt(2)*32));
        }
        array[1] = sb.toString();
        return array;
    }
}

class Calculator<T> {
    public Boolean calculate(T[] array, T answer) {
        try {
            Integer a=(Integer) array[0];
            Integer b=(Integer) array[1];
            return (a/b)==(Integer)answer;
        }catch (Exception e){
            String a=String.valueOf(array[0]);
            String b=String.valueOf(array[1]);
            return (a+b).equals(String.valueOf(answer));
        }
    }
}

class Tile {
    private final Toy toy;

    Tile(Toy toy) {
        this.toy = toy;
    }

    public Toy getToy() {
        return toy;
    }
}

class Toy implements Cloneable{
    private final String name;

    Toy(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    @Override
    public Toy clone() {
        try {
            return (Toy) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

