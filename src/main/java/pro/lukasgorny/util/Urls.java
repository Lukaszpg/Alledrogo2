package pro.lukasgorny.util;

/**
 * Created by lukaszgo on 2017-11-22.
 */
public class Urls {
    public static final String ERROR_404 = "/error/404";
    public static final String ERROR_405 = "/error/405";
    public static final String ERROR_500 = "/error/500";

    public class Auction {
        public static final String PREFIX = "auction";
        public static final String MAIN = "/" + PREFIX;
        public static final String CREATE_SUCCESS_REDIRECT = "redirect:/" + PREFIX + "/create-success";
        public static final String SELL = "/sell";
        public static final String CREATE_SUCCESS = "/create-success";
        public static final String GET = "/get/{id}";
        public static final String BID = "/bid/{id}";
        public static final String BID_SUCCESS_REDIRECT = "redirect:/" + PREFIX + "/bid-success/%s";
        public static final String AUCTION_ENDED_REDIRECT = "redirect:/" + PREFIX + "/auction-ended";
        public static final String BID_SUCCESS = "/bid-success/{id}";
        public static final String AUCTION_ENDED = "/auction-ended";
        public static final String CONFIRM_BUYOUT = "/confirm-buyout/{id}";
        public static final String BUYOUT = "/buyout/{id}";
        public static final String BUYOUT_SUCCESS = "/buyout-success/";
        public static final String BUYOUT_SUCCESS_REDIRECT = "redirect:/" + PREFIX + "/buyout-success";
    }

    public class AuctionRest {
        public static final String PREFIX = "auction-rest";
        public static final String MAIN = "/" + PREFIX;
        public static final String OBSERVE = "/observe/{id}";
        public static final String UNOBSERVE = "/unobserve/{id}";
        public static final String IS_OBSERVING = "/is-observing/{id}";
    }

    public class Login {
        public static final String LOGIN = "/login";
        public static final String LOGIN_REDIRECT = "redirect:/login";
    }

    public class Registration {
        public static final String REGISTER = "/register";
        public static final String EMAIL_ERROR_REDIRECT = "redirect:/email-error";
        public static final String REGISTER_SUCCESS_REDIRECT = "redirect:/register-success";
        public static final String TOKEN_ERROR_REDIRECT = "redirect:/token-error";
        public static final String TOKEN_ERROR = "/token-error";
        public static final String ACTIVATE = "/activate";
        public static final String REGISTER_SUCCESS = "/register-success";
    }

    public class Util {
        public static final String VERSION = "/version";
    }

    public class CategoryRest {
        public static final String MAIN = "/category-rest";
        public static final String GET_ALL_TOP = "/get-all-top";
        public static final String GET_CHILDREN = "/get-children/{id}";
    }

    public class User {
        public static final String MAIN = "/user";
        public static final String ACCOUNT = "/account";
        public static final String OBSERVING = "/observing";
        public static final String RATING = "/rating";
        public static final String RATING_BOUGHT = RATING + "/bought";
        public static final String RATING_SOLD = RATING + "/sold";
        public static final String CREATE_RATING = RATING + "/create/{id}";
        public static final String POST_RATING_BOUGHT = RATING + "/post-bought";
        public static final String POST_RATING_SOLD = RATING + "/post-sold";
    }
}
