package pro.lukasgorny.util;

/**
 * Created by lukaszgo on 2017-05-25.
 */
public class QueryBody {

    public class UserQuery {

    }

    public class CategoryQuery {
        public static final String GET_CATEGORY_COUNT = "SELECT COUNT(*) FROM Category";
    }

    public class RoleQuery {
        public static final String GET_ROLE_COUNT = "SELECT COUNT(*) FROM Role";
    }

    public class AuctionQuery {
        public static final String FIND_CURRENT_ITEMS_AMOUNT = "Select a.currentAmount FROM Auction a WHERE a.id = :id";
    }

    public class TransactionQuery {
        public static final String FIND_WINNING_BID_FOR_AUCTION = "SELECT t FROM Transaction t WHERE t.isWinning = true AND t.auction.id = :id AND t.transactionType = 'BID'";
        public static final String FIND_ALL_ENDED_BUYOUTS_FOR_USER_BUYER = "SELECT t FROM Transaction t WHERE t.transactionType = 'BUYOUT' AND t.user.id = :userId";
        public static final String FIND_ALL_ENDED_BIDS_FOR_USER_BUYER = "SELECT t FROM Transaction t INNER JOIN t.auction a WHERE t.user.id = :userId AND t.isWinning = true AND a.hasEnded = true AND t.transactionType = 'BID'";
        public static final String FIND_ALL_ENDED_BUYOUTS_FOR_USER_SELLER = "SELECT t FROM Transaction t INNER JOIN t.auction a WHERE t.transactionType = 'BUYOUT' AND a.seller.id = :userId";
        public static final String FIND_ALL_ENDED_BIDS_FOR_USER_SELLER = "SELECT t FROM Transaction t INNER JOIN t.auction a WHERE a.seller.id = :userId AND a.hasEnded = true AND t.transactionType = 'BID'";
        public static final String FIND_ALL_BOUGHT_ITEMS_WITHOUT_RATING_FOR_BUYER = "SELECT t FROM Transaction t INNER JOIN t.auction a WHERE t.user.id = :userId AND t.buyerRating is null AND (t.transactionType = 'BUYOUT' OR (t.transactionType = 'BID' AND a.hasEnded = true))";
    }
}
