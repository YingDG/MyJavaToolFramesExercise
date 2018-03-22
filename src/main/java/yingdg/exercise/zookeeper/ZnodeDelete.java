package yingdg.exercise.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * Created by yingdg on 2017/10/26 0026.
 */
public class ZnodeDelete {
    private static ZooKeeper zk;
    private static ZookeeperConnection conn;

    public static void main(String[] args) {
        String path = "/MyFirstZnode"; //Assign path to the znode

        try {
            conn = new ZookeeperConnection();
            zk = conn.connect("localhost");

            // delete the node with the specified path
            Stat stat = zk.exists(path, true);
            zk.delete(path, stat.getVersion());
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
