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

    public int handTotal(){
        int total = 0;
        int aceCounter = 0;
        for(Card card : this.hand){
            total += card.getValue();
            if (card.getRank() == Rank.ACE){
                aceCounter += 1;
            }
        }

        while (aceCounter > 0 && total > 21){
            total -= 10;
            aceCounter -= 1;
        }

        return total;
    }

    public String showCard(int index){
        return this.hand.get(index).cardName();
    }
}
