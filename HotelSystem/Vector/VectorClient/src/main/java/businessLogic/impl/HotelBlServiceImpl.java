package businessLogic.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import businessLogic.service.HotelBlService;
import common.ResultMessage;
import common.RoomType;
import dataService.dao.service.HotelDao;
import po.HotelPo;
import po.HotelTypeRoomPo;
import rmi.RemoteHelper;
import vo.HotelTypeRoomVo;
import vo.HotelVo;

public class HotelBlServiceImpl implements HotelBlService{

	private HotelDao hotelDao;
	private static HotelBlServiceImpl hotelBlServiceImpl;
	private HotelVo vo;
	
	public static HotelBlServiceImpl getInstance(){
		if( hotelBlServiceImpl == null)
			hotelBlServiceImpl = new HotelBlServiceImpl() ;
		return hotelBlServiceImpl;	
	}

	private HotelBlServiceImpl(){
		if(hotelDao == null)
			hotelDao = RemoteHelper.getInstance().getHotelDao();
	}
	
	
	@Override
	public HotelVo getHotelVo(String hotelId){
		this.vo = new HotelVo(hotelDao.findHotel(hotelId));
		return vo;
	}
	
	
    @Override
    public ResultMessage addHotel(String hotelId){
    	HotelPo po = new HotelPo(hotelId);
    	System.out.println(hotelId);
    	return hotelDao.addHotelPO(po);
    }
    
    
    @Override
    public ResultMessage updateBasicInfo(HotelVo vo){
    	return hotelDao.updateHotelList(voTopo(vo));
    }
    
    
    @Override
    public ResultMessage deleteHotel(String hotelId){
    	return hotelDao.deleteHotelPO(hotelId);
    }
    
    
    @Override
    public ResultMessage initializeRoom(String hotelId, RoomType type, int number, int price){
    	return hotelDao.initHotelTypeRoom(hotelId, type, number, price);
    }

    
    @Override
    public ResultMessage checkoutRoom(RoomType type, int number){
    	return hotelDao.updateOrderedRoom(vo.getId(), type, number, false);
    }
    
    
    @Override
    public ResultMessage bookRoom(RoomType type, int number){
    	//订房数量不超过剩余房间数
    	if(number <= getReadyRoom(type))
    		return hotelDao.updateOrderedRoom(vo.getId(), type, number, true);
		
    	//房间数量不够
    	return ResultMessage.FAIL;
    }
    
    
    @Override
	public int getReadyRoom(RoomType type) {
    	return hotelDao.getReadyRoom(vo.getId(), type);
	}
    

    @Override
	public ResultMessage comment(String orderId, String giveComment, double poStrings) {
		List<String> comment = vo.getCommentList();
		//酒店评论为空的情况
		if(!comment.isEmpty()){
			comment = new ArrayList<String>();
		}
		comment.add(giveComment);
		vo.setCommentList(comment);
			
		ResultMessage r1 = hotelDao.updateComment(voTopo(vo));
		
		ResultMessage r2 = givePoStrings(orderId, poStrings);
		
		if(r1 == ResultMessage.SUCCEED && r2 == ResultMessage.SUCCEED)
			return ResultMessage.SUCCEED;
		
		return ResultMessage.FAIL;
	}

    
    /**
     * 客户给予评分,同时将订单状态置为已评价状态
     * @param orderId
     * @param poStrings
     * @return
     */
	private ResultMessage givePoStrings(String orderId, double poStrings) {
		//计算评分
		double points = vo.getPoStrings();
		int num = vo.getNumOfpoint();
		points = (points * num + poStrings) / (num+1);
		
		//保留1位小数
		DecimalFormat df = new DecimalFormat("#.0");
		points = Double.parseDouble(df.format(points));
		vo.setNumOfPoint(num + 1);                  //评分人数发生变化
		vo.setPoStrings(points);
		
		ResultMessage r1 = OrderBlServiceImpl.getInstance().setToFinished(orderId);   //更新订单状态
		ResultMessage r2 = updateBasicInfo(vo);
		
		if(r1.equals(ResultMessage.SUCCEED) && r2.equals(ResultMessage.SUCCEED)) return ResultMessage.SUCCEED;
		else  return ResultMessage.FAIL;
	}

	
	/**
	 * HotelVo -> HotelPo
	 * @param vo
	 * @return
	 */
	private HotelPo voTopo(HotelVo vo){
		//HotelTypeRoomVo --> po
		List<HotelTypeRoomPo> typeRoomListPo = new ArrayList<HotelTypeRoomPo>();
		
		if( !vo.getTypeRoom().isEmpty()){
			Iterator<HotelTypeRoomVo> it = vo.getTypeRoom().iterator();
			while(it.hasNext()){
				HotelTypeRoomVo htrv = it.next();
				HotelTypeRoomPo htrp = new HotelTypeRoomPo(htrv.getType(),htrv.getNumOfTypeRoom(),
					                                   htrv.getPrice());
			
				typeRoomListPo.add(htrp);
			}
		}
		
		
		HotelPo po = new HotelPo(vo.getId(), vo.getHotelName(),vo.getProvince(),vo.getHotelCity(), vo.getHotelPosition(),
				vo.getInBusiness(), vo.getHotelTel(), vo.getStars(), vo.getPoStrings(), vo.getNumOfpoint(),
				vo.getHotelInfo(), vo.getCommentList(), typeRoomListPo);
		return po;
	}
}
