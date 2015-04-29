package cy.ac.ucy.teamc.scc;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class MyImageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.setTitle("Μεγ");
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fullscreen_image);
		Bundle bundle=getIntent().getExtras();
		int imageId=bundle.getInt("imgId");
		ImageView imgView=(ImageView) findViewById(R.id.myImage);
		imgView.setImageResource(imageId);
		
	}

}
