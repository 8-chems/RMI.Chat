import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class PanString extends JPanel{
private static final long serialVersionUID = 1L;
String s;
int ii;
	public PanString(String s,int i) {
		super();
		this.s=s;
		this.ii=i;
		this.setPreferredSize(new Dimension(4*s.length()+5,25));
	}

	@Override
	protected void paintComponent(Graphics arg0) {
	    String[] ss=s.split(":");
	    arg0.drawRect(0, 0,this.getWidth(), 23);
	    if(ii==0)arg0.setColor(Color.GREEN);
	    else arg0.setColor(Color.RED);
		arg0.drawString(ss[0], 10, 15);
		arg0.setColor(Color.BLACK);
		arg0.drawString(" : "+ss[1],ss[0].length()*6+5, 15);
	
	}	
	
}
