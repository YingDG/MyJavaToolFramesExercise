package yingdg.exercise.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoCallback;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;
import yingdg.exercise.kryo.model.Mall;

/**
 * Created by yingdg on 2017/4/19.
 */
public class KryoMain {
    public static void main(String[] args) {
        Mall mall = new Mall(1, "iPhone", 5288);
        System.out.println(mall);

        Kryo kryo = new Kryo();
        // 序列化
        Output output = new Output(128, 512); // 需要配置缓冲参数
        kryo.writeObject(output, mall);
        output.flush();
        output.clear();
        output.close();

        // 反序列化
        Input input = new Input(output.getBuffer());
        Mall mall1 = kryo.readObject(input, Mall.class);
        input.close();
        System.out.println(mall1);

        // 工厂实例化Kryo类
        KryoFactory factory = new KryoFactory() {
            @Override
            public Kryo create() {
                return new Kryo();
            }
        };
        Kryo kryo1 = factory.create();

        // 对象池实例化Kryo类
        KryoPool pool = new KryoPool.Builder(factory).build();
        Kryo kryo2 = pool.borrow();
        pool.release(kryo2);
        pool.run(new KryoCallback<Mall>() {
            @Override
            public Mall execute(Kryo kryo) {
                return kryo.readObject(new Input(output.getBuffer()), Mall.class);
            }
        });
    }
}
