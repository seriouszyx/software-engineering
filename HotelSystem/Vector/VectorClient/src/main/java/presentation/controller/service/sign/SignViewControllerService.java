package presentation.controller.service.sign;

import common.AccountType;

/**
 * @author Molloh
 * @version 2016/11/5
 * @description 处理登录、登出、注册事件
 */
public interface SignViewControllerService {

    AccountType signIn(String memberName, String password);

    String signUp(String memberName, String password, boolean isMember);

    void signOut();

}
