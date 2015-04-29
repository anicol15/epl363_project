package cy.ac.ucy.teamc.scc;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


public class ChoosePersonalInform extends Activity {

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		SharedPreferences s_pref=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		int input_smoker=s_pref.getInt("smoker",5);
		
		
		
		
		super.onCreate(savedInstanceState);

		
		if(input_smoker==5)
		{
				// TODO Auto-generated method stub
				try {
					Class ourClass = Class.forName("cy.ac.ucy.teamc.scc.PersonalInform");
					//triggered a class that user selected.
					Intent ourIntent = new Intent(ChoosePersonalInform.this, ourClass);
					startActivity(ourIntent);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				
				setContentView(R.layout.personal_settings);
			}
	
		else
			
		{
				try {
					Class ourClass = Class.forName("cy.ac.ucy.teamc.scc.PersonalInformNotFirstTime");
					//triggered a class that user selected.
					Intent ourIntent = new Intent(ChoosePersonalInform.this, ourClass);
					startActivity(ourIntent);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
	

	}

	
}