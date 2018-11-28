package rokkhi.abrar.rokkhidaroan.Login;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import de.hdodenhof.circleimageview.CircleImageView;
import rokkhi.abrar.rokkhidaroan.LoginFinal;
import rokkhi.abrar.rokkhidaroan.Model.Person;
import rokkhi.abrar.rokkhidaroan.Model.SecRokkhi;
import rokkhi.abrar.rokkhidaroan.Model.Sec_house_contact;
import rokkhi.abrar.rokkhidaroan.Model.house_contact;
import rokkhi.abrar.rokkhidaroan.R;
import rokkhi.abrar.rokkhidaroan.Signup.Serialno;
import rokkhi.abrar.rokkhidaroan.Startingpage;
import rokkhi.abrar.rokkhidaroan.utils.UniversalImageLoader;

public class InsideApp extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    String from="";
    house_contact house_contact=new house_contact();
    Sec_house_contact sec_house_contact=new Sec_house_contact();
    String pass=null;
    SecRokkhi secRokkhi=new SecRokkhi();
    String daroanno=null;
    private static final String TAG = "InsideApp";

    private FirebaseAuth mAuth;
    TextView navtitle,navdetail;
    FirebaseFirestore firebaseFirestore;
    CircleImageView imageView;
    Context context;
    private static final int REQUEST_READ_PHONE_STATE = 591;
    String imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insideapp);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        context=InsideApp.this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();
        house_contact=intent.getParcelableExtra("contact");
        sec_house_contact=intent.getParcelableExtra("sec_contact");
        daroanno=intent.getStringExtra("daroanno");
        secRokkhi=intent.getParcelableExtra("rokkhi");
        pass=intent.getStringExtra("pass");

       // Log.d(TAG, "onCreate: daroan "+daroanno);
        //getSupportActionBar().setTitle("Rokkhi");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent intent1=new Intent(InsideApp.this,AddActivity.class);

                intent1.putExtra("contact",house_contact);
                intent1.putExtra("daroanno",daroanno);
                intent1.putExtra("sec_contact",sec_house_contact);
                intent1.putExtra("rokkhi",secRokkhi);
                intent1.putExtra("pass",pass);
                startActivity(intent1);


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navtitle=headerView.findViewById(R.id.nav_title);
        navdetail=headerView.findViewById(R.id.nav_detail);
        imageView=headerView.findViewById(R.id.propic);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                navtitle.setText(getDeviceName());
                imei="IMEI: "+ getUniqueId(InsideApp.this);
                if(daroanno!=null){
                    firebaseFirestore.collection("rokkhi").document(daroanno).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Person daroan=documentSnapshot.toObject(Person.class);
                            if(daroan!=null){
                                UniversalImageLoader.setImage(daroan.getPro_pic(), imageView, null, "");
                            }
                            else{
                                UniversalImageLoader.setImage("", imageView, null, "");
                            }
                        }
                    });
                }
                else if(pass!=null){
                    UniversalImageLoader.setImage(secRokkhi.getPro_pic(), imageView, null, "");

                }
                navdetail.setText(imei);

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();




        Fragment mainpage = new Mainpage();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
////// Replace whatever
// is in the fragment_container view with this fragment,
////// and add the transaction to the back stack
        transaction.add(R.id.container_insideapp, mainpage);

        Bundle bundle = new Bundle();
        bundle.putParcelable("contact",house_contact);
        bundle.putString("daroanno",daroanno);
        bundle.putString("pass",pass);
        bundle.putParcelable("sec_contact",sec_house_contact);
        bundle.putParcelable("rokkhi",secRokkhi);

        mainpage.setArguments(bundle);
        transaction.addToBackStack(null);
//
//// Commit the transaction
        transaction.commit();



    }

    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    imei="IMEI :"+getUniqueId(InsideApp.this);
                }
                break;

            default:
                break;
        }
    }


    public static String getUniqueId(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= 23 &&
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);


//                 TODO: Consider calling
//                    ActivityCompat#requestPermissions
//                 here to request the missing permissions, and then overriding
//                   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                                          int[] grantResults)
//                 to handle the case where the user grants the permission. See the documentation
//                 for ActivityCompat#requestPermissions for more details.
                return "";
            }
            String imei = telephonyManager.getDeviceId();
            Log.e("imei", "=" + imei);
            if (imei != null && !imei.isEmpty()) {
                return imei;
            } else {
                return android.os.Build.SERIAL;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "not_found";
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {

            mAuth.signOut();
            Intent i = new Intent(this,LoginFinal.class);
            startActivity(i);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
