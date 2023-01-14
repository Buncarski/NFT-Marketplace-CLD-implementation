package NFT.marketplace;

import NFT.marketplace.Exceptions.*;
import NFT.marketplace.Objects.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class RegisteredUserTest {
    RegisteredUser ru1;
    RegisteredUser ru2;
    RegisteredUser bannedUser;
    NFT nft;

    @Before
    public void setup(){
        ru1 = new RegisteredUser("a","b","c",new Date(1));
        ru2 = new RegisteredUser("aa","bb","cc",new Date(2));
        bannedUser = new RegisteredUser("banned", "user","lmao",new Date(3));
        bannedUser.ban();
        nft = new NFT(1, ru1, null);
        nft.setState(NFTState.APPROVED);
    }

    @Test
    public void registeredUserTest(){
        Assert.assertEquals("a", ru1.getUsername());
        Assert.assertEquals("b", ru1.getName());
        Assert.assertEquals("c", ru1.getSurname());
        Assert.assertEquals(new Date(1), ru1.getDateOfBirth());
    }

    @Test
    public void putItemForSaleTest_invalid() throws IncorrectOwnerException, IncorrectAuctionCreationException, UserIsBannedException, NotApprovedForSaleException {
        ru1.PutItemForSale(nft);
    }

    @Test(expected = IncorrectOwnerException.class)
    public void putItemForSaleTest_valid() throws IncorrectOwnerException, IncorrectAuctionCreationException, UserIsBannedException, NotApprovedForSaleException {
        Auction auction = ru2.PutItemForSale(nft);
        Assert.assertTrue(nft.isOnSale());
        Assert.assertEquals(auction.itemOnSale, nft);
        Assert.assertEquals(nft.getCurrentAuction(), auction);
    }

    @Test(expected = UserIsBannedException.class)
    public void putItemForSaleTest_banned() throws Exception {
        bannedUser.PutItemForSale(nft);
    }

    @Test(expected = NotApprovedForSaleException.class)
    public void putItemForSaleTest_pending() throws Exception {
        nft.setState(NFTState.PENDING);
        ru2.PutItemForSale(nft);
    }

    @Test(expected = NotApprovedForSaleException.class)
    public void putItemForSaleTest_declined() throws Exception {
        nft.setState(NFTState.DECLINED);
        ru2.PutItemForSale(nft);
    }

    @Test
    public void PurchaseTest() throws Exception {
        Auction auction = ru1.PutItemForSale(nft);
        ru2.Purchase(auction);

        Thread.sleep(1);
        Assert.assertEquals(nft.getCurrentOwner(), ru2);
        Assert.assertTrue(auction.hasEnded());
    }

    @Test(expected = UserIsBannedException.class)
    public void PurchaseTest_banned() throws Exception {
        Auction auction = ru1.PutItemForSale(nft);
        bannedUser.Purchase(auction);
    }

    @Test(expected = InvalidBetException.class)
    public void PlaceBetTest_invalidBet() throws InvalidBetException, IncorrectAuctionCreationException, IncorrectOwnerException, UserIsBannedException, NotApprovedForSaleException {
        Auction auction = ru1.PutItemForSale(nft);
        auction.highestBet = new Bet(100.0, ru2);
        ru2.PlaceBet(auction, auction.highestBet.getAmount() - 100.0);
    }

    @Test(expected = UserIsBannedException.class)
    public void PlaceBetTest_banned() throws InvalidBetException, IncorrectAuctionCreationException, IncorrectOwnerException, UserIsBannedException, NotApprovedForSaleException {
        Auction auction = ru1.PutItemForSale(nft);
        auction.highestBet = new Bet(100.0, ru2);
        bannedUser.PlaceBet(auction, auction.highestBet.getAmount() + 100.0);
    }

    @Test
    public void PlaceBet_validBet() throws InvalidBetException, IncorrectAuctionCreationException, IncorrectOwnerException, UserIsBannedException, NotApprovedForSaleException {
        Auction auction = ru1.PutItemForSale(nft);
        auction.highestBet = new Bet(100.0, ru2);
        ru2.PlaceBet(auction, auction.highestBet.getAmount() + 100.0);

        double delta = Math.abs(auction.highestBet.getAmount() - 200.0);
        Assert.assertTrue(delta < 0.001);
    }
}
