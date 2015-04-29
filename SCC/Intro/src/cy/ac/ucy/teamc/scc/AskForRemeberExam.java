package cy.ac.ucy.teamc.scc;

import java.util.Calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
public class AskForRemeberExam extends ActivityNotification {

	 static final int DATE_DIALOG_ID = 999;
	 public static final String NOTIFICATION_DATA = "NOTIFICATION_DATA";
		ImageButton Bsave;
		TextView text1;
		private DatePicker dpResult;
		private int year1;
		private int month1;
		private int day1;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_ask_for_remeber_exam);
			final String data=getIntent().getExtras().getString(NOTIFICATION_DATA);
			
			final int curyear = Calendar.getInstance().get(Calendar.YEAR);
			final int curmonth = Calendar.getInstance().get(Calendar.MONTH);
			final int curday = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			showDialog(DATE_DIALOG_ID);
					
					
					
					LinearLayout ll = (LinearLayout)findViewById(R.id.linearLayout2);
					createAllObjects();
					
					Bsave.setOnClickListener(new View.OnClickListener() {
					
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							
							int year=dpResult.getYear();
							int month=dpResult.getMonth();
							int day=dpResult.getDayOfMonth();
							
							if(curyear>year || (curmonth>month && curyear==year) || (curday>day && curmonth==month && curyear==year))
							{
								text1.setText("Εισάγατε λάθος ημερομηνία.");
								text1.setTextColor(Color.RED);
								}
							else
							{
								 Calendar today = Calendar.getInstance();
								text1.setText("Συγχαρητήρια!! \nΕίσαι σε καλό δρόμο!!");
								text1.setTextColor(Color.GREEN);
								Calendar thatDay = Calendar.getInstance();
								  thatDay.set(Calendar.DAY_OF_MONTH,day);
								  thatDay.set(Calendar.MONTH,month); // 0-11 so 1 less
								  thatDay.set(Calendar.YEAR, year);
								 
								  long diff =  Math.abs((thatDay.getTimeInMillis()-today.getTimeInMillis())); //result in millis
							

								try {
									createNotification(thatDay.getTimeInMillis(),data,diff,1);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								}
							
						}
					});
		}
		
		// display current date
		public void setCurrentDateOnView() {
			dpResult = (DatePicker) findViewById(R.id.dpResult);
	 
			final Calendar c = Calendar.getInstance();
			year1 = c.get(Calendar.YEAR);
			month1 = c.get(Calendar.MONTH);
			day1 = c.get(Calendar.DAY_OF_MONTH);
	 
			// set current date into datepicker
			dpResult.init(year1, month1, day1, null);
	 
		}
		
		private void createAllObjects() {
			// TODO Auto-generated method stub
			text1 = (TextView) findViewById(R.id.text1);
			Bsave = (ImageButton) findViewById(R.id.Bsaveanswer);
			setCurrentDateOnView();
		}
	}

