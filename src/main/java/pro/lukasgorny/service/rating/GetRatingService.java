package pro.lukasgorny.service.rating;

import java.util.List;

import pro.lukasgorny.dto.Rating.RatingResultDto;
import pro.lukasgorny.enums.RatingTypeEnum;

/**
 * Created by lukaszgo on 2018-01-22.
 */
public interface GetRatingService {
    List<RatingResultDto> getReceivedRatingsForUser(String email);
    List<RatingResultDto> getIssuedRatingsForUser(String email);
    Integer getCommentCountByType(List<RatingResultDto> ratings, RatingTypeEnum ratingTypeEnum);
}
