package io.circleline.domain;

/**
 * Created by 1002515 on 2016. 3. 18..
 */
import lombok.Data;

@Data
public class KeyInfo {

    private String apiId;

    public KeyInfo(String apiId) {
        this.apiId = apiId;
    }
}
