package pro.lukasgorny.service.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Łukasz on 31.01.2018.
 */

@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = "C:\\photos";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
