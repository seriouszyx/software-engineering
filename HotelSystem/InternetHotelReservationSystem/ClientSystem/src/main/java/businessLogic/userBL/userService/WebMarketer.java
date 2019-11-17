package businessLogic.userBL.userService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import businessLogic.userBL.userService.service.UserService;
import dataService.webMarketerDataService.WebMarketerDataService;
import exception.inputException.PasswordInputException;
import exception.verificationException.UserInexistException;
import po.WebMarketerPO;
import rmi.ClientRemoteHelper;
import utilities.Ciphertext;
import utilities.Detector;
import utilities.enums.ResultMessage;
import vo.UserVO;
import vo.WebMarketerVO;

/**
 * 
 * @author Byron Dong lastChangedBy Byron Dong updateTime 2016/11/28
 *
 */
public class WebMarketer implements UserService {

	public static int IDLength = 6; // 营销人员的ID长度为6

	private WebMarketerDataService webMarketerDataService;

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/28 构造函数，初始化成员变量
	 */
	public WebMarketer() {
		webMarketerDataService = ClientRemoteHelper.getInstance().getWebMarketerDataService();
//		try {
//			webMarketerDataService = new WebMarketerDataService_Stub();
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/28
	 * @param newUserVO
	 *            从userDoMain传下来的userInfo载体
	 * @return ResultMessage 用户是否成功添加网站营销人员信息
	 */
	public WebMarketerVO add(UserVO newUserVO) {

		try {
			WebMarketerPO webMarketerPO = this.convert(newUserVO);
			return convert(webMarketerDataService.add(webMarketerPO));
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/28
	 * @param newUserVO
	 *            从userDoMain传下来的userInfo载体
	 * @return ResultMessage 用户是否成功修改网站营销人员信息
	 * @throws PasswordInputException 
	 */
	public ResultMessage modify(UserVO userVO) throws PasswordInputException {

		ResultMessage msg = ResultMessage.FAIL;

		try {
			this.infoDetector(userVO);
			WebMarketerPO webMarketerPO = this.convert(userVO);
			msg = webMarketerDataService.modify(webMarketerPO);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/28
	 * @param userVO
	 *            从userDoMain传下来的用户ID
	 * @return UserVO 单一webMarketerInfo载体
	 * @throws UserInexistException 
	 */
	public UserVO getSingle(String userID) throws UserInexistException {

		try {
			UserVO tempUserVO = this.convert(webMarketerDataService.getSingleWebMarketer(userID));
		
			if(tempUserVO==null){
				throw new UserInexistException();
			}
			return tempUserVO;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/28
	 * @param userVO
	 *            从userDoMain传下来的用户ID
	 * @return UserVO 单一webMarketerInfo载体
	 */
	public List<UserVO> getAll() {

		try {
			return this.convert(webMarketerDataService.getAllWebMarketer());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/28
	 * @param userID
	 *            从userDoMain传下来的指定用户ID
	 * @return String 指定用户 的登录信息
	 * @throws UserInexistException 
	 */
	public String getLogInInfo(String userID) throws UserInexistException {

		try {
			UserVO tempUserVO = convert(webMarketerDataService.getSingleWebMarketer(userID));
			if(tempUserVO==null){
				throw new UserInexistException();
			}
			return tempUserVO.password;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/28
	 * @param webMarketerPO
	 *            来自本类webMarketerInfo载体
	 * @return UserVO userInfo载体
	 */
	private WebMarketerVO convert(WebMarketerPO webMarketerPO) {
		if (webMarketerPO == null) {
			return null;
		}
		return new WebMarketerVO(this.decode(webMarketerPO));
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/28
	 * @param userPO
	 *            来自本类userInfo载体
	 * @return WebMarketerPO webMarketerInfo载体
	 */
	private WebMarketerPO convert(UserVO userVO) {
		if (userVO == null) {
			return null;
		}
		return this.encrypt(new WebMarketerPO((WebMarketerVO) userVO));
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/28
	 * @param List<WebMarketerPO>
	 *            来自本类所有webMarketerInfo载体
	 * @return List<UserVO> 所有对应的userInfo载体
	 */
	private List<UserVO> convert(List<WebMarketerPO> list) {
		List<UserVO> result = new ArrayList<UserVO>();
		for (int i = 0; i < list.size(); i++) {
			result.add(new WebMarketerVO(this.decode(list.get(i))));
		}
		return result;
	}

	private boolean infoDetector(UserVO userVO) throws PasswordInputException{
		Detector detector = new Detector();
		WebMarketerVO webMarketerVO = (WebMarketerVO)userVO;
		detector.passwordDetector(webMarketerVO.password);
		
		return true;
	}

	private WebMarketerPO encrypt(WebMarketerPO webMaketerPO) {
		Ciphertext encrypt = new Ciphertext();
		webMaketerPO.setPassword(encrypt.encrypt(webMaketerPO.getPassword()));
		return webMaketerPO;
	}

	private WebMarketerPO decode(WebMarketerPO webMaketerPO) {
		Ciphertext decode = new Ciphertext();
		webMaketerPO.setPassword(decode.decode(webMaketerPO.getPassword()));
		return webMaketerPO;
	}

}
