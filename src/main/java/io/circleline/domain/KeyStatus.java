package io.circleline.domain;

import lombok.Data;

@Data
public class KeyStatus {
    private String key;
    private String status;
    private String action;

    public KeyStatus(String key, String status, String action) {
        this.key = key;
        this.status = status;
        this.action = action;
    }
}
