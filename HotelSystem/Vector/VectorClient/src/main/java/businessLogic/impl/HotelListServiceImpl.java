package businessLogic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import businessLogic.service.HotelListService;
import common.RoomType;
import dataService.dao.service.HotelDao;
import po.HotelPo;
import rmi.RemoteHelper;
import vo.HotelTypeRoomVo;
import vo.HotelVo;

public class HotelListServiceImpl implements HotelListService{
	private List<HotelVo> hotelList = new ArrayList<HotelVo>();  //只通过地址和商圈筛选得到
	private HotelDao hotelDao;
	private static HotelListServiceImpl hotelListServiceImpl;
  
    public static HotelListServiceImpl getInstance(){
        if( hotelListServiceImpl == null)
        	hotelListServiceImpl = new HotelListServiceImpl() ;
        return hotelListServiceImpl;
    }

    private HotelListServiceImpl(){
        hotelDao = RemoteHelper.getInstance().getHotelDao();
    }

	@Override
	public List<HotelVo> sortByName(List<HotelVo> list, boolean isRise) {
		if(isRise){
			list.sort((HotelVo h1,HotelVo h2) -> h1.getHotelName().compareTo(h2.getHotelName()));
        } else {
        	list.sort((HotelVo h1,HotelVo h2) -> h2.getHotelName().compareTo(h1.getHotelName()));
        }
		return list;
	}

	@Override
	public List<HotelVo> sortByStar(List<HotelVo> list, boolean isRise) {
		if(isRise){
			Collections.sort(list, new Comparator<HotelVo>() {
				@Override
				public int compare(HotelVo h1, HotelVo h2) {
					int star1 = h1.getStars();
					int star2 = h2.getStars();
					if(star1 - star2 > 0) return 1;
					if(star1 - star2 < 0) return -1;
					else return 0;
				}
			});
		}else{
			Collections.sort(list, new Comparator<HotelVo>() {
				@Override
				public int compare(HotelVo h1, HotelVo h2) {
					int star1 = h1.getStars();
					int star2 = h2.getStars();
					if(star2 - star1 > 0) return 1;
					if(star2 - star1 < 0) return -1;
					else return 0;
				}
			});
		}
		return list;
	}

	@Override
	public List<HotelVo> sortByPoint(List<HotelVo> list, boolean isRise) {
		if(isRise){
			Collections.sort(list, new Comparator<HotelVo>() {
				@Override
				public int compare(HotelVo h1, HotelVo h2) {
					double p1 = h1.getPoStrings();
					double p2 = h2.getPoStrings();
					if(p1 - p2 > 0) return 1;
					if(p1 - p2 < 0) return -1;
					else return 0;
				}
			});
		}else{
			Collections.sort(list, new Comparator<HotelVo>() {
				@Override
				public int compare(HotelVo h1, HotelVo h2) {
					double p1 = h1.getPoStrings();
					double p2 = h2.getPoStrings();
					if(p2 - p1 > 0) return 1;
					if(p2 - p1 < 0) return -1;
					else return 0;
				}
			});
		}
		return list;
	}

	@Override
	public List<HotelVo> sortByPrice(List<HotelVo> list, RoomType type, boolean isRise) {
		if(isRise){
			Collections.sort(list, new Comparator<HotelVo>() {
				@Override
				public int compare(HotelVo h1, HotelVo h2) {
					int p1 = h1.getOriginPrice(type);
					int p2 = h2.getOriginPrice(type);
					if(p1 - p2 > 0) return 1;
					if(p1 - p2 < 0) return -1;
					else return 0;
				}
			});
		}else{
			Collections.sort(list, new Comparator<HotelVo>() {
				@Override
				public int compare(HotelVo h1, HotelVo h2) {
					int p1 = h1.getOriginPrice(type);
					int p2 = h2.getOriginPrice(type);
					if(p2 - p1 > 0) return 1;
					if(p2 - p1 < 0) return -1;
					else return 0;
				}
			});
		}
		return list;
	}

	@Override
	public List<HotelVo> findByKeyword(String key) {	
		List<HotelVo> list = new ArrayList<HotelVo>();
		List<HotelPo> poList = new ArrayList<HotelPo>();
		
		//考虑列表为空的情况，保护程序
		if( !poList.isEmpty() ){
			Iterator<HotelPo> it = poList.iterator();
			while(it.hasNext()){
				HotelVo vo = new HotelVo(it.next());
				
				if(vo.showInfo().contains(key))
					list.add(vo);
			}
		}
		
		return list;
	}
	
	@Override
	public List<HotelVo> findByAddress(String province, String city, String business){
		hotelList = new ArrayList<HotelVo>();
			
		List<HotelPo> list = hotelDao.keyFind(province + " " + city + " " + business);
		
		if(!list.isEmpty()){
			Iterator<HotelPo> it = list.iterator();
			while(it.hasNext()){
				//po --> vo
				hotelList.add(new HotelVo(it.next()));
			}
		}
		
		return hotelList;
	}

	@Override
	public List<String> getProvinceList(){
			return hotelDao.getProvinceList();
	}
	
	@Override
	public List<String> getCityList(String province){	
		return hotelDao.getCityList(province);
	}
	
	@Override
	public List<String> getBusinessList(String province, String city){
		return hotelDao.getBusinessList(province, city);
	}

	@Override
	public List<HotelVo> findByOriginalPrice(RoomType type, int low, int high, List<HotelVo> list) {
		List<HotelVo> findList = new ArrayList<HotelVo>();
	
		if( !list.isEmpty()){
			for(HotelVo hotel : list){
				int price = hotel.getOriginPrice(type);
			
				
				if(price >= low && price <= high){
					findList.add(hotel);
				}
			}
		}
		return findList;
	}
	
	@Override
	public List<HotelVo> findByPoint(double least, double max, List<HotelVo> list) {
		List<HotelVo> findList = new ArrayList<HotelVo>();
		
		if( !list.isEmpty()){
			for(HotelVo hotel : list){
				if(hotel.getPoStrings() >= least && hotel.getPoStrings() <= max){
					findList.add(hotel);
				}
			}
		}
		
		return findList;
	}

	@Override
	public List<HotelVo> findByStars(int least, int max, List<HotelVo> list) {
		List<HotelVo> findList = new ArrayList<HotelVo>();
		
		if( !list.isEmpty() ){
			for(HotelVo hotel : list){
				if(hotel.getStars() >= least && hotel.getStars() <= max){
					findList.add(hotel);
				}
			}
		}
		
		return findList;
	}

	@Override
	public List<HotelVo> findByRoomType(RoomType type, List<HotelVo> list){
		List<HotelVo> findList = new ArrayList<HotelVo>();
		
		if(!list.isEmpty()){
			for(HotelVo hotel : list){
				List<HotelTypeRoomVo> typeRoomList = hotel.getTypeRoom();
			
				if(typeRoomList.isEmpty()) continue;
				Iterator<HotelTypeRoomVo> it = typeRoomList.iterator();
			
				while(it.hasNext()){
					if(it.next().getType().equals(type))
						findList.add(hotel);
				}
			}
		}
		return findList;
	}
}
