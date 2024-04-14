package com.ms.restaurant.controller;

import com.ms.restaurant.dto.requestDto.CategoryRequestDto;
import com.ms.restaurant.dto.responseDto.CategoryResponseDto;
import com.ms.restaurant.dto.responseDto.ResponseDto;
import com.ms.restaurant.dto.responseDto.SuccessResponseDto;
import com.ms.restaurant.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${base.v1.endpoint}/categories")
@Validated
@RequiredArgsConstructor
@Slf4j
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    // Get all categories
    @GetMapping
    public ResponseDto<?> getAllCategories() {
        List<CategoryResponseDto> categoryResponseDtos = categoryService.getAllCategories();

        return new SuccessResponseDto<>(categoryResponseDtos,"Fetch successfully", HttpStatus.OK);
    }

    // Get category by ID
    @GetMapping("/{categoryId}")
    public ResponseDto<?> getCategoryById(@PathVariable Long categoryId) {
        CategoryResponseDto categoryResponseDto = categoryService.getCategoryById(categoryId);

        return new SuccessResponseDto<>(categoryResponseDto,"Fetch successfully", HttpStatus.OK);
    }

    // Create a new category
    @PostMapping
    public ResponseDto<?> createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.createCategory(categoryRequestDto);
        return new SuccessResponseDto<>(categoryResponseDto,"Save successfully", HttpStatus.CREATED);
    }

    // Update an existing category
    @PutMapping("/{categoryId}")
    public ResponseDto<?> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.updateCategory(categoryId, categoryRequestDto);
        return new SuccessResponseDto<>(categoryResponseDto,"update successfully", HttpStatus.OK);
    }

    // Delete a category
    @DeleteMapping("/{categoryId}")
    public ResponseDto<?> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new SuccessResponseDto<>(null,"delete successfully", HttpStatus.OK);
    }
}
