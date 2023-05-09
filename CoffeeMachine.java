import java.util.Scanner;

public class CoffeeMachine {
    private static final State state = new State();
    public static void main(String[] args) {
        while (true){
            System.out.println("Write action (buy, fill, take, remaining, exit)");
            Scanner scanner = new Scanner(System.in);
            String action = scanner.next();

            switch (action){
                case "take":
                    take();
                    break;
                case "fill":
                    fill(scanner);
                    break;
                case "buy":
                    buy(scanner);
                    break;
                case "exit":
                    return;
                default:
                    break;
            }
        }
    }
    private static void take(){
        int money = state.takeAllMoney();
        System.out.println("I gave you $" + money);
        System.out.println();
    }
    private static void fill(Scanner scanner){
        System.out.println("Write how many ml of water you want to add:");
        int waterToAdd = scanner.nextInt();
        state.addWater(waterToAdd);
        System.out.println("Write how many ml of milk you want to add:");
        int milkToAdd = scanner.nextInt();
        state.addMilk(milkToAdd);
        System.out.println("Write how many grams of coffee beans you want to add:");
        int coffeeToAdd = scanner.nextInt();
        state.addCoffee(coffeeToAdd);
        System.out.println("Write how many disposable cups of coffee you want to add:");
        int cupsToAdd = scanner.nextInt();
        state.addCups(cupsToAdd);
        System.out.println();
    }
    private static void buy(Scanner scanner){
        System.out.println("What do u want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, q - to main menu:");
        String coffeeType = scanner.next();
        switch (coffeeType){
            case "1":
                espresso();
                break;
            case "2":
                latte();
                break;
            case "3":
                cappuccino();
                break;
            case "q":
                return;
            default:
                break;
        }
    }
    private static void espresso() {
        int waterNeed = 250;
        int milkNeed = 0;
        int coffeeNeed = 16;
        int cupsNeed = 1;
        int cost = 4;

        makeCoffee(waterNeed, milkNeed, coffeeNeed, cupsNeed, cost);
    }

    private static void latte() {
        int waterNeed = 350;
        int milkNeed = 75;
        int coffeeNeed = 20;
        int cupsNeed = 1;
        int cost = 7;

        makeCoffee(waterNeed, milkNeed, coffeeNeed, cupsNeed, cost);
    }

    private static void cappuccino() {
        int waterNeed = 200;
        int milkNeed = 100;
        int coffeeNeed = 12;
        int cupsNeed = 1;
        int cost = 6;

        makeCoffee(waterNeed, milkNeed, coffeeNeed, cupsNeed, cost);
    }
    private static void makeCoffee(int waterNeed, int milkNeed, int coffeeNeed, int cupsNeed, int cost) {

        if (state.waterLeft < waterNeed) {
            System.out.println("Sorry, not enough water!");
        } else if (state.milkLeft < milkNeed) {
            System.out.println("Sorry, not enough milk!");
        } else if (state.coffeeLeft < coffeeNeed) {
            System.out.println("Sorry, not enough coffee!");
        } else if (state.cupsLeft < cupsNeed) {
            System.out.println("Sorry, not enough cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            state.change(waterNeed, milkNeed, coffeeNeed, cupsNeed, cost);
        }

        System.out.println();
    }
    private static class State{
        private int waterLeft = 400;
        private int milkLeft = 540;
        private int coffeeLeft = 120;
        private int cupsLeft = 9;
        private int moneyLeft = 550;

        public void change (int waterNeed, int milkNeed, int coffeeNeed, int cupsNeed, int money){
            waterLeft = waterLeft - waterNeed;
            milkLeft = milkLeft - milkNeed;
            coffeeLeft = coffeeLeft - coffeeNeed;
            cupsLeft = cupsLeft - cupsNeed;
            moneyLeft = moneyLeft + money;
        }
        public int takeAllMoney() {
            int money = moneyLeft;
            moneyLeft = 0;
            return money;
        }

        public void print() {
            System.out.println();
            System.out.println("The coffee machine has:");
            System.out.println(waterLeft + " ml of water");
            System.out.println(milkLeft + " ml of milk");
            System.out.println(coffeeLeft + " g of coffee beans");
            System.out.println(cupsLeft + " disposable cups");
            System.out.println("$" + moneyLeft + " of money");
            System.out.println();
        }
        public void addWater(int waterToAdd){
            waterLeft = waterLeft + waterToAdd;
        }
        public void addMilk(int milkToAdd){
            milkLeft = milkLeft + milkToAdd;
        }
        public void addCoffee(int coffeeToAdd){
            coffeeLeft = coffeeLeft + coffeeToAdd;
        }
        public void addCups(int cupsToAdd){
            cupsLeft = cupsLeft +cupsToAdd;
        }
    }
}
