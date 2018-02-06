package pro.lukasgorny.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import pro.lukasgorny.dto.auction.SearchDto;
import pro.lukasgorny.dto.category.CategoryDto;
import pro.lukasgorny.service.category.GetCategoryService;

/**
 * Created by lukaszgo on 2018-01-15.
 */
@ControllerAdvice
public class GlobalController {

    private final GetCategoryService getCategoryService;

    @Autowired
    public GlobalController(GetCategoryService getCategoryService) {
        this.getCategoryService = getCategoryService;
    }

    @ModelAttribute("searchCategories")
    public List<CategoryDto> getAllTopCategoriesForSearch() {
        return getCategoryService.getAllTop();
    }

    @ModelAttribute("searchDto")
    public SearchDto getSearchDto() {
        return new SearchDto();
    }

    @ModelAttribute("currentLoggedInUsername")
    public String getCurrentLoggedInUsername(Principal principal) {
        return principal != null ? principal.getName() : null;
}
}
