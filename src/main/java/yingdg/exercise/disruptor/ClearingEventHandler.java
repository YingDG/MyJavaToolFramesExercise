package yingdg.exercise.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * Created by yingdg on 2018/1/30.
 */
class ObjectEvent<T> {
    T val;

    void clear() {
        val = null;
    }
}

public class ClearingEventHandler<T> implements EventHandler<ObjectEvent<T>> {

    @Override
    public void onEvent(ObjectEvent<T> event, long sequence, boolean endOfBatch) {
        // Failing to call clear here will result in the object associated with the event to live until
        // it is overwritten once the ring buffer has wrapped around to the beginning.
        event.clear();
    }

}