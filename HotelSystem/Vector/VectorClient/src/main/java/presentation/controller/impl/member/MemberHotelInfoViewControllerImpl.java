package presentation.controller.impl.member;

import java.util.List;

import businessLogic.impl.HotelBlServiceImpl;
import common.RoomType;
import presentation.controller.service.member.MemberHotelInfoViewControllerService;
import vo.HotelVo;

/**
 * @author Molloh
 * @version 2017/1/1
 * @description 客户查看酒店信息界面的controller的实现类
 */
public class MemberHotelInfoViewControllerImpl implements MemberHotelInfoViewControllerService {
    private static MemberHotelInfoViewControllerService INSTANCE = new MemberHotelInfoViewControllerImpl();

    private String hotelId;
    private HotelVo hotelVo;

    private MemberHotelInfoViewControllerImpl() {
    }

    public static MemberHotelInfoViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
        hotelVo = HotelBlServiceImpl.getInstance().getHotelVo(hotelId);
    }

    @Override
    public String getHotelName() {
        return hotelVo.getHotelName();
    }

    @Override
    public String getHotelStar() {
        return String.valueOf(hotelVo.getStars());
    }

    @Override
    public String getHotelPoint() {
        return String.valueOf(hotelVo.getPoStrings());
    }

    @Override
    public String getHotelDiscription() {
        return hotelVo.getHotelInfo();
    }

    @Override
    public String getHotelRoomPrice(RoomType T) {
        return String.valueOf(hotelVo.getOriginPrice(T));
    }

    @Override
    public String getRoomNum(RoomType T) {
        return String.valueOf(HotelBlServiceImpl.getInstance().getReadyRoom(T));
    }

    @Override
    public List<String> getComment() {
        return hotelVo.getCommentList();
    }
}
