package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by Administrator on 2016-11-20.
 */
public class RemoteHelper {
	
    DataRemoteObject dataRemoteObject;
	static RemoteHelper Singleton ;
	
	public static RemoteHelper getInstance(){
		if(Singleton==null)
		{
			Singleton = new RemoteHelper();
		}
		return Singleton;
	}
	
    private RemoteHelper(){
    	 try {
			dataRemoteObject = new DataRemoteObject();
	    	 LocateRegistry.createRegistry(8888);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public String initServer(){
        try{
            Naming.bind("rmi://localhost:8888/DataRemoteObject",dataRemoteObject);
            return "Server linked";
        }catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
     //   return "Failed";
    }

    public String stopServer() {
        try{
            Naming.unbind("rmi://localhost:8888/DataRemoteObject");
            return "Server stopped";
        }catch (Exception e) {
        	return  e.toString();
        }
       // return "Failed";
    }
}
