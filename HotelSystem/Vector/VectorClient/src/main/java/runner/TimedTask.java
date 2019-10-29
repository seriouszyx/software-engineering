package runner;

import java.util.Timer;
import java.util.TimerTask;

import businessLogic.impl.OrderBlServiceImpl;
import businessLogic.service.OrderBlService;

public class TimedTask {
	//定时扫描订单，将已经到达最晚执行时间的订单置于异常状态
	public TimedTask() {
		   Timer timer = new Timer();  
		   timer.schedule(new Task_1(), 2000, 1000 * 60 * 60); 
	}
}

class Task_1 extends TimerTask {
	
	@Override
	public void run() {
		OrderBlService orderBlService = OrderBlServiceImpl.getInstance();
		orderBlService.setToAbnormal();
	}
}
