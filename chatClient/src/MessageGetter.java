import java.awt.Color;
import java.rmi.RemoteException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import sun.tools.jar.Main;


public class MessageGetter extends Thread {
	ReturedObjectIntrf roi;
	Tabs t;
	SoundPlayer sp=new SoundPlayer();
	public MessageGetter(ReturedObjectIntrf rio,Tabs t)throws RemoteException{
		this.roi=rio;
		this.t=t;
		this.start();
	}
	public void  run(){
		while(true){
			try {    this.sleep(10);
					for(int i=0;i<t.ls.size();i++){
				    String lss;
				    lss = roi.getMessage(t.ls.get(i).split(";")[1]);
					if(!lss.equals("")) 
					   {
						if(t.onglet.getSelectedIndex()==i){ 
						   t.setmess(t.ls.get(i).split(";")[0]+":"+lss,1);
						  sp.run();  
						}
					   else { 
						   t.ljp.get(i).add(new PanString(t.ls.get(i).split(";")[0]+":"+lss,1));
						   t.onglet.setBackgroundAt(i, Color.red);  
						   sp.run();
					   }
					   }
					   }
				} 
			catch (InterruptedException e) {e.printStackTrace();} 
			catch (RemoteException e) {
			    try {
					throw e;
				} catch (RemoteException e1) {e1.printStackTrace();}
			} 
		}
		}
}
