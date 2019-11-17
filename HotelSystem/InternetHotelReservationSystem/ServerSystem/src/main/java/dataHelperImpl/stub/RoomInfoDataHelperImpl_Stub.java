package dataHelperImpl.stub;

import java.util.ArrayList;
import java.util.List;

import dataHelper.RoomDataHelper;
import po.RoomInfoPO;
import utilities.enums.ResultMessage;
import utilities.enums.RoomType;

/**
 * @Description:RoomInfoDataHelperImpl的桩，供测试使用
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月5日 下午10:54:07
 */
public class RoomInfoDataHelperImpl_Stub implements RoomDataHelper{

	@Override
	public List<RoomInfoPO> getRoomInfo(String hotelID) {
		List<RoomInfoPO> list = new ArrayList<RoomInfoPO>();
		list.add(new RoomInfoPO("12345678",RoomType.SINGLE_BED, 10, 10, 100));
		list.add(new RoomInfoPO("12345678",RoomType.DOUBLE_BED, 10, 10, 150));
		return list;
	}

	@Override
	public ResultMessage updateRoomInfo(RoomInfoPO roomInfoPO) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage addRoomInfo(RoomInfoPO roomInfoPO) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage deleteRoomInfo(String hotelID,RoomType roomType) {
		return ResultMessage.SUCCESS;
	}

}
