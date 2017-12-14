import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Run {

    //create the deck, human hand, computer hand, discard pile
    // and the draw pile. 
    static Scanner input = new Scanner(System.in);
    static Deck deck = new Deck();
    static ArrayDeque<Card> discardPile = new ArrayDeque<>();
    static ArrayList<Card> humanHand = new ArrayList<>();
    static ArrayList<Card> computerHand = new ArrayList<>();
    static Queue<Card> drawPile = new LinkedList<>();
    static ComputerPlayer computer = new ComputerPlayer(discardPile, computerHand);

    public static void runProgram() {
        //deal the cards and send the cards to the queue
        dealDeck();
        deckToQueue();
        //checks to see if anyone one on the first deal
        boolean data = checkWinner();
        int timesRan = 0;
        //if nobody won on the first deal the while loop begins
        while (data) {
            timesRan = humanPlayerAction(timesRan);
            //checks to see if human won after picking and removing a card
            data = checkWinner();
            if (data) {
            } else {
                break;
            }
            //makes sure that the drawPile is not empty, if it is
            //drawPile gets all the cards from the discard pile
            if (drawPile.peek() == null) {
                while (discardPile.peek() != null) {
                    drawPile.add(discardPile.pop());
                }
            }
            computerAction();

            //checks to see if anyone won before re-iterating
            data = checkWinner();
            //if the drawPile is empty, it refills it from discard 
            if (drawPile.peek() == null) {
                while (discardPile.peek() != null) {
                    drawPile.add(discardPile.pop());
                }
            }
        }
    }

    //creates the deck
    public static void dealDeck() {
        for (int i = 0; i < 4; i++) {
            humanHand.add(deck.removeCard());
            computerHand.add(deck.removeCard());
        }
    }

    //checks for a winner
    private static boolean checkWinner() {

        int humanWin = 0;
        int computerWin = 0;
        int value = humanHand.get(0).getValue();
        for (int i = 0; i < humanHand.size(); i++) {
            if (humanHand.get(i).getValue() == value) {
                humanWin++;
            }
        }
        value = computerHand.get(0).getValue();
        for (int i = 0; i < humanHand.size(); i++) {
            if (computerHand.get(i).getValue() == value) {
                computerWin++;
            }
        }
        if (humanWin == 4) {
            System.out.println("You win");
            return false;
        } else if (computerWin == 4) {
            System.out.println("I win");
            return false;
        } else {
            return true;
        }
    }

    //turns deck into a queue
    private static void deckToQueue() {
        for (int i = deck.deckSize(); i > 0; i--) {
            drawPile.offer(deck.removeCard());
        }
    }

    //whether human picksup from deck or discard pile
    public static int pickUpChoice() {
        int choice = 0;
        while (choice < 1 || choice > 2) {
            try {
                choice = input.nextInt();
                while (choice < 1 || choice > 2) {
                    System.out.print("Sorry, you did not enter 1 or 2, please re-enter your choice: ");
                    choice = input.nextInt();
                }
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.print("Sorry, you did not enter 1 or 2, please re-enter your choice: ");
                input.nextLine();
            }
        }
        return choice;
    }

    //human discard choice
    public static int discardChoice() {
        int choice = 0;
        System.out.print("Which one do you want to discard? ");
        while (choice < 1 || choice > 5) {
            try {
                choice = input.nextInt();
                while (choice < 1 || choice > 5) {
                    System.out.print("Sorry, you did not enter a number between 1-5, please re-enter your choice: ");
                    choice = input.nextInt();
                }
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.print("Sorry, you did not enter a number between 1-5, please re-enter your choice: ");
                input.nextLine();
            }
        }
        return choice;
    }

    private static int humanPlayerAction(int timesRan) {
        //prints out the human players card
        System.out.println("Your cards are:");
        humanHand.forEach((c) -> {
            System.out.println("\t" + c);
        });
        //if its the first time going through the while loop
        //notifies user that they have to draw a card
        if (timesRan == 0) {
            System.out.println("The discard pile is currently empty -- you must draw a card");
            humanHand.add(drawPile.poll());
            System.out.println("You drew the " + humanHand.get(4));
            timesRan++;
        } else {
            //after the first time, lets the user choose what cards they want to use, error checking for impromper numbers is used
            System.out.println("The top card in the discard pile is the " + discardPile.peek());
            System.out.print("Do you want to pick up the " + discardPile.peek() + " (1) or draw a card (2)? ");
            int choice = pickUpChoice();
            if (choice == 2) {
                humanHand.add(drawPile.poll());
            } else {
                humanHand.add(discardPile.pop());
            }
            System.out.println("You drew the " + humanHand.get(4));
        }
        //outputs card to human after drawing one
        System.out.println("Now your cards are:");
        int timesThru = 1;
        for (Card c : humanHand) {
            System.out.println("\t" + timesThru + ". " + c);
            timesThru++;
        }
        int remove = discardChoice();
        discardPile.push(humanHand.remove(remove - 1));
        return timesRan;
    }

    private static void computerAction() {
        //allows the computer to either draw from pile or discard pile
        //if they draw from draw pile, does not tell what card they drew
        int choice = computer.smartChoice();
        if (choice == 1) {
            computerHand.add(drawPile.poll());
            System.out.println("I will draw a new card");
        } else {
            computerHand.add(discardPile.pop());
            System.out.println("I will pick up the " + computerHand.get(4));
        }
        //discards random card
        Card c = computer.smartDiscard();
        System.out.println("I will discard the " + c);
        discardPile.push(c);
    }
}
