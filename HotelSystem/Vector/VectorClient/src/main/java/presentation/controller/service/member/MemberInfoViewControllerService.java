package presentation.controller.service.member;

import common.ResultMessage;
import common.Sex;

import java.time.LocalDate;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description Member编辑个人信息处理控制器接口
 */
public interface MemberInfoViewControllerService {

    //设置当前操作的Id
    void setMemberId(String memberId);

    //get方法

    String getMemberName();

    LocalDate getBirthDay();

    String getAddress();

    String getCredit();

    String getPhone();

    Sex getSex();

    String getEnterPrise();

    String getVIPLevel();

    //set方法

    void setSex(Sex sex);

    void setPhone(String phoneNum);

    void setMemberName(String memberName);

    void setBirthDay(LocalDate birthDay);

    void setAddress(String address);

    void setEnterPrise(String enterPrise);

    void setPassword(String password);

    ResultMessage updateInfo();

}
