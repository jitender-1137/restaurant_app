package com.ms.restaurant.service;

import com.ms.restaurant.dto.requestDto.MediaRequestDto;
import com.ms.restaurant.dto.responseDto.MediaResponseDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface MediaService {
    MediaResponseDto saveMedia(MediaRequestDto mediaRequestDto, HttpServletRequest request);

    List<MediaResponseDto> getAllMedias();

    void deleteMedia(Long id);

    MediaResponseDto updateMedia(MediaRequestDto mediaRequestDto, HttpServletRequest request);

    MediaResponseDto getMedia(String path);
}
