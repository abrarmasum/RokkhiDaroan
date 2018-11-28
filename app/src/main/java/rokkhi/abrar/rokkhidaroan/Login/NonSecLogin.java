package rokkhi.abrar.rokkhidaroan.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import rokkhi.abrar.rokkhidaroan.Loginactivity;
import rokkhi.abrar.rokkhidaroan.Model.house_contact;
import rokkhi.abrar.rokkhidaroan.R;

public class NonSecLogin extends AppCompatActivity {

    EditText pass,mobileno;
    private static final String TAG = "NonSecLogin";
    Button submit;
    house_contact house_contact=new house_contact();
    FirebaseFirestore firebaseFirestore;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_sec_login);
        context=NonSecLogin.this;

        pass=findViewById(R.id.pass);
        mobileno= findViewById(R.id.phone);
        submit=findViewById(R.id.submit);
        firebaseFirestore=FirebaseFirestore.getInstance();


        Intent intent = getIntent();
        house_contact=intent.getParcelableExtra("contact");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!pass.getText().toString().equals("")){
                    firebaseFirestore.collection("phidpass").document(house_contact.getPhid()).get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if(task.isSuccessful()){
                                        DocumentSnapshot documentSnapshot=task.getResult();
                                        if(documentSnapshot.exists()){
                                            String password=documentSnapshot.getString("pass");
                                            Log.d(TAG, "onComplete: passhere "+password);

                                            if(password.equals(pass.getText().toString())){
                                                Intent intent1=new Intent(NonSecLogin.this,InsideApp.class);
                                                intent1.putExtra("contact",house_contact);
                                                intent1.putExtra("daroanno","nodaroan");
                                                intent1.putExtra("koitheke",TAG);
                                                startActivity(intent1);
                                            }
                                            else{
                                                Toast.makeText(NonSecLogin.this,"Wrong password!",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            });
                }
                else{
                    String phoneno=mobileno.getText().toString();
                    int len=phoneno.length();
                    if(len!=11){
                        Toast.makeText(context,"Wrong format!",Toast.LENGTH_SHORT).show();
                    }
                    else{

                        String d1=house_contact.getDaroan1();
                        String d2=house_contact.getDaroan2();
                        String d3=house_contact.getDaroan3();
                        String d4=house_contact.getDaroan4();
                        String d5=house_contact.getDaroan5();
                        String d6=house_contact.getDaroan6();
                        String d7=house_contact.getDaroan7();
                        String d8=house_contact.getDaroan8();

                        String xx[]={d1,d2,d3,d4,d5,d6,d7,d8};

                        for(int i=0;i<8;i++){
                            if(phoneno.equals(xx[i])){
                                Intent intent1=new Intent(NonSecLogin.this,Loginactivity.class);
                                intent1.putExtra("contact",house_contact);
                                intent1.putExtra("daroanno",i);
                                intent1.putExtra("daroanmobile",xx[i]);
                                intent1.putExtra("from",TAG);
                                startActivity(intent1);
                            }
                        }
                    }

                }
            }
        });


    }

}
