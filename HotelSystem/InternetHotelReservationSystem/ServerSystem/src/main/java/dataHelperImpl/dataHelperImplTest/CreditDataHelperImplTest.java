package dataHelperImpl.dataHelperImplTest;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import dataHelper.CreditDataHelper;
import dataHelperImpl.CreditDataHelperImpl;
import po.CreditPO;
import utilities.enums.CreditRecord;
import utilities.enums.ResultMessage;

public class CreditDataHelperImplTest {

	CreditDataHelper helper = null;
	
	@Before
	public void setUp() throws Exception {
		helper = new CreditDataHelperImpl();
	}

	@Test
	public void testGetAll() {
		
		List<CreditPO>  list = helper.getAllCreditDetail("1234567900");	
		assertEquals(6, list.size());
	}

	@Ignore
	@Test
	public void testAdd() {
		CreditPO creditPO = new CreditPO("1234567900",LocalDateTime.of(2016, 12, 07, 18, 14,21),"",111,444,CreditRecord.CHARGE);
		
		assertEquals(ResultMessage.SUCCESS,helper.addCredit(creditPO));
	}
}
