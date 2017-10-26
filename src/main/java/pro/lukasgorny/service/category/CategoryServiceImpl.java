package pro.lukasgorny.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.lukasgorny.model.Category;
import pro.lukasgorny.repository.CategoryRepository;

import java.util.List;

/**
 * Created by Łukasz on 26.10.2017.
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findByParentIsNull() {
        return categoryRepository.findByParentIsNull();
    }
}
