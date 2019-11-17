package businessLogic.marketBL;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import businessLogic.memberBL.MemberController;
import businessLogic.userBL.UserController;
import businessLogicService.marketBLService.MarketBLService;
import dataService.marketDataService.MarketDataService;
import exception.verificationException.MemberInexistException;
import exception.verificationException.UserInexistException;
import po.MarketPO;
import rmi.ClientRemoteHelper;
import utilities.enums.ResultMessage;
import vo.GuestVO;
import vo.MarketVO;

/**
 * 
 * @author 61990
 *
 */
public class Market implements MarketBLService{
	
	private MarketDataService marketDataService;
	
	private MemberController member;
	
	private UserController user;
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27 
	 * @构造函数，初始化成员变量
	 */
	public Market() {
		marketDataService = ClientRemoteHelper.getInstance().getMarketDataService();
		member = MemberController.getInstance();
		user = UserController.getInstance();
	}

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @return 得到会员等级信息列表
	 */
	public List<MarketVO> getMemberFormulation() {
		final List<MarketVO> marketVOList = new ArrayList<MarketVO>();
		try {

			final List<MarketPO> marketPOList = marketDataService.getMemberFormulation();
			for (int i = 0; i < marketPOList.size(); i++) {
				marketVOList.add(new MarketVO(marketPOList.get(i)));
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return marketVOList;
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @param marketVOList 从会员等级界面层传下来的list
	 * @return 是否保存等级制度成功
	 */
	public ResultMessage setMemberFormulation(final List<MarketVO> marketVOList) {

		try {
			final List<MarketPO> marketPOList = new ArrayList<MarketPO>();
			for (int i = 0; i < marketVOList.size(); i++) {
				marketPOList.add(new MarketPO(marketVOList.get(i)));
			}
			return marketDataService.setMemberFormulation(marketPOList);

		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/8
	 * @param guestID 需要获取等级客户ID
	 * @return int 获取客户当前会员等级（需要处理未达最低信用值的情况，此时返回值为0）
	 * @throws UserInexistException 
	 * @throws MemberInexistException 
	 */
	public int getLevel(String guestID) throws UserInexistException, MemberInexistException{
		
		if(member.getMemberType(guestID)==null){
			throw new MemberInexistException();
		}
		
		double credit = ((GuestVO)user.getSingle(guestID)).credit;
		List<MarketVO> list = this.getMemberFormulation();
		
		int level = 0;
		for(int i=0;i<list.size();i++){
			if(credit>=list.get(i).marketCredit){
				level++;
			}
			else{
				break;
			}
		}
		
		return level;
	}
	
	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/14
	 * @param guestID 需要获取等级客户ID
	 * @return String 获取客户当前会员等级名称（需要处理未达最低信用值的情况，此时返回值为Lv0）
	 * @throws UserInexistException 
	 * @throws MemberInexistException 
	 */
	public String getLevelName(String userID) throws UserInexistException, MemberInexistException{
		int level = this.getLevel(userID);
		return "Lv"+String.valueOf(level);
	}
	
	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/14
	 * @param guestID 需要获取等级客户ID
	 * @return double 获取客户当前会员折扣
	 * @throws UserInexistException （需要处理未达最低信用值的情况，此时返回值为1）
	 * @throws MemberInexistException 
	 */
	public double getMemberDiscout(String userID) throws UserInexistException, MemberInexistException{
		int level = this.getLevel(userID);
		List<MarketVO> list = this.getMemberFormulation();
		if(level==0){return 1;}
		return list.get(level-1).marketBenefit;
	}
}
