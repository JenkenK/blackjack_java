import java.util.ArrayList;

public class Game {

    ArrayList<Player> players;
    Deck deck;

    public Game(Deck deck){
        this.players = new ArrayList<Player>();
        this.deck = deck;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public int playerCount(){
        return this.players.size();
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void start(int amountOfCards){
        for(Player player:this.players){
            for (int i = 0; i < amountOfCards; i ++){
                Card card = deck.dealOne();
                player.takeCard(card);
            }
        }
    }

    public boolean checkDraw(Game game, int index){
        boolean drawGame = true;
        int handTotal = this.players.get(0).handTotal(game, 0);
        for(Player player: this.players){
            if(player.handTotal(game, index) != handTotal){
                drawGame = false;
            }
        }
        return drawGame;
    }

    public void checkWinner(Game game){
        if (game.checkDraw(game, 1)){
            System.out.println("It's a draw!");
        } else {
            if (game.getPlayers().get(0).handTotal(game, 0) > 21 && game.getPlayers().get(1).handTotal(game, 1) > 21){
                System.out.println("BUST.");
            } else if (game.getPlayers().get(0).handTotal(game, 0) > 21){
                String output = String.format("%s wins!", game.getPlayers().get(1).getName());
                System.out.println(output);
            } else if (game.getPlayers().get(1).handTotal(game, 1) > 21){
                String output = String.format("%s wins!", game.getPlayers().get(0).getName());
                System.out.println(output);
            } else {
                int highest = 0;
                Player winner = null;
                for(Player player : game.getPlayers()){
                    if(player.handTotal(game, game.getPlayers().indexOf(player)) > highest){
                        highest = player.handTotal(game, 0);
                        winner = player;
                    }
                }
                assert winner != null;
                String winnerName = winner.getName();
                String output = String.format("%s wins!", winnerName);
                System.out.println(output);
            }
        }
    }
}