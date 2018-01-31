package pro.lukasgorny.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by Łukasz on 31.01.2018.
 */
public interface StorageService {
    void init();
    String store(MultipartFile file, String storePath);
    Stream<Path> loadAll();
    Path load(String filename);
    Resource loadAsResource(String filename);
    void deleteAll();
}
