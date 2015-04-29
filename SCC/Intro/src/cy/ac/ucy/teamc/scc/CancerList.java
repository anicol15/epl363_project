package cy.ac.ucy.teamc.scc;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CancerList extends ListActivity {
	
	ArrayList<String> listOfChoices;
	ArrayList<Cancer> listCancers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		DatabaseManager db = DatabaseManager.getHelper(getApplicationContext());
		listOfChoices = db.getCancerNames();
		listCancers = db.getAllCancers();
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listOfChoices));

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
			try {
				Class<?> ourClass = Class
						.forName("cy.ac.ucy.teamc.scc.CancerActivity");
				Intent ourIntent = new Intent(CancerList.this, ourClass);
				Bundle b = new Bundle();
				int idCancer = listCancers.get(position).id;
				b.putInt("position", idCancer); //Your id
				ourIntent.putExtras(b); //Put your id to your next Intent
				startActivity(ourIntent);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		

	}

}