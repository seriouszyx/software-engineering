package dataHelperImpl.dataHelperImplTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dataHelper.MarketDataHelper;
import dataHelperImpl.MarketDataHelperImpl;
import po.MarketPO;
import utilities.enums.ResultMessage;

public class MarketDataHelperImplTest {

	MarketDataHelper helper = null;
	
	@Before
	public void setUp() throws Exception {
		helper = new MarketDataHelperImpl();
	}

	@Test
	public void testGetAll() {
		List<MarketPO> list = helper.getAll();
		assertEquals("Lv1",list.get(0).getMarketName());
		assertEquals(500,list.get(0).getMarketCredit(),0);
		assertEquals(0.95,list.get(0).getMarketBenefit(),0);
		
		assertEquals("Lv8",list.get(7).getMarketName());
		assertEquals(31500,list.get(7).getMarketCredit(),0);
		assertEquals(0.6,list.get(7).getMarketBenefit(),0);
	}
	
	@Test
	public void testModifyAll() {
		List<MarketPO> list = new ArrayList<MarketPO>();
		list.add(new MarketPO("Lv1",500,0.95));
		list.add(new MarketPO("Lv2",1500,0.9));
		list.add(new MarketPO("Lv3",3000,0.85));
		list.add(new MarketPO("Lv4",4500,0.8));
		list.add(new MarketPO("Lv5",7500,0.75));
		list.add(new MarketPO("Lv6",12000,0.7));
		list.add(new MarketPO("Lv7",19500,0.65));
		list.add(new MarketPO("Lv8",40500,0.6));
		
		assertEquals(ResultMessage.SUCCESS,helper.modifyAll(list));
	}
}
