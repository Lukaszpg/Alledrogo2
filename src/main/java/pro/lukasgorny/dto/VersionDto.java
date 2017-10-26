package pro.lukasgorny.dto;

/**
 * Created by lukaszgo on 2017-10-26.
 */
public class VersionDto {
    private String version;
    private String message;
    private String compilationDate;

    public VersionDto() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCompilationDate() {
        return compilationDate;
    }

    public void setCompilationDate(String compilationDate) {
        this.compilationDate = compilationDate;
    }
}
