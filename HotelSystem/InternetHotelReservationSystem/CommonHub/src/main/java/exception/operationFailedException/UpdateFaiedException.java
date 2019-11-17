package exception.operationFailedException;

/**
 * @Description:当更新信息失败时，抛出该异常
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月6日 下午4:47:50
 */
public class UpdateFaiedException extends Exception {

	@Override
	public void printStackTrace() {
		System.out.println("更新信息失败");
	}

}
