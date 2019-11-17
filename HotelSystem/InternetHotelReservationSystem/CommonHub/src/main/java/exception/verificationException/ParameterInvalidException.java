package exception.verificationException;

public class ParameterInvalidException extends Exception{
	
	@Override
	public void printStackTrace() {
		System.out.println("参数异常");
	}

}
