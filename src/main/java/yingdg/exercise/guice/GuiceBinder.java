package yingdg.exercise.guice;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;
import yingdg.exercise.guice.repository.GuiceInterface;
import yingdg.exercise.guice.repository.implement.GuiceInterfaceImplement;
import yingdg.exercise.guice.repository.implement.GuiceInterfaceImplement2;

/**
 * Created by yingdg on 2017/4/19.
 */
// 手动配置注入
public class GuiceBinder implements Module {

    // implements Module
    @Override
    public void configure(Binder binder) {
        binder.bind(GuiceInterface.class).annotatedWith(Names.named("A")).to(GuiceInterfaceImplement.class);
        binder.bind(GuiceInterface.class).annotatedWith(Names.named("B")).to(GuiceInterfaceImplement2.class);
        binder.requestStaticInjection(GuiceMain.class);
    }

    // extends AbstractModule
//    @Override
//    protected void configure() {
//        bind(GuiceInterface.class).annotatedWith(Names.named("A")).to(GuiceInterfaceImplement.class);
//        bind(GuiceInterface.class).annotatedWith(Names.named("B")).to(GuiceInterfaceImplement2.class);
//    }

}
