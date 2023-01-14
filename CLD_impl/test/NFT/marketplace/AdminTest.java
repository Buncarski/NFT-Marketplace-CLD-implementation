package NFT.marketplace;

import NFT.marketplace.Objects.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class AdminTest {
    RegisteredUser ru1;
    RegisteredUser ru2;
    Admin admin;
    NFTCreator nftCreator;
    @Before
    public void setup(){
        ru1 = new RegisteredUser("a","b","c",new Date(1));
        ru2 = new RegisteredUser("aa","bb","cc",new Date(2));
        nftCreator = new NFTCreator(ru2);
        admin = new Admin(ru1);
    }

    @Test
    public void BanUserTest(){
        admin.BanUsers(ru2);
        Assert.assertEquals(true, ru2.isBanned());
    }

    @Test
    public void AcceptNFTTest(){
        NFT nft = nftCreator.CreateNFT(1, ru2, null);
        admin.AcceptNFT(nft);
        Assert.assertTrue(nft.isApprovedForSale());
    }

    @Test
    public void DeclineNFTTest(){
        NFT nft = nftCreator.CreateNFT(1, ru2, null);
        admin.DeclineNFT(nft);
        Assert.assertFalse(nft.isApprovedForSale());
    }
}
