package businessLogic.hotelBL.hotelScan.searchCriteria.searchCriteriaImpl;

import java.util.List;

import businessLogic.hotelBL.hotel.Hotel;
import businessLogic.hotelBL.hotel.HotelInfoOperation;
import businessLogic.hotelBL.hotelScan.searchCriteria.SearchCriteria;
import vo.HotelVO;

public class RemainRoomNumCriteria implements SearchCriteria {

	int remainNum;
	
	public RemainRoomNumCriteria(int remainNum) {
		this.remainNum = remainNum;
	}
	
	@Override
	public List<HotelVO> meetCriteria(List<HotelVO> hotelVOList) {
		HotelInfoOperation hotel = new Hotel();
		for(int i = 0;i<hotelVOList.size();){
			if(notHasEnoughRemainRoom(hotel.getRemainRoomNum(hotelVOList.get(i).hotelID))){
				hotelVOList.remove(i);
				continue;
			}
			i++;
		}
		return hotelVOList;
	}

	private boolean notHasEnoughRemainRoom(int hotelRemainRoomNum) {
		if(remainNum > hotelRemainRoomNum)
		{
			return true;
		}
		return false;
	}

}
