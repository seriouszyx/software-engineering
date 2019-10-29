package presentation.controller.service.member;

import common.RoomType;
import vo.HotelVo;

import java.util.List;

/**
 * @ author Molloh
 * @ version 2016/12/6
 * @ description Member 酒店列表界面的控制器接口
 */
public interface MemberHotelListViewControllerService {

    //id查找
    List<HotelVo> findByKeyword(String key);

    //地址筛选
    List<HotelVo> findByAddress(String province, String city, String cbd);

    //价格筛选
    List<HotelVo> findByOriginalPrice(RoomType type, int low, int high, List<HotelVo> list);

    //评分筛选
    List<HotelVo> findByPoint(double least, double max, List<HotelVo> list);

    //房间类型筛选
    List<HotelVo> findByRoomType(RoomType type, List<HotelVo> list);

    //星级筛选
    List<HotelVo> findByStars(int num, List<HotelVo> list);

    //省份list
    List<String> getProvinceList();

    //城市list
    List<String> getCityList(String province);

    //商圈list
    List<String> getCbdList(String province, String city);

}
