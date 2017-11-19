package pro.lukasgorny.model;

import pro.lukasgorny.model.converter.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class Model {

    private Long id;

    @Column(name = "create_date")
    @Convert(converter = LocalDateTimeConverter.class)
    protected LocalDateTime createDate;

    @Column(name = "update_date")
    @Convert(converter = LocalDateTimeConverter.class)
    protected LocalDateTime updateDate;

    @Column(name = "delete_date")
    @Convert(converter = LocalDateTimeConverter.class)
    protected LocalDateTime deleteDate;

    @Column(name = "is_deleted")
    protected Boolean isDeleted;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @PrePersist
    protected void onCreate() {
        updateDate = createDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = LocalDateTime.now();
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public LocalDateTime getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(LocalDateTime deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
