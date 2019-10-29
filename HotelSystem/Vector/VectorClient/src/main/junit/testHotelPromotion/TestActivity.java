package testHotelPromotion;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import businessLogic.impl.HotelPromotionBlServiceImpl;
import businessLogic.service.HotelPromotionBlService;
import common.ResultMessage;
import myTest.TestClient;
import vo.ActivityPromotionVo;

public class TestActivity {
	private HotelPromotionBlService test;
	
	@Before
	public void setUp() throws Exception {
		new TestClient();
		test = HotelPromotionBlServiceImpl.getInstance();
	}
	
	
	@Test
	public void testAdd() {
    	try {
    		//test0
			Date date1 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-11-11-00");
			Date date2 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-12-31-24");
			
			ActivityPromotionVo vo = new ActivityPromotionVo("大促销", date1, date2, 0.8);
			test.addActivityStrategy("H00000", vo);
			
			
			// error test1
			date1 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-11-11-00");
		    date2 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-12-31-24");
		    vo = new ActivityPromotionVo("双十一", date1, date2, 1.85);
			ResultMessage er1 = test.addActivityStrategy("H00000", vo);
			assertEquals(ResultMessage.FAIL,er1);
			
			// error test2
			date1 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-12-11-00");
		    date2 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-11-31-24");
			vo = new ActivityPromotionVo("双十一", date1, date2, 0.85);
			er1 = test.addActivityStrategy("H00000", vo);
			assertEquals(ResultMessage.FAIL,er1);
			
			//error test3
			date1 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("");
		    date2 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("");
		    vo = new ActivityPromotionVo("", date1, date2, 0);
			er1 = test.addActivityStrategy("H00000", vo);
			assertEquals(ResultMessage.FAIL,er1);
			
			//test4
			Date date3 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-12-11-00");
			Date date4 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-12-25-24");
			
			ActivityPromotionVo vo1 = new ActivityPromotionVo("小促销", date3, date4, 0.95);
			test.addActivityStrategy("H00000", vo1);
			
			 date3 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-12-11-00");
			 date4 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-12-12-00");
				
			vo1 = new ActivityPromotionVo("双十一", date3, date4, 0.7);
			test.addActivityStrategy("H00000", vo1);
				
			
			List<ActivityPromotionVo> list = test.getCurrentActStrategy("H00000");
			assertEquals(3, list.size());
			assertEquals("小促销", list.get(1).getPromotionName());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate() {
		ActivityPromotionVo vo = test.getCurrentActStrategy("H00000").get(1);
		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-12-26-12");
			vo.setEndDate(date1);
			test.upActivityStrategy("H00000", vo);
			assertEquals(date1, vo.getEndDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelete() {
		ActivityPromotionVo vo =test.getCurrentActStrategy("H00000").get(0);
		test.delActivityStrategy("H00000", vo);
		List<ActivityPromotionVo> list = test.getCurrentActStrategy("H00000");
		assertEquals(1, list.size());
	}

}
