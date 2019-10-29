package dataService.dao.impl;

import java.util.Iterator;
import java.util.List;

import common.ResultMessage;
import dataService.dao.service.HotelPromotionDao;
import dataService.dataHelper.impl.DataFactoryImpl;
import dataService.dataHelper.service.DataFactory;
import dataService.dataHelper.service.HotelPromotionDataHelper;
import po.ActivityPromotionPo;
import po.BirthdayProPo;
import po.CompanyProPo;
import po.RoomPromotionPo;

/**
 * @version 2017-01-01
 * @author 金灵益
 */
public class HotelPromotionDaoImpl implements HotelPromotionDao{
	
	private DataFactory dataFactory;
	
	private HotelPromotionDataHelper hotelPromotionDataHelper;
	
	private static HotelPromotionDaoImpl hotelPromotionDaoImpl;
	
	public static HotelPromotionDaoImpl getInstance(){
		if(hotelPromotionDaoImpl == null){
			hotelPromotionDaoImpl = new HotelPromotionDaoImpl();
		}
		return hotelPromotionDaoImpl;
	}
	
	private HotelPromotionDaoImpl(){
		super();
		dataFactory = new DataFactoryImpl();
		hotelPromotionDataHelper = dataFactory.getHotelPromotionDataHelper();
	}
	
	
	@Override
	public ResultMessage addActPromotion(String hotelId, ActivityPromotionPo po){
		List<String> list = getActProList(hotelId);
		if(!list.isEmpty()){
			Iterator<String> it = list.iterator();
			while(it.hasNext()){
				//若存在该活动
				if(it.next().startsWith(po.getPromotionName()))
					return ResultMessage.FAIL;
			}
		}
		return hotelPromotionDataHelper.addActivity(hotelId, po);
	}
	
	
	@Override
	public ResultMessage upActPromotion(String hotelId, ActivityPromotionPo po){
		List<String> list = getActProList(hotelId);
		if( !list.isEmpty()){
			Iterator<String> it = list.iterator();
			while(it.hasNext()){
				//若存在该活动
				if(it.next().startsWith(po.getPromotionName()))
					return hotelPromotionDataHelper.updateActivity(hotelId, po);
			}
		}
		//不存在该活动
		return ResultMessage.FAIL;
	}
	
	
	@Override
	public ResultMessage delActPromotion(String hotelId, ActivityPromotionPo po){
		List<String> activityList = getActProList(hotelId);
			
		if(!activityList.isEmpty()){
			Iterator<String> it = activityList.iterator();
			
			//检查有无该促销活动
			while(it.hasNext()){
				String [] token = it.next().split("/");
				if(token[0].equals(po.getPromotionName()))
					return hotelPromotionDataHelper.deleteActivity(hotelId, po);
			}	
		}
		
		return ResultMessage.FAIL;//不存在该活动
	}
	
	public List<String> getActProList(String hotelId){
		return hotelPromotionDataHelper.getActivity(hotelId);
	}

	public ResultMessage upBirthPromotion(String hotelId, BirthdayProPo po){
		return hotelPromotionDataHelper.updateBirthPromotion(hotelId, po);
	}
	
	public BirthdayProPo getBirthPromotion(String hotelId){
		return hotelPromotionDataHelper.getBirthPromotion(hotelId);
	}
	
	public ResultMessage updateCooperPro(String hotelId, CompanyProPo po){
		return hotelPromotionDataHelper.updateCompanyPro(hotelId, po);
	}
	
	public CompanyProPo getCooperPro(String hotelId){
		return hotelPromotionDataHelper.getCompanyPro(hotelId);
	}
	
	public ResultMessage upRoomPromotion(String hotelId, RoomPromotionPo po){
		return hotelPromotionDataHelper.updateRoomPromotion(hotelId, po);
	}
	
	public RoomPromotionPo getRoomPromotion(String hotelId){
		return hotelPromotionDataHelper.getRoomPromotion(hotelId);
	}
}
