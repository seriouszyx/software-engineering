package testMarketPromotion;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import businessLogic.impl.MarketPromotionBlServiceImpl;
import businessLogic.service.MarketPromotionBlService;
import common.ResultMessage;
import myTest.TestClient;
import vo.ActivityPromotionVo;

public class TestActivity {

	private MarketPromotionBlService test;
	
	@Before
	public void setUp() throws Exception {
		new TestClient();
		test = MarketPromotionBlServiceImpl.getInstance();
	}
	
	@Test
	public void testAdd() {
    	try {
    		//succeed test 0
			Date date1 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-11-11-00");
			Date date2 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-12-31-24");
			
			ActivityPromotionVo vo = new ActivityPromotionVo("双十一", date1, date2, 0.85);
			test.addActivityStrategy(vo);
			
			//error test0
			date1 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("");
		    date2 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("");
		    vo = new ActivityPromotionVo("", date1, date2, 0);
		    ResultMessage er1 = test.addActivityStrategy( vo);
			assertEquals(ResultMessage.FAIL,er1);
			
			// error test1
			date1 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-11-11-00");
		    date2 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-12-31-24");
		    vo = new ActivityPromotionVo("双十一", date1, date2, 1.85);
		    er1 = test.addActivityStrategy( vo);
			assertEquals(ResultMessage.FAIL,er1);
			
			// error test2
			date1 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-12-11-00");
		    date2 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-11-31-24");
			vo = new ActivityPromotionVo("双十一", date1, date2, 0.85);
			er1 = test.addActivityStrategy( vo);
			assertEquals(ResultMessage.FAIL,er1);
			
			// error test3
			date1 = null;
		    date2 = null;
		    vo = new ActivityPromotionVo("双十一", date1, date2, 1.85);
			er1 = test.addActivityStrategy( vo);
			assertEquals(ResultMessage.FAIL,er1);
			
			//test4
			
			Date date3 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-05-11-00");
			Date date4 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-05-07-00");
			
			ActivityPromotionVo vo1 = new ActivityPromotionVo("大促销", date3, date4, 0.8);
			test.addActivityStrategy(vo1);
			
			List<ActivityPromotionVo> list = test.getCurrentActStrategy();
			assertEquals(2, list.size());
			assertEquals("大促销", list.get(1).getPromotionName());
			
			//test5
			date3 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-08-11-00");
			date4 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-08-19-00");
			
			vo1 = new ActivityPromotionVo("小促销", date3, date4, 0.95);
			test.addActivityStrategy(vo1);
			
			List<ActivityPromotionVo> list2 = test.getCurrentActStrategy();
			assertEquals(3, list2.size());
			assertEquals("小促销", list2.get(3).getPromotionName());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate() {
		ActivityPromotionVo vo = test.getCurrentActStrategy().get(1);
		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd-HH").parse("2016-12-26-12");
			vo.setEndDate(date1);
			test.upActivityStrategy(vo);
			assertEquals(date1, vo.getEndDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelete() {
		ActivityPromotionVo vo =test.getCurrentActStrategy().get(0);
		test.delActivityStrategy(vo);
		List<ActivityPromotionVo> list = test.getCurrentActStrategy();
		assertEquals(1, list.size());
	}

}
