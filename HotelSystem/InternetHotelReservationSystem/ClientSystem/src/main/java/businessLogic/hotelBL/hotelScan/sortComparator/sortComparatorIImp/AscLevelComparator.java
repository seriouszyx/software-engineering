package businessLogic.hotelBL.hotelScan.sortComparator.sortComparatorIImp;

import java.util.Comparator;

import vo.HotelVO;

public class AscLevelComparator implements Comparator<HotelVO> {

	@Override
	public int compare(HotelVO vo1, HotelVO vo2) {
		return new Integer(vo1.level)-new Integer(vo2.level);
	}


}
