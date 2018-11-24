package rokkhi.abrar.rokkhidaroan.Login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.ServerTimestamp;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import rokkhi.abrar.rokkhidaroan.Model.Visitors;
import rokkhi.abrar.rokkhidaroan.Model.house_contact;
import rokkhi.abrar.rokkhidaroan.R;
import rokkhi.abrar.rokkhidaroan.utils.MyUploadService;
import rokkhi.abrar.rokkhidaroan.utils.UniversalImageLoader;
import rokkhi.abrar.rokkhidaroan.utils.ValueAdapter;

public class AddActivity extends AppCompatActivity {

    house_contact house_contact=new house_contact();
    private static final String TAG = "AddActivity";

    ImageView search;
    CircleImageView visitor_pic;
    TextView upload;
    EditText visitor_name,visitor_mobile_no,flat_no;
    Button add;

    @ServerTimestamp
            Date itime,otime;

    int flat_number,floor_number,total;
    String flattype;

    private ArrayList<String> mStringList;
    Context context;
    AlertDialog alertDialog;
    private ValueAdapter valueAdapter;
    FirebaseFirestore firebaseFirestore;
    private Bitmap bitmap=null;
    Uri uri;
    private BroadcastReceiver mBroadcastReceiver;
    String propic="";
    String name="";
    String mobile="";
    String flat="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context=AddActivity.this;



        firebaseFirestore=FirebaseFirestore.getInstance();

        search=findViewById(R.id.serach);
        visitor_pic=findViewById(R.id.visitor_photo);
        upload=findViewById(R.id.changeProfilePhoto);
        visitor_name=findViewById(R.id.visitor_name);
        visitor_mobile_no=findViewById(R.id.visitor_mobile_no);
        flat_no=findViewById(R.id.flat_no);
        add=findViewById(R.id.add);
        UniversalImageLoader.setImage("", visitor_pic, null, "");


        Intent intent=getIntent();
        house_contact=intent.getParcelableExtra("contact");

        flattype=house_contact.getFlattype();
        flat_number=house_contact.getFloor_per_flat();
        floor_number=house_contact.getFloor_number();







        initsearch();

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onReceive:" + intent);
                //hideProgressDialog();

                if(intent.getAction()!=null)switch (intent.getAction()) {

                    case MyUploadService.UPLOAD_COMPLETED:
                        uri=intent.getParcelableExtra(MyUploadService.EXTRA_DOWNLOAD_URL);
                        String mobile=intent.getStringExtra("mobile");
                        if(uri!=null){
                            UniversalImageLoader.setImage(uri.toString(), visitor_pic, null, "");
                            propic=uri.toString();
                        }

                        break;

                    case MyUploadService.UPLOAD_ERROR:
                        onUploadResultIntent(intent);
                        break;
                }
            }
        };

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PickSetup setup = new PickSetup().setWidth(100).setHeight(100)
                        .setTitle("Choose Photo")
                        .setBackgroundColor(Color.WHITE)
                        .setButtonOrientation(LinearLayout.HORIZONTAL)
                        .setGalleryButtonText("Gallery")
                        .setCameraIcon(R.mipmap.camera_colored)
                        .setGalleryIcon(R.mipmap.gallery_colored)
                        ;




                PickImageDialog.build(setup, new IPickResult() {
                    @Override
                    public void onPickResult(PickResult r) {
                        if(r.getError()== null){
                            // mFileUri=r.getUri();
                            bitmap=r.getBitmap();


                            // -FAISAL UPDATES SOME NODES HERE
                            //final String mFileUri=r.getUri().toString();
                            //String mobileno=house_contact.getDaroan1();
                            //uploadFromUri();
                            uploadFrombitmap(visitor_mobile_no.getText().toString());
                        }
                        else{
                            Toast.makeText(context, r.getError().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                }).show(AddActivity.this);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=visitor_name.getText().toString();
                if(name.equals("")){
                    return;
                }
                if(flat.equals("")) return;

                //Date now=FieldValue.serverTimestamp();

                mobile=visitor_mobile_no.getText().toString();
                Map<String, Object> visitors = new HashMap<>();
                visitors.put("phone", mobile);
                visitors.put("name", name);
                visitors.put("pro_pic", propic);
                visitors.put("phid",house_contact.getPhid());
                visitors.put("flat_no",flat);
                visitors.put("itime",FieldValue.serverTimestamp());
                visitors.put("otime",FieldValue.serverTimestamp());




               // Visitors visitors=new Visitors(mobile,name,propic,house_contact.getPhid(),flat,FieldValue.serverTimestamp(),FieldValue.serverTimestamp());
                if(mobile.equals("")){
                    firebaseFirestore.collection("phid").document(house_contact.getPhid()).collection("visitors")
                            .add(visitors).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(context,"Visitor is added",Toast.LENGTH_SHORT).show();
                            Intent intent1=new Intent();
                            intent1.putExtra("contact",house_contact);
                            startActivity(intent1);
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                   // Toast.makeText(context,"Connection Error!",Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else{
                    firebaseFirestore.collection("phid").document(house_contact.getPhid()).collection("visitors")
                            .document(mobile).set(visitors).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(context,"Visitor is added",Toast.LENGTH_SHORT).show();
                            Intent intent1=new Intent();
                            intent1.putExtra("contact",house_contact);
                            startActivity(intent1);
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                   // Toast.makeText(context,"Connection Error!",Toast.LENGTH_SHORT).show();
                                }
                            });

                }
                }


        });








    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);
        manager.registerReceiver(mBroadcastReceiver, MyUploadService.getIntentFilter());
    }

    @Override
    public void onStop() {
        super.onStop();

        LocalBroadcastManager.getInstance(context).unregisterReceiver(mBroadcastReceiver);
    }

    private void onUploadResultIntent(Intent intent) {
        // Got a new intent from MyUploadService with a success or failure
        // mFileUri = intent.getParcelableExtra(MyUploadService.EXTRA_FILE_URI);
        bitmap = intent.getParcelableExtra(MyUploadService.EXTRA_FILE_BITMAP);
    }

    private void uploadFrombitmap(String contact) {

        // Save the File URI
        Log.d(TAG, "uploadFromUri: uploadedit "+ bitmap.toString());

        // Clear the last download, if any

        // Start MyUploadService to upload the file, so that the file is uploaded
        // even if this Activity is killed or put in the background
        startService(new Intent(context, MyUploadService.class)
                .putExtra(MyUploadService.EXTRA_FILE_BITMAP, bitmap)
                .putExtra("mobile",contact)
                .putExtra("from",TAG)
                .putExtra("contact",house_contact)
                .setAction(MyUploadService.ACTION_UPLOAD));
        // Show loading spinner
    }

    public void initsearch(){

        mStringList=new ArrayList<String>();
        if(flattype.equals("1A")){
            for(int i=1;i<=floor_number;i++){
                for(int j=65;j<=flat_number+65;j++){
                    String flatno=Integer.toString(i)+Character.toString((char)j);
                    mStringList.add(flatno);
                    Log.d(TAG, "initsearch: flatno "+flatno);
                }
            }
        }
        else if(flattype.equals("A1")){
            for(int i=1;i<=floor_number;i++){
                for(int j=65;j<=flat_number+65;j++){
                    String flatno=Character.toString((char)j)+Integer.toString(i);
                    mStringList.add(flatno);
                    Log.d(TAG, "initsearch: flatno "+flatno);
                }
            }
        }
        else if(flattype.equals("101")){
            for(int i=1;i<=floor_number;i++){
                for(int j=1;j<=flat_number;j++){
                    String flatno=Integer.toString(i);
                    if(i<10)flatno+="0";
                    flatno+=Integer.toString(j);
                    mStringList.add(flatno);
                    Log.d(TAG, "initsearch: flatno "+flatno);
                }
            }
        }




        flat_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueAdapter=new ValueAdapter(mStringList,context);

                alertDialog = new AlertDialog.Builder(context).create();
                LayoutInflater inflater = getLayoutInflater();
                View convertView = (View) inflater.inflate(R.layout.custom_list, null);
                final EditText editText=convertView.findViewById(R.id.sear);
                final ListView lv = (ListView) convertView.findViewById(R.id.listView1);
                alertDialog.setView(convertView);
                alertDialog.setCancelable(true);

                lv.setAdapter(valueAdapter);
                alertDialog.show();
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        valueAdapter.getFilter().filter(s);

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        flat_no.setText(lv.getItemAtPosition(position).toString());
                        flat=lv.getItemAtPosition(position).toString();

                        alertDialog.dismiss();

                    }
                });
            }
        });

    }

}
