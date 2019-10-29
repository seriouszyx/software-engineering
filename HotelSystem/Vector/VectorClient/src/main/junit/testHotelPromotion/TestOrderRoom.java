package testHotelPromotion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import businessLogic.impl.HotelPromotionBlServiceImpl;
import businessLogic.service.HotelPromotionBlService;
import myTest.TestClient;
import vo.RoomPromotionVo;

public class TestOrderRoom {
	private HotelPromotionBlService test;
	
	@Before
	public void setUp() throws Exception {
		new TestClient();
		test = HotelPromotionBlServiceImpl.getInstance();
	}
	@Test
	public void testupdate() {
		RoomPromotionVo vo = new RoomPromotionVo("三间以上特惠",3 , 0.8);
		test.updateOrderRoomStrategy("H00000", vo);
		double d1 = test.getOrderRoomDiscount("H00000", 3);
		double d2 = test.getOrderRoomDiscount("H00000", 1);
		assertEquals(0.8, d1, 0.00001);
		assertEquals(1.0, d2, 0.00001);
		assertEquals(null, test.getOrderRoomStrategy("H00000", 1));
	}

}
