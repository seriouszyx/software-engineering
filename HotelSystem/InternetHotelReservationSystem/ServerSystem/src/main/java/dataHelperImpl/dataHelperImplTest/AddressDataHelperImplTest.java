package dataHelperImpl.dataHelperImplTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dataHelper.AddressDataHelper;
import dataHelperImpl.AddressDataHelperImpl;
import po.AddressPO;
import utilities.enums.ResultMessage;

public class AddressDataHelperImplTest {

	AddressDataHelper helper;
	
	@Before
	public void setUp() throws Exception {
		 helper = new AddressDataHelperImpl();
	}

	@Test
	public void testGetCity() {
		List<String> list = helper.getCity();
		assertEquals("北京",list.get(0));
		assertEquals("南京",list.get(1));
		assertEquals("武汉",list.get(2));
	}

	@Test
	public void testGetCircle() {
		List<String> list = helper.getCircle("南京");
		assertEquals("新街口",list.get(0));
		assertEquals("杏林",list.get(1));
		assertEquals("炫舞",list.get(2));
		
	}

	@Test
	public void testGetDiscout() {
		double discout = helper.getDiscout("南京", "新街口");
		assertEquals(0.9,discout,0);
	}

	@Test
	public void testGetAll() {
		List<AddressPO> list = helper.getAll("南京");
		assertEquals("新街口",list.get(0).getCircle());
		assertEquals(0.9,list.get(0).getDiscout(),0);
		assertEquals("杏林",list.get(1).getCircle());
		assertEquals(0.8,list.get(1).getDiscout(),0);
		assertEquals("炫舞",list.get(2).getCircle());
		assertEquals(0.7,list.get(2).getDiscout(),0);
	}

	@Test
	public void testModifyDiscout() {
		assertEquals(helper.modifyDiscout(new AddressPO("武汉","汉庭",0.8)),ResultMessage.SUCCESS);
	}
}
