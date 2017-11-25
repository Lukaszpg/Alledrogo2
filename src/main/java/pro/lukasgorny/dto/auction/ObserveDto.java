package pro.lukasgorny.dto.auction;

/**
 * Created by Łukasz on 24.11.2017.
 */
public class ObserveDto {
    private String auctionId;
    private String username;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }
}
