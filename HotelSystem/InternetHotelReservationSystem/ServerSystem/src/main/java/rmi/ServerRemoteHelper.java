package rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import dataServiceImpl.CreditDataServiceImpl;
import dataServiceImpl.GuestDataServiceImpl;
import dataServiceImpl.HotelDataServiceImpl;
import dataServiceImpl.HotelWorkerDataServiceImpl;
import dataServiceImpl.MarketDataServiceImpl;
import dataServiceImpl.OrderDataServiceImpl;
import dataServiceImpl.PromotionDataServiceImpl;
import dataServiceImpl.SourceDataServiceImpl;
import dataServiceImpl.WebManagerDataServiceImpl;
import dataServiceImpl.WebMarketerDataServiceImpl;

public class ServerRemoteHelper {

	final static String localhost = "rmi://localhost:8889/";
	final static String defaultPort = "8889";
	
	static String ip = "";
	static String port = defaultPort; 
	static String url = localhost;
	
	public ServerRemoteHelper() {
		initServer();
	}
	
	public static void setIPandPort(String ip, String port) {
		ServerRemoteHelper.ip = ip;
		ServerRemoteHelper.port = port;
		ServerRemoteHelper.url = "rmi://" + ip + ":" + port + "/";
	}
	
	public static void setLocalhost() {
		ServerRemoteHelper.ip = "localhost";
		ServerRemoteHelper.port = defaultPort;
		ServerRemoteHelper.url = localhost;
	}
	
	private void initServer(){
		try {
			LocateRegistry.createRegistry(8889);
			Naming.bind(url+"GuestDataService", new GuestDataServiceImpl());
			Naming.bind(url+"HotelDataService", new HotelDataServiceImpl());
			Naming.bind(url+"HotelWorkerDataService", new HotelWorkerDataServiceImpl());
			Naming.bind(url+"MarketDataService", new MarketDataServiceImpl());
			Naming.bind(url+"OrderDataService", new OrderDataServiceImpl());
			Naming.bind(url+"PromotionDataService", new PromotionDataServiceImpl());
			Naming.bind(url+"WebManagerDataService", new WebManagerDataServiceImpl());
			Naming.bind(url+"WebMarketerDataService", new WebMarketerDataServiceImpl());
			Naming.bind(url+"CreditDataService", new CreditDataServiceImpl());
			Naming.bind(url+"SourceDataService", new SourceDataServiceImpl());
			System.out.println("link");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
