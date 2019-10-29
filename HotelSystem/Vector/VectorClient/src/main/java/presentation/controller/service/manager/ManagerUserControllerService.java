package presentation.controller.service.manager;

import common.AccountType;
import common.ResultMessage;
import common.Sex;

import java.time.LocalDate;

/**
 * @author Molloh
 * @version 2016/12/9
 * @description
 */
public interface ManagerUserControllerService {

    String addUser(String password, AccountType type);

    String getUserName();

    void setUserId(String userId);

    void setUserName(String userName);

    ResultMessage updateInfo();

    ResultMessage delUser(String userId);

    /**
     * Member的get/set方法
     */

    LocalDate getBirthDay();

    String getAddress();

    String getCredit();

    String getPhone();

    Sex getSex();

    String getEnterPrise();

    String getVIPLevel();

    void setSex(Sex sex);

    void setPhone(String phoneNum);

    void setMemberName(String memberName);

    void setBirthDay(LocalDate birthDay);

    void setAddress(String address);

    void setEnterPrise(String enterPrise);

    void setVIPLevel(String vipLevel);

    /**
     * Hotel的get/set方法
     */

    String getHotelAddress();

    String getHotelDiscription();

    String getHotelPoint();

    String getHotelStar();

    String getHotelPhone();

    void setHotelAddress(String hotelAddress);

    void setHotelDiscription(String hotelDiscription);

    void setHotelPhone(String hotelPhone);

    void setHotelStar(String hotelStar);

    void setHotelPoint(String hotelPoint);

}
