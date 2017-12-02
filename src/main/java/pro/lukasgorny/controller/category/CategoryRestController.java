package pro.lukasgorny.controller.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.lukasgorny.dto.category.CategoryDto;
import pro.lukasgorny.service.category.GetCategoryService;
import pro.lukasgorny.service.hash.HashService;
import pro.lukasgorny.util.Urls;

import java.util.List;

/**
 * Created by Łukasz on 26.10.2017.
 */

@RestController
@RequestMapping(Urls.CategoryRest.MAIN)
public class CategoryRestController {

    private final GetCategoryService getCategoryService;

    @Autowired
    public CategoryRestController(GetCategoryService getCategoryService) {
        this.getCategoryService = getCategoryService;
    }

    @GetMapping(Urls.CategoryRest.GET_ALL_TOP)
    public ResponseEntity<List<CategoryDto>> getAllTop() {
        List<CategoryDto> list = getCategoryService.getAllTop();

        if(list != null && !list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(Urls.CategoryRest.GET_CHILDREN)
    public ResponseEntity<List<CategoryDto>> getChildren(@PathVariable String id) {
        List<CategoryDto> list = getCategoryService.getChildrenByParentId(id);

        if(list != null && !list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
