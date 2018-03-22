package yingdg.exercise.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * Created by yingdg on 2017/10/26 0026.
 */
public class ZnodeSetData {
    private static ZooKeeper zk;
    private static ZookeeperConnection conn;

    public static void main(String[] args) {
        String path = "/MyFirstZnode";
        byte[] data = "Success".getBytes(); //Assign data which is to be updated.

        try {
            conn = new ZookeeperConnection();
            zk = conn.connect("localhost");

            Stat stat = zk.exists(path, true);
            // update the data in a znode. Similar to getData but without watcher.
            zk.setData(path, data, stat.getVersion());
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
