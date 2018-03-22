package yingdg.exercise.log.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yingdg on 2017/11/6 0006.
 */
public class HelloLogback {
    private static Logger logger = LoggerFactory.getLogger(HelloLogback.class.getName());

    public static void main(String[] args) {
        logger.trace("Enter"); // 未打印，因为配置文件中root.level级别为debug

        logger.debug("print debug log.");
        logger.info("print info log.");
        logger.error("print error log.");
        logger.warn("warn log.");

        // 替换参数用{}
        logger.info("参数占位符：{}", "this is info");
    }
}
