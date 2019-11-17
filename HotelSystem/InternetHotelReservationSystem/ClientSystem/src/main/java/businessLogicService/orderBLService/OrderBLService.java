package businessLogicService.orderBLService;

/**
 * 
 * @author cuihua
 * lastChangedBy charles
 * updateTime 2016/12/8
 *
 * 重构Order模块
 */
public interface OrderBLService extends CommonOrderBLService, GuestOrderBLService, HotelWorkerOrderBLService, 
WebMarketerOrderBLService, OrderForHotelModuleBLService {
	
}
