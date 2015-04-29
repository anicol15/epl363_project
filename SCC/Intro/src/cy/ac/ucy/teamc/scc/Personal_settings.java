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

public class Personal_settings extends Activity {

	ImageButton checkSubmition;
	ImageButton recreate;
	TextView displaySubmit;
	EditText Tweight;
	EditText Theight;

	private DatePicker dpResult;
	private int year;
	private int month;
	private int day;
	public float maza_somatos;

	public final static String EXTRA_ARRAY = "cy.ac.ucy.teamc.scc.MESSAGE";
	ArrayList<Exam> exams;

	static final int DATE_DIALOG_ID = 999;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		DatabaseManager db = DatabaseManager.getHelper(getApplicationContext());
		exams = db.getAllPrevExams();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_settings);

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
						|| Float.parseFloat(Tweight.getText().toString()) < (float) 20) {
					displaySubmit
							.setText("Εισάγατε λάθος όριο βάρους. Το όριο βάρους είναι [20-350]");
					displaySubmit.setTextColor(Color.RED);
				} else if (Theight.getText().toString().equalsIgnoreCase("")) {
					displaySubmit.setText("Δεν έχετε εισάγει όλα τα δεδομένα");
					displaySubmit.setTextColor(Color.RED);
				} else if (Float.parseFloat(Theight.getText().toString()) > (float) 250.00
						|| Float.parseFloat(Theight.getText().toString()) < (float) 40) {
					displaySubmit
							.setText("Εισάγατε λάθος όριο ύψους. Το όριο ύψους είναι[40-250]");
					displaySubmit.setTextColor(Color.RED);
				} else if (dpResult.getYear() >= (curyear)
						|| dpResult.getYear() < (curyear - 120)) {
					displaySubmit
							.setText("Εισάγαται λάθος ημερομηνία Γέννησης");
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
					displaySubmit
							.setText("Τα δεδομένα εισάχθηκαν με επιτυχία!");

					displaySubmit.setTextColor(Color.GREEN);
					ArrayList<Exam> selected_exams = new ArrayList<Exam>();

					selected_exams = informUser(age, smoker, Gender,
							maza_somatos, alcoholic, Preposission,
							SexualSituation);

					// create the file with the user's info
					SharedPreferences s_pref = PreferenceManager
							.getDefaultSharedPreferences(getApplicationContext());
					Editor edit = s_pref.edit();
					edit.putInt("notifActive", 0);

					edit.putInt("year_of_birth", year_of_birth);
					edit.putInt("smoker", smoker);
					edit.putInt("Gender", Gender);
					edit.putInt("alcoholic", alcoholic);
					edit.putInt("Preposission", Preposission);
					edit.putInt("SexualSituation", SexualSituation);
					edit.putFloat("maza_somatos", maza_somatos);

					int num = selected_exams.size();
					edit.putInt("num_of_exam", num);
					if (!selected_exams.isEmpty())
						for (int i = 0; i < selected_exams.size(); i++)
							edit.putInt("exam" + i, selected_exams.get(i)
									.get_id());

					edit.commit();

					float input_maza_somatos = s_pref.getFloat("maza_somatos",
							(float) 0.0);

					if (selected_exams != null) {

						try {
							Class ourClass = Class
									.forName("cy.ac.ucy.teamc.scc.PersonalInformNotFirstTime");

							Intent passIntent = new Intent();
							passIntent.setClass(Personal_settings.this,
									ourClass);
							startActivity(passIntent);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		});

		recreate.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				final Spinner selectsmoke = (Spinner) findViewById(R.id.selectSmoke);
				final Spinner selectGender = (Spinner) findViewById(R.id.selectGender);
				final Spinner selectalcoholic = (Spinner) findViewById(R.id.selectAlcohol);
				final Spinner selectPreposission = (Spinner) findViewById(R.id.selectPreposission);
				final Spinner selectSexualSituation = (Spinner) findViewById(R.id.selectSexualSituation);

				selectsmoke.setSelection(0);
				selectGender.setSelection(0);
				selectalcoholic.setSelection(0);
				selectPreposission.setSelection(0);
				selectSexualSituation.setSelection(0);

				Theight.getText().clear();
				Tweight.getText().clear();
				setCurrentDateOnView();

			}
		});

	}

	// Inform personal the user about the exams that he/she should do
	public ArrayList<Exam> informUser(int age, int smoker, int gender,
			float deiktis_mazas_somatos, int alcoholic, int preposission,
			int sexual_situation) {
		ArrayList<Exam> selected_exams_array = new ArrayList<Exam>();

		for (int i = 0; i < exams.size(); i++) {
			if (exams.get(i) != null) {
				// exams.get(i).id
				int start_age = 0, end_age = 0, start_deiktis_mazas = 0, end_deiktis_mazas = 0, smoker_in, gender_in, alcoholic_in, prepos_in, sexual_situation_in;
				// get age range (split)
				if (exams.get(i).get_age_range() != null) {
					String age_range = exams.get(i).get_age_range();
					String[] age_r = age_range.split("-");
					start_age = Integer.parseInt(age_r[0]);
					end_age = Integer.parseInt(age_r[1]);
				}

				// get deiktis mazas somatos (split)
				if (exams.get(i).get_deiktis_mazas_range() != null) {
					String deiktis_mazas_range = exams.get(i)
							.get_deiktis_mazas_range();
					String[] deiktis_mazas = deiktis_mazas_range.split("-");
					start_deiktis_mazas = Integer.parseInt(deiktis_mazas[0]);
					end_deiktis_mazas = Integer.parseInt(deiktis_mazas[1]);
				}

				smoker_in = (exams.get(i).get_smoker());
				gender_in = (exams.get(i).get_gender());
				alcoholic_in = (exams.get(i).get_alcohol());
				prepos_in = (exams.get(i).get_inheritance());
				sexual_situation_in = (exams.get(i).get_SexualSituation());

				if (deiktis_mazas_somatos >= start_deiktis_mazas
						&& deiktis_mazas_somatos <= end_deiktis_mazas
						&& age >= start_age
						&& age <= end_age
						&& (smoker_in == 3 || smoker_in == smoker)
						&& (gender_in == 2 || gender_in == gender)
						&& (sexual_situation_in == 2 || sexual_situation_in == sexual_situation)
						&& (alcoholic_in == 2 || alcoholic_in == alcoholic)
						&& (prepos_in == 2 || prepos_in == preposission)) {
					selected_exams_array.add(exams.get(i));

				}
			}
		}
		return selected_exams_array;
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
		recreate = (ImageButton) findViewById(R.id.Brecreate);
		displaySubmit = (TextView) findViewById(R.id.msgSubmit);
		Tweight = (EditText) findViewById(R.id.CommandWeight);
		Theight = (EditText) findViewById(R.id.CommandHeigh);
		setCurrentDateOnView();
	}

}
