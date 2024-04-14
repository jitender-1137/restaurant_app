package com.ms.restaurant.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class MediaRequestDto {

    private Long id;

    @NotBlank
    private MultipartFile file;

    private String fileType;

}
