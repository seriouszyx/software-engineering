package businessLogic.hotelBL.hotelScan;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import utilities.enums.SearchCriteriaType;
import utilities.enums.SortStrategy;
import vo.HotelVO;
import vo.SearchCriteriaVO;

/**
 * @Description:测试浏览酒店、搜索酒店的排序和搜索算法
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月6日 上午9:34:34
 */
public class HotelScanTest {

	HotelScan hotelScan;
	List<SearchCriteriaType> searchCriteriaTypes;
	SearchCriteriaVO vo;
	Iterator<HotelVO> itr;
	HotelVO vo1;
	HotelVO vo2;
	HotelVO vo3;
	
	@Before
	public void setUp() throws Exception {
		hotelScan = new HotelScan("12345678");
		hotelScan.getHotels("南京", "仙林");
		vo = new SearchCriteriaVO();
		searchCriteriaTypes = new ArrayList<SearchCriteriaType>();
	}

	@Test
	public void testSortHotels() {
		//按等级升序排序
		itr = hotelScan.sortHotels(SortStrategy.ASCLEVEL);
		vo1 = itr.next();
		vo2 = itr.next();
		vo3 = itr.next();
		assertEquals("12345676",vo1.hotelID);
		assertEquals("12345677",vo2.hotelID);
		assertEquals("12345678",vo3.hotelID);
		//按等级降序排序
		itr = hotelScan.sortHotels(SortStrategy.DESCLEVEL);
		vo1 = itr.next();
		vo2 = itr.next();
		vo3 = itr.next();
		assertEquals("12345678",vo1.hotelID);
		assertEquals("12345677",vo2.hotelID);
		assertEquals("12345676",vo3.hotelID);
		
		
		
		//按评分降序排序
		itr = hotelScan.sortHotels(SortStrategy.DESCSCORE);
		vo1 = itr.next();
		vo2 = itr.next();
		vo3 = itr.next();
		assertEquals("12345676",vo1.hotelID);
		assertEquals("12345677",vo2.hotelID);
		assertEquals("12345678",vo3.hotelID);
	}

	@Test
	public void testSearchHotelsOfNullCriteria() {
		
		itr = hotelScan.searchHotels(searchCriteriaTypes,vo);
		vo1 = itr.next();
		vo2 = itr.next();
		vo3 = itr.next();
		assertEquals("12345678",vo1.hotelID);
		assertEquals("12345677",vo2.hotelID);
		assertEquals("12345676",vo3.hotelID);
	}
	
	@Test
	public void testSearchHotelsOfLevelSpanCriteria() {
		searchCriteriaTypes.add(SearchCriteriaType.LEVEL_SPAN);
		vo.minLevel = 4;
		vo.maxLevel = 5;
		itr = hotelScan.searchHotels(searchCriteriaTypes,vo);
		vo1 = itr.next();
		vo2 = itr.next();
		assertEquals("12345678",vo1.hotelID);
		assertEquals("12345677",vo2.hotelID);
	}
	
	//TODO testSearchHotelsOfOriginCriteria() 价格相同 无法测试
	
}
