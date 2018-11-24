package rokkhi.abrar.rokkhidaroan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import rokkhi.abrar.rokkhidaroan.Model.Initialhouse;

public class ServiceRequestActivity extends FragmentActivity {

    private static final String TAG = "ServiceRequestActivity";

    EditText houseno,roadno,district,area,phone;
    Button signup;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_request_activity);

        houseno=findViewById(R.id.houseno);
        roadno=findViewById(R.id.roadno);
        district=findViewById(R.id.district);
        area=findViewById(R.id.area);
        signup=findViewById(R.id.signup);
        phone= findViewById(R.id.contact);
        firebaseFirestore=FirebaseFirestore.getInstance();
        final Random rand = new Random();






        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new user with a first and last name
                int value = rand.nextInt(1000000);

                ///egula field faka rakha jabe na
                String areastring=area.getText().toString();
                String total=area.getText().toString()+houseno.getText().toString()+roadno.getText().toString();
                final String serial_no="BD"+areastring.substring(0, Math.min(areastring.length(), 3))+Integer.toString(value)+
                        phone.getText().toString();
                final Map<String, Object> serial = new HashMap<>();
                serial.put("houseid",total);

                Initialhouse initialhouse=new Initialhouse(total,district.getText().toString(),
                        area.getText().toString(),
                        houseno.getText().toString(),
                        roadno.getText().toString(),
                        ""

                );

                firebaseFirestore.collection(getString(R.string.collection_serial_no)).document(serial_no)
                        .set(initialhouse).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent=new Intent(ServiceRequestActivity.this,Loginactivity.class);
                        intent.putExtra("contact1", phone.getText().toString());
                        intent.putExtra("from",TAG);
                        startActivity(intent);
                    }
                });

            }
        });
    }
}
