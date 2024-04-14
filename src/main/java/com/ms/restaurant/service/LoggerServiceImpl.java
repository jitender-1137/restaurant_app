package com.ms.restaurant.service;

import com.ms.restaurant.dto.requestDto.LoggerRequestDto;
import com.ms.restaurant.utils.AesEncryption;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoggerServiceImpl implements LoggerService {

	@Override
	public List<LoggerRequestDto> getAllLoggerFiles() {
		List<LoggerRequestDto> loggerRequestDtos = new ArrayList<>();

		Path currentRelativePath = Paths.get("");
		String rootPath = currentRelativePath.toAbsolutePath().toString();
		File directoryPath = new File(rootPath + File.separator + "logs");

		File filesList[] = directoryPath.listFiles();

		LoggerRequestDto loggerRequestDto = new LoggerRequestDto();
		for (File file : filesList) {
			loggerRequestDto = new LoggerRequestDto();
			loggerRequestDto.setFileName(file.getName());
			loggerRequestDto.setFilePath(AesEncryption.encrypt(file.getAbsolutePath()));
			loggerRequestDto.setFileSize(String.valueOf(file.length() / 1024) + " KB");

			loggerRequestDtos.add(loggerRequestDto);
		}
		return loggerRequestDtos;
	}

	@Override
	public void deleteLoggerFile(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void downloadLoggerFile(String path) {
		// TODO Auto-generated method stub

	}

}
