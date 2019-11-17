package dataService.hotelWorkerDataService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import po.HotelWorkerPO;
import utilities.Ciphertext;
import utilities.enums.ResultMessage;

public class HotelWorkerDataService_Stub extends UnicastRemoteObject implements HotelWorkerDataService{

	
	public HotelWorkerDataService_Stub() throws RemoteException {
		super();
	}


	public HotelWorkerPO getSingleHotelWorker(String hotelWorkerID) {
		Ciphertext b = new Ciphertext();
		return new HotelWorkerPO("00001111", b.encrypt("123456"),"school");
	}

	
	public List<HotelWorkerPO> getAllHotelWorker() {
		Ciphertext a = new Ciphertext();
		List<HotelWorkerPO>  list= new ArrayList<HotelWorkerPO>();
		HotelWorkerPO b= new HotelWorkerPO("00001111", a.encrypt("123456"),"school");
		list.add(b);
		return list;
	}
	
	
	public HotelWorkerPO add(HotelWorkerPO newHotelWorkerPO) {
		Ciphertext a = new Ciphertext();
		return new HotelWorkerPO("00001111",a.encrypt("123456"),"school");
	}

	
	public ResultMessage modify(HotelWorkerPO hotelWorkerPO) {
		return ResultMessage.SUCCESS;
	}

	
	public ResultMessage initHotelWorker(String hotelWorkerID) {
		return ResultMessage.SUCCESS;
	}

}
