package dataService.marketDataService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import po.MarketPO;
import utilities.enums.ResultMessage;

public class MarketDataService_Stub extends UnicastRemoteObject implements MarketDataService {


	public MarketDataService_Stub() throws RemoteException {
		super();
	}


	public List<MarketPO> getMemberFormulation() {
		List<MarketPO> a= new ArrayList<MarketPO>();
		MarketPO Lv1= new MarketPO("Lv1",500,0.95);
		MarketPO Lv2= new MarketPO("Lv2",1500,0.9);
		MarketPO Lv3= new MarketPO("Lv3",3000,0.85);
		MarketPO Lv4= new MarketPO("Lv4",4500,0.8);
		MarketPO Lv5= new MarketPO("Lv5",7500,0.75);
		MarketPO Lv6= new MarketPO("Lv6",12000,0.7);
		MarketPO Lv7= new MarketPO("Lv7",19500,0.65);
		MarketPO Lv8= new MarketPO("Lv2",31500,0.6);
		a.add(Lv1);
		a.add(Lv2);
		a.add(Lv3);
		a.add(Lv4);
		a.add(Lv5);
		a.add(Lv6);
		a.add(Lv7);
		a.add(Lv8);
		return a;
	}


	public ResultMessage setMemberFormulation(List<MarketPO> marketPOList) {
		return ResultMessage.SUCCESS;
	}

}
