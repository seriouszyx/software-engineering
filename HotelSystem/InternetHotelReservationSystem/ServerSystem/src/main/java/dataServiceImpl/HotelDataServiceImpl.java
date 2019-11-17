package dataServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dataHelper.HotelDataHelper;
import dataHelper.RoomDataHelper;
import dataHelperImpl.HotelDataHelperImpl;
import dataHelperImpl.RoomDataHelperImpl;
import dataService.hotelDataService.HotelDataService;
import po.HotelPO;
import po.RoomInfoPO;
import utilities.enums.ResultMessage;
import utilities.enums.RoomType;

/**
 * @Description：关于酒店信息的相关操作包括：
 * 获取酒店基本信息、更新酒店基本信息、添加酒店基本信息、获取所有位于指定城市商圈的酒店
 * 以及增删改查客房信息
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月5日 上午12:42:53
 */
public class HotelDataServiceImpl extends UnicastRemoteObject implements HotelDataService{
	
	private static final long serialVersionUID = 7735555578683547312L;
	
	private RoomDataHelper roomDataHelper;
	private HotelDataHelper hotelDataHelper;
	
	public HotelDataServiceImpl() throws RemoteException {
		super();
		roomDataHelper = new RoomDataHelperImpl();
		hotelDataHelper = new HotelDataHelperImpl();
	}

	@Override
	public HotelPO getHotelInfo(String hotelID) throws RemoteException {
		return hotelDataHelper.getHotelInfo(hotelID);
	}

	@Override
	public ResultMessage updateHotelInfo(HotelPO hotelPO) throws RemoteException {
		return hotelDataHelper.updateHotelInfo(hotelPO);
	}
	
	@Override
	public ResultMessage addHotelInfo(HotelPO hotelPO) throws RemoteException {
		return hotelDataHelper.addHotelInfo(hotelPO);
	}

	@Override
	public List<HotelPO> getHotels(String city, String circle) throws RemoteException {
		return hotelDataHelper.getHotels(city, circle);
	}
	
	/**
	 * @Description:调用roomDataHelper的方法，增删改查roominfo
	 * @throws RemoteException
	 * @author: Harvey Gong
	 * @time:2016年12月4日 下午2:02:58
	 */
	@Override
	public List<RoomInfoPO> getRoomInfo(String hotelID) throws RemoteException {
		return roomDataHelper.getRoomInfo(hotelID);
	}

	@Override
	public ResultMessage updateRoomInfo(RoomInfoPO roomInfoPO) throws RemoteException {
		return roomDataHelper.updateRoomInfo(roomInfoPO);
	}

	@Override
	public ResultMessage addRoomInfo(RoomInfoPO roomInfoPO) throws RemoteException {
		return roomDataHelper.addRoomInfo(roomInfoPO);
	}

}
