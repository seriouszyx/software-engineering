package exception.verificationException;

public class MemberInexistException extends Exception{

	
	@Override
	public void printStackTrace() {
		System.out.println("查找的会员不存在");
	}
}
