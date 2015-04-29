package cy.ac.ucy.teamc.scc;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class Menu extends TabActivity {

	TabHost menuTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		menuTabHost = getTabHost();
		TabSpec generalSpec = menuTabHost.newTabSpec("");
		generalSpec.setIndicator("",
				getResources().getDrawable(R.drawable.general));
		Intent GEN = new Intent(this, GeneralInform.class);
		generalSpec.setContent(GEN);
		String prop3 = "";
		TabSpec personalSpec = menuTabHost.newTabSpec(prop3.toLowerCase());
		personalSpec.setIndicator("",
				getResources().getDrawable(R.drawable.personal1));
		SharedPreferences s_pref = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());

		int input_smoker = s_pref.getInt("smoker", 5);

		super.onCreate(savedInstanceState);
		boolean flag_first_time = true;

		if (input_smoker == 5)
			flag_first_time = false;

		if (flag_first_time == false) {

			Intent personallIntent = new Intent(this, PersonalInform.class);
			personalSpec.setContent(personallIntent);
			flag_first_time = true;
			personallIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		}

		else

		{

			Intent personallIntent = new Intent(this,
					PersonalInformNotFirstTime.class);
			personalSpec.setContent(personallIntent);
			personallIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		}

		String prop = "";
		TabSpec settingsSpec = menuTabHost.newTabSpec(prop.toLowerCase());
		settingsSpec.setIndicator(prop.toLowerCase(), getResources()
				.getDrawable(R.drawable.settings));
		Intent settingslIntent = new Intent(this, Settings.class);
		settingsSpec.setContent(settingslIntent);
		settingslIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		String prop2 = "";
		TabSpec pasikafinfo = menuTabHost.newTabSpec(prop2.toLowerCase());
		pasikafinfo.setIndicator("", getResources()
				.getDrawable(R.drawable.logo));
		Intent pasikafinfolIntent = new Intent(this, PasikafInfoafter.class);
		pasikafinfo.setContent(pasikafinfolIntent);
		menuTabHost.addTab(generalSpec);
		menuTabHost.addTab(personalSpec);
		menuTabHost.addTab(settingsSpec);
		menuTabHost.addTab(pasikafinfo);

	}

}
