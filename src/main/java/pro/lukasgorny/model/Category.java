package pro.lukasgorny.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Łukasz on 26.10.2017.
 */
@Entity
@Table(name = "categories")
public class Category extends Model {

    private String name;
    private Boolean isLeaf;
    private Category parent;
    private List<Category> children = new LinkedList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @OrderColumn
    @JoinColumn(name = "parent_id")
    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }
}
