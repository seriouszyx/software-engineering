package exception.verificationException;

/**
 * @Description:当输入密码错误时，抛出该异常
 * @author:Harvey Gong
 * @lastChangedBy:Byron Dong
 * @time:2016年12月6日 下午4:45:04
 */
public class WrongPasswordException extends Exception{

	@Override
	public void printStackTrace() {
		System.out.println("密码输入错误");
	}
	
}
