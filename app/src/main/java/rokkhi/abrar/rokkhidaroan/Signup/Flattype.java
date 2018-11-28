package rokkhi.abrar.rokkhidaroan.Signup;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import rokkhi.abrar.rokkhidaroan.Model.Initialhouse;
import rokkhi.abrar.rokkhidaroan.Model.Sec_house_contact;
import rokkhi.abrar.rokkhidaroan.Model.house_contact;
import rokkhi.abrar.rokkhidaroan.R;
import rokkhi.abrar.rokkhidaroan.utils.HorizontalRecyclerAdapterfortopic;
import rokkhi.abrar.rokkhidaroan.utils.ValueAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Flattype.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Flattype#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Flattype extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "Flattype";
    private  Initialhouse init=new Initialhouse();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button edit,submit;
    TextView flattext;
    EditText security,floor,flat,houseid;
    FirebaseFirestore firebaseFirestore;
    RecyclerView recyclerView;
    private ArrayList<String> mList;
    house_contact house_contact;
    Sec_house_contact sec_house_contact;


    //for security list
    AlertDialog alertDialog;
    private ValueAdapter valueAdapter;




    private ArrayList<String> mStringList;




    public Flattype() {
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
    public static Flattype newInstance(String param1, String param2) {
        Flattype fragment = new Flattype();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            init = getArguments().getParcelable("house");
            mParam2 = getArguments().getString("serial");
        }
        firebaseFirestore=FirebaseFirestore.getInstance();
        mList=new ArrayList<>();



    }
    HorizontalRecyclerAdapterfortopic.OnButtonClickListener  hor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_flattype, container, false);
        submit=view.findViewById(R.id.submit);
        edit=view.findViewById(R.id.edit);
        security=view.findViewById(R.id.security_company);
        floor=view.findViewById(R.id.floornumber);
        flat=view.findViewById(R.id.flatnumber);
        flattext=view.findViewById(R.id.flattext);
        recyclerView=view.findViewById(R.id.flat_recycler_view);
        houseid=view.findViewById(R.id.houseid);
        
        rec_inint();
        initsearch();
        hor=new HorizontalRecyclerAdapterfortopic.OnButtonClickListener() {
            @Override
            public void onButtonClick(String data) {
                if(data.equals("custom")){
                    final AlertDialog new1 = new AlertDialog.Builder(getActivity()).create();
                    LayoutInflater inflater = getLayoutInflater();
                    View convertView = (View) inflater.inflate(R.layout.custom_list1, null);
                    final EditText editText=convertView.findViewById(R.id.flatformat);
                    final Button lv = convertView.findViewById(R.id.submit);
                    new1.setView(convertView);
                    new1.show();
                    lv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String ss=editText.getText().toString();
                            flattext.setText(ss);
                            new1.dismiss();
                        }
                    });
                }
                else flattext.setText(data);
                //Toast.makeText(getActivity(),"fdfidhjfi", Toast.LENGTH_SHORT).show();

            }
        };

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                house_contact=new house_contact(houseid.getText().toString(),mParam2,security.getText().toString(),flattext.getText().toString(),
                        Integer.parseInt(floor.getText().toString()),Integer.parseInt(flat.getText().toString()),"","","","","","","","","","","");

                sec_house_contact=new Sec_house_contact(houseid.getText().toString(),mParam2,security.getText().toString(),flattext.getText().toString(),
                        Integer.parseInt(floor.getText().toString()),Integer.parseInt(flat.getText().toString()),"","","");



                ContactPage contactPage = new ContactPage();
                Bundle arguments = new Bundle();
                arguments.putParcelable("contact",house_contact);
                arguments.putParcelable("sec_contact",sec_house_contact);
                //arguments.putString( "serial" , serial_no);
                contactPage.setArguments(arguments);
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container_signup, contactPage);
                ft.addToBackStack(null);
                ft.commit();

            }
        });


        Log.d(TAG, "onCreateView: houseid " + mParam1);

        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void initsearch(){

        mStringList=new ArrayList<String>();



        firebaseFirestore.collection("seccom").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        mStringList.add(documentSnapshot.getId());
                    }
                    Log.d(TAG, "onComplete: hoise");

                }
                else{
                    Log.d(TAG, "onComplete:  hoini");
                }
            }
        });








        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueAdapter=new ValueAdapter(mStringList,getActivity());

                alertDialog = new AlertDialog.Builder(getActivity()).create();
                LayoutInflater inflater = getLayoutInflater();
                View convertView = (View) inflater.inflate(R.layout.custom_list, null);
                final EditText editText=convertView.findViewById(R.id.sear);
                final ListView lv = (ListView) convertView.findViewById(R.id.listView1);
                alertDialog.setView(convertView);
                alertDialog.setCancelable(false);

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
                        security.setText(lv.getItemAtPosition(position).toString());
//                        myRef.child("districts").child(lv.getItemAtPosition(position).toString()).addListenerForSingleValueEvent(
//                                new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(DataSnapshot dataSnapshot) {
//                                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
//                                            uid.add(dataSnapshot1.getKey());
//                                        }
//
//                                        Log.d(TAG, "onDataChange: eibarekhane "+ uid);
//                                    }
//
//                                    @Override
//                                    public void onCancelled(DatabaseError databaseError) {
//
//                                    }
//                                }
//                        );
                        alertDialog.dismiss();

                    }
                });
            }
        });

    }
    
    public void rec_inint(){

        houseid.setText(init.getHouseid());

        firebaseFirestore.collection("flattype").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        mList.add(documentSnapshot.getId());
                    }
                    recyclerView.setHasFixedSize(true);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recyclerView.setLayoutManager(layoutManager);
                    HorizontalRecyclerAdapterfortopic adapter = new HorizontalRecyclerAdapterfortopic(mList,getActivity(),hor);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    Log.d(TAG, "Error getting documents: ", task.getException());

                }
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edithouseid fragment = new Edithouseid();
                Bundle arguments = new Bundle();
                arguments.putParcelable( "house" , init);
                arguments.putString( "serial" , mParam2);
                fragment.setArguments(arguments);
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container_signup, fragment);
                ft.commit();
            }
        });


    }
}
