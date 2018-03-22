package yingdg.exercise.kryo.builder;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoCallback;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;

import java.io.InputStream;

/**
 * Created by yingdg on 2017/4/19.
 */
public class KryoBuilder {
    private static KryoFactory factory;
    private static KryoPool pool;

    static {
        factory = new KryoFactory() {
            @Override
            public Kryo create() {
                return new Kryo();
            }
        };
        pool = new KryoPool.Builder(factory).build();
    }

    public static Kryo getInstance() {
        return pool.borrow();
    }

    public static void destory(Kryo kryo) {
        pool.release(kryo);
    }

    public static <T> Object deserialize(InputStream input, Class<T> clz) {
        pool.run(new KryoCallback<Object>() {
            @Override
            public Object execute(Kryo kryo) {
                try (Input in = new Input(input)) {
                    return kryo.readObject(in, clz);
                }
            }
        });
        return null;
    }

    public static byte[] serialize(Object obj) {
        pool.run(new KryoCallback<Object>() {
            @Override
            public Object execute(Kryo kryo) {
                try (Output out = new Output(128, 512)) {
                    kryo.writeObject(out, obj);
                    return out.getBuffer();
                }
            }
        });
        return null;
    }
}
