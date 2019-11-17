package dataHelper;

import java.util.List;

import po.HotelPO;
import utilities.enums.ResultMessage;

/**
 * @Description:对数据库中hotel表的操作
 * @author:Harvey Gong
 * @time:2016年12月4日 上午10:10:46
 */
public interface HotelDataHelper {

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param city 城市，circle商圈
	 * @return List<HotelPO> 酒店信息载体列表
	 */
	//根据城市商圈返回位于该地区所有酒店的简介
	public List<HotelPO> getHotels(String city, String circle);

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param hotelID 酒店ID
	 * @return HotelPO 酒店信息载体
	 */
	//根据酒店id返回该酒店的基本信息
	public HotelPO getHotelInfo (String hotelID);

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param hotelPO 酒店信息载体
	 * @return ResultMessage 是否成功更新酒店信息
	 */
	//根据hotelPO更新一条数据库里的po
	public ResultMessage updateHotelInfo(HotelPO hotelPO);

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param hotelPO 酒店信息载体
	 * @return ResultMessage 是否添加更新酒店信息
	 */
	//根据hotelPO添加一条po
	public ResultMessage addHotelInfo(HotelPO hotelPO);
}
