package pro.lukasgorny.dto.auction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by lukaszgo on 2018-02-01.
 */
public class PhotoDto {

    @JsonIgnore
    private String photoPath;

    private byte[] image;
    private boolean isMain;
    private Integer order;

    public PhotoDto() {
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public boolean getIsMain() {
        return isMain;
    }

    public void setIsMain(boolean main) {
        isMain = main;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
