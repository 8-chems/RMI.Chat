import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Interface extends JFrame{
	private static final long serialVersionUID = 1L;
       Tabs ts =new Tabs();
       RightP  rp=new RightP(ts);
       WSPane wsp=new WSPane();
       EDialog ed;
       String info;
        ReturedObjectIntrf rio;
       ReceaverObjectIntrf  rim;
       String url;
       
	public Interface(){
		super("Chat Test 1");
		 this.setSize(500, 500);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 this.setLocationRelativeTo(null);
		 this.setVisible(true);
		 
		 this.setLayout(new BorderLayout());
		 this.add(rp,BorderLayout.EAST);
		 this.add(wsp,BorderLayout.SOUTH);
		 this.add(ts);
		 this.addWindowListener(new WindowAdapter(){
           public void windowClosing(WindowEvent arg0) {
				if(rio!=null)
					try {
						rio.DConect();
					} catch (RemoteException e) {e.printStackTrace();}
			}});
	     ed=new EDialog(this);
		 this.connect();
		 this.setTitle(this.getTitle()+"("+info.split(":")[1]+")");
		 wsp.setParametrs(rio, ts, info.split(":")[1]);
	
		 addKeyListener(new KeyAdapter(){
		    	public void keyPressed(KeyEvent e) 
		        { 
		    		if(e.getKeyCode()==10){wsp.jb.doClick();
		    		System.out.println("kkkkkkkkkkkkkkk");
		    		}
		        }
		    });
		    this.addKeyListener(wsp.getKeyListeners()[0]);
		    try {
		    	
		    	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		        } 
		        catch (UnsupportedLookAndFeelException e) {}
		        catch (ClassNotFoundException e) {}
		        catch (InstantiationException e) {}
		        catch (IllegalAccessException e) {}
	}
	
	public void connect(){
		while(ed.GetOK()==false&&ed.GetPrb()==false){
			try {
			Thread.sleep(100);
		} catch (InterruptedException e) {e.printStackTrace();}
		}
		
		if(ed.GetOK()){
		   this.info=ed.info;
		   ed.dispatchEvent(new WindowEvent(ed, WindowEvent.WINDOW_CLOSING));
		   try {
			  System.out.println(info);
			  try {System.out.println(info);
				url= "rmi://"+info.split(":")[0]+"/ChatRMI";
				rim=(ReceaverObjectIntrf) Naming.lookup(url);
				rio=( ReturedObjectIntrf)rim.getCible();
				
			}catch (MalformedURLException e) {e.printStackTrace();
			}catch (RemoteException e) {
				new JOptionPane().showMessageDialog(null, "Probleme de connexion au serveur fermer l'application et resseyer", "Information",
					JOptionPane.ERROR_MESSAGE);
				    this.dispose();
			}catch (NotBoundException e) {e.printStackTrace();}
	if(rio!=null){
		boolean b=rio.Connect(info.split(":")[1],info.split(":")[2]);
	    if(b){
			try{
			new Updater(rio,rp);
			new MessageGetter(rio,ts);
			new ConversationGetter(rio,ts);
			rp.setRio(rio);
			}catch(RemoteException e){
				new JOptionPane().showMessageDialog(null, "Probleme de connexion au serveur fermer l'application et resseyer", "Information",
					JOptionPane.ERROR_MESSAGE);}
		}else{
			new JOptionPane().showMessageDialog(null, "Mot de passe,Nom" +
				" d'utilisateur n'est pas correct\n ou compte deja ouvert", "Information",
				JOptionPane.INFORMATION_MESSAGE);
			ed=new EDialog(this);
			this.connect();
		}
	}
		   } catch (RemoteException e) {e.printStackTrace();}
		}
		else {ed.dispatchEvent(new WindowEvent(ed, WindowEvent.WINDOW_CLOSING));
		      this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));}
		
	}
	
	
}
