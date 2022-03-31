package com.kamelchukov.common.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorizationResponse {
    private int status;
    private String message;
    private String path;
}
