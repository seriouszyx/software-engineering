/**
 * @version 2017-01-01
 * @author 金灵益
 */
package dataService.dao.service;
import java.rmi.Remote;
import java.util.List;

import common.ResultMessage;
import common.RoomType;
import po.HotelPo;

public interface HotelDao extends Remote{

	/**
	 * 添加酒店
	 * @param po
	 * @return ResultMessage.SUCCEED 添加成功;  ResultMessage.FAIL 添加失败或已存在该酒店
	 */
	public ResultMessage addHotelPO(HotelPo po);
	
	/**
	 * 更新酒店列表信息
	 * @param po
	 * @return ResultMessage.SUCCEED 更新成功;  ResultMessage.FAIL 不存在该酒店
	 */
	public ResultMessage updateHotelList(HotelPo po);
	
	/**
	 * 删除酒店
	 * @param hotelId
	 * @return ResultMessage.SUCCEED 删除成功;  ResultMessage.FAIL 不存在该酒店
	 */
	public ResultMessage deleteHotelPO(String hotelId);
	
	/**
	 * 按ID查找返回酒店
	 * @param hotelId
	 * @return
	 */
	public HotelPo findHotel(String hotelId);
	
	/**
	 * 输入关键字返回所有符合条件的酒店
	 * @param key
	 * @return 酒店列表List
	 */
	public List<HotelPo> keyFind(String key);

	/**
	 * 更新酒店文字评论
	 * @param po
	 * @return ResultMessage.SUCCEED 更新成功;  ResultMessage.FAIL 不存在该酒店
	 */
	public ResultMessage updateComment(HotelPo po);
	
	/**
	 * 初始化酒店房间信息，存储酒店的房间类型，数量，原始价格。不具体到单个房间
	 * @param hotelId
	 * @param type
	 * @param number
	 * @param price
	 * @return ResultMessage.SUCCEED 更新成功;  ResultMessage.FAIL 不存在该酒店或房间价格、数量非法
	 */
	public ResultMessage initHotelTypeRoom(String hotelId, RoomType type, int number, int price);
	
	/**
	 * @return 所有省份列表List
	 */
	public List<String> getProvinceList();
	
	/**
	 * @param province
	 * @return 根据省份返回其市级地区List
	 */
	public List<String> getCityList(String province);
	
	/**
	 * @param province
	 * @param city
	 * @return 根据省市返回所在地的商圈List
	 */
	public List<String> getBusinessList(String province, String city);
	
	/**
	 * 得到某类型空余房间数量
	 * @param hotelId
	 * @param type
	 * @return 房间剩余数量
	 */
	public int getReadyRoom(String hotelId, RoomType type);
	
	/**
	 * 更新酒店房间预订数量
	 * @param hotelId
	 * @param type
	 * @param number
	 * @param isCheckIn
	 * @return ResultMessage.SUCCEED 更新成功;  ResultMessage.FAIL 更新失败
	 */
	public ResultMessage updateOrderedRoom(String hotelId, RoomType type, int number, boolean isCheckIn);
}