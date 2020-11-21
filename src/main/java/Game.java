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

    public boolean checkDraw(){
        boolean drawGame = true;
        int handTotal = this.players.get(0).handTotal();
        for(Player player: this.players){
            if(player.handTotal() != handTotal){
                drawGame = false;
            }
        }
        return drawGame;
    }

    public Player checkWinner(){
        int highest = 0;
        Player winner = null;
        for(Player player:this.players){
            if(player.handTotal() > highest){
                highest = player.handTotal();
                winner = player;
            }
        }
        return winner;
    }
}