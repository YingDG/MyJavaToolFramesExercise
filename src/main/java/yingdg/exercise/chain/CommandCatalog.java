package yingdg.exercise.chain;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.impl.CatalogBase;

import java.util.Map;

/**
 * @author yingdg
 * created in 2019/7/24 3:59 PM
 */
public class CommandCatalog extends CatalogBase implements Catalog2 {

    @Override
    public Map<String, Command> getCommands() {
        return super.commands;
    }

}
