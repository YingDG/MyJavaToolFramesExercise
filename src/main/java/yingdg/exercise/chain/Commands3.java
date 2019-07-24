package yingdg.exercise.chain;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import java.util.Objects;

/**
 * @author yingdg
 * created in 2019/7/24 11:30 AM
 */
public class Commands3 implements Command {

    @Override
    public boolean execute(Context context) throws Exception {
        Object v1 = context.get("k1");
        System.out.println("do Commands3... params is " + JSONObject.toJSONString(context));

        if (Objects.nonNull(v1)) {
            int IntV1 = (Integer) v1;
            context.put("k1", IntV1 + 1);
        }

        return CONTINUE_PROCESSING; // 返回true，则表示终止调用，后续的command将不会被执行
    }

}
