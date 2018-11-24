package rokkhi.abrar.rokkhidaroan.Signup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.util.HashMap;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import rokkhi.abrar.rokkhidaroan.LoginFinal;
import rokkhi.abrar.rokkhidaroan.Model.Initialhouse;
import rokkhi.abrar.rokkhidaroan.Model.house_contact;
import rokkhi.abrar.rokkhidaroan.R;
import rokkhi.abrar.rokkhidaroan.utils.MyUploadService;
import rokkhi.abrar.rokkhidaroan.utils.UniversalImageLoader;

public class DaroanPic extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button finish;
    private Bitmap bitmap=null;
    Uri uri;
    private BroadcastReceiver mBroadcastReceiver;
    private static final String TAG = "DaroanPic";


    FirebaseFirestore firebaseFirestore;


    // TODO: Rename and change types of parameters
    private String dname1;
    private String dname2;
    private String dname3;
    private String dname4;
    private String dname5;
    private String dname6;
    private String dname7;
    private String dname8;
    house_contact house_contact=new house_contact();


    TextView change1,rokkhi1;
    TextView change2,rokkhi2;
    TextView change3,rokkhi3;
    TextView change4,rokkhi4;
    TextView change5,rokkhi5;
    TextView change6,rokkhi6;
    TextView change7,rokkhi7;
    TextView change8,rokkhi8;

    CircleImageView circleImageView1;
    CircleImageView circleImageView2;
    CircleImageView circleImageView3;
    CircleImageView circleImageView4;
    CircleImageView circleImageView5;
    CircleImageView circleImageView6;
    CircleImageView circleImageView7;
    CircleImageView circleImageView8;





    public DaroanPic() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Serialno.
     */
    // TODO: Rename and change types and number of parameters
    public static DaroanPic newInstance(String param1, String param2) {
        DaroanPic fragment = new DaroanPic();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore=FirebaseFirestore.getInstance();
        if (getArguments() != null) {
            house_contact=getArguments().getParcelable("house");
            dname1=getArguments().getString("d1");
            dname2=getArguments().getString("d2");
            dname3=getArguments().getString("d3");
            dname4=getArguments().getString("d4");
            dname5=getArguments().getString("d5");
            dname6=getArguments().getString("d6");
            dname7=getArguments().getString("d7");
            dname8=getArguments().getString("d8");


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_daroanpic, container, false);

        finish=view.findViewById(R.id.submit);

        rokkhi1=view.findViewById(R.id.rokkhi1);
        rokkhi2=view.findViewById(R.id.rokkhi2);
        rokkhi3=view.findViewById(R.id.rokkhi3);
        rokkhi4=view.findViewById(R.id.rokkhi4);
        rokkhi5=view.findViewById(R.id.rokkhi5);
        rokkhi6=view.findViewById(R.id.rokkhi6);
        rokkhi7=view.findViewById(R.id.rokkhi7);
        rokkhi8=view.findViewById(R.id.rokkhi8);



        change1=view.findViewById(R.id.changeProfilePhoto1);
        change2=view.findViewById(R.id.changeProfilePhoto2);
        change3=view.findViewById(R.id.changeProfilePhoto3);
        change4=view.findViewById(R.id.changeProfilePhoto4);
        change5=view.findViewById(R.id.changeProfilePhoto5);
        change6=view.findViewById(R.id.changeProfilePhoto6);
        change7=view.findViewById(R.id.changeProfilePhoto7);
        change8=view.findViewById(R.id.changeProfilePhoto8);

        circleImageView1=view.findViewById(R.id.profile_photo1);
        circleImageView2=view.findViewById(R.id.profile_photo2);
        circleImageView3=view.findViewById(R.id.profile_photo3);
        circleImageView4=view.findViewById(R.id.profile_photo4);
        circleImageView5=view.findViewById(R.id.profile_photo5);
        circleImageView6=view.findViewById(R.id.profile_photo6);
        circleImageView7=view.findViewById(R.id.profile_photo7);
        circleImageView8=view.findViewById(R.id.profile_photo8);

        rokkhi1.setText("1.  "+ dname1);
        rokkhi2.setText("2.  "+ dname2);
        rokkhi3.setText("3.  "+ dname3);
        rokkhi4.setText("4.  "+ dname4);
        rokkhi5.setText("5.  "+ dname5);
        rokkhi6.setText("6.  "+ dname6);
        rokkhi7.setText("7.  "+ dname7);
        rokkhi8.setText("8.  "+ dname8);

        UniversalImageLoader.setImage("", circleImageView1, null, "");
        UniversalImageLoader.setImage("", circleImageView2, null, "");
        UniversalImageLoader.setImage("", circleImageView3, null, "");
        UniversalImageLoader.setImage("", circleImageView4, null, "");
        UniversalImageLoader.setImage("", circleImageView5, null, "");
        UniversalImageLoader.setImage("", circleImageView6, null, "");
        UniversalImageLoader.setImage("", circleImageView7, null, "");
        UniversalImageLoader.setImage("", circleImageView8, null, "");



        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onReceive:" + intent);
                //hideProgressDialog();

                switch (intent.getAction()) {

                    case MyUploadService.UPLOAD_COMPLETED:
                        uri=intent.getParcelableExtra(MyUploadService.EXTRA_DOWNLOAD_URL);
                        String mobile=intent.getStringExtra("mobile");
                        if(uri!=null){
                            if(mobile.equals(house_contact.getDaroan1())){
                                UniversalImageLoader.setImage(uri.toString(), circleImageView1, null, "");
                            }
                            else if(mobile.equals(house_contact.getDaroan2())){
                                UniversalImageLoader.setImage(uri.toString(), circleImageView2, null, "");
                            }
                            else if(mobile.equals(house_contact.getDaroan3())){
                                UniversalImageLoader.setImage(uri.toString(), circleImageView3, null, "");
                            }
                            else if(mobile.equals(house_contact.getDaroan4())){
                                UniversalImageLoader.setImage(uri.toString(), circleImageView4, null, "");
                            }
                            else if(mobile.equals(house_contact.getDaroan5())){
                                UniversalImageLoader.setImage(uri.toString(), circleImageView5, null, "");
                            }
                            else if(mobile.equals(house_contact.getDaroan6())){
                                UniversalImageLoader.setImage(uri.toString(), circleImageView6, null, "");
                            }
                            else if(mobile.equals(house_contact.getDaroan7())){
                                UniversalImageLoader.setImage(uri.toString(), circleImageView7, null, "");
                            }
                            else if(mobile.equals(house_contact.getDaroan8())){
                                UniversalImageLoader.setImage(uri.toString(), circleImageView8, null, "");
                            }
                        }

                        break;

                    case MyUploadService.UPLOAD_ERROR:
                        onUploadResultIntent(intent);
                        break;
                }
            }
        };


        change1.setOnClickListener(new View.OnClickListener() {
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
                            uploadFrombitmap(house_contact.getDaroan1());
                        }
                        else{
                            Toast.makeText(getActivity(), r.getError().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                }).show(getActivity().getSupportFragmentManager());
            }
        });

        change2.setOnClickListener(new View.OnClickListener() {
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
                            uploadFrombitmap(house_contact.getDaroan2());
                        }
                        else{
                            Toast.makeText(getActivity(), r.getError().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                }).show(getActivity().getSupportFragmentManager());
            }
        });


        change3.setOnClickListener(new View.OnClickListener() {
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
                            uploadFrombitmap(house_contact.getDaroan3());
                        }
                        else{
                            Toast.makeText(getActivity(), r.getError().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                }).show(getActivity().getSupportFragmentManager());
            }
        });


        change4.setOnClickListener(new View.OnClickListener() {
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
                            uploadFrombitmap(house_contact.getDaroan4());
                        }
                        else{
                            Toast.makeText(getActivity(), r.getError().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                }).show(getActivity().getSupportFragmentManager());
            }
        });


        change5.setOnClickListener(new View.OnClickListener() {
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
                            uploadFrombitmap(house_contact.getDaroan5());
                        }
                        else{
                            Toast.makeText(getActivity(), r.getError().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                }).show(getActivity().getSupportFragmentManager());
            }
        });


        change6.setOnClickListener(new View.OnClickListener() {
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
                            uploadFrombitmap(house_contact.getDaroan6());
                        }
                        else{
                            Toast.makeText(getActivity(), r.getError().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                }).show(getActivity().getSupportFragmentManager());
            }
        });


        change7.setOnClickListener(new View.OnClickListener() {
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
                            uploadFrombitmap(house_contact.getDaroan7());
                        }
                        else{
                            Toast.makeText(getActivity(), r.getError().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                }).show(getActivity().getSupportFragmentManager());
            }
        });


        change8.setOnClickListener(new View.OnClickListener() {
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
                            uploadFrombitmap(house_contact.getDaroan8());
                        }
                        else{
                            Toast.makeText(getActivity(), r.getError().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                }).show(getActivity().getSupportFragmentManager());
            }
        });




        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                            Intent intent=new Intent(getActivity(),LoginFinal.class);
                            intent.putExtra("contact",house_contact);
                            startActivity(intent);

            }
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getActivity());
        manager.registerReceiver(mBroadcastReceiver, MyUploadService.getIntentFilter());
    }

    @Override
    public void onStop() {
        super.onStop();

        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mBroadcastReceiver);
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
        getActivity().startService(new Intent(getActivity(), MyUploadService.class)
                .putExtra(MyUploadService.EXTRA_FILE_BITMAP, bitmap)
                .putExtra("mobile",contact)
                .putExtra("from",TAG)
                .putExtra("contact",house_contact)
                .setAction(MyUploadService.ACTION_UPLOAD));
        // Show loading spinner
    }

    // TODO: Rename method, update argument and hook method into UI event




}
