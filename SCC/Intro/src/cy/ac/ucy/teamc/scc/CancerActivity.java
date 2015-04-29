package cy.ac.ucy.teamc.scc;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class CancerActivity extends Activity {
	public final static String EXTRA_IMAGE_ID = "cy.ac.ucy.teamc.scc.MESSAGE";
	TextView name;
	TextView description;
	ListView relatedExams;
	ArrayList<Cancer> cNameDescr = new ArrayList<Cancer>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
		int id = b.getInt("position");
		DatabaseManager db = DatabaseManager.getHelper(getApplicationContext());
		ArrayList<Cancer> allcancers = db.getAllCancers();

		int y = allcancers.size();

		for (int i = 0; i < allcancers.size(); i++) {

			if (allcancers.get(i).getId() == id) {

				cNameDescr.add(allcancers.get(i));
			}
		}
		setContentView(R.layout.cancer_view);
		LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout2);
		createAllObjects();
		name.setText(cNameDescr.get(0).getName());
		description.setText(cNameDescr.get(0).getDescription());
		// check if for this exam there is an image
		String img_name = cNameDescr.get(0).getimg_name();

		// check if for this exam there is an image

		int checkExistence = getResources().getIdentifier(img_name, "drawable",
				"cy.ac.ucy.teamc.scc");
		boolean result;

		if (checkExistence != 0) { // the resource exists...
			result = true;
			final String image_id_str = String.valueOf(checkExistence);
			ImageButton Bimage = new ImageButton(this);

			Bimage.setImageResource(checkExistence);

			Bimage.setScaleType(ScaleType.FIT_XY);
			// Bimage.setLayoutParams(new LinearLayout.LayoutParams(100,100));
			Bimage.setLayoutParams(new LinearLayout.LayoutParams(285, 200));

			ll.addView(Bimage);

			Bimage.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent i = new Intent(getApplicationContext(),
							TouchImageViewActivity.class);

					Bundle extras = new Bundle();
					extras.putString("EXTRA_IMAGE_ID", image_id_str);
					i.putExtras(extras);

					startActivity(i);

				}

			});
		} else { // checkExistence == 0 // the resource does NOT exist!!
			result = false;
		}

	}

	private void createAllObjects() {
		// TODO Auto-generated method stub
		name = (TextView) findViewById(R.id.CancerName);
		description = (TextView) findViewById(R.id.CancerDescription);

	}
}
