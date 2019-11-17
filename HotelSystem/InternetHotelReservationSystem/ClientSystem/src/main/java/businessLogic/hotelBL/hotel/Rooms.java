package businessLogic.hotelBL.hotel;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dataService.hotelDataService.HotelDataService;
import po.RoomInfoPO;
import rmi.ClientRemoteHelper;
import utilities.enums.Operation;
import utilities.enums.ResultMessage;
import utilities.enums.RoomType;
import vo.RoomInfoVO;

/**
 * @Description:对于一个酒店里面客房信息的基本操作，由Hotel委托，Rooms具体实现
 * @author:Harvey Gong
 * @time:2016年12月4日 下午7:17:05
 */
class Rooms {

	List<RoomInfoPO> roomInfoPOList;
	private HotelDataService hotelDataService;
	String hotelID;

	public Rooms() {
		hotelDataService = ClientRemoteHelper.getInstance().getHotelDataService();
	}

	/**
	 * @Description: 获得最新的酒店客房信息 
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月6日 上午11:16:21
	 */
	private void initRoomInfoPO(String hotelID) {
		try {
			roomInfoPOList = hotelDataService.getRoomInfo(hotelID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description:获得该酒店的所有客房信息
	 * @return Iterator<RoomInfoVO>
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月4日 上午10:59:13
	 */
	public Iterator<RoomInfoVO> getRoomInfo(String hotelID) {
		initRoomInfoPO(hotelID);
		List<RoomInfoVO> roomInfoVOList = new ArrayList<RoomInfoVO>();
		List<RoomInfoPO> roomInfoPOList = null;
		try {
			roomInfoPOList = hotelDataService.getRoomInfo(hotelID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		for (RoomInfoPO roomInfoPO : roomInfoPOList) {
			roomInfoVOList.add(new RoomInfoVO(roomInfoPO));
		}
		return roomInfoVOList.iterator();
	}

	/**
	 * @Description:为该酒店添加新的客房信息
	 * @param roomInfoVO
	 * @return ResultMessage
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月4日 上午11:16:09
	 */
	public ResultMessage addRoomInfo(RoomInfoVO roomInfoVO) {

		try {
			//判断该类型的客房是否已存在
			if(!roomTypeExist(roomInfoVO.hotelID,roomInfoVO.roomType)){
				hotelDataService.addRoomInfo(new RoomInfoPO(roomInfoVO));
				return ResultMessage.SUCCESS;
			}
			else{
				return ResultMessage.FAIL;
			}
		} catch (RemoteException e) {
			return ResultMessage.FAIL;
		}
	}

	/**
	 * @Description:更新一条hotel的客房信息
	 * @param roomInfoVO
	 * @return ResultMessage
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月4日 上午11:03:24
	 */
	public ResultMessage updateHotelRoomInfo(RoomInfoVO roomInfoVO) {

		try {
			
			initRoomInfoPO(roomInfoVO.hotelID);
			RoomInfoPO po = roomInfoPOList.get(findPO(roomInfoVO.roomType));
			int roomNum = po.getRoomNum();
			// 当房间总数被修改时，需要更新一下剩余房间数量
			if(roomNum != roomInfoVO.roomNum){
				int remainRoomNum = po.getRemainNum();
				roomInfoVO.remainNum = roomInfoVO.roomNum-(roomNum - remainRoomNum);
			}

			hotelDataService.updateRoomInfo(new RoomInfoPO(roomInfoVO));
			
			return ResultMessage.SUCCESS;
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.FAIL;
		}
	}
	
	/**
	 * @Description:获得该酒店所有房间类型
	 * @return Iterator<String>
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月4日 下午6:53:53
	 */
	public Iterator<RoomType> getRoomType(String hotelID) {
		initRoomInfoPO(hotelID);
		List<RoomType> allRoomType = new ArrayList<RoomType>();
		for (int i = 0; i < roomInfoPOList.size(); i++) {
			allRoomType.add(roomInfoPOList.get(i).getRoomType());
		}
		return allRoomType.iterator();
	}

	/**
	 * @Description:获取当前所选房间类型的剩余房间数量
	 * @param roomType
	 * @return int
	 * @exception:
	 * @author: Harvey Gong
	 * @param hotelID
	 * @time:2016年12月4日 下午7:20:02
	 */
	public int getRemainNumOfSpecificType(String hotelID, RoomType roomType) {
		if(roomType == null){
			return 0;
		}
		else
		{
			initRoomInfoPO(hotelID);
			return roomInfoPOList.get(findPO(roomType)).getRemainNum();			
		}
	}

	/**
	 * @Description:根据酒店id获取该酒店所有剩余房间数量
	 * @param hotelID
	 * @return
	 * int
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 下午5:03:56
	 */
	public int getRemainRoomNum(String hotelID) {
		initRoomInfoPO(hotelID);
		int remainRoomNum = 0;
		for (int i = 0; i < roomInfoPOList.size(); i++) {
			remainRoomNum = remainRoomNum + roomInfoPOList.get(i).getRemainNum();
		}
		return remainRoomNum;
	}

	/**
	 * @Description:当入住或退房时，调用这两个方法，更新该房型的剩余房间数量， 线上线下均调用这两个方法
	 * @param hotelID
	 * @param roomName
	 * @param roomNum
	 * @return ResultMessage
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月8日 上午12:42:13
	 */
	public ResultMessage checkIn(String hotelID, RoomType roomType, int roomNum) {
		return updateRemainRoomNum(hotelID, roomType, roomNum, Operation.CHECK_IN);
	}

	public ResultMessage checkOut(String hotelID, RoomType roomType, int roomNum) {
		return updateRemainRoomNum(hotelID, roomType, roomNum, Operation.CHECK_OUT);
	}

	/**
	 * @Description:提供对外的接口，客户撤销订单时调用，更新该房型的剩余房间数量
	 * @param hotelID
	 * @param roomType
	 * @param roomNum
	 * @return
	 * ResultMessage
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 下午5:08:26
	 */
	public ResultMessage updateRemainRoomNumForUndoOrder(String hotelID, RoomType roomType, int roomNum) {
		return updateRemainRoomNum(hotelID, roomType, roomNum, Operation.UNDO_ORDER);
	}
	/**
	 * @Description:获取该酒店最低价格
	 * @return double
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年12月4日 下午2:11:12
	 */
	public double getLowestPrice(String hotelID) {
		initRoomInfoPO(hotelID);
		double min = roomInfoPOList.get(0).getPrice();
		for (int i = 1; i < roomInfoPOList.size(); i++) {
			if (min > roomInfoPOList.get(i).getPrice()) {
				min = roomInfoPOList.get(i).getPrice();
			}
		}
		return min;
	}
	
	/**
	 * @Description:根据酒店id获取该房型的原始价格
	 * @param hotelID
	 * @param roomType
	 * @return
	 * int
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 下午5:08:51
	 */
	public int getOriginPrice(String hotelID, RoomType roomType) {
		initRoomInfoPO(hotelID);
		return roomInfoPOList.get(findPO(roomType)).getPrice();
	}
	
	/**
	 * @Description:更新剩余客房的数量
	 * @param roomType
	 * @param operationedNum
	 * @param operation
	 * @return ResultMessage
	 * @exception:
	 * @author: Harvey Gong
	 * @param roomName
	 * @time:2016年12月4日 下午8:05:19
	 */
	private ResultMessage updateRemainRoomNum(String hotelID, RoomType roomType, int operationedNum,
			Operation operation) {

		initRoomInfoPO(hotelID);
		RoomInfoPO po = roomInfoPOList.get(findPO(roomType));
		if (operation == Operation.CHECK_IN) {
			if((po.getRemainNum()-operationedNum)<0){
				//当入住房间数多于剩余房间数的时候，则返回错误信息
				return ResultMessage.FAIL;
			}
			else
			{
				po.setRemainNum(po.getRemainNum() - operationedNum);	
			}
		} else {
			if((po.getRemainNum()+operationedNum)>po.getRoomNum()){
				return ResultMessage.FAIL;
			}
			else
			{
				//当退房房间数与剩余房间数多余总房间数的时候，则返回错误信息
				po.setRemainNum(po.getRemainNum() + operationedNum);	
			}
		}
		return updateHotelRoomInfo(new RoomInfoVO(po));
	}
	
	/**
	 * @Description:根据房间类型找到该po在列表中的位置
	 * @param roomType
	 * @return
	 * int
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 下午5:07:20
	 */
	private int findPO(RoomType roomType) {
		for (int i = 0; i < roomInfoPOList.size(); i++) {
			if (roomInfoPOList.get(i).getRoomType() == roomType) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * @Description:判断添加的房间类型是否已存在
	 * @param roomType
	 * @return
	 * boolean
	 * @author: Harvey Gong
	 * @param hotelID 
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 下午4:56:49
	 */
	private boolean roomTypeExist(String hotelID, RoomType roomType) {
		Iterator<RoomType> roomTypes = getRoomType(hotelID);
		while(roomTypes.hasNext()){
			if(roomTypes.next()==roomType){
				return true;
			}
		}
		return false;
	}
}
