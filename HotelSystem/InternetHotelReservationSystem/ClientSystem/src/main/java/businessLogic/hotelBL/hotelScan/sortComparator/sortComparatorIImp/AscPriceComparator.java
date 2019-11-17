package businessLogic.hotelBL.hotelScan.sortComparator.sortComparatorIImp;

import java.util.Comparator;

import vo.HotelVO;

public class AscPriceComparator implements Comparator<HotelVO>{

	@Override
	public int compare(HotelVO o1, HotelVO o2) {
		return (int) (o1.minPrice-o2.minPrice);
	}
	
}
