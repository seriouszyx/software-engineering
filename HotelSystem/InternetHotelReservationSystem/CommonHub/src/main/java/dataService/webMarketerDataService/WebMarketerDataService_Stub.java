package dataService.webMarketerDataService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import po.WebMarketerPO;
import utilities.Ciphertext;
import utilities.enums.ResultMessage;

public class WebMarketerDataService_Stub extends UnicastRemoteObject implements WebMarketerDataService{

	
	public WebMarketerDataService_Stub() throws RemoteException {
		super();
	}


	public WebMarketerPO getSingleWebMarketer(String webMarketID) {
		Ciphertext b = new Ciphertext();
		return new WebMarketerPO("000001", b.encrypt("123456"));
	}

	
	public List<WebMarketerPO> getAllWebMarketer() {
		Ciphertext b = new Ciphertext();
		List<WebMarketerPO> list= new ArrayList<WebMarketerPO>();
		WebMarketerPO a= new WebMarketerPO("000001", b.encrypt("123456"));
		list.add(a);
		return list;
	}
	
	
	public WebMarketerPO add(WebMarketerPO newWebMarketerPO) {
		Ciphertext b = new Ciphertext();
		return new WebMarketerPO("000001", b.encrypt("123456"));
	}

	
	public ResultMessage modify(WebMarketerPO webMarketerPO) {
		return ResultMessage.SUCCESS;
	}

	
	public ResultMessage deleteWebMarketer(String webMarketerID) {
		return ResultMessage.SUCCESS;
	}

}
