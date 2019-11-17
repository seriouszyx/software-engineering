package dataService.hotelDataService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import po.HotelPO;
import po.RoomInfoPO;
import utilities.enums.ResultMessage;
import utilities.enums.RoomType;

public class HotelDataService_Stub extends UnicastRemoteObject implements HotelDataService  {
	
	public HotelDataService_Stub() throws RemoteException {
		super();
	}

	@Override
	public List<HotelPO> getHotels(String city,String circle) {
		List<HotelPO> list = new ArrayList<HotelPO>();
		list.add(new HotelPO("12345678", "1天", "南京", "仙林中心", "1号", "5",
				3,"good", "allEquipment",5));
		list.add(new HotelPO("12345677", "2天", "南京", "仙林中心", "2号", "4",
				4,"good", "allEquipment",4));
		list.add(new HotelPO("12345676", "3天", "南京", "仙林中心", "3号", "3",
				5,"good", "allEquipment",4));
		list.add(new HotelPO("12345676", "4天", "北京", "五里屯", "address", "3",
				5,"good", "allEquipment",4));
		return list;
	}
	
	public HotelPO getHotelInfo(String hotelID) {
		return new HotelPO("12345678", "thisHotel", "南京", "仙林中心", "115号", "4",
				5,"good", "allEquipment",5);
	}
	
	public ResultMessage updateHotelInfo(HotelPO hotelInfoPO) {
		return ResultMessage.SUCCESS;
	}

	public ResultMessage updateHotelRoomInfo(List<RoomInfoPO> list) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public List<RoomInfoPO> getRoomInfo(String hotelID) throws RemoteException {
		List<RoomInfoPO> list = new ArrayList<RoomInfoPO>();
		list.add(new RoomInfoPO("12345678",RoomType.SINGLE_BED,1,0,100));
		list.add(new RoomInfoPO("12345678",RoomType.DOUBLE_BED,1,1,300));
		list.add(new RoomInfoPO("12345678",RoomType.TRIPLE_BED,1,0,300));
		list.add(new RoomInfoPO("12345678",RoomType.BUSINESS_SUITE,1,1,400));
		list.add(new RoomInfoPO("12345678",RoomType.PRESIDENTIAL_SUITE,1,0,500));
		return list;
	}


	@Override
	public ResultMessage addRoomInfo(RoomInfoPO roomInfoPO) throws RemoteException {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage addHotelInfo(HotelPO hotelPO) throws RemoteException {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage updateRoomInfo(RoomInfoPO roomInfoPO) throws RemoteException {
		return ResultMessage.SUCCESS;
	}

}
