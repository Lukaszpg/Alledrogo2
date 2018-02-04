package pro.lukasgorny.util;

/**
 * Created by Łukasz on 24.10.2017.
 */
public class Templates {
    public static final String REGISTRATION = "registration";
    public static final String EMAIL_ERROR = "email_error";
    public static final String TOKEN_ACTIVATE = "activate";
    public static final String REGISTRATION_SUCCESS = "registration-success";
    public static final String TOKEN_ERROR = "token-error";
    public static final String ACTIVATION_SUCCESS = "activation-success";
    public static final String LOGIN = "login";
    public static final String INDEX = "index";
    public static final String ERROR_404 = "error/404";
    public static final String ERROR_405 = "error/405";
    public static final String ERROR_500 = "error/500";

    public class AuctionTemplates {
        public static final String PREFIX = "auction/";
        public static final String SELL = PREFIX + "sell";
        public static final String CREATE_SUCCESS = PREFIX + "create-success";
        public static final String ITEM = PREFIX + "item";
        public static final String BID_SUCCESS = PREFIX + "bid-success";
        public static final String AUCTION_ENDED = PREFIX + "auction-ended";
        public static final String CONFIRM_BUYOUT = PREFIX + "confirm-buyout";
        public static final String BUYOUT_SUCCESS = PREFIX + "buyout-success";
    }

    public class UserTemplates {
        public static final String PREFIX = "user/";
        public static final String OBSERVING = PREFIX + "observing";
        public static final String POST_RATING = PREFIX + "rating";
        public static final String MY_RATINGS = PREFIX + "my-ratings";
        public static final String CREATE_RATING = PREFIX + "create-rating";
        public static final String ITEMS_BOUGHT = PREFIX + "items-bought";
        public static final String ITEMS_SOLD = PREFIX + "items-sold";
        public static final String CREATE_RATING_SUCCESS = PREFIX + "create-rating-success";
        public static final String ACCOUNT = PREFIX + "account";
    }

    public class SearchTemplates {
        public static final String PREFIX = "search/";
        public static final String SEARCH_RESULTS = PREFIX + "search-results";
    }
}
