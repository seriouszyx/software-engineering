package exception.verificationException;

/**
 * @Description:当一个客户尝试入住，实际入住日期与现入住日期不符合时，抛出该异常
 * @author:charles
 * @lastChangedBy:charles
 * @time:2017/1/1
 */
public class CheckInException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8403780236954993946L;

	@Override
	public void printStackTrace() {
		System.out.println("该订单的入住日期不正确，请检查后重试");
	}
}
