package yingdg.exercise.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

import java.beans.Beans;

/**
 * Created by yingdg on 2017/8/7.
 */
public class MyRealm implements Realm {
    /*
    自定义Realm实现
     */

    // Realm名字
    @Override
    public String getName() {
        return "myRealm";
    }

    // 支持类型
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return Beans.isInstanceOf(authenticationToken, UsernamePasswordToken.class);
    }

    // 验证方式
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();
        String password = new String((char[]) authenticationToken.getCredentials());
        if (!"zhang".equals(username)) {
            throw new UnknownAccountException();
        }
        if (!"123".equals(password)) {
            System.out.println(password);
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(username, password, getName());
    }

}
