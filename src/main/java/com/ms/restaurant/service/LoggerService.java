package com.ms.restaurant.service;

import com.ms.restaurant.dto.requestDto.LoggerRequestDto;

import java.util.List;

public interface LoggerService {

	List<LoggerRequestDto> getAllLoggerFiles();

	void deleteLoggerFile(String path);

	void downloadLoggerFile(String path);

}
