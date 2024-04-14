//package com.ms.restaurant.controller;
//
//import com.ms.restaurant.dto.requestDto.MenuItemRequestDto;
//import com.ms.restaurant.dto.responseDto.MenuItemResponseDto;
//import com.ms.restaurant.dto.responseDto.ResponseDto;
//import com.ms.restaurant.dto.responseDto.SuccessResponseDto;
//import com.ms.restaurant.service.MenuItemService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("${base.v1.endpoint}/menuItems")
//@Validated
//@RequiredArgsConstructor
//@Slf4j
//public class MenuItemRestController {
//
//    @Autowired
//    private MenuItemService menuItemService;
//
//    // Get all menu items
//    @GetMapping
//    public ResponseDto<?> getAllMenuItems() {
//        List<MenuItemResponseDto> menuItemResponseDtos = menuItemService.getAllMenuItems();
//
//        return new SuccessResponseDto<>(menuItemResponseDtos,"Fetch successfully", HttpStatus.OK);
//    }
//
//    // Get menu item by ID
//    @GetMapping("/{menuItemId}")
//    public ResponseDto<?> getMenuItemById(@PathVariable Long menuItemId) {
//        MenuItemResponseDto menuItemResponseDto = menuItemService.getMenuItemById(menuItemId);
//        return new SuccessResponseDto<>(menuItemResponseDto,"Fetch successfully", HttpStatus.OK);
//    }
//
//    // Create a new menu item
//    @PostMapping
//    public ResponseDto<?> createMenuItem(@RequestBody MenuItemRequestDto menuItemRequestDto) {
//        MenuItemResponseDto menuItemResponseDto = menuItemService.createMenuItem(menuItemRequestDto);
//        return new SuccessResponseDto<>(menuItemResponseDto,"Save successfully", HttpStatus.CREATED);
//    }
//
//    // Update an existing menu item
//    @PutMapping("/{menuItemId}")
//    public ResponseDto<?> updateMenuItem(@PathVariable Long menuItemId, @RequestBody MenuItemRequestDto menuItemRequestDto) {
//        MenuItemResponseDto menuItemResponseDto = menuItemService.updateMenuItem(menuItemId, menuItemRequestDto);
//        return new SuccessResponseDto<>(menuItemResponseDto,"Update successfully", HttpStatus.OK);
//    }
//
//    // Delete a menu item
//    @DeleteMapping("/{menuItemId}")
//    public ResponseDto<?> deleteMenuItem(@PathVariable Long menuItemId) {
//        menuItemService.deleteMenuItem(menuItemId);
//        return new SuccessResponseDto<>(null,"Delete successfully", HttpStatus.OK);
//    }
//}
