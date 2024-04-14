package com.ms.restaurant.service;

import com.ms.restaurant.domains.Media;
import com.ms.restaurant.dto.requestDto.MediaRequestDto;
import com.ms.restaurant.dto.responseDto.MediaResponseDto;
import com.ms.restaurant.dto.responseDto.UserDto;
import com.ms.restaurant.filter.CustomAuthContext;
import com.ms.restaurant.jpaRepo.MediaRepository;
import com.ms.restaurant.utils.constants.FileTypesAndPathConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService{

    protected  final MediaRepository mediaRepository;
    protected final FileTypesAndPathConstants fileConst;
    protected final ModelMapper modelMapper;
    protected final CustomAuthContext authContext;

    @Override
    public MediaResponseDto saveMedia(MediaRequestDto mediaRequestDto, HttpServletRequest request) {

        UserDto user = authContext.getAuthContext();

        MultipartFile file = mediaRequestDto.getFile();
        String basePath = fileConst.BASE_PATH + fileConst.RESTAURANT;
        if(!new File(basePath).exists()) {
            new File(basePath).mkdirs();
        }

        String fileType = file.getContentType();
        String filePath = basePath+ File.separator + file.getOriginalFilename();
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(filePath));

            Media media = new Media();

            media.setFileName(fileName);
            media.setFileType(fileType);
            media.setFilePath(filePath);
            media.setCreatedBy(user.getUsername());
            media.setUpdatedBy(user.getUsername());
            media.setUserId(String.valueOf(user.getId()));

            media = mediaRepository.save(media);
            return modelMapper.map(media, MediaResponseDto.class);

        }catch (IOException e) {
            throw new RuntimeException("Failed to transfer file "+ e.getMessage());
        }
    }

    @Override
    public List<MediaResponseDto> getAllMedias() {
        return null;
    }

    @Override
    public void deleteMedia(Long id) {

    }

    @Override
    public MediaResponseDto updateMedia(MediaRequestDto mediaRequestDto, HttpServletRequest request) {
        return null;
    }

    @Override
    public MediaResponseDto getMedia(String path) {
        return null;
    }
}
