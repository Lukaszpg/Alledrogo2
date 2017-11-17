package pro.lukasgorny.service.category;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.lukasgorny.dto.CategoryDto;
import pro.lukasgorny.model.Category;
import pro.lukasgorny.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Łukasz on 26.10.2017.
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllTop() {
        return categoryRepository.findByParentIsNull().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getChildrenByParentId(Long parentId) {
        return categoryRepository.findByParentId(parentId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        categoryDto.setParentId(category.getParent() != null ? category.getParent().getId() : null);
        categoryDto.setParentOfParentId(category.getParent() != null && category.getParent().getParent() != null ? category.getParent().getParent().getId() : null);
        categoryDto.setParentName(category.getParent() != null ? category.getParent().getName() : null);
        return categoryDto;
    }
}
