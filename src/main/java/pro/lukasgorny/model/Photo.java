package pro.lukasgorny.model;

import javax.persistence.*;

/**
 * Created by lukaszgo on 2018-02-01.
 */
@Entity
@Table(name = "photos")
public class Photo extends Model {

    private Auction auction;
    private boolean isMain;
    private Integer photoOrder;
    private String storagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public boolean getIsMain() {
        return isMain;
    }

    public void setIsMain(boolean main) {
        isMain = main;
    }

    public Integer getPhotoOrder() {
        return photoOrder;
    }

    public void setPhotoOrder(Integer photoOrder) {
        this.photoOrder = photoOrder;
    }

    @Lob
    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }
}
