package yingdg.exercise.chain;

import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.Command;

import java.util.Map;

/**
 * @author yingdg
 * created in 2019/7/24 4:02 PM
 */
public interface Catalog2 extends Catalog {
    Map<String, Command> getCommands();
}
