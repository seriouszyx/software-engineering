package myTest;

import java.rmi.Naming;

import rmi.RemoteHelper;
/*因为不能用真正的Client来RUN,用这个测试的Client来运行Junit*/
public class TestClient {
	 private RemoteHelper remoteHelper;
	 
	 public TestClient(){
		 linkToServer();
	 }
	 
	 private void linkToServer(){
	      try{
	          remoteHelper=RemoteHelper.getInstance();
	          remoteHelper.setRemote(Naming.lookup("rmi://localhost:8888/DataRemoteObject"));
	    
	      }catch (Exception e){
	          e.printStackTrace();
	      }
	 }
	 
	 
}
