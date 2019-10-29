package testMarketPromotion;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import businessLogic.impl.MarketPromotionBlServiceImpl;
import businessLogic.service.MarketPromotionBlService;
import common.ResultMessage;
import myTest.TestClient;
import vo.BusinessProVo;

public class TestBusiness {

	private MarketPromotionBlService test;
	
	@Before
	public void setUp() throws Exception {
		new TestClient();
		test = MarketPromotionBlServiceImpl.getInstance();
	}
	
	@Test
	public void testAdd() {
		BusinessProVo vo1 = new BusinessProVo("夫子庙", 0.7);
		BusinessProVo vo2 = new BusinessProVo("新街口", 0.8);
		test.addBusinessStrategy(vo1);
		test.addBusinessStrategy(vo2);
		assertEquals(0.7, test.getBusinessDiscount("夫子庙"), 0.001);
		
		//error test
		vo1 = new BusinessProVo("新街口", 0);
		ResultMessage msg1 = test.addBusinessStrategy(vo1);
		assertEquals(ResultMessage.FAIL,msg1);
	}
	
	@Test
	public void testUpdate() {
		BusinessProVo vo = test.getBusinessStrategy("夫子庙");
		vo.setDiscount(0.8);
		test.updateBusinessStrategy(vo);
		assertEquals(0.8, test.getBusinessDiscount("夫子庙"), 0.001);
		
		//error test 
		vo.setDiscount(0);
		ResultMessage msg1 = test.addBusinessStrategy(vo);
		assertEquals(ResultMessage.FAIL,msg1);
	}
	
	@Test
	public void testDelete() {
		BusinessProVo vo = test.getBusinessStrategy("夫子庙");
		test.deleteBusinessStrategy(vo);
		assertEquals(null, test.getBusinessStrategy("夫子庙"));
	}
}
