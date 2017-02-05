import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;


public class SoundPlayer extends Thread{


	 public void run(){
		 try{AudioClip clip ;
	      File file = new File("src/fbs.wav");
	      clip = Applet.newAudioClip(file.toURL());
	      clip.play();
	      this.sleep(500);
	  }catch (MalformedURLException e) {
	    } catch (InterruptedException e) {e.printStackTrace();}
	    }
	 }
	


