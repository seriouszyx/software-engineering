package testHotelPromotion;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import businessLogic.impl.HotelPromotionBlServiceImpl;
import businessLogic.service.HotelPromotionBlService;
import myTest.TestClient;
import vo.CompanyProVo;

public class TestCooperation {
	private HotelPromotionBlService test;
	
	@Before
	public void setUp() throws Exception {
		new TestClient();
		test = HotelPromotionBlServiceImpl.getInstance();
	}
	
	@Test
	public void testupdate() {
		List<String> company = new ArrayList<String>();
		company.add("E00001"); company.add("E00002");
		CompanyProVo vo = new CompanyProVo(0.9, company);
		test.updateCooperationStrategy("H00000", vo);
		double d1 = test.getCooperationStrategy("H00000", "E00001");
		double d2 = test.getCooperationStrategy("H00000", "E00005");
		
		assertEquals(0.9, d1, 0.0001);
		assertEquals(1.0, d2, 0.0001);
	}

	
}
