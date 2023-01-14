package NFT.marketplace.Objects;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class Auction {
    public final static int DURATION = 3600;
    public final static int EXTENSION = 300;

    public Instant timeStart;
    public Instant timeEnd;
    public Bet highestBet;

    public NFT itemOnSale;

    public Auction(NFT itemOnSale) {
        this.timeStart = Instant.now();
        this.timeEnd = timeStart.plusSeconds(DURATION);
        this.highestBet = null;
        this.itemOnSale = itemOnSale;
    }

    public void increaseTimeAfterBetPlacement(){
        this.timeEnd = timeEnd.plusSeconds(EXTENSION);
    }

    public void updateBet(Bet bet) {
        this.highestBet = bet;
        this.increaseTimeAfterBetPlacement();
    }

    public void verifyPurchase(){

    }

    public boolean hasEnded(){
        return timeEnd.isBefore(Instant.now());
    }
}
