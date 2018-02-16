package pro.lukasgorny.util;

/**
 * Created by lukaszgo on 2017-05-25.
 */
public class QueryBody {

    public class UserQuery {
        public static final String FIND_NEW_COUNT_MESSAGES_BY_RECEIVER_ID = "Select COUNT(*) FROM Message m WHERE m.receiver.id = :id AND m.isNew = true";
        public static final String FIND_RECEIVED_MESSAGES = "Select m FROM Message m WHERE m.receiver.id = :id";
        public static final String FIND_SENT_MESSAGES = "Select m FROM Message m WHERE m.sender.id = :id";
    }

    public class CategoryQuery {
        public static final String FIND_CATEGORY_COUNT = "SELECT COUNT(*) FROM Category";
        public static final String FIND_ALL_IDS = "SELECT c.id FROM Category c";
        public static final String FIND_BY_PARENT_ID = "SELECT c FROM Category c WHERE parent.id = :parentId order by c.name asc";
    }

    public class RoleQuery {
        public static final String GET_ROLE_COUNT = "SELECT COUNT(*) FROM Role";
    }

    public class AuctionQuery {
        public static final String FIND_CURRENT_ITEMS_AMOUNT = "Select a.currentAmount FROM Auction a WHERE a.id = :id";
        public static final String FIND_BY_CATEGORY_ID_AND_TITLE = "SELECT a FROM Auction a WHERE a.category.id IN (:ids) AND lower(a.title) LIKE lower(CONCAT('%', :title, '%')) AND a.hasEnded = false";
        public static final String FIND_AUCTION_AMOUNT_BY_CATEGORY = "SELECT COUNT(*) FROM Auction a WHERE a.category.id IN (:ids) AND a.hasEnded = false";
        public static final String FIND_OBSERVING_BY_AUCTION_ID_AND_USER_ID = "SELECT a FROM Auction INNER JOIN a.usersObserving";
    }

    public class RatingQuery {
        public static final String FIND_ALL_RECEIVED_RATINGS_FOR_USER = "SELECT r FROM Rating r WHERE r.receiver.id = :userId";
        public static final String FIND_ALL_ISSUED_RATINGS_FOR_USER = "SELECT r FROM Rating r WHERE r.issuer.id = :userId";
    }

    public class TransactionQuery {
        public static final String FIND_WINNING_BID_FOR_AUCTION = "SELECT t FROM Transaction t WHERE t.isWinning = true AND t.auction.id = :id AND t.transactionType = 'BID' AND t.offerAccepted = true";
        public static final String FIND_ALL_ENDED_BUYOUTS_FOR_USER_BUYER = "SELECT t FROM Transaction t WHERE t.transactionType = 'BUYOUT' AND t.user.id = :userId";
        public static final String FIND_ALL_ENDED_BIDS_FOR_USER_BUYER = "SELECT t FROM Transaction t INNER JOIN t.auction a WHERE t.user.id = :userId AND t.isWinning = true AND a.hasEnded = true AND t.transactionType = 'BID' AND t.offerAccepted = true";
        public static final String FIND_ALL_ENDED_BUYOUTS_FOR_USER_SELLER = "SELECT t FROM Transaction t INNER JOIN t.auction a WHERE t.transactionType = 'BUYOUT' AND a.seller.id = :userId";
        public static final String FIND_ALL_ENDED_BIDS_FOR_USER_SELLER = "SELECT t FROM Transaction t INNER JOIN t.auction a WHERE a.seller.id = :userId AND a.hasEnded = true AND t.transactionType = 'BID' AND t.offerAccepted = true";
        public static final String FIND_ALL_BOUGHT_ITEMS_WITHOUT_RATING_FOR_BUYER = "SELECT t FROM Transaction t INNER JOIN t.auction a WHERE t.user.id = :userId AND t.buyerRating is null AND (t.transactionType = 'BUYOUT' OR (t.transactionType = 'BID' AND a.hasEnded = true))";
        public static final String FIND_ALL_SOLD_ITEMS_WITHOUT_RATING_FOR_SELLER = "SELECT t FROM Transaction t INNER JOIN t.auction a WHERE a.seller.id = :userId AND t.sellerRating is null AND (t.transactionType = 'BUYOUT' OR (t.transactionType = 'BID' AND a.hasEnded = true))";
    }
}
