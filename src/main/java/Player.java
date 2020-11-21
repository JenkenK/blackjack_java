import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> hand;

    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<Card>();
    }

    public  ArrayList<Card> getCards(){
        return this.hand;
    }

    public String getName(){
        return this.name;
    }

    public int cardCount(){
        return this.hand.size();
    }

    public void takeCard(Card card){
        this.hand.add(card);
    }

    public int handTotal(Game game, int index){
        int total = 0;
        for(Card card : this.hand){
            total += card.getValue();
        }

        int aceCounter = 0;
        for (Card card: game.getPlayers().get(index).getCards()){
            if (card.getRank() == Rank.ACE){
                aceCounter += 1;
                if (game.getPlayers().get(index).handTotal(game, index) > 21 && aceCounter > 0){
                    total -= 10;
                    aceCounter -= 1;
                }
            }
        }

        return total;
    }

    public String showCard(int index){
        return this.hand.get(index).cardName();
    }
}
