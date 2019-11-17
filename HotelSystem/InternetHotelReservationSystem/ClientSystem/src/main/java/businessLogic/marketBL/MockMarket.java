package businessLogic.marketBL;

import java.util.LinkedList;
import java.util.List;

import utilities.enums.ResultMessage;
import vo.MarketVO;

public class MockMarket extends Market {
	
	public List<MarketVO> getMemberFormulation() {
		List<MarketVO> list = new LinkedList<MarketVO>();
		list.add(new MarketVO("Lv1", 50, 0.9));
		return list;
	}


	public ResultMessage setMemberFormulation(List<MarketVO> marketVOList) {
		return ResultMessage.SUCCESS;
	}
}
