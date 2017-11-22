package pro.lukasgorny.dto.auction;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Łukasz on 26.10.2017.
 */
public class AuctionSaveDto {

    private String id;

    @NotNull
    @Size(max = 50)
    private String title;

    @NotNull
    private String categoryId;

    @NotNull
    private Boolean isNew;

    @NotNull
    private String editorContent;

    @NotNull
    private Double price;

    @NotNull
    private Integer amount;

    private Boolean isBuyout;

    private Boolean isBid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getEditorContent() {
        return editorContent;
    }

    public void setEditorContent(String editorContent) {
        this.editorContent = editorContent;
    }

    public Boolean getIsBuyout() {
        return isBuyout;
    }

    public void setIsBuyout(Boolean buyout) {
        isBuyout = buyout;
    }

    public Boolean getIsBid() {
        return isBid;
    }

    public void setIsBid(Boolean bid) {
        isBid = bid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean aNew) {
        isNew = aNew;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
