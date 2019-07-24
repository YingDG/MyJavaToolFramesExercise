package yingdg.exercise.chain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;

import java.util.Objects;
import java.util.Optional;

/**
 * @author yingdg
 * created in 2019/7/24 11:30 AM
 */
public class Commands2 implements Filter {

    @Override
    public boolean execute(Context context) throws Exception {
        Object v1 = context.get("k1");
        System.out.println("do Commands2... params is " + JSONObject.toJSONString(context));

        if (Objects.nonNull(v1)) {
//            v1 = null;
            int IntV1 = (Integer) v1;
            context.put("k1", IntV1 + 1);
        }

        return PROCESSING_COMPLETE; // 返回true，则表示终止调用，后续的command将不会被执行
    }

    @Override
    public boolean postprocess(Context context, Exception e) {
        System.out.println(JSON.toJSONString(context));
        Optional.ofNullable(e).ifPresent(System.err::println);

        return CONTINUE_PROCESSING;
    }
}
