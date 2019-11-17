package dataServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dataHelper.AddressDataHelper;
import dataHelper.SourceDataHelper;
import dataHelperImpl.AddressDataHelperImpl;
import dataHelperImpl.SourceDataHelperImpl;
import dataService.sourceDataService.SourceDataService;

public class SourceDataServiceImpl extends UnicastRemoteObject implements SourceDataService {

	private static final long serialVersionUID = 5560320095426224562L;

	SourceDataHelper sourceDataHelper;
	AddressDataHelper addressDataHelper;

	public SourceDataServiceImpl() throws RemoteException {
		sourceDataHelper = new SourceDataHelperImpl();
		addressDataHelper = new AddressDataHelperImpl();
	}

	@Override
	public List<String> getCities() throws RemoteException {
		return addressDataHelper.getCity();
	}

	@Override
	public List<String> getCircles(String city) throws RemoteException {
		return addressDataHelper.getCircle(city);
	}

	@Override
	public List<String> getLevels() throws RemoteException{
		return sourceDataHelper.getLevels();
	}

	@Override
	public List<String> getRoomTypes() throws RemoteException{
		return sourceDataHelper.getRoomTypes();
	}

	@Override
	public int getMaxGuestNumEachOrder() {
		return sourceDataHelper.getMaxGuestNumEachOrder();
	}

	@Override
	public int getMaxRoomNumEachOrder() {
		return sourceDataHelper.getMaxRoomNumEachOrder();
	}

	@Override
	public void guestLogInRecord(String guestID) {
		sourceDataHelper.guestLogInRecord(guestID);
	}

	@Override
	public void guestLogOut(String guestID) {
		sourceDataHelper.guestLogOut(guestID);
	}

	@Override
	public boolean guestHasLogged(String guestID) {
		return sourceDataHelper.guestHasLogged(guestID);
	}

}
