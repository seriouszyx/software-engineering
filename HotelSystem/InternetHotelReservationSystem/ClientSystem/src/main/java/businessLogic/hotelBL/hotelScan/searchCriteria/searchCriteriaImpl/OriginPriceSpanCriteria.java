package businessLogic.hotelBL.hotelScan.searchCriteria.searchCriteriaImpl;

import java.util.List;

import businessLogic.hotelBL.hotelScan.searchCriteria.SearchCriteria;
import vo.HotelVO;

public class OriginPriceSpanCriteria implements SearchCriteria {

	double minPrice;
	double maxPrice;
	
	public OriginPriceSpanCriteria(double minPrice2,double maxPrice2) {
		this.minPrice = minPrice2;
		this.maxPrice = maxPrice2;
	}
	
	
	/**
	 * @Description:筛选出位在价格区间内的hotel
	 * @param hotelGeneralVOList
	 * @return
	 * @exception:
	 * @author: Harvey Gong
	 * @time:2016年11月29日 下午7:13:42
	 */
	@Override
	public List<HotelVO> meetCriteria(List<HotelVO> hotelVOList) {
		for(int i = 0;i<hotelVOList.size();){
			if(notInPriceSpan(hotelVOList.get(i).minPrice)){
				hotelVOList.remove(i);
				continue;
			}
			i++;
		}
		return hotelVOList;
	}
	
	private boolean notInPriceSpan(double price) {
		if(price<minPrice||price>maxPrice){
			return true;
		}
		else
		{
			return false;
		}
	}

}
