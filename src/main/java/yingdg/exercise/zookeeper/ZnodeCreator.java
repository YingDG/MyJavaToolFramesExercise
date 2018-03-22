package yingdg.exercise.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by yingdg on 2017/10/26 0026.
 */
public class ZnodeCreator {
    // create static instance for yingdg.exercise.zookeeper class.
    private static ZooKeeper zk;

    // create static instance for ZooKeeperConnection class.
    private static ZookeeperConnection conn;

    // Method to create znode in yingdg.exercise.zookeeper ensemble
    public static void create(String path, byte[] data) throws KeeperException, InterruptedException {
        zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public static void main(String[] args) {
        // znode path
        String path = "/MyFirstZnode"; // Assign path to znode

        // data in byte array
        byte[] data = "My first yingdg.exercise.zookeeper app".getBytes(); // Declare data

        try {
            conn = new ZookeeperConnection();
            zk = conn.connect("localhost");

            create(path, data); // Create the data to the specified path
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
