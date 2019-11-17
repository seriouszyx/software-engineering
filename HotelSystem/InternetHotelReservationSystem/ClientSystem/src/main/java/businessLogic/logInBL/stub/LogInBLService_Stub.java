package businessLogic.logInBL.stub;

import java.time.LocalDate;

import businessLogicService.logInBLService.LogInBLService;
import utilities.enums.UserType;
import vo.GuestVO;
import vo.UserVO;

/**
 * 
 * @author 61990
 *
 */
public class LogInBLService_Stub implements LogInBLService {

	
//	public LogInBLService_Stub(String ID, String password) {
//		this.guest = ID;
//		this.password = password;
//	}

	@Override
	public UserType logIn(String userID, String password) {
		if(userID.length()==10){
			return UserType.GUEST;
		}
		else if(userID.length()==8){
			return UserType.HOTEL_WORKER;
		}
		else if(userID.length()==6){
			return UserType.WEB_MARKETER;
		}
		else if(userID.length()==4){
			return UserType.WEB_MANAGER;
		}
		return null;
	}

	@Override
	public GuestVO guestSignUp(UserVO guestVO) {
		return new GuestVO("1234567890", 
				LocalDate.of(1995, 4, 1), "school","zhangsan", "xiaosan", "000000", "13568792345", 100);
	}

	@Override
	public void logOut(String userID) {
		
	}
}
