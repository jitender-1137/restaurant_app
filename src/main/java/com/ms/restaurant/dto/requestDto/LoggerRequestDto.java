package com.ms.restaurant.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoggerRequestDto {

	private String fileName;

	private String filePath;

	private String fileSize;

}
