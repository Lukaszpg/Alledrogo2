package pro.lukasgorny.service.storage.exception;

/**
 * Created by Łukasz on 31.01.2018.
 */
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
