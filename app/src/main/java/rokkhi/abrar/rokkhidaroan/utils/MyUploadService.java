package rokkhi.abrar.rokkhidaroan.utils;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import rokkhi.abrar.rokkhidaroan.Model.house_contact;
import rokkhi.abrar.rokkhidaroan.R;
import rokkhi.abrar.rokkhidaroan.Signupactivity;

/**
 * Service to handle uploading files to Firebase Storage.
 */
public class MyUploadService extends MyBaseTaskService {

    private static final String TAG = "MyUploadService";

    /** Intent Actions **/
    public static final String ACTION_UPLOAD = "action_upload";
    public static final String UPLOAD_COMPLETED = "upload_completed";
    public static final String UPLOAD_ERROR = "upload_error";
    house_contact  house_contact=new house_contact();
    /** Intent Extras **/
    public static final String EXTRA_FILE_URI = "extra_file_uri";
    public static final String EXTRA_FILE_BITMAP = "extra_file_bitmap";
    public static final String EXTRA_TEXT= "extra_text";
    public static final String EXTRA_DOWNLOAD_URL = "extra_download_url";

    StorageReference photoRef;

    // [START declare_ref]
    private StorageReference mStorageRef;
    private FirebaseFirestore firebaseFirestore;
    //private DatabaseReference myRef;
    // [END declare_ref]

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate: "+ "dhuksesure");

        // [START get_storage_ref]
        mStorageRef = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        //myRef = mFirebaseDatabase.getReference();
        // [END get_storage_ref]
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand:" + intent + ":" + startId);
        if (ACTION_UPLOAD.equals(intent.getAction())) {
           // Uri fileUri = intent.getParcelableExtra(EXTRA_FILE_URI);
            Bitmap bitmap=intent.getParcelableExtra(EXTRA_FILE_BITMAP);
            String ss=intent.getStringExtra("mobile");
            String from=intent.getStringExtra("from");
            house_contact=intent.getParcelableExtra("contact");
            //uploadFromUri(fileUri);
            uploadFrombitmap(bitmap,ss,from);
        }

        return START_REDELIVER_INTENT;
    }



    // [START upload_from_uri]
    private void uploadFrombitmap(final Bitmap bitmap, final String mobile,final String from) {
        Log.d(TAG, "uploadFromUri: uploadservice" + bitmap.toString());

        // [START_EXCLUDE]
        taskStarted();
        FilePaths filePaths = new FilePaths();
        //String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        showProgressNotification(getString(R.string.progress_uploading), 0, 0);
        // [END_EXCLUDE]

        // [START get_child_ref]
        // Get a reference to store file at photos/<FILENAME>.jpg

        if(from.equals("AddActivity")){
            photoRef= FirebaseStorage.getInstance().getReference()
                    .child("visitorpic/"+mobile);
        }
        else {
            photoRef= FirebaseStorage.getInstance().getReference()
                    .child("daroanpic/"+mobile);
        }



        // [END get_child_ref]

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 25, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = photoRef.putBytes(data);

        // Upload file to Firebase Storage
        Log.d(TAG, "uploadFromUri:dst:" + photoRef.getPath());
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        showProgressNotification(getString(R.string.progress_uploading),
                                taskSnapshot.getBytesTransferred(),
                                taskSnapshot.getTotalByteCount());
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Upload succeeded
                        Log.d(TAG, "uploadFromUri:onSuccess " + "hoise1");

                        // Get the public download URL
                        photoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri firebaseUrl = uri;
                                Log.d(TAG, "onSuccess: " +"hoise2");
                                setProfilePhoto(firebaseUrl.toString(),mobile,from);

                                // [START_EXCLUDE]
                                broadcastUploadFinished(firebaseUrl, bitmap,mobile);
                                showUploadFinishedNotification(firebaseUrl, bitmap,mobile);
                                taskCompleted();
                            }
                        });



                        // [END_EXCLUDE]
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Upload failed
                        Log.w(TAG, "uploadFromUri:onFailure", exception);

                        // [START_EXCLUDE]
                        broadcastUploadFinished(null, bitmap,mobile);
                        showUploadFinishedNotification(null, bitmap,mobile);
                        taskCompleted();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END upload_from_uri]

    /**
     * Broadcast finished upload (success or failure).
     * @return true if a running receiver received the broadcast.
     */
    private boolean broadcastUploadFinished(@Nullable Uri downloadUrl, @Nullable Bitmap bitmap, @Nullable String mobile) {
        boolean success = downloadUrl != null;

        String action = success ? UPLOAD_COMPLETED : UPLOAD_ERROR;

        Intent broadcast = new Intent(action)
                .putExtra(EXTRA_DOWNLOAD_URL, downloadUrl)
                .putExtra(EXTRA_FILE_BITMAP, bitmap)
                .putExtra("mobile",mobile);
        return LocalBroadcastManager.getInstance(getApplicationContext())
                .sendBroadcast(broadcast);
    }

    /**
     * Show a notification for a finished upload.
     */
    private void showUploadFinishedNotification(@Nullable Uri downloadUrl, @Nullable Bitmap bitmap,@Nullable String mobile) {
        // Hide the progress notification
        dismissProgressNotification();

        // Make Intent to MainActivity
        Intent intent = new Intent(this, Signupactivity.class)
                .putExtra(EXTRA_DOWNLOAD_URL, downloadUrl)
                .putExtra(EXTRA_FILE_BITMAP, bitmap)
                .putExtra("mobile",mobile)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        boolean success = downloadUrl != null;
        String caption = success ? getString(R.string.upload_success) : getString(R.string.upload_failure);
        showFinishedNotification(caption, intent, success);
    }

    public static IntentFilter getIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UPLOAD_COMPLETED);
        filter.addAction(UPLOAD_ERROR);

        return filter;
    }

    private void setProfilePhoto(String url,String mobile,String from){
        Log.d(TAG, "setProfilePhoto: setting new profile image: " + url);


        if(from.equals("DaroanPic")){
            firebaseFirestore.collection("rokkhi").document(mobile).update("pro_pic",url).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){

                    }
                }
            });
        }





    }

}
