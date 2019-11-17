package exception.operationFailedException;

/**
 * @Description:获取信息失败时，抛出该异常
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月6日 下午5:11:16
 */
public class GetFailedException extends Exception {

	@Override
	public void printStackTrace() {
		System.out.println("获取信息失败");
	}

}
