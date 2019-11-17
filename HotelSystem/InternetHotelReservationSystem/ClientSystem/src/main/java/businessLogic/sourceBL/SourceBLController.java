package businessLogic.sourceBL;

import java.rmi.RemoteException;
import java.util.Iterator;

import businessLogicService.sourceBLService.SourceBLService;
import dataService.sourceDataService.SourceDataService;
import rmi.ClientRemoteHelper;

public class SourceBLController implements SourceBLService {

	private static SourceBLController sourceBLController = new SourceBLController();
	
	SourceDataService sourceDataService;
	
	private SourceBLController() {
		sourceDataService = ClientRemoteHelper.getInstance().getSourceDataService();
	}
	
	public static SourceBLController getInstance(){
		return sourceBLController;
	}
	
	@Override
	public Iterator<String> getCities() {
		try {
			return sourceDataService.getCities().iterator();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Iterator<String> getLevels() {
		try {
			return sourceDataService.getLevels().iterator();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Iterator<String> getRoomTypes() {
		try {
			return sourceDataService.getRoomTypes().iterator();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Iterator<String> getCircles(String city) {
		try {
			return sourceDataService.getCircles(city).iterator();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getMaxGuestNumEachOrder() {
		try {
			return sourceDataService.getMaxGuestNumEachOrder();
		} catch (RemoteException e) {
			return 0;
		}
	}

	@Override
	public int getMaxRoomNumEachOrder() {
		try {
			return sourceDataService.getMaxRoomNumEachOrder();
		} catch (RemoteException e) {
			return 0;
		}
	}
	
}
