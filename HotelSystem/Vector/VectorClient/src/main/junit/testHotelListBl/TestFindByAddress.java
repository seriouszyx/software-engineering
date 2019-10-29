package testHotelListBl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import businessLogic.impl.HotelListServiceImpl;
import businessLogic.service.HotelListService;
import myTest.TestClient;
import vo.HotelVo;

public class TestFindByAddress {
	private HotelListService test;
	
	@Before
	public void setUp() throws Exception {
		new TestClient();
		test = HotelListServiceImpl.getInstance();
	}
	
	@Test
	public void test() {
		List<HotelVo> list0 = test.findByAddress("江苏", "南京", "");
		assertEquals(0, list0.size());
		
		
		List<HotelVo> list_1 = test.findByAddress("江苏", "南京", "新街口");
		assertEquals(2,  list_1.size());
		assertEquals("H00021",  list_1.get(0).getId());
		assertEquals("H00023",  list_1.get(1).getId());
		
		List<HotelVo> list1 = test.findByAddress("江苏", "南京", "夫子庙");
		assertEquals(5, list1.size());
		assertEquals("H00010", list1.get(0).getId());
		assertEquals("H00011", list1.get(1).getId());
		assertEquals("H00007", list1.get(2).getId());
		assertEquals("H00002", list1.get(3).getId());
		assertEquals("H00024", list1.get(4).getId());
		
		
		
		List<HotelVo> list2 = test.findByAddress("上海", "虹口区", "虹口足球场地区");
		assertEquals(2, list2.size());
		assertEquals("H00004", list2.get(0).getId());
		assertEquals("H00023", list2.get(1).getId());
		
		List<HotelVo> list3 = test.findByAddress("江苏", "苏州", "太湖风景区");
		assertEquals(1, list3.size());
		assertEquals("H00008", list3.get(0).getId());
	}

}
