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
    }

    public class UserTemplates {
        public static final String PREFIX = "user/";
        public static final String ACCOUNT = PREFIX + "account";
    }
}
