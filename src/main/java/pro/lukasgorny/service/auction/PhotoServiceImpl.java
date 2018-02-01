package pro.lukasgorny.service.auction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import pro.lukasgorny.dto.auction.PhotoDto;
import pro.lukasgorny.model.Photo;
import pro.lukasgorny.repository.PhotoRepository;
import pro.lukasgorny.service.hash.HashService;

/**
 * Created by lukaszgo on 2018-02-01.
 */
@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final HashService hashService;
    private final ApplicationContext applicationContext;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository, HashService hashService, ApplicationContext applicationContext) {
        this.photoRepository = photoRepository;
        this.hashService = hashService;
        this.applicationContext = applicationContext;
    }

    @Override
    public void save(Photo photo) {
        photoRepository.save(photo);
    }

    @Override
    public List<PhotoDto> getAllForAuction(String id) {
        List<Photo> photos = photoRepository.findByAuctionId(hashService.decode(id));
        return createDtoListFromEntityList(photos);
    }

    @Override
    public byte[] readImageFromDisk(String path) {
        Resource resource = applicationContext.getResource("file:" + path);

        try {
            return IOUtils.toByteArray(resource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<PhotoDto> createDtoListFromEntityList(List<Photo> photos) {
        return photos != null ? photos.stream().map(this::createDtoFromEntity).collect(Collectors.toList()) : new ArrayList<>();
    }

    private PhotoDto createDtoFromEntity(Photo photo) {
        PhotoDto photoDto = new PhotoDto();
        photoDto.setPhotoPath(photo.getStoragePath());
        photoDto.setImage(readImageFromDisk(photoDto.getPhotoPath()));
        return photoDto;
    }
}
