package businessLogic.userBL.userService;

import businessLogic.userBL.userService.service.UserService;
import utilities.enums.UserType;

/**
 * @Description:根据用户类型创建用户对象
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月5日 上午10:45:45
 */
public class UserFactory {
	UserService user = null;
	
	public UserService createUser(UserType userType){
		if (userType == UserType.GUEST) {
			user = new Guest();
		}

		if (userType == UserType.HOTEL_WORKER) {
			user = new HotelWorker();
		}

		if (userType == UserType.WEB_MARKETER) {
			user = new WebMarketer();
		}

		if (userType == UserType.WEB_MANAGER) {
			user = new WebManager();
		}
		return user;
	}
}
