import java.util.Scanner;

public class Runner {

    // method that displays and output player's hand
    public static void displayPlayerHand(Game game, int index){
        // prints out prompt of "--players name-- has: "
        String playerCards = String.format("%s has:", game.getPlayers().get(index).getName());
        System.out.println(playerCards);
        // loops through players cards in hand and prints it out cards and total hand value"
        for(int i = 0; i < game.getPlayers().get(index).cardCount(); i ++){
            System.out.println(game.getPlayers().get(index).showCard(i));
        }
        System.out.printf("Hand total: %s%n", game.getPlayers().get(index).handTotal());
    }

    public static void hit(Game game, Deck deck){
        game.getPlayers().get(1).takeCard(deck.dealOne());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deck deck = new Deck();
        Game game = new Game(deck);

        System.out.println("Welcome to Blackjack!");

        Player dealer = new Player("Dealer");
        game.addPlayer(dealer);

        System.out.println("Enter player name: ");
        String playerName = scanner.next();
        Player player1 = new Player(playerName);
        game.addPlayer(player1);

        game.start(2);

        System.out.println("----- PLAYERS CARDS -----");
        displayPlayerHand(game, 1);


        // this checks if their hand is less than 21 and asks if they want to hit or stick
        while (game.getPlayers().get(1).handTotal() < 21) {
            System.out.println("Hit or Stick? (input H/h or S/s)");
            String playerMove = scanner.next();
            if (playerMove.equals("h") || playerMove.equals("H")){
                hit(game, deck);
                System.out.println("----- PLAYERS CARDS -----");
                displayPlayerHand(game,1 );
            } else if (playerMove.equals("s") || playerMove.equals("S")){
                break;
            } else {
                System.out.println("Please input H/h or S/s. ");
            }
        }

        System.out.println("----- DEALERS CARDS -----");
        displayPlayerHand(game, 0);

        System.out.println("----- RESULTS -----");
        if(game.checkDraw()){
            System.out.println("It's a draw!");
        } else {
            Player winner = game.checkWinner();
            String winnerName = winner.getName();
            String output = String.format("%s wins!", winnerName);
            System.out.println(output);
        }
    }
}
