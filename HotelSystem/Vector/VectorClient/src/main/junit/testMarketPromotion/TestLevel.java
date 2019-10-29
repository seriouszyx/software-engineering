package testMarketPromotion;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import businessLogic.impl.MarketPromotionBlServiceImpl;
import businessLogic.service.MarketPromotionBlService;
import myTest.TestClient;
import vo.LevelVo;

public class TestLevel {
	private MarketPromotionBlService test;
	
	@Before
	public void setUp() throws Exception {
		new TestClient();
		test = MarketPromotionBlServiceImpl.getInstance();
	}
	
	@Test
	public void testUpdate() {
		
		LevelVo vo1 = new LevelVo(1,0,1.0);
		LevelVo vo2 = new LevelVo(2,100,0.95);
		LevelVo vo3 = new LevelVo(3,300,0.9);
		List<LevelVo> list = new ArrayList<LevelVo>();
		list.add(vo1);
		list.add(vo2);
		list.add(vo3);
		test.updateLevelStrategy(list);
		double d = test.getLevelStrategy(2);
		assertEquals(0.95, d, 0.0001);
		
	}

}
