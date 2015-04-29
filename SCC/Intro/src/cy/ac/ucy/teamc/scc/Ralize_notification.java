package cy.ac.ucy.teamc.scc;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Ralize_notification extends ActivityNotification {

	public static final String NOTIFICATION_DATA = "NOTIFICATION_DATA";

	ArrayList<Exam> exams;
ImageButton Bsave;
TextView text;
TextView text1;
ToggleButton toggleButton;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_ralize_notification);
	
	final String data=getIntent().getExtras().getString(NOTIFICATION_DATA);
	
	
		LinearLayout ll = (LinearLayout)findViewById(R.id.linearLayout2);
		createAllObjects();
		text.setText("Υπενθύμιση!!! \n Έχετε πραγματοποιήσει την εξέταση: "+data);
		
		toggleButton=(ToggleButton)findViewById(R.id.deActivateNotification);
		Bsave.setOnClickListener(new View.OnClickListener() {
		
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				if (toggleButton.isChecked()) {
				
					text1.setText("Συγχαρητήρια!! ");
					text1.setTextColor(Color.GREEN);
					DatabaseManager db =DatabaseManager.getHelper(getApplicationContext());
					exams=db.getAllPrevExams();
					int freq=0;
					for(int i=0; i<exams.size();i++)
						{
						Exam cur=exams.get(i);
						
						if(data.equals(cur.get_name()))
							{
								freq=cur.get_frequency();
								break;
							}
						}
				if(freq!=0)
				{
					 Calendar today = Calendar.getInstance();
					 Calendar thatday = Calendar.getInstance();
					 thatday.add(Calendar.MONTH, freq);		
					  long diff =  Math.abs((thatday.getTimeInMillis()-today.getTimeInMillis())); //result in millis
					
					
					try {
						createNotification(thatday.getTimeInMillis(),data,diff,0);
						//createNotification(thatday.getTimeInMillis(),data,15000,0);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 }
				
			}
				else{
					try{
					text1.setText("Πραγματοποιήστε την εξέταση άμεσα! \nΜην το αμελήσετε!!! ");
					text1.setTextColor(Color.RED);
					Class ourClass = Class.forName("cy.ac.ucy.teamc.scc.AskForRemeberExam");
					//triggered a class that user selected.
					Intent ourIntent = new Intent(Ralize_notification.this, ourClass);
					ourIntent.putExtra(NOTIFICATION_DATA, data);
					startActivity(ourIntent);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
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

