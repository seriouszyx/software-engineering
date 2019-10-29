package testHotelListBl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import businessLogic.impl.HotelListServiceImpl;
import businessLogic.service.HotelListService;
import myTest.TestClient;

public class TestAddressList {
	
	private HotelListService test;
	
	@Before
	public void setUp() throws Exception {
		new TestClient();
		test = HotelListServiceImpl.getInstance();
	}
	
	@Test
	public void testProvince() {
		List<String> list = test.getProvinceList();
		assertEquals("上海", list.get(0));
		assertEquals("江苏", list.get(1));
		assertEquals("浙江", list.get(2));
	}
	
	@Test
	public void testCity() {
		List<String> list1 = test.getCityList("江苏");
		assertEquals(2, list1.size());
		assertEquals(true, list1.contains("南京"));
		assertEquals(true, list1.contains("苏州"));
		
		List<String> list2 = test.getCityList("上海");
		assertEquals(4, list2.size());
		assertEquals(true, list2.contains("徐汇区"));
		assertEquals(true, list2.contains("浦东新区"));
		assertEquals(true, list2.contains("黄浦区"));
		assertEquals(true, list2.contains("虹口区"));
		
		List<String> list3 = test.getCityList("浙江");
		assertEquals(2, list3.size());
		assertEquals(true, list3.contains("杭州"));
		assertEquals(true, list3.contains("温州"));
	}

	@Test
	public void testBusiness() {
		
		List<String> list1 = test.getBusinessList("江苏", "南京");
		assertEquals(3, list1.size());
		assertEquals(true, list1.contains("夫子庙"));
		assertEquals(true, list1.contains("仙林学府区"));
		assertEquals(true, list1.contains("新街口"));
		
		List<String> list2 = test.getBusinessList("浙江", "杭州");
		assertEquals(5, list2.size());
		assertEquals(true, list2.contains("武林广场"));
		assertEquals(true, list2.contains("西溪湿地"));
		assertEquals(true, list2.contains("钱江新城"));
		
		List<String> list3 = test.getBusinessList("上海", "黄浦区");
		assertEquals(3, list3.size());
		//assertEquals(true, list3.contains("外滩地区"));
		
		//err test
		List<String> list4 = test.getBusinessList("上海", "");
		assertEquals(null, list4.size());
		
		List<String> list5 = test.getBusinessList("上海", "###");
		assertEquals(null, list5.size());
		
		List<String> list6 = test.getBusinessList("上海", "外滩地区");
		assertEquals(0, list6.size());
	}
}
