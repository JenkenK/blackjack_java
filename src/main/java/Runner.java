import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deck deck = new Deck();
        Game game = new Game(deck);

        System.out.println("Welcome to Blackjack!");

        Player dealer = new Player("Dealer");
        game.addPlayer(dealer);

        System.out.println("Enter player name: ");
        String playerName = scanner.next();
//        System.out.println(playerName);
        Player player1 = new Player(playerName);
        game.addPlayer(player1);

        game.start(2);

        // gets player's name from 2nd index of players in game array
//        System.out.println(game.getPlayers().get(1).getName());


        // prints out prompt of "--players name-- has: "
        String playerCards = String.format("%s has:", game.getPlayers().get(1).getName());
        System.out.println(playerCards);
        // loops through players cards in hand and prints it out cards and total hand value"
        for(int i = 0; i < game.getPlayers().get(1).cardCount(); i ++){
            System.out.println(game.getPlayers().get(1).showCard(i));
        }
        System.out.printf("Hand total: %s%n", game.getPlayers().get(1).handTotal());


        // this checks if their hand is less than 21 and asks if they want to hit or stick
        if (game.getPlayers().get(1).handTotal() < 21){
            System.out.println("Hit or Stick? (input H/h or S/s)");
            String playerMove = scanner.next();
            if (playerMove.equals("h") || playerMove.equals("H")){
                do {
                    Card cardFromHit = deck.dealOne();
                    game.getPlayers().get(1).takeCard(cardFromHit);
                    System.out.println(cardFromHit.cardName());
                    System.out.println(game.getPlayers().get(1).handTotal());
                }
                while(game.getPlayers().get(1).handTotal() < 21);
            }
//            System.out.println(game.getPlayers().get(1).handTotal());
        }

//        for(Player player: game.getPlayers()){
//            String output = String.format("%s has:", player.getName());
//            System.out.println(output);
//            for(int i = 0; i < player.cardCount(); i ++){
//                System.out.println(player.showCard(i));
//            }
//            System.out.printf("Hand total: %s%n", player.handTotal());
//        }


//        if(game.checkDraw()){
//            System.out.println("It's a draw!");
//        } else {
//            Player winner = game.checkWinner();
//            String winnerName = winner.getName();
//            String output = String.format("%s wins!", winnerName);
//            System.out.println(output);
//        }
    }
}
