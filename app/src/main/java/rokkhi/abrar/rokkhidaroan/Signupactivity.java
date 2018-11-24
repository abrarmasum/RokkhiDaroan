package rokkhi.abrar.rokkhidaroan;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import rokkhi.abrar.rokkhidaroan.Signup.Serialno;

public class Signupactivity extends AppCompatActivity {
    private static final String TAG = "Signupactivity";


    //ekhane dekhjte hobe login hye ase naki nai

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);


        Fragment serialno = new Serialno();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

//// Replace whatever is in the fragment_container view with this fragment,
//// and add the transaction to the back stack
        transaction.add(R.id.container_signup, serialno);
        transaction.addToBackStack(null);
//
//// Commit the transaction
        transaction.commit();

    }

}

