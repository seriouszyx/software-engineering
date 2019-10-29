package dataService.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import common.ResultMessage;
import common.RoomType;
import dataService.dao.service.HotelDao;
import dataService.dataHelper.impl.DataFactoryImpl;
import dataService.dataHelper.service.DataFactory;
import dataService.dataHelper.service.HotelDataHelper;
import po.HotelPo;
import po.HotelTypeRoomPo;

public class HotelDaoImpl implements HotelDao{
	
	private Map<String, HotelPo> map;
	
	private HotelDataHelper hotelDataHelper;
	
	private DataFactory dataFactory;
	
	private static HotelDaoImpl hotelDataServiceImpl;
	
	public static HotelDaoImpl getInstance(){
		if(hotelDataServiceImpl == null){
			hotelDataServiceImpl = new HotelDaoImpl();
		}
		
		return hotelDataServiceImpl;
	}

	public HotelDaoImpl(){
		if(map == null){
			dataFactory = new DataFactoryImpl();
			hotelDataHelper = dataFactory.getHotelDataHelper();
			map = hotelDataHelper.getHotelData();
		}
	}

	
	@Override
	public ResultMessage addHotelPO(HotelPo po) {
		if(!map.containsKey(po.getId())) {	    
			hotelDataHelper.addHotelData(po);
			map.put(po.getId(), po);
			return ResultMessage.SUCCEED;   
		}
		//若已存在该po
		return ResultMessage.FAIL;	
	}
	
	
	@Override
	public ResultMessage updateHotelList(HotelPo po) {
		String hotelId = po.getId();
		if(map.get(hotelId) != null){
			map.put(hotelId, po);//修改map对应元素
			
			hotelDataHelper.updateHotelListData(map);
			return ResultMessage.SUCCEED;
		}
		return ResultMessage.FAIL;
	}
	
	
	@Override
	public ResultMessage initHotelTypeRoom(String hotelId, RoomType type, int number, int price){
		if(map.get(hotelId) != null){
			if(price <= 0 && number < 0)   
				return ResultMessage.FAIL;
			return hotelDataHelper.initRoom(hotelId, type, number, price);
		}
		return ResultMessage.FAIL;
	}
	
	
	@Override
	public ResultMessage updateComment(HotelPo po){
		String hotelId = po.getId();
		if(map.get(hotelId) != null){
			return hotelDataHelper.updateComments(hotelId, po.getCommentList());
		}
		return ResultMessage.FAIL;
	}
	
	
	@Override
	public ResultMessage deleteHotelPO(String hotelId){
		return hotelDataHelper.deleteHotelData(hotelId);
	}

	
	@Override
	public HotelPo findHotel(String hotelId){
		Iterator<Map.Entry<String,HotelPo>> iterator = map.entrySet().iterator();

		while(iterator.hasNext()){
			Map.Entry<String, HotelPo> entry = iterator.next();
			HotelPo po = entry.getValue();
			if(po.getId().equals(hotelId)){
				return po;
			}
		}
		return null;
	}

	
	@Override
	public List<HotelPo> keyFind(String key){
		List<HotelPo> hotelList = new ArrayList<HotelPo>();
		Iterator<Map.Entry<String,HotelPo>> iterator = map.entrySet().iterator();
		
		while(iterator.hasNext()){
			Map.Entry<String, HotelPo> entry = iterator.next();
			HotelPo po = entry.getValue();
			//根据酒店基本信息包含关键字，都符合条件
			if(po.showInfo().contains(key)){
				hotelList.add(po);
			}
		}
		return hotelList;
	}

	
	@Override
	public List<String> getProvinceList(){
		return hotelDataHelper.getProvinceList();
	}
	
	
	@Override
	public List<String> getCityList(String province){
		return hotelDataHelper.getCityList(province);
	}
	
	
	@Override
	public List<String> getBusinessList(String province, String city){
		return hotelDataHelper.getBusinessList(province, city);
	}
	
	
	@Override
	public ResultMessage updateOrderedRoom(String hotelId, RoomType type, int number, boolean isCheckIn){
		return hotelDataHelper.updateOrderedRoom(hotelId, type, number, isCheckIn);
	}
	
	
	@Override
	public int getReadyRoom(String hotelId, RoomType type){
		map = hotelDataHelper.getHotelData();
		HotelPo po = findHotel(hotelId);
		
		List<HotelTypeRoomPo> list = po.getTypeRoom();
		int sum = 0;
		int readyRoom = 0;
		
		if( !list.isEmpty() ){
			Iterator<HotelTypeRoomPo> it = list.iterator();
		
			//获得酒店该类型房间的总数
			while(it.hasNext()){
				HotelTypeRoomPo rpo = it.next();
			
				if(rpo.getType().equals(type)){
					sum = rpo.getNumOfTypeRoom();
					break;
				}
			}
			//根据被预订数量计算
			readyRoom = sum - hotelDataHelper.getOrderedRoom(hotelId, type);
		}
		return readyRoom;
	}
}