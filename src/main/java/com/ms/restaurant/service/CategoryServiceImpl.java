package com.ms.restaurant.service;

import com.ms.restaurant.domains.Category;
import com.ms.restaurant.dto.requestDto.CategoryRequestDto;
import com.ms.restaurant.dto.responseDto.CategoryResponseDto;
import com.ms.restaurant.dto.responseDto.UserDto;
import com.ms.restaurant.exceptions.ServiceException;
import com.ms.restaurant.filter.CustomAuthContext;
import com.ms.restaurant.jpaRepo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    protected final CategoryRepository categoryRepository;
    protected final CustomAuthContext authContext;
    protected final ModelMapper modelMapper;

    @Override
    public List<CategoryResponseDto> getAllCategories() {

        UserDto userDto = authContext.getAuthContext();

        List<Category> categories = categoryRepository.getByUserId(userDto.getId());

        if (categories.isEmpty()) {
            log.info("No category is present");
            return null;
        }

        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryResponseDto.class)).toList();
    }

    @Override
    public CategoryResponseDto getCategoryById(Long categoryId) {

        Category category = categoryRepository.findByIdAndUserIdAndEnabledTrue(categoryId, authContext.getAuthContext().getId())
                .orElseThrow(() -> new ServiceException("AS_01"));

        return modelMapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {

        boolean isCategoryExist = categoryRepository.existsByNameAndUserIdAndEnabledTrue(categoryRequestDto.getName(), authContext.getAuthContext().getId());
        if (isCategoryExist) {
            throw new ServiceException("AS_02");
        }
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        category.setCreatedBy(authContext.getAuthContext().getUsername());
        category.setUpdatedBy(authContext.getAuthContext().getUsername());
        category.setUserId(authContext.getAuthContext().getId());

        category = categoryRepository.save(category);
        return modelMapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto updateCategory(Long categoryId, CategoryRequestDto categoryRequestDto) {
        boolean isCategoryExistById = categoryRepository.existsByIdAndUserIdAndEnabledTrue(categoryId, authContext.getAuthContext().getId());
        if (isCategoryExistById) {
            boolean isCategoryExist = categoryRepository.existsByNameAndUserIdAndEnabledTrue(categoryRequestDto.getName(), authContext.getAuthContext().getId());
            if (isCategoryExist) throw new ServiceException("AS_02");
            Category category = new Category();
            category.setName(categoryRequestDto.getName());
            category.setUpdatedBy(authContext.getAuthContext().getUsername());

            category = categoryRepository.save(category);
            return modelMapper.map(category, CategoryResponseDto.class);
        } else {
            throw new ServiceException("AS_02");
        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
        boolean isCategoryExistById = categoryRepository.existsByIdAndUserIdAndEnabledTrue(categoryId, authContext.getAuthContext().getId());

        if (!isCategoryExistById) throw new ServiceException("AS_02");
        Category category = categoryRepository.findByIdAndUserIdAndEnabledTrue(categoryId, authContext.getAuthContext().getId())
                .orElseThrow(() -> new ServiceException("AS_02"));

        category.setEnabled(false);
        category.setUpdatedBy(authContext.getAuthContext().getUsername());

        categoryRepository.save(category);
    }
}
