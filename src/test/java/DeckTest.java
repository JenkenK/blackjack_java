import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeckTest {

    Deck deck;

    @Before
    public void setUp(){
        deck = new Deck();
    }

    @Test
    public void deckStartsWith52Cards() {
        assertEquals(52, deck.cardCount());
    }

    @Test
    public void cardsCanBeTakenFromDeck(){
        deck.dealOne();
        assertEquals(51, deck.cardCount());
    }
}
