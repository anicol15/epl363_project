package cy.ac.ucy.teamc.scc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PasikafInfo extends Activity{

	ImageButton BenterMenuList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pasikafinfo);
		
		createAllObjects();
				
		BenterMenuList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					
					
					Class ourClass = Class.forName("cy.ac.ucy.teamc.scc.Menu");
					//triggered a class that user selected.
					Intent ourIntent = new Intent(PasikafInfo.this, ourClass);
					ourIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(ourIntent);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void createAllObjects() {
		// TODO Auto-generated method stub
		BenterMenuList= (ImageButton) findViewById(R.id.BmenuList);
	}

}
