package exception.inputException;

/**
 * @Description:如果应该输入数字的地方输入了其它数据，抛出该异常
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月6日 下午4:38:03
 */
public class NotOnlyNumberException extends InvalidInputException {

	@Override
	public void printStackTrace() {
		System.out.println("只能输入数字");
	}

}
