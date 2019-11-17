package businessLogic.hotelBL.hotelScan.searchCriteria.searchCriteriaImpl;

import java.util.List;

import businessLogic.hotelBL.hotelScan.searchCriteria.SearchCriteria;
import vo.HotelVO;

public class NullCriteria implements SearchCriteria {

	/**
	 * @Description:没有任何搜索条件，不做任何操作，直接返回传进来的list
	 * @param hotelGeneralVOList
	 * @return hotelGeneralVOList
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年11月29日 下午7:00:16
	 */
	public List<HotelVO> meetCriteria(List<HotelVO> hotelVOList) {
		return hotelVOList;
	}
	
	public void print(){
		System.out.println();
	}

}
