package exception.operationFailedException;

/**
 * @Description:当添加信息失败时，抛出该异常
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月6日 下午4:46:22
 */
public class AddFaidException extends Exception{

	@Override
	public void printStackTrace() {
		System.out.println("添加失败");
	}

}
