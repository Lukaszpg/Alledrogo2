package pro.lukasgorny.service.rating;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.lukasgorny.dto.Rating.RatingResultDto;
import pro.lukasgorny.enums.RatingTypeEnum;
import pro.lukasgorny.model.Rating;
import pro.lukasgorny.model.User;
import pro.lukasgorny.repository.RatingRepository;
import pro.lukasgorny.service.user.UserService;
import pro.lukasgorny.util.DateFormatter;

/**
 * Created by lukaszgo on 2018-01-22.
 */
@Service
public class GetRatingsServiceImpl implements GetRatingService {

    private final UserService userService;
    private final RatingRepository ratingRepository;

    @Autowired
    public GetRatingsServiceImpl(UserService userService, RatingRepository ratingRepository) {
        this.userService = userService;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<RatingResultDto> getReceivedRatingsForUser(String email) {
        User user = userService.getByEmail(email);
        return createDtoListFromEntityList(ratingRepository.findAllReceivedRatingsByUserId(user.getId()));
    }

    @Override
    public List<RatingResultDto> getIssuedRatingsForUser(String email) {
        User user = userService.getByEmail(email);
        return createDtoListFromEntityList(ratingRepository.findAllIssuedRatingsByUserId(user.getId()));
    }

    @Override
    public Integer getCommentCountByType(List<RatingResultDto> ratings, RatingTypeEnum ratingTypeEnum) {
        return ratings.stream().filter(ratingResultDto ->
                ratingTypeEnum.equals(RatingTypeEnum.valueOf(ratingResultDto.getRatingType().toUpperCase())))
                .collect(Collectors.toList()).size();
    }

    private List<RatingResultDto> createDtoListFromEntityList(List<Rating> ratings) {
        return ratings != null ? ratings.stream().map(this::createDtoFromEntity).collect(Collectors.toList()) : new ArrayList<>();
    }

    private RatingResultDto createDtoFromEntity(Rating rating) {
        RatingResultDto ratingResultDto = new RatingResultDto();
        ratingResultDto.setContent(rating.getContent());
        ratingResultDto.setRatingType(rating.getRatingTypeEnum().toString().toLowerCase());
        ratingResultDto.setDate(DateFormatter.formatDateToHourMinuteFormat(rating.getCreateDate()));
        ratingResultDto.setAuctionName(rating.getTransaction().getAuction().getTitle());
        return ratingResultDto;
    }
}
