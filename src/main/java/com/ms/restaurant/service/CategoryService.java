package com.ms.restaurant.service;

import com.ms.restaurant.dto.requestDto.CategoryRequestDto;
import com.ms.restaurant.dto.responseDto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto getCategoryById(Long categoryId);

    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto updateCategory(Long categoryId, CategoryRequestDto categoryRequestDto);

    void deleteCategory(Long categoryId);
}
