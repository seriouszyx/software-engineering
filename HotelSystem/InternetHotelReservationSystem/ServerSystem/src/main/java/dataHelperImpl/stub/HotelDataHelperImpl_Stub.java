package dataHelperImpl.stub;

import java.util.ArrayList;
import java.util.List;

import dataHelper.HotelDataHelper;
import po.HotelPO;
import utilities.enums.ResultMessage;

public class HotelDataHelperImpl_Stub implements HotelDataHelper{

	@Override
	public List<HotelPO> getHotels(String city, String circle) {
		List<HotelPO> list = new ArrayList<HotelPO>();
		list.add(new HotelPO("12345678", "Hotel1", "NanJing", "center1", "address1", "4",
				5,"good", "allEquipment",5));
		list.add(new HotelPO("12345679", "Hotel2", "NanJing", "center2", "address2", "4",
				5,"good", "allEquipment",5));
		return list;
	}

	@Override
	public HotelPO getHotelInfo(String hotelID) {
		return new HotelPO("12345670", "Hotel1", "NanJing", "center1", "address1", "4",
				5,"good", "allEquipment",5);
	}

	@Override
	public ResultMessage updateHotelInfo(HotelPO hotelPO) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage addHotelInfo(HotelPO hotelPO) {
		return ResultMessage.SUCCESS;
	}

}
