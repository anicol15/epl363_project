package cy.ac.ucy.teamc.scc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Settings extends Activity {

	ImageButton Bpersonal;
	ImageButton Bnotification;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_setting_type);

		createAllObjects();

		Bpersonal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					Class ourClass = Class.forName("cy.ac.ucy.teamc.scc.Personal_settings");
					//triggered a class that user selected.
					Intent ourIntent = new Intent(Settings.this, ourClass);
					startActivity(ourIntent);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				
				setContentView(R.layout.personal_settings);
			}
		});

		Bnotification.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				SharedPreferences s_pref=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				int input_smoker=s_pref.getInt("smoker",5);
			
				
				//if user have not insert his personal information cannot see the notification screen
				if(input_smoker!=5){
				// TODO Auto-generated method stub
				try {
					Class ourClass = Class.forName("cy.ac.ucy.teamc.scc.ActivityNotification");
					//triggered a class that user selected.
					Intent ourIntent = new Intent(Settings.this, ourClass);
					startActivity(ourIntent);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			
			
			else{
				Toast.makeText(getApplicationContext(), "Παρακαλώ εισάγετε πρώτα τα προσωπικά σας στοιχεία για να μπορέσετε να ενεργοποιήσετε τις υπενθυμίσεις.",Toast.LENGTH_SHORT).show();
				
			}
			}});
		
	}

	private void createAllObjects() {
		// TODO Auto-generated method stub
		Bpersonal = (ImageButton) findViewById(R.id.BpersonalInfo);
		Bnotification = (ImageButton) findViewById(R.id.Bnotification);
	}

}
