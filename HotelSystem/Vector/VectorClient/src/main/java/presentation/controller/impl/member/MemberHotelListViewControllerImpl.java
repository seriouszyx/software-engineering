package presentation.controller.impl.member;

import businessLogic.impl.HotelListServiceImpl;
import businessLogic.service.HotelListService;
import common.RoomType;
import presentation.controller.service.member.MemberHotelListViewControllerService;
import vo.HotelVo;

import java.util.List;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description Member 酒店列表界面的控制器实现类
 */
public class MemberHotelListViewControllerImpl implements MemberHotelListViewControllerService {
    private HotelListService hotelList;
    private static MemberHotelListViewControllerService INSTANCE = new MemberHotelListViewControllerImpl();

    private MemberHotelListViewControllerImpl() {
        hotelList = HotelListServiceImpl.getInstance();
    }

    public static MemberHotelListViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<HotelVo> findByKeyword(String key) {
        return hotelList.findByKeyword(key);
    }

    @Override
    public List<HotelVo> findByAddress(String province, String city, String cbd) {
        return hotelList.findByAddress(province, city, cbd);
    }

    @Override
    public List<HotelVo> findByOriginalPrice(RoomType type, int low, int high, List<HotelVo> list) {
        return hotelList.findByOriginalPrice(type, low, high, list);
    }

    @Override
    public List<HotelVo> findByPoint(double least, double max, List<HotelVo> list) {
        return hotelList.findByPoint(least, max, list);
    }

    @Override
    public List<HotelVo> findByRoomType(RoomType type, List<HotelVo> list) {
        System.out.print(hotelList.findByRoomType(type, list).isEmpty());
        return hotelList.findByRoomType(type, list);
    }

    @Override
    public List<HotelVo> findByStars(int num, List<HotelVo> list) {
        return hotelList.findByStars(num, num, list);
    }

    @Override
    public List<String> getProvinceList() {
        return hotelList.getProvinceList();
    }

    @Override
    public List<String> getCityList(String province) {
        return hotelList.getCityList(province);
    }

    @Override
    public List<String> getCbdList(String province, String city) {
        return hotelList.getBusinessList(province, city);
    }
}
