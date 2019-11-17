package dataHelperImpl.dataHelperImplTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dataHelper.WebManagerDataHelper;
import dataHelperImpl.WebManagerDataHelperImpl;
import po.WebManagerPO;
import utilities.enums.ResultMessage;

public class WebManagerDataHelperImplTest {

	WebManagerDataHelper helper = null;
	
	@Before
	public void setUp() throws Exception {
		helper = new WebManagerDataHelperImpl();
	}

	@Test
	public void testAdd() {
		WebManagerPO webManagerPO = new WebManagerPO("1002", "111111");
		assertEquals(ResultMessage.SUCCESS,helper.add(webManagerPO));
	}

	@Test
	public void testModify() {
		WebManagerPO webManagerPO = new WebManagerPO("1001", "111111");
		assertEquals(ResultMessage.SUCCESS,helper.modify(webManagerPO));

	}

	@Test
	public void testGetSingle() {
		WebManagerPO webManagerPO = helper.getSingle("1001");
		assertEquals("111111",webManagerPO.getPassword());
	}

	@Test
	public void testGetAll() {
		List<WebManagerPO> list = helper.getAll();
		assertEquals("111111",list.get(2).getPassword());
	}
}
