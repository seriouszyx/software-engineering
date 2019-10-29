package dataService.dataHelper.service;


import java.util.List;
import java.util.Map;

import common.ResultMessage;
import common.RoomType;
import po.HotelPo;

/**
 * @author 金灵益
 * @version 2017-01-01
 * @description
 */
public interface HotelDataHelper {
	/**
	 * 从数据文件,hotelList中读取酒店数据
	 * @return map
	 */
	public Map<String,HotelPo> getHotelData();
	
	/**
	 * 向hoteLlist写入酒店数据,初始化酒店房间、评论文件
	 * @param po
	 * @return ResultMessage.SUCCEED, 添加成功；  ResultMessage.FAIL, 添加失败
	 */
	public ResultMessage addHotelData(HotelPo po);
	
	/**
	 * 向数据文件中hotelList写入酒店数据
	 * @param list	
	 * @return ResultMessage.SUCCEED, 更新成功；  ResultMessage.FAIL, 更新失败
	 */
	public ResultMessage updateHotelListData(Map<String, HotelPo> map);
	
	/**
	 * 从数据文件中删除数据
	 * @param hotelId
	 * @return ResultMessage.SUCCEED 删除成功;  ResultMessage.FAIL 不存在该酒店
	 */
	public ResultMessage deleteHotelData(String hotelId);
	
	/**
	 * @param hotelId
	 * @return 酒店的文字评论List
	 */
	public List<String> getComments(String hotelId);
	
	/**
	 * 更新酒店文字评论
	 * @param hotelId
	 * @param comment
	 * @return ResultMessage.SUCCEED, 更新成功；  ResultMessage.FAIL, 更新失败
	 */
	public ResultMessage updateComments(String hotelId,List<String> commentList);
	
	/**
	 * 初始化酒店房间信息，存储酒店的房间类型，数量，原始价格。不具体到单个房间
	 * @param hotelId
	 * @param type
	 * @param number
	 * @param price
	 * @return ResultMessage.SUCCEED, 更新成功；  ResultMessage.FAIL, 更新失败
	 */
	public ResultMessage initRoom(String hotelId, RoomType type, int number, int price);
	
	/**
	 * @return 所有省份列表
	 */
	public List<String> getProvinceList();
	
	/**
	 * 根据省份返回其市级地区
	 * @param province
	 * @return
	 */
	public List<String> getCityList(String province);
	
	/**
	 * 根据省市返回所在地的商圈
	 * @param province
	 * @param city
	 * @return
	 */
	public List<String> getBusinessList(String province, String city);
	
	/**
	 * 更新酒店房间预订数量
	 * @param hotelId
	 * @param type
	 * @param number
	 * @param isCheckIn
	 * @return ResultMessage.SUCCEED, 更新成功；  ResultMessage.FAIL, 更新失败
	 */
	public ResultMessage updateOrderedRoom(String hotelId, RoomType type, int number, boolean isCheckIn);
	
	/**
	 * 得到房间预订数量
	 * @param hotelId
	 * @param type
	 * @return
	 */
	public int getOrderedRoom(String hotelId, RoomType type);
}