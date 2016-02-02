package third.hazecast;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by 1002515 on 2016. 2. 2..
 */
@Data
public class Value implements Serializable{
    private Key key;
    private String value1;
    private Long value2;


    public Value(Key key, String value1, Long value2){
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
    }

}
