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
        public static final String FIND_ENDED_NOT_WON_AUCTIONS = "SELECT a FROM Auction a INNER JOIN a.bids b WHERE a.hasEnded = true AND b.isWinning = false AND b.user.id = :id";
        public static final String FIND_ENDED_WON_AUCTIONS = "SELECT a FROM Auction a INNER JOIN a.bids b WHERE a.hasEnded = true AND b.isWinning = true AND b.user.id = :id";
        public static final String FIND_ENDED_BUYOUT_AUCTIONS = "SELECT a FROM Auction a INNER JOIN a.buyouts bo WHERE a.hasEnded = true AND bo.auction.id = a.id AND bo.user.id = :id";
    }
}
