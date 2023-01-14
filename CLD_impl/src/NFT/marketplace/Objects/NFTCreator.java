package NFT.marketplace.Objects;

import java.util.Date;

public class NFTCreator extends RegisteredUser {
    public NFTCreator(String username, String name, String surname, Date dateOfBirth) {
        super(username, name, surname, dateOfBirth);
    }
    public NFTCreator(RegisteredUser ru) {
        super(ru.getUsername(), ru.getName(), ru.getSurname(), ru.getDateOfBirth());
    }

    public NFT CreateNFT(int ID, RegisteredUser owner, Media media){
        return new NFT(ID, owner, media);
    }
}
