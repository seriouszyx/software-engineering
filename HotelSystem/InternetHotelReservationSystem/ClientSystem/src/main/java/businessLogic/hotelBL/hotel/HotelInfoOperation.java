package businessLogic.hotelBL.hotel;

import java.util.Iterator;

import po.HotelPO;
import utilities.Address;
import utilities.enums.ResultMessage;
import utilities.enums.RoomType;

/**
 * @Description:对外提供的更新和获取酒店相关信息的接口
 * @author:Harvey Gong
 * lastChangedBy:Harvey Gong
 * @time:2016年12月4日 下午9:52:00
 */
public interface HotelInfoOperation {

	/**
	 * @Description:根据酒店编号返回酒店的编号
	 * @param hotelID
	 * @return
	 * HotelPO
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月24日 下午5:17:51
	 */
	public HotelPO getHotelPO(String hotelID);
	
	/**
	 * @Description:更新酒店评分
	 * @param score
	 * @return
	 * ResultMessage ResultMessage.HOTEL_SCORE_UPDATE_FAILURE/ResultMessage.HOTEL_SCORE_UPDATE_SUCCESS
	 * @author: Harvey Gong
	 * lastChangedBy: charles
	 * @time:2016年12月4日 22:55:56
	 */
	public ResultMessage scoreUpdate(String hotelID,double score);

	/**
	 * @Description:获取酒店所在的城市商圈
	 * @return
	 * Address
	 * @author: Harvey Gong
	 * lastChangedBy: Harvey Gong
	 * @time:2016年12月4日 下午9:56:21
	 */
	public Address getHotelAddress(String hotelID);

	/**
	 * @Description:委托给Rooms，获取该酒店的所有房间类型
	 * @return
	 * Iterator<String>
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月4日 下午11:08:42
	 */
	public Iterator<RoomType> getRoomType(String hotelID);

	/**
	 * @Description:根据酒店id获取该酒店剩余的房间总数量
	 * @param hotelID
	 * @return
	 * int
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月6日 下午5:32:46
	 */
	public int getRemainRoomNum(String hotelID);

	/**
	 * @Description:办理入住
	 * @param hotelID
	 * @param roomName
	 * @param roomNum
	 * @return
	 * ResultMessage
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月8日 上午12:44:36
	 */
	public ResultMessage checkIn(String hotelID, RoomType roomType, int roomNum);

	/**
	 * @Description:办理退房
	 * @param hotelID
	 * @param roomType
	 * @param roomNum
	 * @return
	 * ResultMessage
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月8日 上午12:44:56
	 */
	public ResultMessage checkOut(String hotelID, RoomType roomType, int roomNum);
	
	/**
	 * @Description:客户撤销未执行订单时更新此酒店的剩余房间数
	 * @param hotelID 目标酒店编号
	 * @param roomType 目标房间类型
	 * @param roomNum 此订单的房间数
	 * @return ResultMessage 是否撤销成功
	 * @author: charles
	 * @lastChangedBy: charles
	 * @time:2016/12/10
	 */
	public ResultMessage updateRemainRoomNumForUndoOrder(String hotelID, RoomType roomType, int roomNum);
}
