/**
* @version 2017-01-01
* @author 金灵益
*/
package businessLogic.service;
import common.ResultMessage;
import common.RoomType;
import vo.HotelVo;


/**
* 酒店的属性有：
* 编号，名称，所属省份、城市、商圈，简介，地址，联系方式，星级，评分，评价，评分的人数
* 酒店每种类型房间的初始价格、总数量，被预定的数量
*/
public interface HotelBlService {
	
	/**
	 * 增加酒店，当创建好酒店ID时调用方法初始化酒店
	 * @param hotelId
	 */
	public ResultMessage addHotel(String hotelId);
	
	/**
	 * 当酒店基本信息变化时，只更新酒店列表
	 * 基本信息：编号，名称，所属省份、城市、商圈，简介，地址，联系方式，星级，评分，评价，评分的人数
	 * @param vo
	 */
	public ResultMessage updateBasicInfo(HotelVo vo);
	
	/**
	 * 删除酒店 
	 * @param hotelId
	 */
	public ResultMessage deleteHotel(String hotelId);
	
	/**
	 * 得到酒店vo
	 * @param hotelId
	 * @return
	 */
	public HotelVo getHotelVo(String hotelId);
	 
	/**
	 * 酒店工作人员录入客房信息
	 * @param hotelId
	 * @param type
	 * @param number
	 * @param price
	 */
	public ResultMessage initializeRoom(String hotelId, RoomType type, int number, int price);
	
	/**
	 * 当酒店人员执行退房时，调用此方法，更新房间预订数量，订单状态改为已执行
	 * @param type
	 * @return 
	 */
	public ResultMessage checkoutRoom(RoomType type, int number);
	
	/**
	 * 当客户预定房间，调用此方法；若房间有空，则更新酒店房间预订数量信息；若预订数量超过剩余，则返回fail
	 * @param type
	 * @param number  该类型房间预订的数量
	 * @return 
	 */
	 public ResultMessage bookRoom(RoomType type, int number);
	
	/**
	 * 返回该类型房间的空余数量
	 * @param type
	 * @return
	 */
	public int getReadyRoom(RoomType type);

	/**
	 * 客户给予文字评价和评分，同时将订单状态改为已评价
	 * @param orderId
	 * @param giveComment
	 * @param poStrings
	 * @return
	 */
	public ResultMessage comment(String orderId, String giveComment, double poStrings);

}