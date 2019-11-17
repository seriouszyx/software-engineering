package businessLogic.userBL.userService;

import businessLogic.userBL.userService.service.UserService;

public class UserLengthFactory {
	
	UserService user = null;

	public UserService createUser(int IDLength){
		if (IDLength == Guest.IDLength) {
			user = new Guest();
		}

		if (IDLength == HotelWorker.IDLength) {
			user = new HotelWorker();
		}

		if (IDLength == WebMarketer.IDLength) {
			user = new WebMarketer();
		}

		if (IDLength == WebManager.IDLength) {
			user = new WebManager();
		}
		return user;
	}
}
