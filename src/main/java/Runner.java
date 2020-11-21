import java.util.Scanner;

import static java.lang.Integer.parseInt;

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
        System.out.println(playerName);
        Player player1 = new Player(playerName);
        game.addPlayer(player1);

        game.start(2);

        for(Player player: game.getPlayers()){
            String output = String.format("%s has:", player.getName());
            System.out.println(output);
            for(int i = 0; i < player.cardCount(); i ++){
                System.out.println(player.showCard(i));
            }
            System.out.printf("Hand total: %s%n", player.handTotal());
        }

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
