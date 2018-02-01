package pro.lukasgorny.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

import pro.lukasgorny.model.Auction;

/**
 * Created by Łukasz on 31.01.2018.
 */
public interface StorageService {
    String store(MultipartFile file, Auction auction);
    Stream<Path> loadAll();
    Path load(String filename);
    Resource loadAsResource(String filename);
    void deleteAll();
}
