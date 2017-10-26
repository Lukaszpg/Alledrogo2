package pro.lukasgorny.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Łukasz on 26.10.2017.
 */
public class AuctionDto {

    @NotNull
    @Size(max = 50)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
