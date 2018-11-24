package rokkhi.abrar.rokkhidaroan.Signup;

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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

import rokkhi.abrar.rokkhidaroan.Model.Initialhouse;
import rokkhi.abrar.rokkhidaroan.R;

public class Edithouseid extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText serial;
    Button submit;
    EditText houseno,roadno,district,area,phone;
    Button signup;
    FirebaseFirestore firebaseFirestore;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Initialhouse init=new Initialhouse();



    public Edithouseid() {
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
    public static Edithouseid newInstance(String param1, String param2) {
        Edithouseid fragment = new Edithouseid();
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
            init = getArguments().getParcelable("house");
            mParam2 = getArguments().getString("serial");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_editbutton, container, false);
        houseno=view.findViewById(R.id.houseno);
        roadno=view.findViewById(R.id.roadno);
        district=view.findViewById(R.id.district);
        area=view.findViewById(R.id.area);
        submit=view.findViewById(R.id.signup);
        final Random rand = new Random();

        houseno.setText(init.getHouseno());
        roadno.setText(init.getRoadno());
        district.setText(init.getDistrict());
        area.setText(init.getArea());


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = rand.nextInt(1000000);
                ///egula field faka rakha jabe na
                String areastring = area.getText().toString();
                final String total = area.getText().toString() + houseno.getText().toString() + roadno.getText().toString()
                        + Integer.toString(value);

                final Initialhouse initialhouse=new Initialhouse(total,district.getText().toString(),
                        area.getText().toString(),
                        houseno.getText().toString(),
                        roadno.getText().toString(),
                        ""

                );


                firebaseFirestore.collection("sn").document(mParam2)
                        .set(initialhouse)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Flattype fragment = new Flattype();
                                Bundle arguments = new Bundle();
                                arguments.putParcelable( "house" , initialhouse);
                                arguments.putString( "serial" , mParam2);
                                fragment.setArguments(arguments);
                                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.container_signup, fragment);
                                ft.commit();
                                Toast.makeText(getActivity(), "Successfully updated",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Error updating",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event




}
