package cy.ac.ucy.teamc.scc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Intro extends Activity{
	MediaPlayer ourSong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);
		ourSong=MediaPlayer.create(Intro.this, R.raw.my_music2);
		
		DatabaseManager db = DatabaseManager.getHelper(getApplicationContext());
		DatabaseUpdate customTask = new DatabaseUpdate();
	    AsyncTaskExecutionHelper.executeParallel(customTask,getApplicationContext());
		//db.addData();
		
		AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		//sound won't play if device is in silent mode
		switch (am.getRingerMode()) {
	    	case AudioManager.RINGER_MODE_SILENT:
				        							break;
	    	case AudioManager.RINGER_MODE_VIBRATE:
				        							break;
	    	case AudioManager.RINGER_MODE_NORMAL:
				    								ourSong.start();
				    								break;
		}
		
		Thread timer=new Thread(){
			public void run(){
				try{
					sleep(3000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openStartingPoint=new Intent("cy.ac.ucy.teamc.scc.PASIKAFINFO");
					startActivity(openStartingPoint);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSong.release();
		finish();
	}

}
