package testHotelListBl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import businessLogic.impl.HotelListServiceImpl;
import businessLogic.service.HotelListService;
import common.RoomType;
import myTest.TestClient;
import vo.HotelVo;

public class TestSort {

	private HotelListService test;
	List<HotelVo> list1;
	
	@Before
	public void setUp() throws Exception {
		new TestClient();
		test = HotelListServiceImpl.getInstance();
		list1 = test.findByAddress("江苏", "南京", "夫子庙");
	}
	
	@Test
	public void testSortName() {
		List<HotelVo> l1 = test.sortByName(list1, true);
		assertEquals("南京乌衣巷客栈夫子庙店", l1.get(0).getHotelName());
		assertEquals("南京夜泊秦淮君亭酒店", l1.get(1).getHotelName());
		assertEquals("如家快捷酒店", l1.get(2).getHotelName());
		assertEquals("桔子酒店", l1.get(3).getHotelName());
		assertEquals("都市客栈", l1.get(4).getHotelName());
		
		l1 = test.sortByName(list1, false);
		assertEquals("都市客栈", l1.get(0).getHotelName());
	}

	@Test
	public void testSortStar() {
		List<HotelVo> l1 = test.sortByStar(list1, true);
		assertEquals("南京乌衣巷客栈夫子庙店", l1.get(0).getHotelName());
		assertEquals("如家快捷酒店", l1.get(1).getHotelName());
		assertEquals("都市客栈", l1.get(2).getHotelName());
		assertEquals("桔子酒店", l1.get(3).getHotelName());
		assertEquals("南京夜泊秦淮君亭酒店", l1.get(4).getHotelName());
		
		l1 = test.sortByStar(list1, false);
		assertEquals("南京夜泊秦淮君亭酒店", l1.get(0).getHotelName());
	}
	
	@Test
	public void testSortPoint() {
		List<HotelVo> l1 = test.sortByPoint(list1, true);
		assertEquals("南京乌衣巷客栈夫子庙店", l1.get(0).getHotelName());
		assertEquals("都市客栈", l1.get(1).getHotelName());
		assertEquals("如家快捷酒店", l1.get(2).getHotelName());
		assertEquals("桔子酒店", l1.get(3).getHotelName());
		assertEquals("南京夜泊秦淮君亭酒店", l1.get(4).getHotelName());
		
		l1 = test.sortByPoint(list1, false);
		assertEquals("南京夜泊秦淮君亭酒店", l1.get(0).getHotelName());
	}
	
	
	@Test
	public void testSortPrice(){
		List<HotelVo> l1 = test.findByRoomType(RoomType.DOUBLE, list1);
		
		l1 = test.sortByPrice(l1, RoomType.DOUBLE, true);
		assertEquals("都市客栈", l1.get(0).getHotelName());
		assertEquals("如家快捷酒店", l1.get(1).getHotelName());
		assertEquals("南京乌衣巷客栈夫子庙店", l1.get(2).getHotelName());
		assertEquals("桔子酒店", l1.get(3).getHotelName());
		assertEquals("南京夜泊秦淮君亭酒店", l1.get(4).getHotelName());
		
		l1 = test.sortByPrice(list1, RoomType.DOUBLE, false);
		assertEquals("南京夜泊秦淮君亭酒店", l1.get(0).getHotelName());
		
		List<HotelVo> l2 = test.findByRoomType(RoomType.SINGLE, list1);
		l2 = test.sortByPrice(l2, RoomType.SINGLE, true);
		assertEquals(2, l2.size());
		assertEquals("南京乌衣巷客栈夫子庙店", l2.get(0).getHotelName());
		assertEquals("如家快捷酒店", l2.get(1).getHotelName());
	}
}
