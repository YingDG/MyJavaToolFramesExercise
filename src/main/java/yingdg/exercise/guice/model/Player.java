package yingdg.exercise.guice.model;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created by yingdg on 2017/4/19.
 */
@Singleton
public class Player {
    private String name;
    @Inject
    private Laptop laptop;

    public Player() {
    }

    // @Inject
    public Player(Laptop laptop) {
        this.laptop = laptop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", laptop=" + laptop +
                '}';
    }

}
