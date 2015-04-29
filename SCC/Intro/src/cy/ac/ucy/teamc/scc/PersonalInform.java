package cy.ac.ucy.teamc.scc;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class PersonalInform extends Activity {
	public final static String EXTRA_ARRAY = "cy.ac.ucy.teamc.scc.MESSAGE";

	ImageButton checkSubmition;
	TextView displaySubmit;
	EditText Tweight;
	EditText Theight;

	private DatePicker dpResult;
	private int year;
	private int month;
	private int day;
	public float maza_somatos;
	ArrayList<Exam> exams;

	static final int DATE_DIALOG_ID = 999;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		DatabaseManager db = DatabaseManager.getHelper(getApplicationContext());
		exams = db.getAllPrevExams();

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal);
		createAllObjects();

		checkSubmition.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub
				int curyear = Calendar.getInstance().get(Calendar.YEAR);
				showDialog(DATE_DIALOG_ID);

				if (Tweight.getText().toString().equalsIgnoreCase("")) {
					displaySubmit.setText("Δεν έχετε εισάγει όλα τα δεδομένα");
					displaySubmit.setTextColor(Color.RED);
				} else if (Float.parseFloat(Tweight.getText().toString()) > (float) 350.00
						|| Float.parseFloat(Tweight.getText().toString()) < (float) 30) {
					displaySubmit.setText("Εισάγατε λάθος τιμή βάρους. \r\n Το όριο βάρους είναι [30-350]");displaySubmit.setTextColor(Color.RED);
				} else if (Theight.getText().toString().equalsIgnoreCase("")) {
					displaySubmit.setText("Δεν έχετε εισάγει όλα τα δεδομένα");
					displaySubmit.setTextColor(Color.RED);
				} else if (Float.parseFloat(Theight.getText().toString()) > (float) 250.00
						|| Float.parseFloat(Theight.getText().toString()) < (float) 100) {
					displaySubmit.setText("Εισάγατε λάθος τιμή ύψους. \r\n  Το όριο ύψους είναι[100-250]");displaySubmit.setTextColor(Color.RED);
				} else if (dpResult.getYear() >= (curyear)
						|| dpResult.getYear() < (curyear - 120)) {
					displaySubmit.setText("Εισάγατε λάθος ημερομηνία Γέννησης");
					displaySubmit.setTextColor(Color.RED);
				} else {
					// get the user's personals information from screen
					int age = curyear - dpResult.getYear();
					int year_of_birth = dpResult.getYear();

					final Spinner selectsmoke = (Spinner) findViewById(R.id.selectSmoke);
					final Spinner selectGender = (Spinner) findViewById(R.id.selectGender);
					final Spinner selectalcoholic = (Spinner) findViewById(R.id.selectAlcohol);
					final Spinner selectPreposission = (Spinner) findViewById(R.id.selectPreposission);
					final Spinner selectSexualSituation = (Spinner) findViewById(R.id.selectSexualSituation);

					int smoker = selectsmoke.getSelectedItemPosition();
					int Gender = selectGender.getSelectedItemPosition();
					int alcoholic = selectalcoholic.getSelectedItemPosition();
					int Preposission = selectPreposission
							.getSelectedItemPosition();
					int SexualSituation = selectSexualSituation
							.getSelectedItemPosition();
					maza_somatos = Float.parseFloat(Tweight.getText()
							.toString())
							/ (((Float.parseFloat(Theight.getText().toString())) * (Float
									.parseFloat(Theight.getText().toString()))) / 10000);
					displaySubmit.setText("Τα δεδομένα εισάχθηκαν με επιτυχία!");

					displaySubmit.setTextColor(Color.GREEN);
				//	ArrayList<Exam> selected_exams = new ArrayList<Exam>();

					/*selected_exams = informUser(age, smoker, Gender,
							maza_somatos, alcoholic, Preposission,
							SexualSituation);
*/
					// create the file with the user's info
					SharedPreferences s_pref = PreferenceManager
							.getDefaultSharedPreferences(getApplicationContext());
					Editor edit = s_pref.edit();
					edit.putInt("year_of_birth", year_of_birth);
					edit.putInt("notifActive", 0);
					edit.putInt("smoker", smoker);
					edit.putInt("Gender", Gender);
					edit.putInt("alcoholic", alcoholic);
					edit.putInt("Preposission", Preposission);
					edit.putInt("SexualSituation", SexualSituation);
					edit.putFloat("maza_somatos", maza_somatos);
					edit.putInt("notif_time", 0);
					
					/*if (!selected_exams.isEmpty())
						for (int i = 0; i < selected_exams.size(); i++)
							edit.putInt("exam" + i, selected_exams.get(i)
									.get_id());
*/
					edit.commit();
				
					float input_maza_somatos = s_pref.getFloat("maza_somatos",
							(float) 0.0);

					

						try {
							Class ourClass = Class
									.forName("cy.ac.ucy.teamc.scc.PersonalInformNotFirstTime");

							Intent passIntent = new Intent();
							passIntent.setClass(PersonalInform.this, ourClass);

							startActivity(passIntent);

						} catch (Exception e) {
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
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		// set current date into datepicker
		dpResult.init(year, month, day, null);

	}

	private void createAllObjects() {
		// TODO Auto-generated method stub
		checkSubmition = (ImageButton) findViewById(R.id.Bsubmit);
		displaySubmit = (TextView) findViewById(R.id.msgSubmit);
		Tweight = (EditText) findViewById(R.id.CommandWeight);
		Theight = (EditText) findViewById(R.id.CommandHeigh);
		setCurrentDateOnView();
	}

}
