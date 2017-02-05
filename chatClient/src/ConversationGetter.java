import java.rmi.RemoteException;


public class ConversationGetter extends Thread{
	ReturedObjectIntrf  roii;
	Tabs tt;
     public ConversationGetter(ReturedObjectIntrf  roi,Tabs t) {
    	 this.roii=roi;
    	 this.tt=t;
    	 this.start();
     }
	public void run(){
	 while(true){   
		try {
			this.sleep(1000);
			String s;
			s = roii.GetConv();
			if(!s.equals(""))   
		        for(int i=0;i<s.split(":").length;i++){
		        if(!s.split(":")[i].equals(""))tt.SelectOnglet(s.split(":")[i]);
		        }
		} catch (RemoteException e) {
			try {
				throw e;
			} catch (RemoteException e1) {e1.printStackTrace();}
		} 
		catch (InterruptedException e) {e.printStackTrace();}
	   
}}

}
	
