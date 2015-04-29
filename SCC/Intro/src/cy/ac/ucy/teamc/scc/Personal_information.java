package cy.ac.ucy.teamc.scc;


import java.util.ArrayList;
import java.util.Calendar;

import android.R.color;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class Personal_information extends Activity {

	ArrayList<Exam> exams;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		DatabaseManager db =DatabaseManager.getHelper(getApplicationContext());
		exams=db.getAllPrevExams();
		int curyear = Calendar.getInstance().get(Calendar.YEAR);
		
		///
	//check if the user has already gives his personal data
		
		SharedPreferences s_pref=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		//the related exams of the exam
			
				int input_num_of_exmas2=s_pref.getInt("num_of_exam",9999999);
				
				if(input_num_of_exmas2!=9999999)
				{
					Editor edit = s_pref.edit();
					//delete the exams from the file
					
						for (int i = 0; i < input_num_of_exmas2; i++)
							edit.remove("exam" + i);
				}				
					//call the inform user from personalinform
						int year_of_birth=s_pref.getInt("year_of_birth",2014);
						int smoker=s_pref.getInt("smoker", 3);
						int Gender=s_pref.getInt("Gender", 0);
						int alcoholic=s_pref.getInt("alcoholic", 0);
						int Preposission=s_pref.getInt("Preposission", 0);
						int SexualSituation=s_pref.getInt("SexualSituation", 0);
						float maza_somatos=s_pref.getFloat("maza_somatos", 0);
						
						int age = curyear - year_of_birth;
						ArrayList<Exam> selected_exams = new ArrayList<Exam>();

						selected_exams = informUser(age, smoker, Gender,
								maza_somatos, alcoholic, Preposission,
								SexualSituation);
						
						Editor edit = s_pref.edit();
						int num = selected_exams.size();
						edit.putInt("num_of_exam", num);
						if (!selected_exams.isEmpty())
							for (int i = 0; i < selected_exams.size(); i++)
								edit.putInt("exam" + i, selected_exams.get(i)
										.get_id());

						edit.commit();
						
						///
		
	
		
		
		
		
	ArrayList<Exam> list = new ArrayList<Exam>();
	
	
	
	//the related exams of the exam
			int input_exam;
			int input_num_of_exmas=s_pref.getInt("num_of_exam",9999999);
			for(int i=0;i<input_num_of_exmas;i++)
				{
				
				input_exam=s_pref.getInt("exam"+i,99999);

				for(int J=0; J<exams.size();J++){
					
					
					if(exams.get(J).id==input_exam)
					{
						
						
						Exam current=exams.get(J);
						list.add(current);
				
					}
				}
				
				
				
				
				
				}
	
	
		
	 // Create the text view
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
 
        LinearLayout ll = (LinearLayout)findViewById(R.id.linearLayout2);
 
        for(int i=0;i<list.size();i++)
        {
        	String name =  list.get(i).get_name();
    	    String description =  list.get(i).get_description();
    	    String img_name = list.get(i).get_image_name();
    	    int freq =   list.get(i).get_frequency();
        
        // add text view for the name of the exam
        EditText tv_name = new EditText(this);
        tv_name.setText(name);
        tv_name.setTextSize(20);
        tv_name.setBackgroundColor(Color.parseColor("#ddfac0"));
        tv_name.setClickable(false);
        tv_name.setKeyListener(null);
        tv_name.setCursorVisible(false);
        tv_name.setPressed(false);
        tv_name.setFocusable(false);
        tv_name.setGravity(Gravity.CENTER);
        ll.addView(tv_name);
	    
        if(freq>0)
        {String frequency =   Integer.toString(list.get(i).get_frequency());
	     // add text view for the frequency of the exam
	        EditText tv_freq = new EditText(this);
	        tv_freq.setText("Συχνότητα διεξαγωγής της εξέτασης: κάθε "+frequency+" μήνες."); tv_freq.setBackgroundColor(color.holo_green_light);
	        tv_freq.setTextSize(10);
	        tv_freq.setGravity(Gravity.CENTER);
	        tv_freq.setClickable(false);
	        tv_freq.setKeyListener(null);
	        tv_freq.setCursorVisible(false);
	        tv_freq.setPressed(false);
	        tv_freq.setFocusable(false);
	        ll.addView(tv_freq);
        }
	    
        
     // add text view for the description of the exam
        EditText tv_descr = new EditText(this);
        tv_descr.setText(description);
        tv_descr.setBackgroundColor(color.holo_green_light);
        tv_descr.setTextSize(10);
        tv_descr.setGravity(Gravity.CENTER);
        tv_descr.setClickable(false);
        tv_descr.setKeyListener(null);
        tv_descr.setCursorVisible(false);
        tv_descr.setPressed(false);
        tv_descr.setFocusable(false);
        ll.addView(tv_descr);
	    

        
        //check if for this exam there is an image
        if(!(img_name.equals("-")))
        {
        
        int checkExistence = getResources().getIdentifier(img_name, "drawable","cy.ac.ucy.teamc.scc");
        boolean result;
        
        
        
        if ( checkExistence != 0 ) {  // the resource exists
            result = true;
            final String image_id_str = String.valueOf(checkExistence);
            ImageButton Bimage = new ImageButton(this);
            Bimage.setImageResource(checkExistence);
   
            Bimage.setScaleType(ScaleType.FIT_XY);
            

            Bimage.setLayoutParams(new LinearLayout.LayoutParams(285,200));
            ll.addView(Bimage);
            
            
            
            Bimage.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent i = new Intent(getApplicationContext(),TouchImageViewActivity.class);
					
					Bundle extras = new Bundle();
					extras.putString("EXTRA_IMAGE_ID",image_id_str);
					i.putExtras(extras);
					
					
	                startActivity(i);
	  
				}
            
            });
        }
        else {  // checkExistence == 0  // the resource does NOT exist!!
            result = false;
           
        }
        }
        
        }
	}
				// Inform personal the user about the exams that he/she should do
				public ArrayList<Exam> informUser(int age, int smoker, int gender,
						float deiktis_mazas_somatos, int alcoholic, int preposission,
						int sexual_situation) {
					ArrayList<Exam> selected_exams_array = new ArrayList<Exam>();

					for (int i = 0; i < exams.size(); i++) {
						if (exams.get(i) != null) {
							// exams.get(i).id
							int start_age = 0, end_age = 0, start_deiktis_mazas = 0, end_deiktis_mazas = 0, smoker_in = 0, gender_in = 0, alcoholic_in = 0, prepos_in = 0, sexual_situation_in = 0;
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

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.personal_information, menu);
		return true;
	}
	
	
}


