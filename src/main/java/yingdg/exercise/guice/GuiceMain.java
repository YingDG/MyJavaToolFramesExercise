package yingdg.exercise.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import yingdg.exercise.guice.model.Player;

import java.util.Objects;

/**
 * Created by yingdg on 2017/4/19.
 */
public class GuiceMain {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector();
        Player player = injector.getInstance(Player.class);
        player.setName("player");
        player.getLaptop().setLname("laptop");
        System.out.println(player);

        Player player2 = injector.getInstance(Player.class);
        System.out.println(Objects.equals(player, player2));
        System.out.println(player2);
    }
}
