package businessLogic.marketBL;

import java.util.List;

import businessLogicService.marketBLService.MarketBLService;
import exception.verificationException.MemberInexistException;
import exception.verificationException.UserInexistException;
import utilities.enums.ResultMessage;
import vo.MarketVO;

/**
 * 
 * @author 61990
 *
 */
public final class MarketController implements MarketBLService {
	
	private Market market;
	
	private static MarketController marketController;
	

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27 
	 * @构造函数，初始化成员变量
	 */
	private MarketController() {
		//new the mock object
		market = new Market();
//		market = new MockMarket();
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @return login controller的实例，单例化
	 */
	public static MarketController getInstance() {
		if (marketController == null) {
			marketController = new MarketController();
		}
		return marketController;
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @return 得到会员等级信息列表
	 */
	public List<MarketVO> getMemberFormulation() {
		return market.getMemberFormulation();
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @param marketVOList
	 *            从会员等级界面层传下来的list
	 * @return 是否保存等级制度成功
	 */
	public ResultMessage setMemberFormulation(final List<MarketVO> marketVOList) {
		return market.setMemberFormulation(marketVOList);
	}

	@Override
	public int getLevel(String guestID) throws UserInexistException, MemberInexistException {
		return market.getLevel(guestID);
	}

	@Override
	public String getLevelName(String userID) throws UserInexistException, MemberInexistException {
		return market.getLevelName(userID);
	}

	@Override
	public double getMemberDiscout(String userID) throws UserInexistException, MemberInexistException {
		return market.getMemberDiscout(userID);
	}

}
