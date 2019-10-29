package testHotelPromotion;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import businessLogic.impl.HotelPromotionBlServiceImpl;
import businessLogic.service.HotelPromotionBlService;
import myTest.TestClient;
import vo.BirthdayProVo;

public class TestBirthday {
	private HotelPromotionBlService test;
	
	@Before
	public void setUp() throws Exception {
		new TestClient();
		test = HotelPromotionBlServiceImpl.getInstance();
	}
	@Test
	public void testUpdate() {
		BirthdayProVo vo = new BirthdayProVo();
		vo.setDiscount(0.75);
		test.upBirthStrategy("H00000", vo);
		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2016-12-18");
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2016-11-11");

			assertEquals(0.75, test.getBirthStrategy("H00000", date1), 0.0001);
			assertEquals(1.0, test.getBirthStrategy("H00000", date2), 0.0001);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
