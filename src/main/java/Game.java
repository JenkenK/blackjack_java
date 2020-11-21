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

    public void checkWinner(Game game){
//        int highest = 0;
//        Player winner = null;
//        for(Player player:this.players){
//            if(player.handTotal() > highest){
//                highest = player.handTotal();
//                winner = player;
//            }
//        }
//        return winner;

        if (game.getPlayers().get(0).handTotal() > 21 && game.getPlayers().get(1).handTotal() > 21){
            System.out.println("BUST.");
        } else if (game.getPlayers().get(0).handTotal() > 21){
            String output = String.format("%s wins!", game.getPlayers().get(1).getName());
            System.out.println(output);
        } else if (game.getPlayers().get(1).handTotal() > 21){
            String output = String.format("%s wins!", game.getPlayers().get(0).getName());
            System.out.println(output);
        } else {
            int highest = 0;
            Player winner = null;
            for(Player player : game.getPlayers()){
                if(player.handTotal() > highest){
                    highest = player.handTotal();
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