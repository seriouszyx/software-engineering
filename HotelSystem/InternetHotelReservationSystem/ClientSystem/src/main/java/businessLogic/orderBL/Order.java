package businessLogic.orderBL;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import businessLogic.promotionBL.DiscountCalculator;
import businessLogic.promotionBL.DiscountInSpan;
import dataService.orderDataService.OrderDataService;
import exception.verificationException.UserInexistException;
import po.CheckInPO;
import po.CheckOutPO;
import po.HotelEvaluationPO;
import po.OrderGeneralPO;
import po.OrderPO;
import rmi.ClientRemoteHelper;
import utilities.enums.OrderState;
import utilities.enums.ResultMessage;
import vo.CheckInVO;
import vo.CheckOutVO;
import vo.HotelEvaluationVO;
import vo.OrderGeneralVO;
import vo.OrderVO;
import vo.PreOrderVO;

/**
 * 
 * @author charles
 * lastChangedBy charles
 * updateTime 2016/12/7
 *
 */
public class Order {
	
	private OrderDataService orderDataService;
	
	private DiscountInSpan discountCalculator;
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * 构造函数，初始化成员变量
	 */
	public Order() {
		orderDataService = ClientRemoteHelper.getInstance().getOrderDataService();
		discountCalculator = new DiscountCalculator();
	}

	/**
	 * 
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @param orderVO 从客户界面层传下来的Order载体
	 * @return 若客户创建此订单，需要付的款项
	 */
	public int getTempPrice(OrderVO orderVO) {
		Iterator<Double> discountsInSpan = null;
		try {
			discountsInSpan = discountCalculator.getDiscountInSpan(new PreOrderVO(orderVO));
		} catch (UserInexistException e) {
			e.printStackTrace();
		}
		final double prePrice = orderVO.previousPrice;
		double result = 0;
		while(discountsInSpan.hasNext()) {
			result += prePrice * discountsInSpan.next();
		}
		return (int)result;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @param orderVO 从客户界面层传下来的Order载体
	 * @return 客户是否成功创建此订单
	 */
	public ResultMessage createOrder(final OrderVO orderVO) {
		ResultMessage resultMessage = ResultMessage.FAIL;
		
		if (orderVO.orderGeneralVO.orderID != null && orderVO.orderGeneralVO.price != -1
				&& orderVO.checkInTime != null && orderVO.checkOutTime != null 
				&& orderVO.roomNumber != null && orderVO.score != -1 && orderVO.comment != null) {
			return resultMessage;
		}else {
			try {
				orderVO.orderGeneralVO.price = getTempPrice(orderVO);
				resultMessage = orderDataService.createOrder(new OrderPO(orderVO));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			return resultMessage;
		}
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param orderID 酒店工作人员当前需要执行订单的订单号
	 * @return 酒店工作人员是否成功执行此订单
	 */
	public ResultMessage executeOrder(final String orderID) {
		ResultMessage resultMessage = ResultMessage.FAIL;
		
		OrderState thisOrderState = getOrderDetail(orderID).orderGeneralVO.state;
		if (thisOrderState != OrderState.UNEXECUTED && thisOrderState != OrderState.ABNORMAL) {
			return resultMessage;
		}else {
			try {
				resultMessage = orderDataService.executeOrder(orderID);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			return resultMessage;
		}
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param orderID 网站营销人员当前需要撤销的异常订单的订单号
	 * @return 网站营销人员是否成功撤销此异常订单
	 */
	public ResultMessage undoAbnormalOrder(final String orderID, final double percent) {
		ResultMessage resultMessage = ResultMessage.FAIL;
		
		OrderState thisOrderState = getOrderDetail(orderID).orderGeneralVO.state;
		if (thisOrderState != OrderState.ABNORMAL) {
			return resultMessage;
		}else {
			try {
				resultMessage = orderDataService.undoAbnormalOrder(orderID, percent);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			return resultMessage;
		}
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param orderID 客户当前需要撤销的正常订单的订单号
	 * @return 客户是否成功撤销此正常订单
	 */
	public ResultMessage undoNormalOrder(final String orderID) {
		ResultMessage resultMessage = ResultMessage.FAIL;
		
		OrderState thisOrderState = getOrderDetail(orderID).orderGeneralVO.state;
		if (thisOrderState != OrderState.UNEXECUTED) {
			return resultMessage;
		}else {
			try {
				resultMessage = orderDataService.undoNormalOrder(orderID);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			return resultMessage;
		}
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param orderID 用户当前需要查看的订单的订单号
	 * @return 此被需要订单的详情载体
	 */
	public OrderVO getOrderDetail(final String orderID) {
		OrderVO thisOrderVO = null;
		
		try {
			thisOrderVO = new OrderVO(orderDataService.getOrderDetail(orderID));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return thisOrderVO;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param guestID 客户要查看个人所有订单时，客户的编号
	 * @return 客户个人所有订单
	 */
	public List<OrderGeneralVO> getAllGuestOrderGeneral(final String guestID) {
		final List<OrderGeneralVO> result = new ArrayList<OrderGeneralVO>();
		
		List<OrderGeneralPO> orderGeneralPOs = null;
		try {
			orderGeneralPOs = orderDataService.getAllGuestOrderGeneral(guestID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if (orderGeneralPOs != null) {
			for (int i = 0; i < orderGeneralPOs.size(); i++) {
				result.add(new OrderGeneralVO(orderGeneralPOs.get(i)));
			}
		}
		
		return result;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @param guestID 客户要查看个人<所有未执行>订单时，客户的编号
	 * @return 客户个人<所有未执行>订单
	 * 
	 * 直接从本层本模块getAllGuestOrderGeneral走
	 */
	public List<OrderGeneralVO> getAllGuestUnexecutedOrderGeneral(final String guestID) {
		final List<OrderGeneralVO> orderGeneralVOs = getAllGuestOrderGeneral(guestID);
		return orderStateFilter(orderGeneralVOs, OrderState.UNEXECUTED);
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @param guestID 客户要查看个人<所有已执行>订单时，客户的编号
	 * @return 客户个人<所有已执行>订单
	 * 
	 * 直接从本层本模块getAllGuestOrderGeneral走
	 */
	public List<OrderGeneralVO> getAllGuestExecutedOrderGeneral(final String guestID) {
		final List<OrderGeneralVO> orderGeneralVOs = getAllGuestOrderGeneral(guestID);
		return orderStateFilter(orderGeneralVOs, OrderState.EXECUTED);
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @param guestID 客户要查看个人<所有异常>订单时，客户的编号
	 * @return 客户个人<所有异常>订单
	 * 
	 * 直接从本层本模块getAllGuestOrderGeneral走
	 */
	public List<OrderGeneralVO> getAllGuestAbnormalOrderGeneral(final String guestID) {
		final List<OrderGeneralVO> orderGeneralVOs = getAllGuestOrderGeneral(guestID);
		return orderStateFilter(orderGeneralVOs, OrderState.ABNORMAL);
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @param guestID 客户要查看个人<所有已撤销>订单时，客户的编号
	 * @return 客户个人<所有已撤销>订单
	 * 
	 * 直接从本层本模块getAllGuestOrderGeneral走
	 */
	public List<OrderGeneralVO> getAllGuestCancelledOrderGeneral(final String guestID) {
		final List<OrderGeneralVO> orderGeneralVOs = getAllGuestOrderGeneral(guestID);
		return orderStateFilter(orderGeneralVOs, OrderState.CANCELLED);
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @param guestID 客户要查看个人<所有已评论>订单时，客户的编号
	 * @return 客户个人<所有已评论>订单
	 * 
	 * 直接从本层本模块getAllGuestOrderGeneral走
	 */
	public List<OrderGeneralVO> getAllGuestCommentedOrderGeneral(final String guestID) {
		final List<OrderGeneralVO> orderGeneralVOs = getAllGuestOrderGeneral(guestID);
		
		List<OrderGeneralVO> result = new ArrayList<OrderGeneralVO>();
		for (int i = 0; i < orderGeneralVOs.size(); i++) {
			OrderGeneralVO thisOrderGeneral = orderGeneralVOs.get(i);
			if (thisOrderGeneral.state == OrderState.EXECUTED && thisOrderGeneral.hasCommented == true) {
				result.add(thisOrderGeneral);
			}
		}
		return result;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @param guestID 客户要查看个人<所有未评价>订单时，客户的编号
	 * @return 客户个人<所有未评论>订单
	 * 
	 * 直接从本层本模块getAllGuestOrderGeneral走
	 */
	public List<OrderGeneralVO> getAllGuestUncommentedOrderGeneral(final String guestID) {
		final List<OrderGeneralVO> orderGeneralVOs = getAllGuestOrderGeneral(guestID);
		
		List<OrderGeneralVO> result = new ArrayList<OrderGeneralVO>();
		for (int i = 0; i < orderGeneralVOs.size(); i++) {
			OrderGeneralVO thisOrderGeneral = orderGeneralVOs.get(i);
			if (thisOrderGeneral.state == OrderState.EXECUTED && thisOrderGeneral.hasCommented == false) {
				result.add(thisOrderGeneral);
			}
		}
		return result;
	}
	
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param hotelID 酒店工作人员要查看本酒店<所有>订单时，酒店的编号
	 * @return 此酒店<所有>订单
	 */
	public List<OrderGeneralVO> getAllHotelOrderGeneral(final String hotelID) {
		final List<OrderGeneralVO> result = new ArrayList<OrderGeneralVO>();
		
		List<OrderGeneralPO> orderGeneralPOs = null;
		try {
			orderGeneralPOs = orderDataService.getAllHotelOrderGeneral(hotelID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if (orderGeneralPOs != null) {
			for (int i = 0; i < orderGeneralPOs.size(); i++) {
				result.add(new OrderGeneralVO(orderGeneralPOs.get(i)));
			}
		}
		
		return result;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @param hotelID 酒店工作人员要查看本酒店<所有未执行>订单时，酒店的编号
	 * @return 此酒店<所有未执行>的所有订单
	 * 
	 * 直接从本层本模块getAllHotelOrderGeneral走
	 */
	public List<OrderGeneralVO> getAllHotelUnexecutedOrderGeneral(final String hotelID) {
		final List<OrderGeneralVO> orderGeneralVOs = getAllHotelOrderGeneral(hotelID);
		return orderStateFilter(orderGeneralVOs, OrderState.UNEXECUTED);
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @param hotelID 酒店工作人员要查看本酒店<所有已执行>订单时，酒店的编号
	 * @return 此酒店<所有已执行>订单
	 * 
	 * 直接从本层本模块getAllHotelOrderGeneral走
	 */
	public List<OrderGeneralVO> getAllHotelExecutedOrderGeneral(final String hotelID) {
		final List<OrderGeneralVO> orderGeneralVOs = getAllHotelOrderGeneral(hotelID);
		return orderStateFilter(orderGeneralVOs, OrderState.EXECUTED);
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @param hotelID 酒店工作人员要查看本酒店<所有异常>订单时，酒店的编号
	 * @return 此酒店所有异常>订单
	 * 
	 * 直接从本层本模块getAllHotelOrderGeneral走
	 */
	public List<OrderGeneralVO> getAllHotelAbnormalOrderGeneral(final String hotelID) {
		final List<OrderGeneralVO> orderGeneralVOs = getAllHotelOrderGeneral(hotelID);
		return orderStateFilter(orderGeneralVOs, OrderState.ABNORMAL);
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @param hotelID 酒店工作人员要查看本酒店<所有已撤销>订单时，酒店的编号
	 * @return 此酒店<所有已撤销>订单
	 * 
	 * 直接从本层本模块getAllHotelOrderGeneral走
	 */
	public List<OrderGeneralVO> getAllHotelCancelledOrderGeneral(final String hotelID) {
		final List<OrderGeneralVO> orderGeneralVOs = getAllHotelOrderGeneral(hotelID);
		return orderStateFilter(orderGeneralVOs, OrderState.CANCELLED);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param date 网站营销人员撤销异常订单时输入的指定日期
	 * @return 网站营销人员需要查看的当天所有的异常订单
	 */
	public List<OrderGeneralVO> getAllAbnormalOrderGeneral(final LocalDate date) {
		final List<OrderGeneralVO> result = new ArrayList<OrderGeneralVO>();
		
		List<OrderGeneralPO> orderGeneralPOs = null;
		try {
			orderGeneralPOs = orderDataService.getAllAbnormalOrderGeneral(date);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if (orderGeneralPOs != null) {
			for (int i = 0; i < orderGeneralPOs.size(); i++) {
				result.add(new OrderGeneralVO(orderGeneralPOs.get(i)));
			}
		}
		
		return result;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/29
	 * @param date 网站营销人员查看未执行订单时输入的指定日期
	 * @return 网站营销人员需要查看的当天所有的未执行订单
	 */
	public List<OrderGeneralVO> getAllUnexecutedOrderGeneral(final LocalDate date) {
		final List<OrderGeneralVO> result = new ArrayList<OrderGeneralVO>();
		
		List<OrderGeneralPO> orderGeneralPOs = null;
		try {
			orderGeneralPOs = orderDataService.getAllUnexecutedOrderGeneral(date);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if (orderGeneralPOs != null) {
			for (int i = 0; i < orderGeneralPOs.size(); i++) {
				result.add(new OrderGeneralVO(orderGeneralPOs.get(i)));
			}
		}
		
		return result;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @param checkInVO 酒店工作人员更新订单入住信息
	 * @return 是否成功更新
	 */
	public ResultMessage updateCheckIn (CheckInVO checkInVO) {
		ResultMessage resultMessage = ResultMessage.FAIL;
		
		try {
			resultMessage = orderDataService.updateCheckIn(new CheckInPO(checkInVO));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return resultMessage;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @param checkInVO 酒店工作人员更新订单退房信息
	 * @return 是否成功更新
	 */
	public ResultMessage updateCheckOut (CheckOutVO checkOutVO) {
		ResultMessage resultMessage = ResultMessage.FAIL;
		
		try {
			resultMessage = orderDataService.updateCheckOut((new CheckOutPO(checkOutVO)));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return resultMessage;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/7
	 * @param hotelID 酒店工作人员／客户查看酒店的评论
	 * @return 此酒店的所有评价
	 */
	public Iterator<HotelEvaluationVO> getEvaluations(String hotelID) {
		final List<HotelEvaluationVO> result = new ArrayList<HotelEvaluationVO>();
		
		List<HotelEvaluationPO> hotelEvaluationPOs = null;
		try {
			hotelEvaluationPOs = orderDataService.getEvaluations(hotelID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if (hotelEvaluationPOs != null) {
			for (int i = 0; i < hotelEvaluationPOs.size(); i++) {
				result.add(new HotelEvaluationVO(hotelEvaluationPOs.get(i)));
			}
		}
		
		return result.iterator();
	}
	
	/*
	 * 只提供给Hotel的接口
	 */
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param guestID 客户需要查看个人定过的酒店时依照的客户个人编号
	 * @return 客户定过的酒店列表
	 */
	public List<String> getBookedHotels(final String guestID) {
		List<String> result = null;
		
		try {
			result = orderDataService.getBookedHotels(guestID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @param guestID 此客户的客户编号
	 * @param hotelID 此客户相对的酒店编号
	 * @return 此客户在此相应酒店预定过的订单状态
	 * 若此客户在此酒店没有订单记录，就返回null
	 * 
	 * 直接从本层本模块getAllGuestOrderGeneral走
	 */
	public OrderState getOrderState(String guestID, String hotelID) {
		List<OrderGeneralVO> guestOrders = getAllGuestOrderGeneral(guestID);
		
		List<OrderState> states = new ArrayList<OrderState>();
		if (guestOrders != null) {
			for (int i = 0; i < guestOrders.size(); i++) {
				OrderGeneralVO thisOrderGeneral = guestOrders.get(i);
				if (thisOrderGeneral.hotelID.equals(hotelID)) {
					states.add(thisOrderGeneral.state);
				}
			}
		}else {
			return OrderState.NULL;
		}
		
		if (states.size() == 0) {
			return OrderState.NULL;
		}else {
			return getMaxOrderState(states);
		}
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/5
	 * @param states 此客户在此相应酒店预定过的所有订单状态
	 * @return 优先级最高的订单状态（已取消 < 异常 < 未执行 < 已执行）
	 */
	private OrderState getMaxOrderState(List<OrderState> states) {
		List<Integer> integers = new ArrayList<Integer>();
		for (int i = 0; i < states.size(); i++) {
			integers.add(states.get(i).ordinal());
		}
		
		int indexOfMax = 0;
		for (int i = 0; i < integers.size(); i++) {
			if (integers.get(i) > integers.get(indexOfMax)) {
				indexOfMax = i;
			}
		}
		return OrderState.values()[integers.get(indexOfMax)];
	}
	
	
	private List<OrderGeneralVO> orderStateFilter(List<OrderGeneralVO> orderGenerals, OrderState expectOrderState) {
		System.out.println("filter to " + expectOrderState);
		
		List<OrderGeneralVO> result = new ArrayList<OrderGeneralVO>();
		for (int i = 0; i < orderGenerals.size(); i++) {
			OrderGeneralVO thisOrderGeneral = orderGenerals.get(i);
			if (thisOrderGeneral.state.equals(expectOrderState)) {
				result.add(thisOrderGeneral);
			}
		}
		return result;
	}
}
