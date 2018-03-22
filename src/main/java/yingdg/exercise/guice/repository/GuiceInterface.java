package yingdg.exercise.guice.repository;

import com.google.inject.ProvidedBy;
import yingdg.exercise.guice.GuiceProvider;

/**
 * Created by yingdg on 2017/4/19.
 */
// @ImplementedBy(GuiceInterfaceImplement2.class) // 指定默认实现类
@ProvidedBy(GuiceProvider.class)
public interface GuiceInterface {
    String go();
}
