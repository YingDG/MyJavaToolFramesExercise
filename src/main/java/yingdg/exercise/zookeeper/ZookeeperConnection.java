package yingdg.exercise.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by yingdg on 2017/10/26 0026.
 */
public class ZookeeperConnection {
    // declare yingdg.exercise.zookeeper instance to access ZooKeeper ensemble
    private ZooKeeper zoo;
    private final CountDownLatch connectedSignal = new CountDownLatch(1);

    // Method to connect yingdg.exercise.zookeeper ensemble.
    public ZooKeeper connect(String host) throws IOException, InterruptedException {
        zoo = new ZooKeeper(host, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent we) {
                if (we.getState() == Event.KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                }
            }
        });

        connectedSignal.await();
        return zoo;
    }

    // Method to disconnect from yingdg.exercise.zookeeper server
    public void close() throws InterruptedException {
        zoo.close();
    }

}
