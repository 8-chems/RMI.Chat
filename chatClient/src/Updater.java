import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Updater extends Thread{
	ReturedObjectIntrf  roi;
	RightP rp;
	public Updater(ReturedObjectIntrf  roi,RightP rp) {
		this.roi=roi;
		this.rp=rp;
		this.start();
	}

	public void run(){
	while(true){
			try {
				this.sleep(1000);
			        String ff=roi.getMembersList();
			       rp.addMembersGr(ff);
				
			} catch (InterruptedException e) {e.printStackTrace();
			} catch (RemoteException e) {
				try {
				throw e;
			} catch (RemoteException e1) {e1.printStackTrace();}}
	}
	}
}
