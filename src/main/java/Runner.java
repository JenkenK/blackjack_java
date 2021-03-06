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

    public static void hit(Game game, Deck deck, int index){
        Card drawCard = deck.dealOne();
        game.getPlayers().get(index).takeCard(drawCard);
    }

    public static void checkBlackjack(Game game, int index){
        if (game.getPlayers().get(index).cardCount() == 2 && game.getPlayers().get(index).handTotal() == 21){
            if (game.checkDraw()){
                System.out.println("\n---------- // RESULTS // ----------");
                System.out.println("It's a draw!");
                System.exit(0);
            } else {
                System.out.println("\n/////// BLACKJACK /////");
                String blackoutOutput = String.format("%s wins!", game.getPlayers().get(index).getName());
                System.out.println(blackoutOutput);
                System.exit(0);
            }
        }
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
//        aceChecker(game, deck, 1);
        String dealerCard = String.format("\nDealer face up card: %s", game.getPlayers().get(0).showCard(0));
        System.out.println(dealerCard);
        checkBlackjack(game, 1);


        // this checks if their hand is less than 21 and asks if they want to hit or stick
        while (game.getPlayers().get(1).handTotal() < 21) {
            System.out.println("\nHit or Stick? (input H/h or S/s)");
            String playerMove = scanner.next();
            if (playerMove.equals("h") || playerMove.equals("H")){
                hit(game, deck, 1);
                System.out.println("----- PLAYERS CARDS -----");
                displayPlayerHand(game,1 );
            } else if (playerMove.equals("s") || playerMove.equals("S")){
                break;
            } else {
                System.out.println("Please input H/h or S/s. ");
            }
        }

        if (game.getPlayers().get(1).handTotal() > 21){
            System.out.println("\n---------- // RESULTS // ----------");
            System.out.println("\n----- DEALERS CARDS -----");
            displayPlayerHand(game, 0);
            checkBlackjack(game, 0);
            String playerWinOutput = String.format("\n%s loses", game.getPlayers().get(1).getName());
            String dealerWinOutput = String.format("%s wins!", game.getPlayers().get(0).getName());
            System.out.println(playerWinOutput);
            System.out.println(dealerWinOutput);
            System.exit(0);
        }

        System.out.println("\n----- DEALERS CARDS -----");
        displayPlayerHand(game, 0);
        checkBlackjack(game, 0);

        while(game.getPlayers().get(0).handTotal() < 17){
            hit(game, deck, 0);
            System.out.println("\n----- DEALERS CARDS -----");
            displayPlayerHand(game, 0);
            if (game.getPlayers().get(0).handTotal() >= 17){
                break;
            }
        }

        System.out.println("\n---------- // RESULTS // ----------");
        game.checkWinner(game);

    }
}




