package businessLogic.hotelBL.hotelScan.sortComparator;

import java.util.Comparator;

import businessLogic.hotelBL.hotelScan.sortComparator.sortComparatorIImp.AscLevelComparator;
import businessLogic.hotelBL.hotelScan.sortComparator.sortComparatorIImp.AscPriceComparator;
import businessLogic.hotelBL.hotelScan.sortComparator.sortComparatorIImp.DescLevelComparator;
import businessLogic.hotelBL.hotelScan.sortComparator.sortComparatorIImp.DescPriceComparator;
import businessLogic.hotelBL.hotelScan.sortComparator.sortComparatorIImp.DescScoreComparator;
import utilities.enums.SortStrategy;
import vo.HotelVO;

public class SortComparatorFactory {
	Comparator<HotelVO> comparator;
	
	public SortComparatorFactory() {
	}
	
	public Comparator<HotelVO> createComparator(SortStrategy sortStrategy){
		if(sortStrategy == SortStrategy.ASCLEVEL){
			comparator = new AscLevelComparator();
		}
		else if(sortStrategy == SortStrategy.ASCPRICE){
			comparator = new AscPriceComparator();
		}
		else if(sortStrategy == SortStrategy.DESCLEVEL){
			comparator = new DescLevelComparator();
		}
		else if(sortStrategy == SortStrategy.DESCPRICE){
			comparator = new DescPriceComparator();
		}
		else if(sortStrategy == SortStrategy.DESCSCORE){
			comparator = new DescScoreComparator();
		}
		return comparator;
	}
}
