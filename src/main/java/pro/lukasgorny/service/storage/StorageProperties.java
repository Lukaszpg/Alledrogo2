package pro.lukasgorny.service.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Łukasz on 31.01.2018.
 */

@ConfigurationProperties("storage")
public class StorageProperties {

    @Value("${photos.path}")
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
