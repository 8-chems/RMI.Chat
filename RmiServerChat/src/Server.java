import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class Server {


	
	public void Lancer(){
		
			try {
				String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/ChatRMI";
				LocateRegistry.createRegistry(1099);
				ReceaverObjectImp  ro= new ReceaverObjectImp();
			    Naming.rebind(url, ro);
			   
			} catch (RemoteException e) {e.printStackTrace();
			} catch (MalformedURLException e) {e.printStackTrace();
			} catch (UnknownHostException e) {e.printStackTrace();
			}
			}
	public void Stopper(){
		
		try {
			Naming.unbind("ChatRMI");
		} catch (RemoteException e) {e.printStackTrace();
		} catch (MalformedURLException e) {e.printStackTrace();
		} catch (NotBoundException e) {e.printStackTrace();}
	}

}
