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

public class TestFindByFigure {

	private HotelListService test;
	List<HotelVo> list1;
	@Before
	public void setUp() throws Exception {
		new TestClient();
		test = HotelListServiceImpl.getInstance();
		list1 = test.findByAddress("江苏", "南京", "夫子庙");
	}
	
	@Test
	public void testByStars() {
		//45213
		List<HotelVo> l1 = HotelListServiceImpl.getInstance().findByStars(0, 4, list1);
		assertEquals(4, l1.size());
		List<HotelVo> l2 = HotelListServiceImpl.getInstance().findByStars(3, 5, list1);
		assertEquals("H00010", l2.get(0).getId());
		assertEquals("H00011", l2.get(1).getId());
		assertEquals("H00007", l2.get(2).getId());
	}
	
	
	@Test
	public void testByPoint(){
		//4.6 4.8 4.4 0.0 4.1
		List<HotelVo> l1 = HotelListServiceImpl.getInstance().findByPoint(4.0, 5.0, list1);
		assertEquals(4, l1.size());
		List<HotelVo> l2 = HotelListServiceImpl.getInstance().findByPoint(0.0, 3.0, list1);
		assertEquals("H00002", l2.get(0).getId());	
	}
	
	@Test
	public void testByOringinalPrice() {
		List<HotelVo> l1 = HotelListServiceImpl.getInstance().findByOriginalPrice(RoomType.SINGLE, 0, 200, list1);
		assertEquals("H00002", l1.get(0).getId());
		assertEquals("H00024", l1.get(1).getId());
		
		List<HotelVo> l2 = HotelListServiceImpl.getInstance().findByOriginalPrice(RoomType.DOUBLE, 200, 250, list1);
		assertEquals(2, l2.size());
		
	}
	
	
	@Test
	public void testBykey(){
		List<HotelVo> l1 = HotelListServiceImpl.getInstance().findByKeyword("H00001");
		assertEquals("H00001", l1.get(0).getId());	
		
		List<HotelVo> l2 = HotelListServiceImpl.getInstance().findByKeyword("上海");
		assertEquals(6, l2.size());
	}
}
