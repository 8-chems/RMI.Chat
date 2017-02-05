import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class WSPane extends JPanel implements ActionListener{
   private static final long serialVersionUID = 1L;
   TextField tf=new TextField();
   ReturedObjectIntrf rio;
   Tabs t;
   String s;
   public JButton jb=new JButton("         Send        ");
   public WSPane(){
	   this.setBorder(BorderFactory.createBevelBorder(0));
	   this.setLayout(new BorderLayout());
	   jb.setSize(120,120);
	   this.add(jb,BorderLayout.EAST);
	   this.add(tf);
	   jb.addActionListener(this);
	   jb.setFocusable(false);
	   this.addKeyListener(new KeyAdapter(){
	    	public void keyPressed(KeyEvent e) 
	        { System.out.println(e.getKeyCode());
	    		if(e.getKeyCode()==30){
	    			jb.doClick();
	    		}
	        }
	    });
	    setFocusable(true);
	    requestFocusInWindow();
	 
	    try {
	    	
	    	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	        } 
	        catch (UnsupportedLookAndFeelException e) {}
	        catch (ClassNotFoundException e) {}
	        catch (InstantiationException e) {}
	        catch (IllegalAccessException e) {}
	}
//methodes
public void setParametrs(ReturedObjectIntrf rio,Tabs t,String s){
	this.rio=rio;
	this.t=t;
	this.s=s;
}
public void actionPerformed(ActionEvent arg0) {
	 try {
		t.setmess(s+":"+tf.getText(),0);
		rio.SetMessage(t.getOngletID(),tf.getText());
	} catch (RemoteException e) {
		try {
			throw e;
		} catch (RemoteException e1) {e1.printStackTrace();}
	}
	 tf.setText("");
}
  
}
