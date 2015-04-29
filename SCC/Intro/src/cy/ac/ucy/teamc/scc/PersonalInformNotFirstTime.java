package cy.ac.ucy.teamc.scc;

import java.text.DecimalFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PersonalInformNotFirstTime extends Activity {
	TextView cont;
	ImageButton showRelatedExams;
	
	public static String roundToOneDigit(float paramFloat) {
	    return String.format("%.2f%n", paramFloat);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_inform_not_first_time);
		
		LinearLayout ll = (LinearLayout)findViewById(R.id.linearLayout2);
		createAllObjects();
		SharedPreferences s_pref=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		int curyear = Calendar.getInstance().get(Calendar.YEAR);
		
		int input_smoker=s_pref.getInt("smoker",0);
		int input_Gender=s_pref.getInt("Gender",0);
		int input_year_of_birth=s_pref.getInt("year_of_birth",0);
		int input_alcoholic=s_pref.getInt("alcoholic",0);
		int input_Preposission=s_pref.getInt("Preposission",0);
		int input_SexualSituation=s_pref.getInt("SexualSituation",0);
		float input_maza_somatos=s_pref.getFloat("maza_somatos",(float) 0.0);
		
		
		//calculate the  age of the use
		int age=curyear-input_year_of_birth;
		String in_Gender, in_Smoker,in_alcoholic,in_Preposission,in_SexualSituation,in_maza_somatos = null;
		
		//inGender
		if(input_Gender==0)
			in_Gender="άντρας. ";
		else
			in_Gender="γυναίκα. ";
		
		//inSmoker
		if(input_smoker==0)
		{
			if(input_Gender==0)
				in_Smoker="\nΕίσαστε νυν καπνιστής. Ενταχθείτε έγκαιρα σε ομάδα διακοπής του καπνίσματος. ";
			else
				in_Smoker="\nΕίσαστε νυν καπνίστρια. Ενταχθείτε έγκαιρα σε ομάδα διακοπής του καπνίσματος. ";

			
		}
		else if(input_smoker==1)
		{
			if(input_Gender==0)
				in_Smoker="\nΕίσαστε πρώην καπνιστής. Συγχαρητήρια! Είσαστε παθητικός καπνιστής? Διεκδικίστε το δικαώμα σας για χώρους ελεύθερους από καπνό. ";
			else
				in_Smoker="\nΕίσαστε πρώην καπνίστρια. Συγχαρητήρια! Είσαστε παθητικός καπνιστής? Διεκδικίστε το δικαώμα σας για χώρους ελεύθερους από καπνό. ";
			
		}
			
		else
		{
			if(input_Gender==0)
				in_Smoker="\nΕίσαστε μη καπνιστής. Συγχαρητήρια! Είσαστε παθητικός καπνιστής? Διεκδικίστε το δικαώμα σας για χώρους ελεύθερους από καπνό. ";
			
			else
				in_Smoker="\nΕίσαστε μη  καπνίστρια. Συγχαρητήρια! Είσαστε παθητική καπνίστρια? Διεκδικίστε το δικαώμα σας για χώρους ελεύθερους από καπνό. ";
			
		}
			
		
		//in_alcoholic
		if(input_alcoholic==0)
		{
			if(input_Gender==0)
				in_alcoholic="\nΕίσαστε καταναλωτής αλκοόλ. Καταναλώνετε αλκοόλ συστηματικά πέρα του ενός ή 2 ποτήρια την ημέρα. Ενταχθείτε έγκαιρα σε προγράμματα διακοπής του αλκοόλ. ";
			else
				in_alcoholic="\nEίσαστε καταναλώτρια αλκοόλ. Καταναλώνετε αλκοόλ συστηματικά πέρα του ενός ή 2 ποτήρια την ημέρα. Ενταχθείτε έγκαιρα σε προγράμματα διακοπής του αλκοόλ.";
			
		}
		else 
		{
			if(input_Gender==0)
				in_alcoholic="\nΔεν είσαστε καταναλωτής αλκοόλ. Δεν καταναλώνετε αλκοόλ συστηματικά πέρα του ενός ή 2 ποτήρια την ημέρα.";
			else
				in_alcoholic="\nΔεν είσαστε καταναλώτρια αλκοόλ. Δεν καταναλώνετε αλκοόλ συστηματικά πέρα του ενός ή 2 ποτήρια την ημέρα.";
			
		}
		
		//in_Preposission
		if(input_Preposission==0)
			in_Preposission="\nΈχετε Προδιάθεση/Οικογενειακό ιστορικό. ";
		else
			in_Preposission="\nΔεν εχετε Προδιάθεση/Οικογενειακό ιστορικό. ";
		
		//in_SexualSituation
		if(input_SexualSituation==0)
		{
			if(input_Gender==0)
				in_SexualSituation="\nΕίσαστε σεξουαλικά ενεργός για περισσότερα από 2 χρόνια. ";
			else
				in_SexualSituation="\nΕίσαστε σεξουαλικά ενεργή για περισσότερα από 2 χρόνια. ";
			
		}
		else
		{
			if(input_Gender==0)
				in_SexualSituation="\nΔεν είσαστε σεξουαλικά ενεργός για περισσότερα από 2 χρόνια. ";
			else
				in_SexualSituation="\nΔεν είσαστε σεξουαλικά ενεργή για περισσότερα από 2 χρόνια. ";
		}	
		
		
		
		
		if(input_maza_somatos<18.5)
			in_maza_somatos="\nΕίσαστε λιποβαρής. Συμβουλευτείτε το διαιτολόγο σας. ";
		else if(input_maza_somatos>=18.5  && input_maza_somatos<24.9)
			in_maza_somatos="\n'Εχετε κανονικό βάρος. Συγχαρητήρια! ";
		else if(input_maza_somatos>=25 && input_maza_somatos<=29.9)
		in_maza_somatos="\nΕίσαστε παχύσαρκος. Προσέξτε το σωματικό σας βάρος με ισσοροπημένη διατροφή και άσκηση. Συμβουλευτείτε το διαιτολόγο σας. ";
		else
			in_maza_somatos="\nΕίσαστε υπέρβαρος. Προσέξτε το σωματικό σας βάρος με ισσοροπημένη διατροφή και άσκηση. Συμβουλευτείτε το διαιτολόγο σας. ";
		
		DecimalFormat df = new DecimalFormat("###.##");
			
		
		  // set the of the text view for context of the user's info
       
        cont.setText("Έχετε ήδη εισάγει τα προσωπικά σας στοιχεία. \nΑν επιθυμήτε να τα τροποποιήσετε υπάρχει η επιλογή Ρυθμίσεις. \nΕίσαστε "+in_Gender+" \nΕίσαστε "+age+" χρονών. \nΟ δείκτης μάζας σώματος σας είναι "+df.format(input_maza_somatos)+". "+in_maza_somatos+in_Smoker+in_alcoholic+in_Preposission+in_SexualSituation);
       
		
        

        showRelatedExams.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					Class ourClass = Class.forName("cy.ac.ucy.teamc.scc.Personal_information");
					//triggered a class that user selected.
					Intent ourIntent = new Intent(PersonalInformNotFirstTime.this, ourClass);
					startActivity(ourIntent);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			}
		});
	}
	

	
	

	public boolean createAllObjects() {
		cont = (TextView) findViewById(R.id.Cont);
		showRelatedExams = (ImageButton) findViewById(R.id.Bsubmit);
		return true;
	}

}
