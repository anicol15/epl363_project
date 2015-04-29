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

public class ExamActivity extends Activity {
	public final static String EXTRA_IMAGE_ID = "cy.ac.ucy.teamc.scc.MESSAGE";
	TextView name;
	TextView description;
	ListView relatedExams;
	ArrayList<Exam> cNameDescr = new ArrayList<Exam>();
	ArrayList<Exam> allexams;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
		int id = b.getInt("position");
		DatabaseManager db = DatabaseManager.getHelper(getApplicationContext());

		allexams = db.getAllExams();

		for (int i = 0; i < allexams.size(); i++) {

			if (allexams.get(i).id == id) {

				cNameDescr.add(allexams.get(i));

			}
		}

		setContentView(R.layout.cancer_view);
		LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout2);
		createAllObjects();

		name.setText(cNameDescr.get(0).get_name());
		description.setText(cNameDescr.get(0).get_description());

		String img_name = cNameDescr.get(0).get_image_name();

		// check if for this exam there is an image
		if (!img_name.equals("-")) {

			int checkExistence = getResources().getIdentifier(img_name,
					"drawable", "cy.ac.ucy.teamc.scc");
			boolean result;

			if (checkExistence != 0) { // the resource exists...

				result = true;
				final String image_id_str = String.valueOf(checkExistence);
				ImageButton Bimage = new ImageButton(this);
				Bimage.setImageResource(checkExistence);

				Bimage.setScaleType(ScaleType.FIT_XY);
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
			} else { // checkExistence == 0 // the resouce does NOT exist!!
				result = false;

			}

		}

	}

	private void createAllObjects() {
		// TODO Auto-generated method stub

		name = (TextView) findViewById(R.id.CancerName);
		description = (TextView) findViewById(R.id.CancerDescription);

	}
}
