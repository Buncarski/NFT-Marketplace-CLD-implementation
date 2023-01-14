package NFT.marketplace.Objects;

public class Movie extends Media{
    public String title;
    public String director;
    public String genre;
    public int lengthInSec;

    public Movie(String title, String director, String genre, int lengthInSec) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.lengthInSec = lengthInSec;
    }
}
