package yingdg.exercise.chain;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;

/**
 * @author yingdg
 * created in 2019/7/24 11:25 AM
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Command command = new CommandChain();

        Context context = new ContextBase();
        context.put("k1", 1);
        command.execute(context);
    }

}
