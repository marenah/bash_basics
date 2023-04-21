



    import java.util.Scanner;

    public class Game {

        // Muhammad Marenah
        private Map gameMap;
        private Player player;

        public Game() {
            // initialize the game map and player
            gameMap = new Map();
            player = new Player(100, 10, "Player", gameMap.getStartingRoom());

        }

        public void start() {
            System.out.println("Welcome to the game, Madreign!”);

                    Scanner scanner = new Scanner(System.in);

            while (player.getRemainingLives() > 0) {
                System.out.println("You are currently in " + player.getCurrentRoom().getDescription());
                System.out.println("What would you like to do?");
                System.out.println("1. Move to another room");
                System.out.println("2. Check inventory");
                System.out.println("3. Use a consumable");
                System.out.println("4. Quit game");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // move to another room
                        System.out.println("Which direction would you like to move in?");
                        String direction = scanner.next();
                        Room newRoom = gameMap.getAdjacentRoom(player.getCurrentRoom(), direction);
                        if (newRoom != null) {
                            player.setCurrentRoom(newRoom);
                        } else {
                            System.out.println("You can't move in that direction.");
                        }
                        break;
                    case 2:
                        // check inventory
                        ArrayList<Item> inventory = player.getPlayerInventory();
                        System.out.println("You have the following items in your inventory:");
                        for (Item item : inventory) {
                            System.out.println(item.getName());
                        }
                        break;
                    case 3:
                        // use a consumable
                        ArrayList<Item> consumables = player.getConsumables();
                        if (consumables.size() == 0) {
                            System.out.println("You don't have any consumables.");
                        } else {
                            System.out.println("Which consumable would you like to use?");
                            for (int i = 0; i < consumables.size(); i++) {
                                System.out.println((i+1) + ". " + consumables.get(i).getName());
                            }
                            int consumableIndex = scanner.nextInt() - 1;
                            Consumable consumable = (Consumable)consumables.get(consumableIndex);
                            int healedAmount = consumable.getRemainingHP();
                            player.setHitPoints(player.getHitPoints() + healedAmount);
                            if (player.getHitPoints() > 100) {
                                player.setHitPoints(100);
                            }
                            consumable.setRemainingHP(0);
                            System.out.println("You used " + consumable.getName() + " and restored " + healedAmount + " HP.");
                        }
                        break;
                    case 4:
                        // quit game
                        System.out.println("Thanks for playing!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please choose again.");
                        break;
                }
            }

            System.out.println("You have run out of lives. Game over!");
            System.exit(0);
        }

        public static void main(String[] args) {
            Game game = new Game();
            game.start();
        }
    }
