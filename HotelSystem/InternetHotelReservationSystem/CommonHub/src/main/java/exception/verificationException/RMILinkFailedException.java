package exception.verificationException;

/**
 * @Description:当rmi连接出错的时候抛出该异常
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月6日 下午5:19:03
 */
public class RMILinkFailedException extends Exception {

	@Override
	public void printStackTrace() {
		System.out.println("网络连接异常");
	}
	
}
