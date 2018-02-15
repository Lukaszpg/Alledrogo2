package pro.lukasgorny.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import pro.lukasgorny.dto.auction.SearchDto;
import pro.lukasgorny.dto.category.CategoryDto;
import pro.lukasgorny.model.User;
import pro.lukasgorny.service.category.GetCategoryService;
import pro.lukasgorny.service.message.GetMessageService;
import pro.lukasgorny.service.user.UserService;

/**
 * Created by lukaszgo on 2018-01-15.
 */
@ControllerAdvice
public class GlobalController {

    private final GetMessageService getMessageService;
    private final GetCategoryService getCategoryService;
    private final UserService userService;

    @Autowired
    public GlobalController(GetMessageService getMessageService, GetCategoryService getCategoryService, UserService userService) {
        this.getMessageService = getMessageService;
        this.getCategoryService = getCategoryService;
        this.userService = userService;
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

    @ModelAttribute("newMessagesCount")
    public Integer getCurrentNewMessagesCount(Principal principal) {
        if(principal == null) {
            return null;
        }

        User user = userService.getByEmail(principal.getName());
        return getMessageService.getNewCountByReceiverId(user.getId());
    }
}
