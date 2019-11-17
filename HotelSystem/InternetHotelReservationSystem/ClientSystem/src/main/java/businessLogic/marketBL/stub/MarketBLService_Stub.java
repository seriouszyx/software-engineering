package businessLogic.marketBL.stub;

import java.util.LinkedList;
import java.util.List;

import businessLogicService.marketBLService.MarketBLService;
import exception.verificationException.MemberInexistException;
import exception.verificationException.UserInexistException;
import utilities.enums.ResultMessage;
import vo.MarketVO;

public class MarketBLService_Stub implements MarketBLService {
//	String marketName;
//	int marketCredit;
//	String marketBenefit;
//
//	public MarketBLService_Stub(String marketName, int marketCredit, String marketBenefit) {
//		this.marketName = marketName;
//		this.marketCredit = marketCredit;
//		this.marketBenefit = marketBenefit;
//	}

	public List<MarketVO> getMemberFormulation() {
		List<MarketVO> list = new LinkedList<MarketVO>();
		list.add(new MarketVO("Lv1",500 , 0.9));
		list.add(new MarketVO("Lv2",500 , 0.9));
		list.add(new MarketVO("Lv3",500 , 0.9));
		list.add(new MarketVO("Lv4",500 , 0.9));
		list.add(new MarketVO("Lv5",500 , 0.9));
		return list;
	}

	public ResultMessage setMemberFormulation(List<MarketVO> marketVOList) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public int getLevel(String guestID) {
		return 0;
	}

	@Override
	public String getLevelName(String userID) throws UserInexistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getMemberDiscout(String userID) throws UserInexistException, MemberInexistException {
		// TODO Auto-generated method stub
		return 0;
	}

}
