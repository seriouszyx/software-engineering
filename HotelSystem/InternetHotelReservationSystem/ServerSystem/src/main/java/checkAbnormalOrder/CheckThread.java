package checkAbnormalOrder;

import java.time.LocalDateTime;
import java.util.List;

import dataHelper.CreditDataHelper;
import dataHelper.GuestDataHelper;
import dataHelper.OrderDataHelper;
import dataHelperImpl.DataFactoryImpl;
import po.CreditPO;
import po.GuestPO;
import po.OrderPO;
import utilities.enums.CreditRecord;
import utilities.enums.OrderState;

public class CheckThread implements Runnable {

	OrderDataHelper orderDateHelper;
	CreditDataHelper creditDataHelper;
	GuestDataHelper guestDataHelper;
	
	public CheckThread() {
		orderDateHelper = DataFactoryImpl.getInstance().getOrderDataHelper();
		creditDataHelper = DataFactoryImpl.getInstance().getCreditDataHelper();
		guestDataHelper = DataFactoryImpl.getInstance().getGuestDataHelper();
	}
	@Override
	public void run() {
		
		List<OrderPO> unexecutedOrders = orderDateHelper.getUnexecuted();
		
		for(int i = 0;i < unexecutedOrders.size();i++){
			OrderPO po = unexecutedOrders.get(i);
			if(timePassed(po)){
				//将该order的状态置为异常
				orderDateHelper.setState(po.getOrderID(), OrderState.ABNORMAL);
				//添加一条异常信用记录
				double afterCredit = addAbnormalCredit(po.getGuestID(),po.getOrderID(),po.getPrice());
				//修改客户的信用值
				modifyClientCredit(po.getGuestID(),afterCredit);
				
				System.out.println("有订单异常");
			}
		}
		try {
			//每5s运行一次
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description:修改订单异常的客户的信用值
	 * @param guestID
	 * void
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月24日 下午4:55:38
	 */
	private void modifyClientCredit(String guestID,double afterCredit) {
		GuestPO po = guestDataHelper.getSingle(guestID);
		po.setCredit(afterCredit);
		guestDataHelper.modify(po);
	}
	/**
	 * @Description:增加一条异常信用记录
	 * 返回异常之后的总信用值
	 * @author: Harvey Gong
	 * @param guestID 
	 * @param price 
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 下午10:08:13
	 */
	private double addAbnormalCredit(String guestID,String orderID,int price) {
		CreditPO po = new CreditPO();
		po.setCreditRecord(CreditRecord.OVERDUE);
		po.setGuestID(guestID);
		po.setOrderID(orderID);
		
		double preCredit = guestDataHelper.getSingle(guestID).getCredit();
		double afterCredit = preCredit-price;
		
		po.setPreCredit(preCredit);
		po.setAfterCredit(afterCredit);
		po.setTime(LocalDateTime.now());
		
		creditDataHelper.addCredit(po);
		
		return afterCredit;
	}
	/**
	 * @Description:判断是否超过预期入住时间
	 * @param po
	 * @return
	 * boolean
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 下午10:07:56
	 */
	private boolean timePassed(OrderPO po){
		if(po.getExpectExecuteTime().isBefore(LocalDateTime.now())){
			return true;
		}
		else
		{
			return false;
		}
	}

}
