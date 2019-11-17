package dataService.creditDataService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import po.CreditPO;
import utilities.enums.CreditRecord;
import utilities.enums.ResultMessage;

public class CreditDataService_Stub extends UnicastRemoteObject implements CreditDataService {

	public CreditDataService_Stub() throws RemoteException {
		super();
	}

	@Override
	public List<CreditPO> getAllCreditDetail(String guestID) throws RemoteException {
		List<CreditPO> list = new ArrayList<CreditPO>();
		LocalDateTime date = LocalDateTime.of(2016, 11, 11, 11, 11, 11, 11);
		list.add(new CreditPO("1234567890",date,"123456789",100,200,CreditRecord.EXECUTE));
		list.add(new CreditPO("1234567891",date,"123456789",100,200,CreditRecord.EXECUTE));
		list.add(new CreditPO("1234567892",date,"123456789",100,200,CreditRecord.EXECUTE));
		return list;
	}

	@Override
	public ResultMessage addCredit(CreditPO creditPO) throws RemoteException {
		return null;
	}

//	@Override
//	public List<CreditPO> getCreditOfOneOrder(String guestID) throws RemoteException {
//		return null;
//	}

}
