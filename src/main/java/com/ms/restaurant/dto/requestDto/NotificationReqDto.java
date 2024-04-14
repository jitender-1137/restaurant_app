package com.ms.restaurant.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class NotificationReqDto {

    private String name;
    private String toEmailId;
    private String type;
    private String providerType;
    private String brand;
    private Map<String, String> dataMap;
}
