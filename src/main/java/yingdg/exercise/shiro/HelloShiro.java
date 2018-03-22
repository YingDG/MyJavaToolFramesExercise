package yingdg.exercise.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yingdg on 2017/8/7.
 */
public class HelloShiro {
    private static final transient Logger LOGGER = LoggerFactory.getLogger(HelloShiro.class);

    public static void main(String[] args) {
        LOGGER.info("hello shiro");

        // 登录信息
        UsernamePasswordToken token = new UsernamePasswordToken(
                "username", "password");

        // 配置
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(new UserRealm());
        // securityManager.setRealms(Lists.newArrayList(new UserRealm()));
        // securityManager.setRememberMeManager(new CookieRememberMeManager());
        SecurityUtils.setSecurityManager(securityManager);

        // 验证
        Subject subject = SecurityUtils.getSubject();
        // 手动try/catch
        try {
            securityManager.logout(subject);
            subject.login(token);
            // securityManager.login(subject, token);

            System.out.println(subject.isRemembered());
            System.out.println(subject.hasRole("admin"));
            System.out.println(subject.isPermitted("write"));
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
        } catch (UnknownAccountException e) {
            e.printStackTrace();
        } catch (ExcessiveAttemptsException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (ShiroException e) {
            e.printStackTrace();
        } finally {
            System.out.println(subject.getPrincipal());
            subject.logout();
        }
    }
}
