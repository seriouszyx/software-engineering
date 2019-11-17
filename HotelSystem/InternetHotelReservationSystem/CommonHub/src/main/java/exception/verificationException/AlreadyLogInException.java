package exception.verificationException;

/**
 * @Description:当一个用户试图登录已经登录过的用户时，抛出该异常
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月6日 下午5:02:08
 */
public class AlreadyLogInException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2075971249677613469L;

	@Override
	public void printStackTrace() {
		System.out.println("该用户已登录，请勿重复登录");
	}

}
