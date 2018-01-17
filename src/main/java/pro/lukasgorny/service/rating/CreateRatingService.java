package pro.lukasgorny.service.rating;

import pro.lukasgorny.dto.Rating.RatingSaveDto;

/**
 * Created by lukaszgo on 2018-01-17.
 */
public interface CreateRatingService {
    void createRating(RatingSaveDto ratingSaveDto);
}
