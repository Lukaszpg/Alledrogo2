package pro.lukasgorny.service.category;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.lukasgorny.dto.CategoryDto;
import pro.lukasgorny.model.Category;
import pro.lukasgorny.repository.CategoryRepository;
import pro.lukasgorny.service.hash.HashService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Łukasz on 26.10.2017.
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final HashService hashService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, HashService hashService) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.hashService = hashService;
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
    public List<CategoryDto> getChildrenByParentId(String parentId) {
        Long decodedId = hashService.decode(parentId);
        return categoryRepository.findByParentId(decodedId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        categoryDto.setId(getEncodedId(category));
        categoryDto.setParentId(getEncodedParentId(category));
        categoryDto.setParentOfParentId(getEncodedParentOfParentId(category));
        categoryDto.setParentName(category.getParent() != null ? category.getParent().getName() : null);
        return categoryDto;
    }

    private String getEncodedId(Category category) {
        return hashService.encode(category.getId());
    }

    private String getEncodedParentId(Category category) {
        if(category.getParent() != null) {
            return hashService.encode(category.getParent().getId());
        }

        return null;
    }

    private String getEncodedParentOfParentId(Category category) {
        if(category.getParent() != null && category.getParent().getParent() != null) {
            return hashService.encode(category.getParent().getParent().getId());
        }

        return null;
    }
}
