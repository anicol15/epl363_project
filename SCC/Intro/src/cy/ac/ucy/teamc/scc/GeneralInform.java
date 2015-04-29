package cy.ac.ucy.teamc.scc;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class GeneralInform extends Activity {

	ImageButton Bexams;
	ImageButton Bactiv;
	ImageButton Bcancer;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general);
		createAllObjects();
		
		Bexams.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					Class ourClass = Class.forName("cy.ac.ucy.teamc.scc.ExaminationList");
					//triggered a class that user selected.
					Intent ourIntent = new Intent(GeneralInform.this, ourClass);
					startActivity(ourIntent);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		
Bactiv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					Class ourClass = Class.forName("cy.ac.ucy.teamc.scc.ActivityList");
					//triggered a class that user selected.
					Intent ourIntent = new Intent(GeneralInform.this, ourClass);
					startActivity(ourIntent);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});



Bcancer.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		try {
			Class ourClass = Class.forName("cy.ac.ucy.teamc.scc.CancerList");
			//triggered a class that user selected.
			Intent ourIntent = new Intent(GeneralInform.this, ourClass);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
});

	}

	private void createAllObjects() {
		// TODO Auto-generated method stub
		
		Bactiv= (ImageButton) findViewById(R.id.Bactiv);
		Bcancer= (ImageButton) findViewById(R.id.Bcancer);
		Bexams= (ImageButton) findViewById(R.id.Bexam);
		
	}
	
    
}
