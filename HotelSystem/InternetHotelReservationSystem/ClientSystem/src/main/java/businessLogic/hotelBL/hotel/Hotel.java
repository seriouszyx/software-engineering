package businessLogic.hotelBL.hotel;

import java.rmi.RemoteException;
import java.util.Iterator;

import businessLogic.promotionBL.promotions.HotelFixedPromotion;
import dataService.hotelDataService.HotelDataService;
import po.HotelPO;
import rmi.ClientRemoteHelper;
import utilities.Address;
import utilities.enums.ResultMessage;
import utilities.enums.RoomType;
import vo.HotelVO;
import vo.RoomInfoVO;


/**
 * @Description:对于单个酒店的基本操作：
 * 获取、更新酒店详细信息、客房信息，增加客房信息
 * @author:Harvey Gong
 * @time:2016年12月3日 下午9:52:10
 */
public class Hotel implements HotelInfoOperation{

	private HotelDataService hotelDataService;
	private HotelPO hotelPO;
	private Rooms rooms;

	public Hotel(String hotelID) {
		initHotel(hotelID);
	}
	
	public Hotel() {
		initHotelDataService();
		initRooms();
	}

	private void initHotel(String hotelID) {
		initHotelDataService();
		initHotelPO(hotelID);
		initRooms();
	}

	private void initRooms() {
		rooms = new Rooms();
	}

	private void initHotelPO(String hotelID) {
		try {
			hotelPO = hotelDataService.getHotelInfo(hotelID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void initHotelDataService() {
		hotelDataService = ClientRemoteHelper.getInstance().getHotelDataService();
	}

	/**
	 * @Description:返回hotelPO
	 * @param hotelID
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月24日 下午5:19:23
	 */
	@Override
	public HotelPO getHotelPO(String hotelID) {
		initHotelPO(hotelID);
		return hotelPO;
	}
	
	/**
	 * @Description:根据酒店工作人员id获取酒店的基本信息
	 * @param hotelWorkerID
	 * @return
	 * HotelVO
	 * @author: Harvey Gong
	 * @time:2016年12月3日 下午9:44:37
	 */
	public HotelVO getHotelInfo(String hotelID) {
		try {
			HotelPO hotelPO = hotelDataService.getHotelInfo(hotelID);
			return new HotelVO(hotelPO);
		} catch (RemoteException e) {
			return null;
		}
	}

	/**
	 * @Description:更新酒店基本信息
	 * @param hotelVO
	 * @return
	 * ResultMessage
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月3日 下午9:49:19
	 */
	public ResultMessage updateHotelInfo(HotelVO hotelVO) {

		hotelPO = new HotelPO(hotelVO);
		try {
			ResultMessage msg = hotelDataService.updateHotelInfo(hotelPO);
			return msg;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;

	}

	/**
	 * @Description:添加新的酒店
	 * @param hotelVO
	 * @return
	 * ResultMessage
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月3日 下午9:49:40
	 */
	public ResultMessage addHotelInfo(HotelVO hotelVO){

		try {
			
			hotelPO = new HotelPO(hotelVO);
			hotelDataService.addHotelInfo(hotelPO);
			//默认添加1间单人间，价格为100块
			RoomInfoVO roomVO = new RoomInfoVO();
			roomVO.hotelID = hotelVO.hotelID;
			roomVO.price = 100;
			roomVO.roomNum = 1;
			roomVO.remainNum = 1;
			roomVO.roomType = RoomType.SINGLE_BED;
			addRoomInfo(roomVO);
			return ResultMessage.SUCCESS;
		} catch (RemoteException e) {
			return ResultMessage.FAIL;
		}
	}

	/**
	 * @Description:当有该酒店的订单的评价时，更新酒店的评分
	 * @param score
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月6日 上午11:11:51
	 */
	@Override
	public ResultMessage scoreUpdate(String hotelID,double score) {
		initHotelPO(hotelID);
		int commentsNum = hotelPO.getCommentsNum();
		hotelPO.setScore((commentsNum*hotelPO.getScore()+score)/(commentsNum+1));
		return updateHotelInfo(new HotelVO(hotelPO));
	}
	
	
	/**
	 * @Description:委托给room，获取客房信息
	 * @param hotelWorkerID
	 * @return
	 * Iterator<RoomInfoVO>
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月3日 下午9:49:59
	 */
	public Iterator<RoomInfoVO> getHotelRoomInfo(String hotelWorkerID) {
		return rooms.getRoomInfo(hotelWorkerID);
	}

	/**
	 * @Description:委托给room，增加一条客房信息
	 * @param roomInfoVO
	 * @return
	 * ResultMessage
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月4日 下午3:13:09
	 */
	public ResultMessage addRoomInfo(RoomInfoVO roomInfoVO){
		return rooms.addRoomInfo(roomInfoVO);
	}

	/**
	 * @Description:委托给room，更新客房信息
	 * @param roomInfoVOList
	 * @return
	 * ResultMessage
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月3日 下午9:50:41
	 */
	public ResultMessage updateHotelRoomInfo(RoomInfoVO roomInfoVO) {
		return rooms.updateHotelRoomInfo(roomInfoVO);
	}

	/**
	 * @Description:委托给Rooms，获取该酒店房间的最低价格
	 * @return
	 * double
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月4日 下午7:25:02
	 */
	public double getLowestPrice(String hotelID){
		return rooms.getLowestPrice(hotelID);
	}

	/**
	 * @Description:获取当前所选房型的剩余房间数量
	 * @param roomType
	 * @return
	 * int
	 * @exception:
	 * @author: Harvey Gong
	 * @param hotelID 
	 * @time:2016年12月4日 下午7:31:07
	 */
	public int getRemainNumOfSpecificType(String hotelID, RoomType roomType){
		return rooms.getRemainNumOfSpecificType(hotelID,roomType);
	}
	
	//获取指定酒店的城市商圈
	@Override
	public Address getHotelAddress(String hotelID){
		initHotelPO(hotelID);
		return new Address(hotelPO.getCity(), hotelPO.getCircle());
	}

	//获取指定酒店所有房型
	@Override
	public Iterator<RoomType> getRoomType(String hotelID){
		return rooms.getRoomType(hotelID);
	}

	//获取指定酒店剩余房间总数
	public int getRemainRoomNum(String hotelID) {
		return rooms.getRemainRoomNum(hotelID);
	}
	
	//获取指定房型原始价格
	public int getOriginPrice(String hotelID, RoomType roomType) {
		return rooms.getOriginPrice(hotelID,roomType);
	}

	/**
	 * @Description:入住与退房
	 */
	public ResultMessage checkIn(String hotelID, RoomType roomType, int roomNum) {
		return rooms.checkIn(hotelID,roomType,roomNum);
		
	}

	public ResultMessage checkOut(String hotelID, RoomType roomType, int roomNum) {
		return rooms.checkOut(hotelID,roomType,roomNum);
	}

	/**
	 * @Description:客户撤销未执行订单时更新此酒店的剩余房间数
	 * @param hotelID 目标酒店编号
	 * @param roomType 目标房间类型
	 * @param roomNum 此订单的房间数
	 * @return ResultMessage 是否撤销成功
	 * @author: charles
	 * @lastChangedBy: Harvey
	 * @time:2016/12/10
	 * 
	 */
	@Override
	public ResultMessage updateRemainRoomNumForUndoOrder(String hotelID, RoomType roomType, int roomNum) {
		return rooms.updateRemainRoomNumForUndoOrder(hotelID,roomType,roomNum);
	}

}