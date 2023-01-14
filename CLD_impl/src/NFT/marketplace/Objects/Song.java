package NFT.marketplace.Objects;

public class Song extends Media{
    public String title;
    public String author;
    public String genre;
    public int lengthInSec;

    public Song(String title, String author, String genre, int lengthInSec) {
        this.title = title;
        this.lengthInSec = lengthInSec;
        this.author = author;
        this.genre = genre;
    }
}
