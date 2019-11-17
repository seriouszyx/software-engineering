package exception.inputException;

public class InvalidLengthInputException extends Exception{

	@Override
	public void printStackTrace() {
		System.out.println("输入的长度无效");
	}
	
	public void printStackTrace(String msg) {
		System.out.println(msg);
	}
}
