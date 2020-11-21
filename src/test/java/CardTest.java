import javax.smartcardio.Card;

public class CardTest {
    Card card;

    @Before
    public void setUp(){
        card = new Card(Suit.SPADES, Rank.ACE);
    }

    @Test
    public void cardHasSuit(){
        assertEquals(Suit.SPADES, card.getSuit());
    }
}
