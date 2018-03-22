package yingdg.exercise.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by yingdg on 2017/10/26 0026.
 */
public class ZnodeGetData {
    private static ZooKeeper zk;
    private static ZookeeperConnection conn;

    public static void main(String[] args) {
        String path = "/MyFirstZnode";
        final CountDownLatch connectedSignal = new CountDownLatch(1);

        try {
            conn = new ZookeeperConnection();
            zk = conn.connect("localhost");

            Stat stat = zk.exists(path, true);
            if (stat != null) {
                byte[] b = zk.getData(path, new Watcher() {
                    @Override
                    public void process(WatchedEvent we) {
                        if (we.getType() == Event.EventType.None) {
                            switch (we.getState()) {
                                case Expired:
                                    connectedSignal.countDown();
                                    break;
                            }
                        } else {
                            String path = "/MyFirstZnode";

                            try {
                                byte[] bn = zk.getData(path, false, null);
                                String data = new String(bn, "UTF-8");
                                System.out.println(data);

                                connectedSignal.countDown();
                            } catch (InterruptedException | UnsupportedEncodingException | KeeperException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, null);

                String data = new String(b, "UTF-8");
                System.out.println(data);
                connectedSignal.await();
            } else {
                System.out.println("Node does not exists");
            }
        } catch (InterruptedException | KeeperException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
