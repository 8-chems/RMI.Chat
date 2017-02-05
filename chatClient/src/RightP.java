
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class RightP extends JPanel  implements ActionListener{
     private static final long serialVersionUID = 1L;
     Label l=new Label("Membres",Label.CENTER);
     ArrayList<JButton>  ljb=new ArrayList<JButton>();
     ArrayList<String> ls=new ArrayList<String>();
     JScrollPane js;
     Tabs tt;
     JPanel box = new JPanel();
     ReturedObjectIntrf rio;
public RightP(Tabs tt){
	this.tt=tt;
	this.setLayout(new BorderLayout());
	l.setPreferredSize(new Dimension(110,30));
	l.setBackground(Color.CYAN);
    l.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 25)); 
	this.add(l,BorderLayout.NORTH);
    box.setLayout(new GridLayout(100,1));
	this.setPreferredSize(new Dimension(110,30));
	this.setBorder(BorderFactory.createBevelBorder(0));
	js=new  JScrollPane(box);this.add(js);
    js.setBackground(Color.green);
    try {
    	
    	UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } 
        catch (UnsupportedLookAndFeelException e) {}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
}
///methodes
public void addMembersGr(String s){
	ArrayList<String> l=new ArrayList<String>();;
	String[] mem=null;
	if(!s.equals("")){
	mem=s.split(":");
	System.out.println("s ="+s);
	for(int ii=0;ii<mem.length;ii++) l.add(mem[ii]);
	} 
	
	for(int i=0;i<ls.size();i++){
	if(!l.contains(ljb.get(i).getText()+";"+ls.get(i))){
		System.out.println("in  remove");
		tt.It_is_Out(ljb.get(i).getText()+";"+ls.get(i));
	    this.removeMemb(i);
	    }
	else{
     l.remove(ljb.get(i).getText()+";"+ls.get(i));
	}
	}
for(int i=0;i<l.size();i++){
		addOne((String)l.get(i));
}
}

public void addOne(String s){
	
	JButton jb=new JButton(s.split(";")[0]);
	ls.add(s.split(";")[1]);
	ljb.add(jb);
	jb.setPreferredSize(new Dimension(60,40));
	box.add(jb);
	jb.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
	jb.addActionListener(this);
}
public void removeMemb(int i){
	ls.remove(i);
	box.remove(ljb.get(i));
	box.repaint();
	JButton jb=ljb.remove(i);
	jb.disable();
}
public void setRio(ReturedObjectIntrf rio){
	
	this.rio=rio;
}
public void actionPerformed(ActionEvent arg0) {
	 try {
    	rio.CreateConv(ls.get(ljb.indexOf(((JButton)arg0.getSource()))));
		tt.SelectOnglet(((JButton)arg0.getSource()).getText()+";"+ls.get(ljb.indexOf(((JButton)arg0.getSource()))));
	} catch (RemoteException e) {e.printStackTrace();}
}

}
