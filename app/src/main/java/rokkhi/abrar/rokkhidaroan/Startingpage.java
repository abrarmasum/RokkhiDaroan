package rokkhi.abrar.rokkhidaroan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Startingpage extends AppCompatActivity {

    EditText houseid;
    Button barcode;
    Button signup,login,service_req;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startingpage);
        signup=findViewById(R.id.signup);
        login=findViewById(R.id.login);
        service_req=findViewById(R.id.serevice);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Startingpage.this,Signupactivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Startingpage.this,LoginFinal.class);
                startActivity(intent);
            }
        });
        service_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Startingpage.this,ServiceRequestActivity.class);
                startActivity(intent);
            }
        });




    }


}
