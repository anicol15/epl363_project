package cy.ac.ucy.teamc.scc;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.ListActivity;
import android.content.Intent;

public class ActivityList extends ListActivity {

	ArrayList<String> listOfChoices;
	ArrayList<Exam> listCancers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DatabaseManager db = DatabaseManager.getHelper(getApplicationContext());
		ArrayList<String> listOfChoices = db.getExamNamesonlyPreventions();
		listCancers = db.getAllPrev();
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listOfChoices));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		try {
			Class<?> ourClass = Class
					.forName("cy.ac.ucy.teamc.scc.PreventionActivity");
			Intent ourIntent = new Intent(ActivityList.this, ourClass);
			Bundle b = new Bundle();
			int idCancer = listCancers.get(position).id;
			b.putInt("position", idCancer); // Your id
			ourIntent.putExtras(b); // Put your id to your next Intent
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}