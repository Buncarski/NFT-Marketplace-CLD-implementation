package NFT.marketplace.Objects;

public class NFT {
    private int unique;
    private RegisteredUser currentOwner;
    private boolean isOnSale;
    private Auction currentAuction;
    private Media media;
    private NFTState state = NFTState.PENDING;

    public NFT(int unique, RegisteredUser currentOwner, Media media) {
        this.unique = unique;
        this.currentOwner = currentOwner;
        this.media = media;
    }

    public int getUnique() {
        return unique;
    }

    public RegisteredUser getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(RegisteredUser currentOwner) {
        this.currentOwner = currentOwner;
    }

    public boolean isOnSale() {
        return isOnSale;
    }

    public void setOnSale(boolean onSale) {
        isOnSale = onSale;
    }

    public Auction getCurrentAuction() {
        return currentAuction;
    }

    public void setCurrentAuction(Auction currentAuction) {
        this.currentAuction = currentAuction;
    }

    public void setState(NFTState state) {
        this.state = state;
    }

    public boolean isApprovedForSale() {
        return state == NFTState.APPROVED;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }
}
