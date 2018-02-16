package pro.lukasgorny.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.lukasgorny.service.message.GetMessageService;
import pro.lukasgorny.util.Urls;

/**
 * Created by lukaszgo on 2018-02-16.
 */
@RestController
@RequestMapping(Urls.UserRest.MAIN)
public class UserRestController {

    private final GetMessageService getMessageService;

    @Autowired
    public UserRestController(GetMessageService getMessageService) {
        this.getMessageService = getMessageService;
    }

    @GetMapping(Urls.UserRest.CHANGE_STATUS)
    public ResponseEntity changeMessageStatus(@PathVariable("id") String id) {
        getMessageService.setMessageStatusToNotNew(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
