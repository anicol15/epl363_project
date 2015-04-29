package cy.ac.ucy.teamc.scc;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ExaminationList extends ListActivity {

	ArrayList<String> listOfChoices;
	ArrayList<Exam> listExams;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DatabaseManager db = DatabaseManager.getHelper(getApplicationContext());
		listOfChoices = db.getExamNamesonly();
		listExams = db.getAllExams();
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listOfChoices));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
			try {
				Class<?> ourClass = Class
						.forName("cy.ac.ucy.teamc.scc.ExamActivity");
				Intent ourIntent = new Intent(ExaminationList.this, ourClass);
				Bundle b = new Bundle();
				int idExam = listExams.get(position).id;
				b.putInt("position", idExam); 
				ourIntent.putExtras(b); 
				startActivity(ourIntent);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		

	}

}