package guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.Before;
import org.junit.Test;
import yingdg.exercise.guice.GuiceBinder;
import yingdg.exercise.guice.repository.GuiceInterface;
import yingdg.exercise.guice.service.GuiceService;

/**
 * Created by yingdg on 2017/4/19.
 */
public class GuiceTest {
    private Injector injector;
    private Injector injector2;
    private GuiceService service;

    @Before
    public void init() {
        injector = Guice.createInjector();
        injector2 = Guice.createInjector(new Module[]{new GuiceBinder()});
    }

    @Test
    public void test() {
        GuiceInterface inter = injector.getInstance(GuiceInterface.class);
        System.out.println(inter.go());
    }

    @Test
    public void test2() {
        service = injector2.getInstance(GuiceService.class);
        System.out.println(service.interA.go());
        System.out.println(service.interB.go());
    }

}
