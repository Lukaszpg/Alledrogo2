package pro.lukasgorny.dto.auction;

import com.sun.istack.internal.NotNull;

/**
 * Created by lukaszgo on 2018-01-16.
 */
public class SearchDto {

    @NotNull
    private String searchString;

    @NotNull
    private String categoryId;

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
