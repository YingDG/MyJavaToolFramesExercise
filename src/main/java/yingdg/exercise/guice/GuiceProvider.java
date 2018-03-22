package yingdg.exercise.guice;

import com.google.inject.Provider;
import yingdg.exercise.guice.repository.GuiceInterface;
import yingdg.exercise.guice.repository.implement.GuiceInterfaceImplement;

/**
 * Created by yingdg on 2017/4/19.
 */
public class GuiceProvider implements Provider<GuiceInterface> {
    @Override
    public GuiceInterface get() {
        return new GuiceInterfaceImplement(); // "A"
    }
}
