package NFT.marketplace.Objects;

import NFT.marketplace.Exceptions.*;

import java.time.Instant;
import java.util.Date;

public class RegisteredUser extends User{
    private String username;
    private String name;
    private String Surname;
    private Date DateOfBirth;
    private boolean isBanned = false;

    public RegisteredUser(String username, String name, String surname, Date dateOfBirth) {
        this.username = username;
        this.name = name;
        Surname = surname;
        DateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return Surname;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void ban() {
        isBanned = true;
    }

    public Auction PutItemForSale(NFT nft)
            throws IncorrectOwnerException,
            IncorrectAuctionCreationException,
            UserIsBannedException, NotApprovedForSaleException {
        if(this.isBanned){
            throw new UserIsBannedException();
        }

        if(!nft.isApprovedForSale()) {
            throw new NotApprovedForSaleException();
        }

        if(this != nft.getCurrentOwner()) {
            throw new IncorrectOwnerException();
        }
        if(nft.getCurrentAuction() != null){
            throw new IncorrectAuctionCreationException();
        }

        nft.setOnSale(true);
        Auction auction = new Auction(nft);
        nft.setCurrentAuction(auction);
        return auction;
    }

    public void Purchase(Auction auction) throws UserIsBannedException {
        if(this.isBanned){
            throw new UserIsBannedException();
        }

        auction.timeEnd = Instant.now();
        auction.itemOnSale.setCurrentOwner(this);
    }

    public void PlaceBet(Auction auction, double betAmount) throws InvalidBetException, UserIsBannedException {
        if(this.isBanned){
            throw new UserIsBannedException();
        }

        if(auction.highestBet != null && auction.highestBet.getAmount() >= betAmount){
            throw new InvalidBetException();
        }

        auction.updateBet(new Bet(betAmount, this));
    }

    public boolean isBanned(){
        return this.isBanned;
    }
}
