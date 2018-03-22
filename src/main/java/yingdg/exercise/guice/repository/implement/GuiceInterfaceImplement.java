package yingdg.exercise.guice.repository.implement;

import com.google.inject.Singleton;
import yingdg.exercise.guice.repository.GuiceInterface;

/**
 * Created by yingdg on 2017/4/19.
 */
@Singleton
public class GuiceInterfaceImplement implements GuiceInterface {

    @Override
    public String go() {
        return "A";
    }

}
