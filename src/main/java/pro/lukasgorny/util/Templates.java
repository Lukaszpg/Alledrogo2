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

    public class AuctionTemplates {
        public static final String prefix = "auction/";
        public static final String SELL = prefix + "sell";
        public static final String CREATE_SUCCESS = prefix + "create-success";
        public static final String ITEM = prefix + "item";
    }
}
