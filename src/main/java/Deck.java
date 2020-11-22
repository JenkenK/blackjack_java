import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;

    // you can probably add a C style loop and have a variable called numOfDecks
    public Deck(){
        this.cards = new ArrayList<Card>();
        for (int i = 0; i < 6 ; i++){
            for (Suit suit : Suit.values()){
                for (Rank rank : Rank.values()){
                    Card card = new Card(suit, rank);
                    this.cards.add(card);
                }
            }
        }
        Collections.shuffle(this.cards);
    }

    public int cardCount(){
        return this.cards.size();
    }

    public Card dealOne() {
        return this.cards.remove(0);
    }

    public ArrayList<String> showAllCards(){
        ArrayList<String> listOfCards = new ArrayList<String>();
        for (Card card : this.cards){
            listOfCards.add(card.cardName());
        }
        return listOfCards;
    }
}
