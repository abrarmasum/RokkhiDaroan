package rokkhi.abrar.rokkhidaroan.Signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Batch;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import rokkhi.abrar.rokkhidaroan.LoginFinal;
import rokkhi.abrar.rokkhidaroan.Model.Person;
import rokkhi.abrar.rokkhidaroan.Model.house_contact;
import rokkhi.abrar.rokkhidaroan.R;

public class ContactPage extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FirebaseFirestore firebaseFirestore;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private house_contact house_contact;

    TextView security;
    EditText ophn1;
    EditText ophn2;
    EditText ophn3;
    EditText dphn1;
    EditText dphn2;
    EditText dphn3;
    EditText dphn4;
    EditText dphn5;
    EditText dphn6;
    EditText dphn7;
    EditText dphn8;
    EditText oname1;
    EditText oname2;
    EditText oname3;
    EditText dname1;
    EditText dname2;
    EditText dname3;
    EditText dname4;
    EditText dname5;
    EditText dname6;
    EditText dname7;
    EditText dname8;

    RelativeLayout relativeLayout;


    public ContactPage() {
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
    public static ContactPage newInstance(String param1, String param2) {
        ContactPage fragment = new ContactPage();
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
           // mPaPram2 = getArguments().getString(ARG_PARAM2);
            house_contact=getArguments().getParcelable("contact");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_contact, container, false);

        security=view.findViewById(R.id.security_company);
        ophn1=view.findViewById(R.id.phone1);
        ophn2=view.findViewById(R.id.phone2);
        ophn3=view.findViewById(R.id.phone3);
        dphn1=view.findViewById(R.id.dphone1);
        dphn2=view.findViewById(R.id.dphone2);
        dphn3=view.findViewById(R.id.dphone3);
        dphn4=view.findViewById(R.id.dphone4);
        dphn5=view.findViewById(R.id.dphone5);
        dphn6=view.findViewById(R.id.dphone6);
        dphn7=view.findViewById(R.id.dphone7);
        dphn8=view.findViewById(R.id.dphone8);

        oname1=view.findViewById(R.id.name1);
        oname2=view.findViewById(R.id.name2);
        oname3=view.findViewById(R.id.name3);
        dname1=view.findViewById(R.id.dname1);
        dname2=view.findViewById(R.id.dname2);
        dname3=view.findViewById(R.id.dname3);
        dname4=view.findViewById(R.id.dname4);
        dname5=view.findViewById(R.id.dname5);
        dname6=view.findViewById(R.id.dname6);
        dname7=view.findViewById(R.id.dname7);
        dname8=view.findViewById(R.id.dname8);

        relativeLayout=view.findViewById(R.id.daroanno);

        String seccom=house_contact.getSecurity();
        if(!seccom.equals("none")){
            relativeLayout.setVisibility(View.GONE);
        }

        Button submit=view.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                house_contact.setOwner1(ophn1.getText().toString());
                house_contact.setOwner2(ophn2.getText().toString());
                house_contact.setOwner3(ophn3.getText().toString());
                house_contact.setDaroan1(dphn1.getText().toString());
                house_contact.setDaroan2(dphn2.getText().toString());
                house_contact.setDaroan3(dphn3.getText().toString());
                house_contact.setDaroan4(dphn4.getText().toString());
                house_contact.setDaroan5(dphn5.getText().toString());
                house_contact.setDaroan6(dphn6.getText().toString());
                house_contact.setDaroan7(dphn7.getText().toString());
                house_contact.setDaroan8(dphn8.getText().toString());

                Person person1=new Person(dphn1.getText().toString(),dname1.getText().toString(),"",house_contact.getPhid());
                Person person2=new Person(dphn2.getText().toString(),dname2.getText().toString(),"",house_contact.getPhid());
                Person person3=new Person(dphn3.getText().toString(),dname3.getText().toString(),"",house_contact.getPhid());
                Person person4=new Person(dphn4.getText().toString(),dname4.getText().toString(),"",house_contact.getPhid());
                Person person5=new Person(dphn5.getText().toString(),dname5.getText().toString(),"",house_contact.getPhid());
                Person person6=new Person(dphn6.getText().toString(),dname6.getText().toString(),"",house_contact.getPhid());
                Person person7=new Person(dphn7.getText().toString(),dname7.getText().toString(),"",house_contact.getPhid());
                Person person8=new Person(dphn8.getText().toString(),dname8.getText().toString(),"",house_contact.getPhid());
                Person person9=new Person(ophn1.getText().toString(),oname1.getText().toString(),"",house_contact.getPhid());
                Person person10=new Person(ophn2.getText().toString(),oname2.getText().toString(),"",house_contact.getPhid());
                Person person11=new Person(ophn3.getText().toString(),oname3.getText().toString(),"",house_contact.getPhid());

                WriteBatch batch = firebaseFirestore.batch();

                DocumentReference nycRef = firebaseFirestore.collection("rokkhi").document(dphn1.getText().toString());
                batch.set(nycRef, person1);

                DocumentReference nycRef9 = firebaseFirestore.collection("owner").document(ophn1.getText().toString());
                batch.set(nycRef9, person9);

                if(!dphn2.getText().toString().isEmpty()){
                    DocumentReference nycRef2 = firebaseFirestore.collection("rokkhi").document(dphn2.getText().toString());
                    batch.set(nycRef2, person2);
                }
                if(!dphn3.getText().toString().isEmpty()){
                    DocumentReference nycRef3 = firebaseFirestore.collection("rokkhi").document(dphn3.getText().toString());
                    batch.set(nycRef3, person3);
                }
                if(!dphn4.getText().toString().isEmpty()){
                    DocumentReference nycRef4 = firebaseFirestore.collection("rokkhi").document(dphn4.getText().toString());
                    batch.set(nycRef4, person4);
                }
                if(!dphn5.getText().toString().isEmpty()){
                    DocumentReference nycRef5 = firebaseFirestore.collection("rokkhi").document(dphn5.getText().toString());
                    batch.set(nycRef5, person5);
                }
                if(!dphn6.getText().toString().isEmpty()){
                    DocumentReference nycRef6 = firebaseFirestore.collection("rokkhi").document(dphn6.getText().toString());
                    batch.set(nycRef6, person6);
                }
                if(!dphn7.getText().toString().isEmpty()){
                    DocumentReference nycRef7 = firebaseFirestore.collection("rokkhi").document(dphn7.getText().toString());
                    batch.set(nycRef7, person7);
                }
                if(!dphn8.getText().toString().isEmpty()){
                    DocumentReference nycRef8 = firebaseFirestore.collection("rokkhi").document(dphn8.getText().toString());
                    batch.set(nycRef8, person8);
                }
                if(!ophn2.getText().toString().isEmpty()){
                    DocumentReference nycRef10 = firebaseFirestore.collection("owner").document(ophn2.getText().toString());
                    batch.set(nycRef10, person10);
                }
                if(!ophn3.getText().toString().isEmpty()){
                    DocumentReference nycRef11 = firebaseFirestore.collection("owner").document(ophn3.getText().toString());
                    batch.set(nycRef11, person11);
                }







                DocumentReference task2 = firebaseFirestore.collection("phid").document(house_contact.getPhid());
                batch.set(task2, house_contact);

                batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){

//                            Intent intent=new Intent(getActivity(),LoginFinal.class);
//                            intent.putExtra("contact",house_contact);
//                            startActivity(intent);


                            DaroanPic fragment = new DaroanPic();
                            Bundle arguments = new Bundle();
                            arguments.putParcelable( "house" , house_contact);
                            arguments.putString( "d1" , dname1.getText().toString());
                            arguments.putString( "d2" , dname2.getText().toString());
                            arguments.putString( "d3" , dname3.getText().toString());
                            arguments.putString( "d4" , dname4.getText().toString());
                            arguments.putString( "d5" , dname5.getText().toString());
                            arguments.putString( "d6" , dname6.getText().toString());
                            arguments.putString( "d7" , dname7.getText().toString());
                            arguments.putString( "d8" , dname8.getText().toString());
                            fragment.setArguments(arguments);
                            final FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.container_signup, fragment);
                            ft.addToBackStack(null);
                            ft.commit();
                        }
                        else{
                            Toast.makeText(getActivity(),"Connection Error",Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });




//                firebaseFirestore.collection("phid").document(house_contact.getPhid())
//                        .set(house_contact).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
////                            Intent intent=new Intent(getActivity(),LoginFinal.class);
////                            intent.putExtra("contact",house_contact);
////                            startActivity(intent);
//                        }
//                        else{
//                            Toast.makeText(getActivity(),"Connection Error",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
            }
        });



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event




}
