package presentation.controller.impl.manager;

import businessLogic.impl.AccountBlServiceImpl;
import businessLogic.impl.HotelBlServiceImpl;
import businessLogic.impl.MemberBlServiceImpl;
import businessLogic.service.AccountBlService;
import businessLogic.service.HotelBlService;
import businessLogic.service.MemberBlService;
import common.AccountType;
import common.ResultMessage;
import common.Sex;
import presentation.controller.service.manager.ManagerUserControllerService;
import vo.AccountVo;
import vo.HotelVo;
import vo.MemberVo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description ManagerUserView 的 controller 实现类
 */
public class ManagerUserControllerImpl implements ManagerUserControllerService {
    private static final ManagerUserControllerService INSTANCE = new ManagerUserControllerImpl();

    private AccountBlService account;
    private AccountVo accountVo;
    private MemberBlService member;
    private MemberVo memberVo;
    private HotelBlService hotel;
    private HotelVo hotelVo;

    private ManagerUserControllerImpl(){
        account = AccountBlServiceImpl.getInstance();
        member = MemberBlServiceImpl.getInstance();
        hotel = HotelBlServiceImpl.getInstance();
    }

    public static ManagerUserControllerService getInstance() {
        return INSTANCE;
    }

    /**
     * 全局的方法
     */
    @Override
    public void setUserId(String userId) {
        accountVo = account.findAccount(userId);
        AccountType accountType = account.getAccountTypeById(userId);
        if(accountType == AccountType.Member) {
            memberVo = member.getInfo(userId);
        }else if(accountType == AccountType.Hotel) {
            hotelVo = hotel.getHotelVo(userId);
        }
    }

    @Override
    public void setUserName(String userName) {
        accountVo.setMemberName(userName);
        if(memberVo != null) {
            memberVo.setName(userName);
        }
        if(hotelVo != null) {
            hotelVo.setHotelName(userName);
        }
    }

    @Override
    public String getUserName() {
        if(accountVo == null)
            System.out.print("sad");

        return accountVo.getMemberName();
    }

    @Override
    public String addUser(String password, AccountType type) {
        return account.insertAccount("default", password, type);
    }

    @Override
    public ResultMessage delUser(String userId) {
        return account.deleteAccount(userId);
    }

    /**
     * Member get 方法
     */

    @Override
    public LocalDate getBirthDay() {
        if(memberVo != null) {
            return memberVo.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return null;
    }

    @Override
    public String getAddress() {
        if(memberVo != null) {
            return memberVo.getAddress();
        }
        return null;
    }

    @Override
    public String getCredit() {
        if(memberVo != null) {
            return String.valueOf(memberVo.getCredit());
        }
        return null;
    }

    @Override
    public String getPhone() {
        if(memberVo != null) {
            return  String.valueOf(memberVo.getPhone());
        }
        return null;
    }

    @Override
    public Sex getSex() {
        if(memberVo != null) {
            return  memberVo.getSex();
        }
        return null;
    }

    @Override
    public String getEnterPrise() {
        if(memberVo != null) {
            return memberVo.getName();
        }
        return null;
    }

    @Override
    public String getVIPLevel() {
        if(memberVo != null) {
            return String.valueOf(memberVo.getVip());
        }
        return null;
    }

    @Override
    public ResultMessage updateInfo() {
        ResultMessage msg =  account.updateAccount(accountVo);
        if(memberVo != null)
            msg = member.modifyInfo(this.memberVo);
        if(hotelVo != null)
            msg = hotel.updateBasicInfo(hotelVo);

        return msg;
    }

    /**
     * Member set 方法
     */

    @Override
    public void setSex(Sex sex) {
        if(memberVo != null) {
            memberVo.setSex(sex);
        }
    }

    @Override
    public void setPhone(String phoneNum) {
        if(memberVo != null) {
            memberVo.setPhone(phoneNum);
        }
    }

    @Override
    public void setMemberName(String memberName) {
        if(memberVo != null) {
            memberVo.setName(memberName);
        }
    }

    @Override
    public void setBirthDay(LocalDate birthDay) {
        if(memberVo != null) {
            memberVo.setBirthday(Date.from(birthDay.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
    }

    @Override
    public void setAddress(String address) {
        if(memberVo != null) {
            memberVo.setAddress(address);
        }
    }

    @Override
    public void setEnterPrise(String enterPrise) {
        if(memberVo != null) {
            memberVo.setName(enterPrise);
        }
    }

    @Override
    public void setVIPLevel(String vipLevel) {
        if(memberVo != null) {
            memberVo.setVip(Integer.valueOf(vipLevel));
        }
    }

    /**
     * hotel 的 get 方法
     */

    @Override
    public String getHotelAddress() {
        if(hotelVo != null) {
            return hotelVo.getHotelPosition();
        }
        return null;
    }

    @Override
    public String getHotelDiscription() {
        if(hotelVo != null) {
            return hotelVo.getHotelInfo();
        }
        return null;
    }

    @Override
    public String getHotelPoint() {
        if(hotelVo != null) {
            return String.valueOf(hotelVo.getNumOfpoint());
        }
        return null;
    }

    @Override
    public String getHotelStar() {
        if(hotelVo != null) {
            return String.valueOf(hotelVo.getStars());
        }
        return null;
    }

    @Override
    public String getHotelPhone() {
        if(hotelVo != null) {
            return hotelVo.getHotelTel();
        }
        return null;
    }

    /**
     * hotel 的 set 方法
     */

    @Override
    public void setHotelAddress(String hotelAddress) {
        if(hotelVo != null) {
            hotelVo.setHotelPosition(hotelAddress);
        }
    }

    @Override
    public void setHotelDiscription(String hotelDiscription) {
        if(hotelVo != null) {
            hotelVo.setHotelInfo(hotelDiscription);
        }
    }

    @Override
    public void setHotelPhone(String hotelPhone) {
        if(hotelVo != null) {
            hotelVo.setHotelTel(hotelPhone);
        }
    }

    @Override
    public void setHotelStar(String hotelStar) {
        if(hotelVo != null) {
            hotelVo.setStars(Integer.valueOf(hotelStar));
        }
    }

    @Override
    public void setHotelPoint(String hotelPoint) {
        if(hotelVo != null) {
            hotelVo.setNumOfPoint(Integer.valueOf(hotelPoint));
        }
    }
}
