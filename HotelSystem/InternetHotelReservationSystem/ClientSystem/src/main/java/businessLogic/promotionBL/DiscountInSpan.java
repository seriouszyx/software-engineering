package businessLogic.promotionBL;

import java.util.Iterator;

import exception.verificationException.MemberInexistException;
import exception.verificationException.UserInexistException;
import vo.PreOrderVO;

public interface DiscountInSpan {
	/**
	 * @Description 计算出在入住时间内，每天打的折扣
	 * @param preOrder
	 * @return
	 * Iterator<Double>
	 * @author: Harvey Gong
	 * @throws UserInexistException 
	 * @throws MemberInexistException 
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月8日 上午2:01:44
	 */
	public Iterator<Double> getDiscountInSpan(PreOrderVO preOrder) throws UserInexistException;
}
