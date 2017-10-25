package pro.lukasgorny.enums;

/**
 * Created by Łukasz on 24.10.2017.
 */
public enum TemplatesEnum {
    REGISTRATION("registration"),
    EMAIL_ERROR("email-error"),
    TOKEN_ACTIVATE("activate"),
    REGISTRATION_SUCCESS("registration-success"),
    TOKEN_ERROR("token-error");

    TemplatesEnum(String templateName) {
        this.templateName = templateName;
    }

    private String templateName;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
