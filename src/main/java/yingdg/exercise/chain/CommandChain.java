package yingdg.exercise.chain;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.impl.ChainBase;

import java.util.List;

/**
 * @author yingdg
 * created in 2019/7/24 11:32 AM
 */
public class CommandChain extends ChainBase /*implements Chain*/ {

    public CommandChain() {
        /*
        加上3个流程
         */
        super.addCommand(new Commands1());
        super.addCommand(new Commands2());
        super.addCommand(new Commands3());
    }

    public CommandChain(List<Command> commandList) {
        super(commandList);
    }

}
