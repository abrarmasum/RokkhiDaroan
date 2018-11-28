package rokkhi.abrar.rokkhidaroan.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import rokkhi.abrar.rokkhidaroan.Model.SecRokkhi;
import rokkhi.abrar.rokkhidaroan.Model.Sec_house_contact;
import rokkhi.abrar.rokkhidaroan.Model.house_contact;
import rokkhi.abrar.rokkhidaroan.R;

public class SecLogin extends AppCompatActivity {

    EditText pass;
    TextView seccom;
    Button submit;
    Sec_house_contact sec_house_contact;
    FirebaseFirestore firebaseFirestore;
    private static final String TAG = "SecLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sec_house_contact=new Sec_house_contact();
        setContentView(R.layout.activity_sec_login);
        submit=findViewById(R.id.submit);
        pass=findViewById(R.id.pass);
        seccom=findViewById(R.id.sectext);
        firebaseFirestore=FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        sec_house_contact=intent.getParcelableExtra("sec_contact");

        seccom.setText(sec_house_contact.getSecurity());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String password=pass.getText().toString();
                firebaseFirestore.collection("seccom").document(sec_house_contact.getSecurity())
                        .collection("seccontact").document(password).get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if(task.isSuccessful()){
                                    DocumentSnapshot documentSnapshot=task.getResult();
                                    if(documentSnapshot.exists()){

                                        SecRokkhi secRokkhi=documentSnapshot.toObject(SecRokkhi.class);
                                        Intent intent1=new Intent(SecLogin.this,InsideApp.class);
                                        intent1.putExtra("sec_contact",sec_house_contact);
                                        intent1.putExtra("rokkhi",secRokkhi);
                                        intent1.putExtra("pass",password);
                                        intent1.putExtra("koitheke",TAG);
                                        startActivity(intent1);


                                        //here to go main page

                                    }
                                    else{
                                        Toast.makeText(SecLogin.this,"password not found!",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });




    }

}
