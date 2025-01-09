package org.moysha.lab4t.models;

import lombok.Data;

@Data
public class AuthResponseDto {
    private final String token;
    private final String username;
}