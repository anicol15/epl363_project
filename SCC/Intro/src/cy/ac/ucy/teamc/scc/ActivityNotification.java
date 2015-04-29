package cy.ac.ucy.teamc.scc;

import java.util.ArrayList;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

public class ActivityNotification extends Activity implements OnClickListener{
	public static final String NOTIFICATION_DATA = "NOTIFICATION_DATA";
	
	TimePicker notificationTime;
	ImageButton BsetTimeNotification;
	TextView text;
	Thread t;
	ToggleButton toggleButton;
	ArrayList<Exam> exams;
	ArrayList<Exam> list = new ArrayList<Exam>();
	static int ID=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		DatabaseManager db =DatabaseManager.getHelper(getApplicationContext());
		exams=db.getAllPrevExams();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification);
		createAllObjects();
	
		toggleButton=(ToggleButton)findViewById(R.id.deActivateNotification);
		BsetTimeNotification=(ImageButton)findViewById(R.id.BtimeNotification);		
		
		BsetTimeNotification.setOnClickListener(this);
		
	}

			@SuppressLint("ShowToast")
			public void onClick(View view) {
				
				SharedPreferences s_pref=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				if(s_pref!=null){
					
					int notif_Activ=s_pref.getInt("notifActive",9999999);
					Editor edit=s_pref.edit();
				if (toggleButton.isChecked() && notif_Activ==0) {
					
					
					
					edit.putInt("notifActive",1);
					edit.commit();
					
					try{
						

						   
					text.setText("Συγχαρητήρια! Είναι σημαντικό για σας να ειδοποιήστε όταν θα πρέπει να διεξάγεται μια προτεινόμενη εξέταση.");
					
					
					//the related exams of the exam
					if(s_pref!=null){
							int input_exam;
							int input_num_of_exmas=s_pref.getInt("num_of_exam",9999999);
						
							//the related exams of the exam
									
									int notif_time=s_pref.getInt("notif_time",9999999);
							for(int i=0;i<input_num_of_exmas;i++)
								{
								
								input_exam=s_pref.getInt("exam"+i,99999);
								if(input_exam<999)
								{
									Exam current=exams.get(input_exam-1);
									if(current!=null)
									{
										if(current.get_type()==0 && current.get_frequency()>0)
											{
											list.add(current);
											Log.e("exma-add", ""+current.get_name());
											
											
											edit.putString("notific"+i,current.get_name());
											edit.putInt("idnot",i);
											edit.putString("notif",current.get_name());
											edit.commit();
											
											/*Calendar c = Calendar.getInstance();
									    	*/

											 Calendar now = Calendar.getInstance();

											    // add seconds to current date using Calendar.add method
											   // now.add(Calendar.SECOND, 30);
											   // now.set(2014, 5, 23);
									    now.set(Calendar.HOUR_OF_DAY, 0);
									    	now.set(Calendar.MINUTE, 0);
									    
									    	now.set(Calendar.DATE, 7);
									    	// Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
									    	if(notif_time==0)
									    		{
									    		//after 7 days = 604800000
									    		createNotification(now.getTimeInMillis(), current.get_name(),604800000,0);
									    		
									    		
									    		}
									    	
									    	
											}
									}	
								}}
				}
					
					
					}
					catch(Exception e){
						
					}
				
				
				}
				
				else
				{
					text.setText("Είναι σημαντικό για σας να ειδοποιήστε όταν θα πρέπει να διεξάγεται μια προτεινόμενη εξέταση. \n Μπορείται να ενεργοποιήσετε τις ειδοποιήσεις όταν το επιθυμείτε.");
					edit.putInt("notifActive",0);
					edit.putInt("notif_time",0);
					edit.commit();
					//t.stop();
					
					
				}
				
				
				}}
			
			 private void createAllObjects() {
					// TODO Auto-generated method stub
					text = (TextView) findViewById(R.id.text);
					

				}
				
	
			public void createNotification(long when,String data,final long time, int dieksag) throws InterruptedException{
				//dieks=0 programmatise, else if=1 pragmatopiises
				
				final SharedPreferences s_pref=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				if(s_pref!=null){
					
					int notif_Activ=s_pref.getInt("notifActive",9999999);
					if(notif_Activ==1)
					{
					
						String notificationContent="";
						if(dieksag==0)
						{
							 notificationContent ="Υπενθύμιση: Ενημερώνεστε ότι θα πρέπει να διεξάγετε την προτεινομένη εξέταση; ";
							
						}
						else
						{
							 notificationContent ="Υπενθύμιση: Έχετε πραγματοποιήσει την προτεινομένη εξέταση; ";
							
						}
						String notificationTitle ="Stop Cancer Cyprus";
						
						//large icon for notification,normally use App icon
						Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
						int smalIcon =R.drawable.logo;
						String notificationData=data;
						Intent intent ;
						if(dieksag==0)
						{
						/*create intent for show notification details when user clicks notification*/
						 intent =new Intent(getApplicationContext(), NotificationDetailsActivity.class);
						
						}
						else
						{
						intent =new Intent(getApplicationContext(), Ralize_notification.class);
						}
						intent.putExtra(NOTIFICATION_DATA, notificationData);
						/*create unique this intent from  other intent using setData */
						intent.setData(Uri.parse("content://"+when));
						/*create new task for each notification with pending intent so we set Intent.FLAG_ACTIVITY_NEW_TASK */
						PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
						
						/*get the system service that manage notification NotificationManager*/
						final NotificationManager   mNotifyManager =(NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE); 
						
						/*build the notification*/
						final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
								getApplicationContext())
								.setContentText(notificationContent)
								.setContentTitle(notificationTitle)
								.setSmallIcon(smalIcon)
								.setAutoCancel(true)
								.setTicker(notificationTitle)
								.setLargeIcon(largeIcon)
								.setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_VIBRATE| Notification.DEFAULT_SOUND)
								.setContentIntent(pendingIntent)
								;
						
						 t=new Thread(
							 new Runnable() {
							        @Override
							        public void run() {
							            int incr = 0;
							            
							                  
							                        // Sleeps the thread, simulating an operation
							                        // that takes time
							                        try {
							                            // Sleep for 5 seconds
							                            Thread.sleep(time);
							                        } catch (InterruptedException e) {
							                            Log.d("TAG", "sleep failure");
							                        }
							                        
							                        int notif_Activ=s_pref.getInt("notifActive",9999999);
							                        if(notif_Activ==1)
							                        {
											            mNotifyManager.notify(ID, mBuilder.build());
											            ID++;
							                        }
							        }
							    }
							// Starts the thread by calling the run() method in its Runnable
							);
						 if(notif_Activ==1)
							 t.start();
						
						
					}

				}
			}
			
			
		}

