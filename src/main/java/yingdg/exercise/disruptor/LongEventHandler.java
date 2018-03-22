package yingdg.exercise.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * Created by yingdg on 2018/1/30.
 * <p>
 * 消息消费者
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(sequence + "-" + event.getValue() + "-" + endOfBatch);
    }
}
