package yingdg.exercise.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by yingdg on 2018/1/30.
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
