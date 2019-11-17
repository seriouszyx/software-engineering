package businessLogic.hotelBL.hotelScan.searchCriteria;

import businessLogic.hotelBL.hotelScan.searchCriteria.searchCriteriaImpl.BookedOnlyCriteria;
import businessLogic.hotelBL.hotelScan.searchCriteria.searchCriteriaImpl.HotelNameCriteria;
import businessLogic.hotelBL.hotelScan.searchCriteria.searchCriteriaImpl.LevelSpanCriteria;
import businessLogic.hotelBL.hotelScan.searchCriteria.searchCriteriaImpl.NullCriteria;
import businessLogic.hotelBL.hotelScan.searchCriteria.searchCriteriaImpl.OriginPriceSpanCriteria;
import businessLogic.hotelBL.hotelScan.searchCriteria.searchCriteriaImpl.RemainRoomNumCriteria;
import businessLogic.hotelBL.hotelScan.searchCriteria.searchCriteriaImpl.RoomTypeCriteria;
import businessLogic.hotelBL.hotelScan.searchCriteria.searchCriteriaImpl.ScoreSpanCriteria;
import utilities.enums.SearchCriteriaType;
import vo.SearchCriteriaVO;

public class SearchCriteriaFactory {
	

	public SearchCriteria createSearchCriteria(SearchCriteriaType searchCriteriaType,SearchCriteriaVO vo){
		if(searchCriteriaType == SearchCriteriaType.HOTEL_NAME){
			return new HotelNameCriteria(vo.keyHotelName);
		}
		else if(searchCriteriaType == SearchCriteriaType.LEVEL_SPAN){
			return new LevelSpanCriteria(vo.minLevel, vo.maxLevel);
		}
		else if(searchCriteriaType == SearchCriteriaType.ORGIN_PRICE_SPAN){
			return new OriginPriceSpanCriteria(vo.minPrice, vo.maxPrice);
		}
		else if(searchCriteriaType == SearchCriteriaType.SCORE_SPAN){
			return new ScoreSpanCriteria(vo.minScore, vo.maxScore);
		}
		else if(searchCriteriaType == SearchCriteriaType.ROOM_TYPE){
			return new RoomTypeCriteria(vo.roomTypes);
		}
		else if(searchCriteriaType == SearchCriteriaType.REMAIN_ROOM_NUM){
			return new RemainRoomNumCriteria(vo.remainRoomNum);
		}
		else if(searchCriteriaType == SearchCriteriaType.BOOKED_ONLY){
			return new BookedOnlyCriteria();
		}
		return new NullCriteria();
	}
	
}
