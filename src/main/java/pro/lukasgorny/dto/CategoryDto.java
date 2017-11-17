package pro.lukasgorny.dto;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Łukasz on 07.11.2017.
 */
public class CategoryDto {
    private Long id;
    private Long parentId;
    private Long parentOfParentId;
    private String parentName;
    private String name;
    private List<CategoryDto> children = new LinkedList<>();
    private Boolean isLeaf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

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

    public Long getParentOfParentId() {
        return parentOfParentId;
    }

    public void setParentOfParentId(Long parentOfParentId) {
        this.parentOfParentId = parentOfParentId;
    }
}
