package NFT.marketplace;

import NFT.marketplace.Objects.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class NFTCreatorTest {
    @Test
    public void CreateNFT_SongTest(){
        // given
        Song song = new Song("Some day", "Julia Jacklin", "Pop", 186);
        NFTCreator nftCreator = new NFTCreator(new RegisteredUser("a","b","c",new Date(1)));

        // when
        NFT nft = nftCreator.CreateNFT(1, nftCreator, song);

        // then
        Assert.assertEquals(1, nft.getUnique());
        Assert.assertEquals(nftCreator, nft.getCurrentOwner());
        Assert.assertEquals(nft.getMedia(), song);
    }

    @Test
    public void CreateNFT_MovieTest(){
        // given
        Movie movie = new Movie("Avatar", "James Cameron", "Science-Fiction", 10800);
        NFTCreator nftCreator = new NFTCreator(new RegisteredUser("a","b","c",new Date(1)));

        // when
        NFT nft = nftCreator.CreateNFT(1, nftCreator, movie);

        // then
        Assert.assertEquals(1, nft.getUnique());
        Assert.assertEquals(nftCreator, nft.getCurrentOwner());
        Assert.assertEquals(nft.getMedia(), movie);
    }
}
