package NFT.marketplace.Objects;

import java.util.Date;

public class Admin extends RegisteredUser{
    public Admin(String username, String name, String surname, Date dateOfBirth) {
        super(username, name, surname, dateOfBirth);
    }

    public Admin(RegisteredUser ru) {
        super(ru.getUsername(), ru.getName(), ru.getSurname(), ru.getDateOfBirth());
    }

    public void BanUsers(RegisteredUser ru){
        ru.ban();
    }

    public void AcceptNFT(NFT nft){
        nft.setState(NFTState.APPROVED);
    }

    public void DeclineNFT(NFT nft){
        nft.setState(NFTState.DECLINED);
    }
}
