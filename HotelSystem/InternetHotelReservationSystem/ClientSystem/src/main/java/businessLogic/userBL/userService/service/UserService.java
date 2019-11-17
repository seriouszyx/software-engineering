package businessLogic.userBL.userService.service;

import java.util.List;

import exception.inputException.InvalidInputException;
import exception.inputException.InvalidLengthInputException;
import exception.inputException.PasswordInputException;
import exception.operationFailedException.UpdateFaiedException;
import exception.verificationException.UserInexistException;
import utilities.enums.ResultMessage;
import vo.UserVO;

public interface UserService {
	
	public UserVO add(UserVO newUserVO);

	public ResultMessage modify(UserVO userVO) throws InvalidLengthInputException, InvalidInputException, PasswordInputException, UpdateFaiedException;
	
	public UserVO getSingle(String userID) throws UserInexistException;
	
	public List<UserVO> getAll();
	
	public String getLogInInfo(String userID) throws UserInexistException;
	
}
