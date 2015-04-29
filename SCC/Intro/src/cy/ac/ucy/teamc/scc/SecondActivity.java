package cy.ac.ucy.teamc.scc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;


/**
 * This is the activity that is started when the user presses the notification in the status bar
 * @author paul.blundell
 */
public class SecondActivity extends Activity {
	ImageButton Bsave;
	TextView text;
	TextView text1;
	ToggleButton toggleButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		//read notication's name
		SharedPreferences s_pref=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		//the related exams of the exam
				String exam_name=s_pref.getString("notif","");
				
				
				LinearLayout ll = (LinearLayout)findViewById(R.id.linearLayout2);
				createAllObjects();
				text.setText("Υπενθύμιση!!! \n Θα πρέπει να πραγματοποιήσετε την εξέταση: "+exam_name);
				
				toggleButton=(ToggleButton)findViewById(R.id.deActivateNotification);
				Bsave.setOnClickListener(new View.OnClickListener() {
				
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						
						if (toggleButton.isChecked()) {
						try {
							text1.setText("Συγχαρητήρια!! ");
							text1.setTextColor(Color.GREEN);
							Class ourClass = Class.forName("cy.ac.ucy.teamc.scc.AskForRemeberExam");
							//triggered a class that user selected.
							Intent ourIntent = new Intent(SecondActivity.this, ourClass);
							startActivity(ourIntent);
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
						
						
						
					}
						else{
							text1.setText("Προγραμμάτιστε την εξέταση άμεσα! \nΜην το αμελήσετε!!! ");
							text1.setTextColor(Color.RED);
						}
					}
				});
	}
	private void createAllObjects() {
		// TODO Auto-generated method stub
		text = (TextView) findViewById(R.id.text);
		text1 = (TextView) findViewById(R.id.text1);
		Bsave = (ImageButton) findViewById(R.id.Bsaveanswer);

	}
}
