package exception.inputException;

/**
 * @Description:输入非法抛出该异常
 * @author:Harvey Gong
 * @lastChangedBy:Byron dong
 * @time:2016年12月9日 下午4:38:40
 */
public class InvalidInputException extends Exception {

	@Override
	public void printStackTrace() {
		System.out.println("无效输入");
	}
}