package yingdg.exercise.chain;

import com.alibaba.fastjson.JSON;
import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.CatalogFactoryBase;
import org.apache.commons.chain.impl.ContextBase;

import java.util.Map;
import java.util.Objects;

/**
 * @author yingdg
 * created in 2019/7/24 11:25 AM
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // 方式一
        /*
        //ConfigRuleSet中定义了"规则"
        ConfigParser configParser = new ConfigParser();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //位于classpath位置
        URL url = classLoader.getResource("catalog.xml");
        configParser.parse(url);
        */
        //获得默认的catalog
        //其中CatalogFactoryBase是单例模式,在parse是会触发CatalogFactory的实例化
        Catalog catalog = CatalogFactoryBase.getInstance().getCatalog();
        if (Objects.isNull(catalog)) catalog = new CommandCatalog();
        catalog.addCommand("c1", new Commands1());
        catalog.addCommand("c2", new Commands2());
        catalog.addCommand("c3", new Commands3());
        Map<String, Command> commands = ((Catalog2) catalog).getCommands();
        System.out.println(JSON.toJSONString(commands.keySet()));

        // 方式二
        Command command = new CommandChain();

        Context context = new ContextBase();
        context.put("k1", 1);
        command.execute(context);

//        Command c3 = catalog.getCommand("c3");
//        c3.execute(context);
    }

}
