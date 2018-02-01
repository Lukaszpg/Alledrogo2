package pro.lukasgorny.controller.photo;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pro.lukasgorny.dto.auction.PhotoDto;
import pro.lukasgorny.service.auction.PhotoService;
import pro.lukasgorny.util.Urls;

/**
 * Created by lukaszgo on 2018-02-01.
 */
@RestController
public class PhotoRestController {

    private final PhotoService photoService;

    @Autowired
    public PhotoRestController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping(Urls.Photo.GET_ALL)
    public ResponseEntity<List<PhotoDto>> getAllPhotosForAuction(@PathVariable String id) throws IOException {
        List<PhotoDto> photoDtos = photoService.getAllForAuction(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        return new ResponseEntity<>(photoDtos, headers, HttpStatus.OK);
    }

}
