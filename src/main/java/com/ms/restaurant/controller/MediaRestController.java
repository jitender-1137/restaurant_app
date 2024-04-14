package com.ms.restaurant.controller;

import com.ms.restaurant.dto.requestDto.MediaRequestDto;
import com.ms.restaurant.dto.responseDto.MediaResponseDto;
import com.ms.restaurant.dto.responseDto.ResponseDto;
import com.ms.restaurant.dto.responseDto.SuccessResponseDto;
import com.ms.restaurant.service.MediaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("${base.v1.endpoint}/media")
@Validated
@RequiredArgsConstructor
@Slf4j
public class MediaRestController {

    protected final MediaService mediaService;

    @PostMapping("/save")
    public ResponseDto<?> saveMedia(@Valid  @RequestParam("file") MultipartFile attachment, @RequestParam("id") Long id, @RequestParam("type") String type,
                                    @NonNull HttpServletRequest request) {

        MediaResponseDto mediaResponseDto = mediaService.saveMedia(new MediaRequestDto(id, attachment, type), request);

        log.info("Media save Successfully");
        return new SuccessResponseDto<>(mediaResponseDto, "Media save Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyMedia('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseDto<?> getAllMedias() {
        List<MediaResponseDto> mediaResponseDtos = mediaService.getAllMedias();

        log.info("Fetch Successfully");
        return new SuccessResponseDto<>(mediaResponseDtos, "Fetch Successfully", Long.valueOf(mediaResponseDtos.size()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<?> deleteMedia(@PathVariable("id") Long id) {

        mediaService.deleteMedia(id);

        log.info("Media delete successfully");
        return new SuccessResponseDto<>(null, "Media delete successfully", id);
    }

    @PostMapping("update")
    public ResponseDto<?> updateMedia(@RequestBody @Valid MediaRequestDto mediaRequestDto, @NonNull HttpServletRequest request) {

        MediaResponseDto mediaResponseDto = mediaService.updateMedia(mediaRequestDto, request);

        log.info("Media updated successfully");
        return new SuccessResponseDto<>(mediaResponseDto, "Media update successfully", HttpStatus.OK);
    }

    @GetMapping("/get/{path}")
    public ResponseDto<?> getMedia(@PathVariable("path") String path) {

        MediaResponseDto mediaResponseDto = mediaService.getMedia(path);

        log.info("Media fetch successfully");
        return new SuccessResponseDto<>(mediaResponseDto, "Media fetch successfully", HttpStatus.OK);
    }

}
