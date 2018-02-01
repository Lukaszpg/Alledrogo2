package pro.lukasgorny.service.auction;

import java.util.List;

import pro.lukasgorny.dto.auction.PhotoDto;
import pro.lukasgorny.model.Photo;

/**
 * Created by lukaszgo on 2018-02-01.
 */
public interface PhotoService {
    void save(Photo photo);
    List<PhotoDto> getAllForAuction(String id);
    byte[] readImageFromDisk(String path);
}
