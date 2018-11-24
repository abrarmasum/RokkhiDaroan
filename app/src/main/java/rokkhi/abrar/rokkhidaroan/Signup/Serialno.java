package rokkhi.abrar.rokkhidaroan.Signup;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import rokkhi.abrar.rokkhidaroan.Model.Initialhouse;
import rokkhi.abrar.rokkhidaroan.R;

public class Serialno extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText serial;
    Button submit;
    FirebaseFirestore firebaseFirestore;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Serialno() {
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
    public static Serialno newInstance(String param1, String param2) {
        Serialno fragment = new Serialno();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_serialno, container, false);
        serial=view.findViewById(R.id.serial_no);
        submit=view.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String serial_no=serial.getText().toString();
                DocumentReference docRef = firebaseFirestore.collection(getString(R.string.collection_serial_no)).document(serial_no);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document=task.getResult();
                            if(document.exists()){
                                Initialhouse initialhouse=document.toObject(Initialhouse.class);
                                Flattype fragment = new Flattype();
                                Bundle arguments = new Bundle();
                                arguments.putParcelable("house",initialhouse);
                                arguments.putString( "serial" , serial_no);
                                fragment.setArguments(arguments);
                                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.container_signup, fragment);
                                ft.commit();
                            }
                            else{
                                Toast.makeText(getActivity(), "seral no doesn't exists",Toast.LENGTH_SHORT).show();
                            }

                        }

                        else{
                            Toast.makeText(getActivity(), "connection error",Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event




}
