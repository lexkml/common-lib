package com.kamelchukov.common.model.dto.messageDto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MessageDto {
    private String username;
    private String path;
    private LocalDateTime time;
}
