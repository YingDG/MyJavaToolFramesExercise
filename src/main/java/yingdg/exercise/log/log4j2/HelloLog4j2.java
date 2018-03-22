package yingdg.exercise.log.log4j2;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by yingdg on 2017/11/6 0006.
 */
public class HelloLog4j2 {
    /*
    log4j2不支持properties配置文件
     */
    private static Logger logger = LogManager.getLogger(HelloLog4j2.class.getName());

    public static void main(String[] args) {
        logger.entry();   //trace级别的信息，可以在某个方法或者程序逻辑开始的时候调用，相当于logger.trace("Enter")

        /*
        日志级别：all<trace<debug<info<warn<error<fatal<off
         */
        logger.trace("entry");
        logger.debug("我是debug信息");
        logger.info("我是info信息");
        logger.warn("我是warn信息");
        logger.error("我是error信息");
        logger.fatal("我是fatal信息");

        logger.log(Level.DEBUG, "我是debug信息");   //自定义Level类型的调用

        // 替换符
        logger.info("{}","hello");

        logger.exit();    //和entry()对应的结束方法，和logger.trace("exit");一个意思
    }
}
