package yingdg.exercise.lombok;

import lombok.Builder;
import lombok.Data;

/**
 * Created by yingdg on 2017/4/19.
 */
@Data
@Builder
public class Model {
    private int id;
    private String mname;
    private transient Integer price;
}
