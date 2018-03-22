package yingdg.exercise.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

/**
 * Created by yingdg on 2017/10/26 0026.
 */
public class ZnodeGetChildren {
    private static ZooKeeper zk;
    private static ZookeeperConnection conn;

    public static void main(String[] args) {
        String path = "/MyFirstZnode"; // Assign path to the znode

        try {
            conn = new ZookeeperConnection();
            zk = conn.connect("localhost");

            Stat stat = zk.exists(path, true); // Stat checks the path
            if (stat != null) {
                //“getChildren" method- get all the children of znode.It has two args, path and watch
                List<String> children = zk.getChildren(path, false);
                for (String child : children) {
                    System.out.println(child);
                }
            } else {
                System.out.println("Node does not exists");
            }
        } catch (InterruptedException | KeeperException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
