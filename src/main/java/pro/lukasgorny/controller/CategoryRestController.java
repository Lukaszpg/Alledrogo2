package pro.lukasgorny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.lukasgorny.dto.CategoryDto;
import pro.lukasgorny.service.category.CategoryService;

import java.util.List;

/**
 * Created by Łukasz on 26.10.2017.
 */

@RestController
@RequestMapping("/category-rest")
public class CategoryRestController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get-all-top")
    public ResponseEntity<List<CategoryDto>> getAllTop() {
        List<CategoryDto> list = categoryService.getAllTop();

        if(list != null && !list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get-children/{id}")
    public ResponseEntity<List<CategoryDto>> getChildren(@PathVariable Long id) {
        List<CategoryDto> list = categoryService.getChildrenByParentId(id);

        if(list != null && !list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
