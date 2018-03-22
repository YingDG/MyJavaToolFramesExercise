package yingdg.exercise.guice.service;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import yingdg.exercise.guice.repository.GuiceInterface;

/**
 * Created by yingdg on 2017/4/19.
 */
public class GuiceService {
    @Inject
    @Named("A")
    public GuiceInterface interA;
    @Inject
    @Named("B")
    public GuiceInterface interB;
}
