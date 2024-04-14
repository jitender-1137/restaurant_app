package com.ms.restaurant.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.restaurant.dto.responseDto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotSupportedException;

import java.io.IOException;

import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class MediaTypeNotSupportedHandler {

    private final ObjectMapper objectMapper;

    public void handle(HttpServletRequest request, HttpServletResponse response,
                       HttpMediaTypeNotSupportedException mediaTypeNotSupportedException) throws IOException {
        ErrorResponseDto<?> errorResponseDto = new ErrorResponseDto<>("UM_E_1", mediaTypeNotSupportedException.getMessage());
        response.setStatus(UNSUPPORTED_MEDIA_TYPE.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), errorResponseDto);
    }
}
