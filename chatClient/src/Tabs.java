import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Tabs extends JPanel implements ActionListener{
  private static final long serialVersionUID = 1L;
  public JTabbedPane onglet=new JTabbedPane();
  ArrayList<JPanel> ljp=new ArrayList<JPanel>();
  public ArrayList<String> ls=new ArrayList<String>();
  JScrollPane js;
  Color c;
public Tabs(){
	this.setBorder(BorderFactory.createBevelBorder(0));
	this.setLayout(new BorderLayout());
	this.add(onglet);
	c=this.onglet.getBackground();
	onglet.addChangeListener(new ChangeListener(){
       public void stateChanged(ChangeEvent arg0) {
        	int i=onglet.getSelectedIndex();
            if(i>=0)
            	onglet.setBackgroundAt(i,c);
        }});
 try {
    	
    	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } 
        catch (UnsupportedLookAndFeelException e) {}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
}
//methodes	

   public void setmess(String s,int i){
	PanString  ps =new PanString(s,i);
	ljp.get(onglet.getSelectedIndex()).add(ps);
	ps.repaint();
	ljp.get(onglet.getSelectedIndex()).repaint();
	ljp.get(onglet.getSelectedIndex()).getParent().getParent().validate();
	
	
}
   
   String getOngletID(){
	   String ss=onglet.getTitleAt(onglet.getSelectedIndex());
	  String ret="";
	   for(int i=0;i<ls.size();i++){
		   if(ss.equals(ls.get(i).split(";")[0])){
			   ret=ls.get(i).split(";")[1];
			   break;
			   }
		}
	   
	  return ret; 
   }
   
   public void It_is_Out(String s){
	   for(int i=0;i<onglet.getTabCount();i++){
			if(onglet.getTitleAt(i).equals(s.split(";")[0])){
				onglet.getComponentAt(i).disable();
				onglet.setTitleAt(i, onglet.getTitleAt(i)+"(out)");
				onglet.setBackgroundAt(i,Color.YELLOW);
				ls.remove(i);
				ljp.remove(i);
				onglet.removeTabAt(i);
			}
			}
	   
   }
   
   public void SelectOnglet(String s){
	   System.out.println("select on g"+ s);
    int io=-1;
    for(int i=0;i<onglet.getTabCount();i++){
	if(onglet.getTitleAt(i).equals(s.split(";")[0])){
		  io=i;break;
	}
}
	 if(io==-1){  
	   ls.add(s);
	   JPanel jp=new JPanel(); 
	   jp.setLayout(new GridLayout(600,1));
	   JScrollPane js=new JScrollPane(jp);
	   ljp.add(jp);
	   onglet.addTab(s.split(";")[0],js);
	   int index = onglet.indexOfTab(s.split(";")[0]);
	   JPanel pnlTab = new JPanel(new GridBagLayout());
	   pnlTab.setOpaque(false);
	   JLabel lblTitle = new JLabel(s.split(";")[0]+"  ");
	   JButton btnClose = new JButton("x");
	   btnClose.setBackground(Color.red);
       GridBagConstraints gbc = new GridBagConstraints();
	   gbc.gridx = 0;
	   gbc.gridy = 0;
	   gbc.weightx = 1;
       pnlTab.add(lblTitle, gbc);
       gbc.gridx++;
	   gbc.weightx = 0;
	   pnlTab.add(btnClose, gbc);
       onglet.setTabComponentAt(index, pnlTab);
       btnClose.addActionListener(this);
	 }//else onglet.setSelectedIndex(io);
	 }

public void actionPerformed(ActionEvent arg0) {
	 int selected = onglet.getSelectedIndex();
     this.It_is_Out(ls.get(selected));     

}
}
 