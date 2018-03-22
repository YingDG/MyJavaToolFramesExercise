package yingdg.exercise.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * Created by yingdg on 2017/10/26 0026.
 */
public class ZnodeExists {
    private static ZooKeeper zk;
    private static ZookeeperConnection conn;

    public static void main(String[] args) {
        String path = "/MyFirstZnode"; // Assign znode to the specified path

        try {
            conn = new ZookeeperConnection();
            zk = conn.connect("localhost");

            // check existence of znode and its status, if znode is available.
            Stat stat = zk.exists(path, true); // Stat checks the path of the znode
            if (stat != null) {
                System.out.println("Node exists and the node version is " + stat.getVersion());
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
