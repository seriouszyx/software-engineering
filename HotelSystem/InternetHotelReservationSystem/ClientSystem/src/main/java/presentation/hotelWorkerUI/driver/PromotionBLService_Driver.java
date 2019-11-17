package presentation.hotelWorkerUI.driver;

import businessLogic.promotionBL.stub.PromotionBLService_Stub;

public class PromotionBLService_Driver {
	PromotionBLService_Stub promotionBLService;
	
	public PromotionBLService_Driver(PromotionBLService_Stub stub) {
		this.promotionBLService = stub;
	}
}
