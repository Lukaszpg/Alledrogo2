package pro.lukasgorny.service.category;

import pro.lukasgorny.model.Category;

import java.util.List;

/**
 * Created by Łukasz on 26.10.2017.
 */
public interface CategoryService {

    List<Category> findAll();

    List<Category> findByParentIsNull();
}
