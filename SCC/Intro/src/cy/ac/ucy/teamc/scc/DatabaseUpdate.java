package cy.ac.ucy.teamc.scc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;

import android.content.Context;
import android.os.AsyncTask;

@SuppressWarnings("rawtypes")
public class DatabaseUpdate extends AsyncTask {

	private Context c;
	
	public JSONArray getInternetCancers(Context c) throws Exception {

		String timeStamp = WriteTimestamp.readTime(c);
		URL url = new URL(
				"http://apps.cs.ucy.ac.cy/stopcancercyprus/api/getCancers.php?udate="
						+ timeStamp);
		URLConnection urlConnection = url.openConnection();
		InputStream o = urlConnection.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(o));
		StringBuffer sb = new StringBuffer("");
		String l = "";
		String nl = System.getProperty("line.separator");
		while ((l = in.readLine()) != null) {
			sb.append(l + nl);
		}
		in.close();
		String data = sb.toString();
		JSONArray jArray = new JSONArray(data);
		return jArray;
	}

	public JSONArray getInternetExams(Context c) throws Exception {
		String timeStamp = WriteTimestamp.readTime(c);

		URL url = new URL(
				"http://apps.cs.ucy.ac.cy/stopcancercyprus/api/getExams.php?udate="
						+ timeStamp);
		URLConnection urlConnection = url.openConnection();
		InputStream o = urlConnection.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(o));
		StringBuffer sb = new StringBuffer("");
		String l = "";
		String nl = System.getProperty("line.separator");
		while ((l = in.readLine()) != null) {
			sb.append(l + nl);
		}
		in.close();
		String data = sb.toString();
		JSONArray jArray = new JSONArray(data);
		return jArray;
	}

	@Override
	protected Object doInBackground(Object... arg0) {
		// TODO Auto-generated method stub
		c = (Context) arg0[0];
		JSONArray[] ret = new JSONArray[2];
		ret[0] = null;
		ret[1] = null;
		try {
			ret[0] = getInternetCancers(c);
		} catch (Exception e) {
			e.printStackTrace();
			ret[0] = null;
		}
		try {
			ret[1] = getInternetExams(c);
		} catch (Exception e) {
			e.printStackTrace();
			ret[1] = null;
		}
		if (ret[0] != null || ret[1] != null)
			// write timestamp only if something was updated, in case of errors
			WriteTimestamp.writeCurrentTime(c);
		
		return ret;
	}
	
	@Override
	protected void onPostExecute(Object tables){
		DatabaseManager.getHelper(c).addData(tables);
	}

}
