import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    Player player;
    Player dealer;
    Deck deck;
    Game game;
    Card highCard;
    Card lowCard;

    @Before
    public void setUp(){
        player = new Player("Jenken");
        dealer = new Player("Dealer");
        deck = new Deck();
        game = new Game(deck);
        game.addPlayer(player);
        game.addPlayer(dealer);

        highCard = new Card(Suit.SPADES, Rank.KING);
        lowCard = new Card(Suit.SPADES, Rank.TWO);
    }

    @Test
    public void gameHasPlayers(){
        assertEquals(2, game.playerCount());
    }

    @Test
    public void gameCanStart(){
        game.start(1);
        assertEquals(1, player.cardCount());
        assertEquals(1, dealer.cardCount());
    }

}
