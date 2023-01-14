package NFT.marketplace;

import NFT.marketplace.Exceptions.*;
import NFT.marketplace.Objects.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class AuctionTest {
    Auction auction;
    NFT nft;
    RegisteredUser ru1;
    RegisteredUser ru2;
    @Before
    public void setup() throws Exception {
        ru1 = new RegisteredUser("a","b","c",new Date(1));
        ru2 = new RegisteredUser("aa","bb","cc",new Date(2));
        nft = new NFT(1, ru1, null);
        nft.setState(NFTState.APPROVED);
        auction = ru1.PutItemForSale(nft);
        auction.highestBet = new Bet(300, ru2);
    }

    @Test
    public void increaseTimeAfterBetPlacementTest(){
        var timeEnd = auction.timeEnd;
        auction.increaseTimeAfterBetPlacement();
        Assert.assertEquals(timeEnd.plusSeconds(300), auction.timeEnd);
    }

    @Test
    public void updateBetTest() throws UserIsBannedException, InvalidBetException {
        var timeEnd = auction.timeEnd;
        auction.updateBet(new Bet(400, ru2));

        var delta = auction.highestBet.getAmount() - 400;
        Assert.assertTrue(delta < 0.001);
        Assert.assertEquals(timeEnd.plusSeconds(Auction.EXTENSION), auction.timeEnd);
    }
}
