package exception.operationFailedException;

/**
 * @Description:删除信息失败时，抛出该异常
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月6日 下午4:57:39
 */
public class DeleteFaiedException extends Exception {

	@Override
	public void printStackTrace() {
		System.out.println("删除失败");
	}

}
