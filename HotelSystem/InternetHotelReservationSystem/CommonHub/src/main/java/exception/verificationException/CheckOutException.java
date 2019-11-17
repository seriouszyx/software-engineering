package exception.verificationException;

/**
 * @Description:当一个客户尝试退房，实际退房日期与现退房日期不符合时，抛出该异常
 * @author:charles
 * @lastChangedBy:charles
 * @time:2017/1/1
 */
public class CheckOutException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6468607655445441269L;

	@Override
	public void printStackTrace() {
		System.out.println("该订单的退房日期不正确，请检查后重试");
	}
}
