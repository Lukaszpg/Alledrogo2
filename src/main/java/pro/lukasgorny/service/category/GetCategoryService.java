package pro.lukasgorny.service.category;

import pro.lukasgorny.dto.category.CategoryDto;
import pro.lukasgorny.model.Category;

import java.util.List;

/**
 * Created by Łukasz on 26.10.2017.
 */
public interface GetCategoryService {

    List<CategoryDto> getAll();
    List<CategoryDto> getAllTop();
    List<CategoryDto> getChildrenByParentId(String parentId);
    Category getById(String id);
    Category getById(Long id);
}
