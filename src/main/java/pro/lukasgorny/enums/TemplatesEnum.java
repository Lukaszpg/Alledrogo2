package pro.lukasgorny.enums;

/**
 * Created by Łukasz on 24.10.2017.
 */
public enum TemplatesEnum {
    REGISTRATION("registration"), EMAIL_ERROR("email-error");

    TemplatesEnum(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
