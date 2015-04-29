package cy.ac.ucy.teamc.scc;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;


public class TouchImageViewActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		
		//get the name and the description of the exam 
		String id = extras.getString("EXTRA_IMAGE_ID");
		int resource=Integer.parseInt(id);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        TouchImageView img = (TouchImageView) findViewById(R.id.img);
        img.setImageResource(resource);
        img.setMaxZoom(4);
    }
}