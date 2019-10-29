package testHotelBl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import businessLogic.impl.HotelBlServiceImpl;
import businessLogic.service.HotelBlService;
import common.ResultMessage;
import common.RoomType;
import myTest.TestClient;
import vo.HotelVo;

public class TestHotel {
	private HotelBlService test;
	private HotelVo vo;
	
	@Before
	public void setUp() throws Exception {
		new TestClient();
		test = HotelBlServiceImpl.getInstance();
	}
	
	
	@Test
	public void testGetComment() {
		vo = test.getHotelVo("H00000"); 
		List<String> l1 = vo.getCommentList();
		assertEquals(4, l1.size());
	}
	
	@Test
	public void testGiveComment() {
		vo = test.getHotelVo("H00000");
		ResultMessage msg = HotelBlServiceImpl.getInstance().comment("2016110891541","好评！",5.0);
		assertEquals(ResultMessage.SUCCEED,msg);
		
		msg = HotelBlServiceImpl.getInstance().comment("2016110891541","一般",4.0);
		assertEquals(ResultMessage.SUCCEED,msg);
		
		msg = HotelBlServiceImpl.getInstance().comment("2016110891541","",5.0);
		assertEquals(ResultMessage.SUCCEED,msg);
		
		
		List<String> l1 = vo.getCommentList();
		assertEquals(5, l1.size());
		assertEquals("好评！",l1.get(3));
	}

	@Test
	public void testReadyRoom() {
		vo = test.getHotelVo("H00000");
		int x = test.getReadyRoom(RoomType.FAMILY);
		assertEquals(8, x);
		
		test.bookRoom(RoomType.FAMILY, 3);
		x = test.getReadyRoom(RoomType.FAMILY);
		assertEquals(5, x);
		
		test.checkoutRoom(RoomType.FAMILY, 2);
		x = test.getReadyRoom(RoomType.FAMILY);
		assertEquals(7, x);
	}
	
	@Test
	public void testinitializeRoom(){
		test.initializeRoom("H00000", RoomType.DOUBLE, 25, 140);
		vo = test.getHotelVo("H00000");
		assertEquals(140, vo.getOriginPrice(RoomType.DOUBLE));
	}
	
	@Test
	public void testUpdateBasicInfo(){
		vo = test.getHotelVo("H00000");
		vo.setHotelTel("85991165");
		test.updateBasicInfo(vo);
		
		vo = test.getHotelVo("H00000");
		assertEquals("85991165", vo.getHotelTel());
		
		vo.setHotelTel("#123432");
		vo = test.getHotelVo("H00000");
		assertEquals("85991165", vo.getHotelTel());
	}
}
