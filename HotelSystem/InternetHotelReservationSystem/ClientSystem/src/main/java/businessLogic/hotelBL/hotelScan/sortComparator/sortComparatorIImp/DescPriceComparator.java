package businessLogic.hotelBL.hotelScan.sortComparator.sortComparatorIImp;

import java.util.Comparator;

import vo.HotelVO;

public class DescPriceComparator implements Comparator<HotelVO> {

	@Override
	public int compare(HotelVO vo1, HotelVO vo2) {
		return (int) (vo1.minPrice-vo2.minPrice);
	}

}
