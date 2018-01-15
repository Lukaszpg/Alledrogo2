package pro.lukasgorny.util;

/**
 * Created by lukaszgo on 2017-05-25.
 */
public class QueryBody {

    public class UserQuery {

    }

    public class RoleQuery {
        public static final String GET_ROLE_COUNT = "SELECT COUNT(*) FROM Role";
    }

    public class AuctionQuery {
        public static final String FIND_ENDED_NOT_WON_AUCTIONS = "SELECT a FROM Auction a INNER JOIN a.transactions t WHERE a.hasEnded = true AND t.isWinning = false AND t.user.id = :id AND t.transactionType = 'BID'";
        public static final String FIND_ENDED_WON_AUCTIONS = "SELECT a FROM Auction a INNER JOIN a.transactions t WHERE a.hasEnded = true AND t.isWinning = true AND t.user.id = :id AND t.transactionType = 'BID'";
    }

    public class TransactionQuery {
        public static final String FIND_WINNING_BID_FOR_AUCTION = "SELECT t FROM Transaction t WHERE t.isWinning = true AND t.auction.id = :id AND t.transactionType = 'BID'";
    }
}
