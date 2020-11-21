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
        game.getPlayers().get(index).takeCard(deck.dealOne());
    }

//    public static void aceChecker(Game game, int index){
//        int aceCounter = 0;
//        for (Card card: game.getPlayers().get(index).getCards()){
//            if (card.getRank() == Rank.ACE){
//                aceCounter += 1;
//                if (game.getPlayers().get(index).handTotal() > 21 && aceCounter > 0){
//                    // take away 10 from player hand total
//                    aceCounter -= 1;
//                }
//            }
//        }
//        String output = String.format("(Ace counts: %s)", aceCounter);
//        System.out.println(output);
//    }

    public static void checkBlackjack(Game game, int index){
        if (game.getPlayers().get(index).cardCount() == 2 && game.getPlayers().get(index).handTotal() == 21){
            game.checkDraw(game, 1);
            System.out.println("\n/////// BLACKJACK /////");
            String output = String.format("%s wins!", game.getPlayers().get(index).getName());
            System.exit(0);
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

        // ace doesn't count as 11, hence can't ever get blackjack lol, you need to write ace logic first
//        checkBlackjack(game, deck, 1);

        System.out.println("----- PLAYERS CARDS -----");
        displayPlayerHand(game, 1);
//        aceChecker(game, deck, 1);
        String dealerCard = String.format("\nDealer face up card: %s", game.getPlayers().get(0).showCard(0));
        System.out.println(dealerCard);


        // this checks if their hand is less than 21 and asks if they want to hit or stick
        while (game.getPlayers().get(1).handTotal() < 21) {
            System.out.println("\nHit or Stick? (input H/h or S/s)");
            String playerMove = scanner.next();
            if (playerMove.equals("h") || playerMove.equals("H")){
                hit(game, deck, 1);
                System.out.println("----- PLAYERS CARDS -----");
                displayPlayerHand(game,1 );
//                aceChecker(game, deck, 1);
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
//            aceChecker(game, deck, 0);
            String playerWinOutput = String.format("\n%s loses", game.getPlayers().get(1).getName());
            String dealerWinOutput = String.format("%s wins!", game.getPlayers().get(0).getName());
            System.out.println(playerWinOutput);
            System.out.println(dealerWinOutput);
            System.exit(0);
        }

        System.out.println("\n----- DEALERS CARDS -----");
        displayPlayerHand(game, 0);
//        aceChecker(game, deck, 0);

        while(game.getPlayers().get(0).handTotal() < 17){
            hit(game, deck, 0);
            System.out.println("\n----- DEALERS CARDS -----");
            displayPlayerHand(game, 0);
//            aceChecker(game, deck, 0);
            if (game.getPlayers().get(0).handTotal() >= 17){
                break;
            }
        }

        System.out.println("\n---------- // RESULTS // ----------");
        game.checkWinner(game);

    }
}




