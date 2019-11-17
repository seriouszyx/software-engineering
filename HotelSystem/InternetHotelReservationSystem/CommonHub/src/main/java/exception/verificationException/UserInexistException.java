package exception.verificationException;

public class UserInexistException extends Exception{

	@Override
	public void printStackTrace() {
		System.out.println("该用户不存在");
	}
	
	
}
