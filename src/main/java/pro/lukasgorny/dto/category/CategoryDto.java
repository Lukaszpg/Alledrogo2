package pro.lukasgorny.dto.category;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Łukasz on 07.11.2017.
 */
public class CategoryDto {
    private String id;
    private String parentId;
    private String parentOfParentId;
    private String parentName;
    private String name;
    private List<CategoryDto> children = new LinkedList<>();
    private Boolean isLeaf;
    private Integer itemsAmount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryDto> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryDto> children) {
        this.children = children;
    }

    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean leaf) {
        isLeaf = leaf;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentOfParentId() {
        return parentOfParentId;
    }

    public void setParentOfParentId(String parentOfParentId) {
        this.parentOfParentId = parentOfParentId;
    }

    public Integer getItemsAmount() {
        return itemsAmount;
    }

    public void setItemsAmount(Integer itemsAmount) {
        this.itemsAmount = itemsAmount;
    }
}
