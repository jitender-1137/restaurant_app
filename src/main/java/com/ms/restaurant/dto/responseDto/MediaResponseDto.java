package com.ms.restaurant.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MediaResponseDto {

    private Long id;

    private byte[] data;

    private String filePath;

    private String fileName;

    private String fileType;
}
