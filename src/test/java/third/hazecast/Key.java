package third.hazecast;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by 1002515 on 2016. 2. 2..
 */
@Data
public class Key implements Serializable{
    private String key;

    public Key(String key){
        this.key = key;
    }

}
