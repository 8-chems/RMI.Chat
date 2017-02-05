import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class EDialog extends JDialog {
    private static final long serialVersionUID = 1L;
	JLabel l1=new  JLabel("Entrer ID   :"),
			l2=new JLabel("Entrer MP   :"),
			l0=new JLabel("Addresse IP :");
	   JButton jb1=new JButton("OK"),
			   jb2=new JButton("Annuler");
	    String ipp = 
				"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
				"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
				"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
				"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	   String info="";
	   JDialog j=this;
	   boolean ok=false;
	   boolean prb=false;
	   JTextField  jt1=new JTextField(),jt0=new JTextField();
	   JPasswordField jt2=new JPasswordField(); 
	public EDialog(JFrame f){
		this.setLayout(null);
		this.setTitle("LOGIN");
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	    this.setSize(250, 170);
	    this.setLocationRelativeTo(f);
	    this.setAlwaysOnTop(true);
	    
	    l0.setBounds(10, 10, 120, 30);
	    l1.setBounds(10, 40, 120, 30);
	    l2.setBounds(10, 70, 120, 30);
	    
	    jt0.setBounds(100,10, 120, 25);
	    jt1.setBounds(100,40, 120, 25);
	    jt2.setBounds(100, 70, 120, 25);
	    jt2.setEchoChar('*');
		
	    jb1.setBounds(40, 100, 80, 25);
	    jb2.setBounds(140, 100, 80, 25);
	    
	    this.add(l1);    this.add(l2);
	    this.add(jt1);	    this.add(jt2);
	    this.add(jb1);	    this.add(jb2);
	    this.add(l0);	    this.add(jt0);
	    
	    jt0.addFocusListener(new FocusAdapter(){
            public void focusLost(FocusEvent arg0) {
            	if(!jt0.getText().toString().matches(ipp)) 
            	{
            		new JOptionPane().showMessageDialog(j, "Format Addresse IP est incorect","Inforation",JOptionPane.INFORMATION_MESSAGE);
            	    jt0.requestFocus();
            	    jt0.setText("");
            }
            }
	    });
	    jb1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				info= jt0.getText()+":"+jt1.getText()+":"+jt2.getText();
				ok=true;
			}});
	jb2.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent arg0) {
		prb=true;
		}});
	 try {
	    	
	    	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	        } 
	        catch (UnsupportedLookAndFeelException e) {}
	        catch (ClassNotFoundException e) {}
	        catch (InstantiationException e) {}
	        catch (IllegalAccessException e) {}
	}
	 
	
	public boolean GetOK() {
		return this.ok;
	}
	public void SetNotOK() {
		this.ok=false;
	}
	public boolean GetPrb() {
		return this.prb;
	}
	public void SetNOTPrb() {
		this.prb=false;
	}
}
