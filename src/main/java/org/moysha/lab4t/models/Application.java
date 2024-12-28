package org.moysha.lab4t.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Application {
    private long id;
    private String name;
    private String author;
    private String version;
}
