package pro.lukasgorny.service.rating;

import pro.lukasgorny.dto.rating.RatingSaveDto;

/**
 * Created by lukaszgo on 2018-01-17.
 */
public interface CreateRatingService {
    void createRating(RatingSaveDto ratingSaveDto);
}
