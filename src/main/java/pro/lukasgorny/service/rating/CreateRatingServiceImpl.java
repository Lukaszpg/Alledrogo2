package pro.lukasgorny.service.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import pro.lukasgorny.dto.Rating.RatingSaveDto;
import pro.lukasgorny.enums.RatingTypeEnum;
import pro.lukasgorny.model.Rating;
import pro.lukasgorny.model.Transaction;
import pro.lukasgorny.model.User;
import pro.lukasgorny.repository.RatingRepository;
import pro.lukasgorny.repository.TransactionRepository;
import pro.lukasgorny.service.auction.GetTransactionService;
import pro.lukasgorny.service.user.UserService;

/**
 * Created by lukaszgo on 2018-01-17.
 */
@Service
public class CreateRatingServiceImpl implements CreateRatingService {

    private final RatingRepository ratingRepository;
    private final GetTransactionService getTransactionService;
    private final UserService userService;
    private final TransactionRepository transactionRepository;

    @Autowired
    public CreateRatingServiceImpl(RatingRepository ratingRepository, GetTransactionService getTransactionService, UserService userService,
            TransactionRepository transactionRepository) {
        this.ratingRepository = ratingRepository;
        this.getTransactionService = getTransactionService;
        this.userService = userService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public void createRating(RatingSaveDto ratingSaveDto) {
        Rating rating = createEntityFromDto(ratingSaveDto);
        Transaction transaction = getTransactionService.getOneEntity(ratingSaveDto.getTransactionId());
        rating.setTransaction(transaction);
        ratingRepository.save(rating);

        transaction = setRatingAsIssuerOrBuyer(transaction, rating);
        transactionRepository.save(transaction);
    }

    private Transaction setRatingAsIssuerOrBuyer(Transaction transaction, Rating rating) {
        if (rating.getIssuer().getId().equals(transaction.getUser().getId())) {
            transaction.setBuyerRating(rating);
        } else {
            transaction.setSellerRating(rating);
        }

        return transaction;
    }

    private Rating createEntityFromDto(RatingSaveDto ratingSaveDto) {
        Rating rating = new Rating();
        rating.setContent(ratingSaveDto.getContent());
        rating.setRatingTypeEnum(RatingTypeEnum.valueOf(ratingSaveDto.getRatingType().toUpperCase()));
        rating.setIssuer(getIssuer(ratingSaveDto));
        rating.setReceiver(getReceiver(ratingSaveDto));
        return rating;
    }

    private User getIssuer(RatingSaveDto ratingSaveDto) {
        return userService.getByEmail(ratingSaveDto.getIssuerName());
    }

    private User getReceiver(RatingSaveDto ratingSaveDto) {
        Transaction transaction = getTransactionService.getOneEntity(ratingSaveDto.getTransactionId());
        User user = userService.getByEmail(ratingSaveDto.getIssuerName());

        if (!isTheIssuerSeller(transaction, user)) {
            return transaction.getAuction().getSeller();
        } else if(isTheIssuerSeller(transaction, user)) {
            return transaction.getUser();
        }

        return user;
    }

    private boolean isTheIssuerSeller(Transaction transaction, User user) {
        return transaction.getAuction().getSeller().getId().equals(user.getId());
    }
}
