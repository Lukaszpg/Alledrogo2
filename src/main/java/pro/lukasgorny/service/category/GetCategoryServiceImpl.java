package pro.lukasgorny.service.category;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.lukasgorny.dto.category.CategoryDto;
import pro.lukasgorny.model.Category;
import pro.lukasgorny.repository.CategoryRepository;
import pro.lukasgorny.service.hash.HashService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Łukasz on 26.10.2017.
 */

@Service
public class GetCategoryServiceImpl implements GetCategoryService {

    private final HashService hashService;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GetCategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, HashService hashService) {
        this.hashService = hashService;
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
    public List<CategoryDto> getChildrenByParentId(String parentId) {
        Long decodedId = hashService.decode(parentId);
        return categoryRepository.findByParentId(decodedId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Category getById(String id) {
        return categoryRepository.findOne(getEncodedId(id));
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findOne(id);
    }

    private CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        categoryDto.setId(getEncodedId(category));
        categoryDto.setParentId(getEncodedParentId(category));
        categoryDto.setParentOfParentId(getEncodedParentOfParentId(category));
        categoryDto.setParentName(category.getParent() != null ? category.getParent().getName() : null);
        return categoryDto;
    }

    private Long getEncodedId(String id) {
        return hashService.decode(id);
    }

    private String getEncodedId(Category category) {
        return hashService.encode(category.getId());
    }

    private String getEncodedParentId(Category category) {
        if (category.getParent() != null) {
            return hashService.encode(category.getParent().getId());
        }

        return null;
    }

    private String getEncodedParentOfParentId(Category category) {
        if (category.getParent() != null && category.getParent().getParent() != null) {
            return hashService.encode(category.getParent().getParent().getId());
        }

        return null;
    }
}
