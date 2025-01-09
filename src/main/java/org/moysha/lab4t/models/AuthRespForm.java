package org.moysha.lab4t.models;

import lombok.Data;

@Data
public class AuthRespForm {
    private final String token;
    private final String username;
}