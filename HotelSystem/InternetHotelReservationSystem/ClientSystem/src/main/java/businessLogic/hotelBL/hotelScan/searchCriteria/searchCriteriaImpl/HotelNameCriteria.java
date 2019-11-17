package businessLogic.hotelBL.hotelScan.searchCriteria.searchCriteriaImpl;

import java.util.List;

import businessLogic.hotelBL.hotelScan.searchCriteria.SearchCriteria;
import vo.HotelVO;

public class HotelNameCriteria implements SearchCriteria {

	private String keyHotelName;
	
	public HotelNameCriteria(String hotelName) {
		this.keyHotelName = hotelName;
	}
	
	/**
	 * @Description:如果酒店姓名中不含这个关键字，就在列表里面移除该酒店
	 * @param hotelGeneralVOList
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月6日 下午3:16:58
	 */
	@Override
	public List<HotelVO> meetCriteria(List<HotelVO> hotelGeneralVOList) {
		for(int i = 0;i < hotelGeneralVOList.size();){
			if(!hotelGeneralVOList.get(i).hotelName.contains(keyHotelName)){
				hotelGeneralVOList.remove(i);
				continue;
			}
			i++;
		}
		return hotelGeneralVOList;
	}

}
