package pro.lukasgorny.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Łukasz on 20.11.2017.
 */
@Entity
@Table(name = "auctions")
public class Auction extends Model {

    private String title;
    private Boolean isNew;
    private String editorContent;
    private Boolean isBid;
    private Boolean isBuyout;
    private Boolean hasEnded;
    private BigDecimal price;
    private BigDecimal bidStartingPrice;
    private BigDecimal bidMinimalPrice;
    private Integer amount;
    private Category category;
    private LocalDateTime endDate;
    private List<Bid> bids;
    private User user;
    private List<User> usersObserving;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    @Lob
    public String getEditorContent() {
        return editorContent;
    }

    public void setEditorContent(String editorContent) {
        this.editorContent = editorContent;
    }

    public Boolean getBid() {
        return isBid;
    }

    public void setBid(Boolean bid) {
        isBid = bid;
    }

    public Boolean getBuyout() {
        return isBuyout;
    }

    public void setBuyout(Boolean buyout) {
        isBuyout = buyout;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @OneToMany(mappedBy="auction")
    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getBidStartingPrice() {
        return bidStartingPrice;
    }

    public void setBidStartingPrice(BigDecimal bidStartingPrice) {
        this.bidStartingPrice = bidStartingPrice;
    }

    public BigDecimal getBidMinimalPrice() {
        return bidMinimalPrice;
    }

    public void setBidMinimalPrice(BigDecimal bidMinimalPrice) {
        this.bidMinimalPrice = bidMinimalPrice;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getHasEnded() {
        return hasEnded;
    }

    public void setHasEnded(Boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    @ManyToMany
    @JoinTable(name = "auction_user_observing", joinColumns = @JoinColumn(name = "auction_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    public List<User> getUsersObserving() {
        return usersObserving;
    }

    public void setUsersObserving(List<User> usersObserving) {
        this.usersObserving = usersObserving;
    }
}