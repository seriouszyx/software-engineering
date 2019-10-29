package businessLogic.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import businessLogic.service.OrderBlService;
import common.OrderCondition;
import common.ResultMessage;
import common.RoomType;
import dataService.dao.service.OrderDao;
import po.OrderPo;
import rmi.RemoteHelper;
import vo.OrderVo;

public class OrderBlServiceImpl implements OrderBlService {
	
	private OrderDao orderDao;
	private static OrderBlServiceImpl orderBlServiceImpl;
	
    public static OrderBlServiceImpl getInstance(){
        if( orderBlServiceImpl == null)
            orderBlServiceImpl = new OrderBlServiceImpl() ;
        return orderBlServiceImpl;
    }
    
    private OrderBlServiceImpl(){
        orderDao = RemoteHelper.getInstance().getOrderDao();
    }
	
    @Override
    public OrderVo getOrder(String orderId) {
    	return new OrderVo(orderDao.findOrder(orderId));
    }
    
	@Override
	public String getOrderId(String orderId) {
		return orderDao.findOrder(orderId).getOrderId();
	}
	
	@Override
	public OrderCondition getOrderCondition(String orderId) {
		return orderDao.findOrder(orderId).getCondition();
	}
	
	@Override
	public String getMemberId(String orderId) {
		return orderDao.findOrder(orderId).getMemberId();
	}
    
	@Override
	public String getMemberName(String orderId) {
		return orderDao.findOrder(orderId).getMemberName();
	}

	@Override
	public Date getCreateTime(String orderId) {
		return orderDao.findOrder(orderId).getCreateTime();
	}
	
	@Override
	public Date getCheckInTime(String orderId) {
		return orderDao.findOrder(orderId).getCheckInTime();
	}
	
	@Override
	public Date getCheckOutTime(String orderId) {
		return orderDao.findOrder(orderId).getCheckOutTime();
	}
	
	@Override
	public String getHotelName(String orderId) {
		return orderDao.findOrder(orderId).getHotel();
	}
	
	@Override
	public String getHotelId(String orderId) {
		return orderDao.findOrder(orderId).getHotelId();
	}
	
	@Override
	public RoomType getRoomType(String orderId) {
		return orderDao.findOrder(orderId).getRoomType();
	}
	
	@Override
	public int getNumOfRoom(String orderId) {
		return orderDao.findOrder(orderId).getNumOfRoom();
	}
	
	@Override
	public int getNumOfGuest(String orderId) {
		return orderDao.findOrder(orderId).getNumOfGuest();
	}
	
	@Override
	public boolean getChildExist(String orderId) {
		return orderDao.findOrder(orderId).getChildExist();
	}
	
	@Override
	public int getOriginalPrice(String orderId) {
		return orderDao.findOrder(orderId).getOriginalPrice();
	}
	
	@Override
	public double getDiscount(String orderId) {
		return orderDao.findOrder(orderId).getDiscount();
	}

	@Override
	public int getDiscountedPrice(String orderId) {
		return orderDao.findOrder(orderId).getDiscountedPrice();
	}


	@Override
	public List<OrderVo> getAllOrdersByHotel(String hotelId) {
		List<OrderVo> voList = new ArrayList<OrderVo>();
		List<OrderPo> poList = orderDao.getAllByHotel(hotelId);
		for(int i = 0; i < poList.size(); i++) {
			voList.add(new OrderVo(poList.get(i)));
		}
		return voList;
	}
	
	@Override
	public List<OrderVo> getAllOrdersByMember(String memberId) {
		List<OrderVo> voList = new ArrayList<OrderVo>();
		List<OrderPo> poList = orderDao.getAllByMember(memberId);
		//System.out.println("test:" + poList.size());
		for(int i = 0; i < poList.size(); i++) {
			voList.add(new OrderVo(poList.get(i)));
		}
		return voList;
	}

	@Override
	public List<OrderVo> getOrdersInConditionByHotel(String hotelId, OrderCondition condition) {
		List<OrderVo> allList = getAllOrdersByHotel(hotelId);
		List<OrderVo> conditionList = new ArrayList<OrderVo>();
		for(int i = 0; i < allList.size(); i++) {
			if(allList.get(i).getCondition() == condition) {
				conditionList.add(allList.get(i));
			}
		}
		return conditionList;
	}
	
	@Override
	public List<OrderVo> getOrdersInConditionByMember(String memberId, OrderCondition condition) {
		List<OrderVo> allList = getAllOrdersByMember(memberId);
		List<OrderVo> conditionList = new ArrayList<OrderVo>();
		for(int i = 0; i < allList.size(); i++) {
			if(allList.get(i).getCondition() == condition) {
				conditionList.add(allList.get(i));
			}
		}
		return conditionList;
	}
	
	@Override
	public List<OrderVo> getNotExecutedOrderByWebSite() {
		List<OrderVo> voList = new ArrayList<OrderVo>();
		List<OrderPo> poList = orderDao.getNotExecuted();
		for(int i = 0; i < poList.size(); i++) {
			voList.add(new OrderVo(poList.get(i)));
		}
		return voList;
	}
	
	@Override
	public List<OrderVo> getAbnormalByWebSite() {
		List<OrderVo> voList = new ArrayList<OrderVo>();
		List<OrderPo> poList = orderDao.getAbnormal();
		for(int i = 0; i < poList.size(); i++) {
			voList.add(new OrderVo(poList.get(i)));
		}
		return voList;
	}
	
	@Override
	public ResultMessage submit(String memberId, String planCheckInTimeStr, String hotelId, int numOfDays,
			RoomType roomType, int numOfRoom, int numOfGuest, boolean childExist) {
		HotelBlServiceImpl.getInstance().getHotelVo(hotelId);
		if(HotelBlServiceImpl.getInstance().bookRoom(roomType, numOfRoom) == ResultMessage.FAIL) {
			return ResultMessage.FAIL;
		}
		Date d = new Date();  
		SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyyMMdd");  
		String dateNowStr = sdf_1.format(d);
		Random rm = new Random();
		double pross = (1 + rm.nextDouble()) * Math.pow(10, 5);
		String randomStr = String.valueOf(pross).substring(1, 6);
		
		String orderId = dateNowStr + randomStr;
		
		String memberName = MemberBlServiceImpl.getInstance().getInfo(memberId).getName();
		
		Date createTime = new Date();
		
	    SimpleDateFormat sdf_2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    Date planCheckInTime = null;
	    Date invalidDate = null;
		try {
			invalidDate = sdf_2.parse("1970-01-01 00:00:00");
			planCheckInTime = sdf_2.parse(planCheckInTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		
		String hotelName = HotelBlServiceImpl.getInstance().getHotelVo(hotelId).getHotelName();
		//获取酒店此种房间单价
		int originalPrice = HotelBlServiceImpl.getInstance().getHotelVo(hotelId).getOriginPrice(roomType);
		
		//获取所有适用的打折策略并取最低的计算折扣
		double minDiscount = 1.0;
		List<Double> hotelDiscountList = new ArrayList<Double>();
		hotelDiscountList = HotelPromotionBlServiceImpl.getInstance().getCurrentActDiscount(hotelId);
		hotelDiscountList.add(HotelPromotionBlServiceImpl.getInstance().getOrderRoomDiscount(hotelId, numOfRoom));
		hotelDiscountList.add(HotelPromotionBlServiceImpl.getInstance().getBirthStrategy(hotelId,
				MemberBlServiceImpl.getInstance().getInfo(memberId).getBirthday()));
		hotelDiscountList.add(HotelPromotionBlServiceImpl.getInstance().getCooperationStrategy(hotelId, memberId));
		
		List<Double> marketDiscountList = MarketPromotionBlServiceImpl.getInstance().getCurrentActDiscount();
		marketDiscountList.add(MarketPromotionBlServiceImpl.getInstance()
				.getBusinessDiscount(HotelBlServiceImpl.getInstance().getHotelVo(hotelId).getInBusiness()));
		marketDiscountList.add(MarketPromotionBlServiceImpl.getInstance()
				.getLevelStrategy(MemberBlServiceImpl.getInstance().getInfo(memberId).getVip()));
		
		for(double discount : hotelDiscountList) {
			if(discount < minDiscount) {
				minDiscount = discount;
			}
		}
		for(double discount : marketDiscountList) {
			if(discount < minDiscount) {
				minDiscount = discount;
			}
		}
		//由单价，天数，折扣计算最终价格
		int discountedPrice = (int) (minDiscount * originalPrice * numOfDays * numOfRoom);
		//由上面所有信息创建orderPo对象并存入数据层
		OrderPo orderPo = new OrderPo(orderId, OrderCondition.WAITING, memberId, memberName, createTime,
				planCheckInTime, invalidDate, invalidDate, invalidDate, hotelName, hotelId, numOfDays, roomType, numOfRoom, numOfGuest,
				childExist, originalPrice, minDiscount, discountedPrice);
		return orderDao.insertOrder(orderPo);
	}

	@Override
	public ResultMessage setToAbnormal() {
		List<OrderPo> poList = orderDao.getNotExecuted();
		for(OrderPo orderPo : poList) {
			if(orderPo.getPlanCheckInTime().before(new Date())) {
				orderPo.setCondition(OrderCondition.ABNORMAL);
				MemberBlServiceImpl.getInstance().chargeCredit(orderPo.getMemberId(), - orderPo.getDiscountedPrice());
				ResultMessage updateSucceed = orderDao.updateOrder(orderPo);
				if(updateSucceed == ResultMessage.FAIL) {
					return ResultMessage.FAIL;
				}
			}
		}
		return ResultMessage.SUCCEED;
	}	

	@Override
	public ResultMessage cancel(String orderId) {
		OrderPo orderPo = orderDao.findOrder(orderId);
		orderPo.setCondition(OrderCondition.CANCELED);
		return orderDao.updateOrder(orderPo);
	}
	
	@Override
	public ResultMessage checkIn(String orderId) {
		OrderPo orderPo = orderDao.findOrder(orderId);
		orderPo.setCondition(OrderCondition.EXECUTING);
		orderPo.setCheckInTime(new Date());
		CreditBlServiceImpl.getInstance().addCreditByOrder(orderPo.getMemberId(), orderPo.getDiscountedPrice(), new OrderVo(orderPo));
		return orderDao.updateOrder(orderPo);
	}

	@Override
	public ResultMessage abnormalCheckIn(String orderId) {
		OrderPo orderPo = orderDao.findOrder(orderId);
		if(orderPo.getCondition() == OrderCondition.ABNORMAL) {
			orderPo.setCondition(OrderCondition.EXECUTING);
			orderPo.setCheckInTime(new Date());
			CreditBlServiceImpl.getInstance().addCreditByOrder(orderPo.getMemberId(), orderPo.getDiscountedPrice(), new OrderVo(orderPo));
			return orderDao.updateOrder(orderPo);
		}
		else {
			return ResultMessage.FAIL;
		}
	}
	
	@Override
	public ResultMessage checkOut(String orderId) {
		OrderPo orderPo = orderDao.findOrder(orderId);
		orderPo.setCondition(OrderCondition.EXECUTED);
		orderPo.setCheckOutTime(new Date());
		HotelBlServiceImpl.getInstance().getHotelVo(orderPo.getHotelId());
		ResultMessage roomUpdate = HotelBlServiceImpl.getInstance().checkoutRoom(orderPo.getRoomType(), orderPo.getNumOfRoom());
		ResultMessage orderUpdate = orderDao.updateOrder(orderPo);
		if(roomUpdate == ResultMessage.SUCCEED && orderUpdate == ResultMessage.SUCCEED) {
			return ResultMessage.SUCCEED;
		}
		else {
			return ResultMessage.FAIL;
		}
	}

	@Override
	public ResultMessage revoke(String orderId, double allOrHalf) {
		OrderPo orderPo = orderDao.findOrder(orderId);
		orderPo.setCondition(OrderCondition.REVOKED);
		orderPo.setRevokeTime(new Date());
		CreditBlServiceImpl.getInstance().addCreditByOrder(orderPo.getMemberId(), (int)allOrHalf * orderPo.getDiscountedPrice(), new OrderVo(orderPo));
		return orderDao.updateOrder(orderPo);
	}
	
	@Override
	public ResultMessage delete(String orderId) {
		return orderDao.deleteOrder(orderId);
	}

	@Override
	public ResultMessage setToFinished(String orderId) {
		OrderPo orderPo = orderDao.findOrder(orderId);
		orderPo.setCondition(OrderCondition.FINISHED);
		return orderDao.updateOrder(orderPo);
	}

}
