package pro.lukasgorny.service.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.lukasgorny.dto.Rating.RatingSaveDto;
import pro.lukasgorny.model.Rating;
import pro.lukasgorny.repository.RatingRepository;

/**
 * Created by lukaszgo on 2018-01-17.
 */
@Service
public class CreateRatingServiceImpl implements CreateRatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public CreateRatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public void createRating(RatingSaveDto ratingSaveDto) {
        Rating rating = createEntityFromDto(ratingSaveDto);
        ratingRepository.save(rating);
    }

    private Rating createEntityFromDto(RatingSaveDto ratingSaveDto) {
        Rating rating = new Rating();
        rating.setContent(ratingSaveDto.getContent());
        rating.setRatingTypeEnum(ratingSaveDto.getRatingType());
        return rating;
    }
}
