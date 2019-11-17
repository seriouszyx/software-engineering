package exception.inputException;

/**
 * @Description:输入非法抛出该异常
 * @author:Byron dong
 * @lastChangedBy:Byron dong
 * @time:2016年12月10日
 */
public class PasswordInputException extends Exception{
	
	@Override
	public void printStackTrace() {
		System.out.println("密码至少要含有一个数字和一个字母");
	}

}
