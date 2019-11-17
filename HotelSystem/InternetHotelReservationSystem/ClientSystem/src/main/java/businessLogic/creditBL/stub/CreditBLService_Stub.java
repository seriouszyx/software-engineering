package businessLogic.creditBL.stub;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import businessLogicService.creditBLService.CreditBLService;
import exception.operationFailedException.UpdateFaiedException;
import exception.verificationException.UserInexistException;
import utilities.enums.ResultMessage;
import vo.BasicInfoVO;
import vo.CreditVO;
import vo.GuestVO;
import vo.MarketVO;

public class CreditBLService_Stub implements CreditBLService {

	public ResultMessage charge(int chargeNum) {
		return ResultMessage.SUCCESS;
	}

	public BasicInfoVO getBasicInfo(String ID) {
		return new BasicInfoVO(new GuestVO("1234567890", LocalDate.of(1995, 4, 1), "Samsung","Carol","cal", "123456", 
				"13555550000", 400), "L1");
	}

	
	public Iterator<CreditVO> getAllCreditDetail(String guestID) {
		return null;
	}

	
	public ResultMessage charge(String guestID, float chargeNum) {
		return ResultMessage.SUCCESS;
	}

	
	/**
	 * 
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/5
	 * @param creditVO 此次信用记录的载体
	 * @return 添加此条信用记录的结果
	 */
	public ResultMessage addCreditRecord(final CreditVO creditVO) {
		return ResultMessage.SUCCESS;
	}
	
	
	public List<MarketVO> getMemberFormulation() {
		return null;
	}

//	@Override
//	public Iterator<CreditVO> getCreditOfOneOrder(String orderID) {
//		return null;
//	}


	@Override
	public double charge(String guestID, double creditNum) throws UserInexistException, UpdateFaiedException {
		// TODO Auto-generated method stub
		return 0;
	}


	
}
