/*package com.baizhi.util;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.util.SpringContextUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;

public class MyRealm extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        AdminDao adminDao = (AdminDao) SpringContextUtil.getBean(AdminDao.class);
        //获取身份信息
        String username = (String) authenticationToken.getPrincipal();
        Admin admin = adminDao.selectAdminByUserName(username);
        if(admin==null){
            return null;
        }else{
            //1 2凭证3类名
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,admin.getPassword(),this.getName());
            return simpleAuthenticationInfo;
        }
    }
}
*/