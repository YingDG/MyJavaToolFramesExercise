package yingdg.exercise.shiro;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Objects;

/**
 * Created by yingdg on 2017/8/7.
 */
public class UserRealm extends AuthorizingRealm {
    /*
    AuthorizationInfo：权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // String username = (String) principalCollection.getPrimaryPrincipal().toString();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        // 将角色名称提供给info
        authorizationInfo.setRoles(Sets.newHashSet("user", "admin"));
        // 将权限名称提供给info
        authorizationInfo.setStringPermissions(Sets.newHashSet("read", "write"));

        return authorizationInfo;
    }

    /*
    AuthenticationInfo：角色
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();
        if (Objects.isNull(username)) {
            // 用户名不存在抛出异常
            throw new UnknownAccountException();
        }
        if (Strings.isNullOrEmpty(username)) {
            // 用户被管理员锁定抛出异常
            throw new LockedAccountException();
        }

        return new SimpleAuthenticationInfo("username", "password",
                ByteSource.Util.bytes("user"), getName());
    }

}
