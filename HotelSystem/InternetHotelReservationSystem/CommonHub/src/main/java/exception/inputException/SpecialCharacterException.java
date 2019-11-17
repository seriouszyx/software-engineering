package exception.inputException;

/**
 * @Description:当输入不允许使用特殊字符，但用户使用了特殊字符，抛出该异常
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月6日 下午4:42:51
 */
public class SpecialCharacterException extends InvalidInputException {

	@Override
	public void printStackTrace() {
		System.out.println("不能使用特殊字符");
	}

}
