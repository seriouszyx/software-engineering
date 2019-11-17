package businessLogic.userBL.userService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import businessLogic.userBL.userService.service.UserService;
import dataService.hotelWorkerDataService.HotelWorkerDataService;
import exception.inputException.InvalidInputException;
import exception.inputException.PasswordInputException;
import exception.verificationException.UserInexistException;
import po.HotelWorkerPO;
import rmi.ClientRemoteHelper;
import utilities.Ciphertext;
import utilities.Detector;
import utilities.enums.ResultMessage;
import vo.HotelWorkerVO;
import vo.UserVO;

/**
 * 
 * @author Byron Dong lastChangedBy Byron Dong updateTime 2016/11/28
 *
 */
public class HotelWorker implements UserService {

	public static int IDLength = 8; // 酒店工作人员的ID长度为8

	private HotelWorkerDataService hotelWorkerDataService;

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/28 构造函数，初始化成员变量
	 */
	public HotelWorker() {
		hotelWorkerDataService = ClientRemoteHelper.getInstance().getHotelWorkerDataService();
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/9
	 * @param newUserVO
	 *            从userDoMain传下来的userInfo载体
	 * @return ResultMessage 用户是否成功添加酒店工作人员信息
	 */
	public UserVO add(UserVO newUserVO) {

		try {
			HotelWorkerPO hotelWorkerPO = this.convert(newUserVO);
			return this.convert(hotelWorkerDataService.add(hotelWorkerPO));
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
	 * @return ResultMessage 用户是否成功修改酒店工作人员信息
	 * @throws InvalidInputException 
	 * @throws PasswordInputException 
	 */
	public ResultMessage modify(UserVO userVO) throws PasswordInputException, InvalidInputException {

		ResultMessage msg = ResultMessage.FAIL;

		try {
			this.infoDetector(userVO);
			HotelWorkerPO hotelWorkerPO = this.convert(userVO);
			msg = hotelWorkerDataService.modify(hotelWorkerPO);
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
	 * @return UserVO 单一hotelWorkerInfo载体
	 * @throws UserInexistException 
	 */
	public UserVO getSingle(String userID) throws UserInexistException {

		try {
			UserVO tempUserVO = this.convert(hotelWorkerDataService.getSingleHotelWorker(userID));
			if(tempUserVO==null){
				throw new UserInexistException();
			}
			return tempUserVO;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null; // 若不存在ID对应项，就返回null
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/27
	 * @param
	 * @return List<UserVO> 指定用户类型的所有hotelWorkerInfo载体
	 */
	public List<UserVO> getAll() {

		try {
			return this.convert(hotelWorkerDataService.getAllHotelWorker());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null; // 若不存在ID对应项，就返回null
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
			UserVO tempUserVO = convert(hotelWorkerDataService.getSingleHotelWorker(userID));
			if(tempUserVO==null){
				throw new UserInexistException();
			}
			return tempUserVO.password;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null; // 需要后期处理，是决定继续采用null，还是换其他
		}
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/9
	 * @param hotelWorkerPO
	 *            来自本类hotelWorkerInfo载体
	 * @return UserVO userInfo载体
	 */
	private UserVO convert(HotelWorkerPO hotelWorkerPO) {
		if (hotelWorkerPO == null) {
			return null;
		}
		return new HotelWorkerVO(this.decode(hotelWorkerPO));
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/28
	 * @param userPO
	 *            来自本类userInfo载体
	 * @return HotelWorkerVO hotelWorkerInfo载体
	 */
	private HotelWorkerPO convert(UserVO userVO) {
		if (userVO == null) {
			return null;
		}
		return this.encrypt(new HotelWorkerPO((HotelWorkerVO) userVO));
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/28
	 * @param List<HotelWorkerPO>
	 *            来自本类所有hotelWorkerInfo载体
	 * @return List<UserVO> 所有对应的userInfo载体
	 */
	private List<UserVO> convert(List<HotelWorkerPO> list) {
		List<UserVO> result = new ArrayList<UserVO>();
		for (int i = 0; i < list.size(); i++) {
			result.add(new HotelWorkerVO(this.decode(list.get(i))));
		}
		return result;
	}

	private boolean infoDetector(UserVO userVO) throws PasswordInputException, InvalidInputException{
		Detector detector = new Detector();
		HotelWorkerVO hotelWorkerVO = (HotelWorkerVO)userVO;
		
		detector.passwordDetector(hotelWorkerVO.password);
		detector.infoDetector(hotelWorkerVO.hotelName);
		
		return true;
	}

	private HotelWorkerPO encrypt(HotelWorkerPO hotelWorkerPO) {
		Ciphertext encrypt = new Ciphertext();
		hotelWorkerPO.setPassword(encrypt.encrypt(hotelWorkerPO.getPassword()));
		return hotelWorkerPO;
	}

	private HotelWorkerPO decode(HotelWorkerPO hotelWorkerPO) {
		Ciphertext decode = new Ciphertext();
		hotelWorkerPO.setPassword(decode.decode(hotelWorkerPO.getPassword()));
		return hotelWorkerPO;
	}

}
