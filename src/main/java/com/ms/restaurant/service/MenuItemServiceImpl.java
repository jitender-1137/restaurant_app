package com.ms.restaurant.service;

import com.ms.restaurant.domains.MenuItem;
import com.ms.restaurant.dto.requestDto.MenuItemRequestDto;
import com.ms.restaurant.dto.responseDto.MenuItemResponseDto;
import com.ms.restaurant.filter.CustomAuthContext;
import com.ms.restaurant.jpaRepo.CategoryRepository;
import com.ms.restaurant.jpaRepo.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuItemServiceImpl /**implements MenuItemService*/ {

    protected final MenuItemRepository menuItemRepository;
    protected final CategoryRepository categoryRepository;
    protected final CustomAuthContext authContext;
    protected final ModelMapper modelMapper;


//    @Override
//    public List<MenuItemResponseDto> getAllMenuItems() {
////        List<MenuItem> MenuItems = menuItemRepository.findBy
//        return null;
//    }
//
//    @Override
//    public MenuItemResponseDto getMenuItemById(Long menuItemId) {
////        return menuItemRepository.findById(menuItemId)
////                .orElseThrow(() -> new ResourceNotFoundException("MenuItem not found with id: " + menuItemId));
//        return null;
//
//    }

//    @Override
//    public MenuItemResponseDto createMenuItem(MenuItemRequestDto menuItemRequestDto) {
//        return null;
//    }

//    @Override
//    public MenuItemResponseDto createMenuItem(MenuItemRequestDto menuItemRequestDto) {
//        Category category = categoryRepository.findById(menuItemRequestDto.getCategoryId())
//                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + menuItemRequestDto.getCategoryId()));
//
//        MenuItem menuItem = new MenuItem();
//        menuItem.setName(menuItemRequestDto.getName());
//        menuItem.setDescription(menuItemRequestDto.getDescription());
//        menuItem.setPrice(menuItemRequestDto.getPrice());
//        menuItem.setAvailable(menuItemRequestDto.isAvailable());
//        menuItem.setCategory(category);
//
//        return menuItemRepository.save(menuItem);
//    }

//    @Override
//    public MenuItemResponseDto updateMenuItem(Long menuItemId, MenuItemRequestDto menuItemRequestDto) {
//        MenuItem menuItem = getMenuItemById(menuItemId);
//        Category category = categoryRepository.findById(menuItemRequestDto.getCategoryId())
//                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + menuItemRequestDto.getCategoryId()));
//
//        menuItem.setName(menuItemRequestDto.getName());
//        menuItem.setDescription(menuItemRequestDto.getDescription());
//        menuItem.setPrice(menuItemRequestDto.getPrice());
//        menuItem.setAvailable(menuItemRequestDto.isAvailable());
//        menuItem.setCategory(category);
//
//        return menuItemRepository.save(menuItem);
//    }
//
//    @Override
//    public void deleteMenuItem(Long menuItemId) {
//        MenuItem menuItem = getMenuItemById(menuItemId);
//        menuItemRepository.delete(menuItem);
//    }
}
