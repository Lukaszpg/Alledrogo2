package pro.lukasgorny.service.storage.exception;

/**
 * Created by Łukasz on 31.01.2018.
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
