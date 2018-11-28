package rokkhi.abrar.rokkhidaroan.Login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import de.hdodenhof.circleimageview.CircleImageView;
import rokkhi.abrar.rokkhidaroan.Model.Initialhouse;
import rokkhi.abrar.rokkhidaroan.Model.Person;
import rokkhi.abrar.rokkhidaroan.Model.SecRokkhi;
import rokkhi.abrar.rokkhidaroan.Model.Sec_house_contact;
import rokkhi.abrar.rokkhidaroan.Model.Visitors;
import rokkhi.abrar.rokkhidaroan.Model.house_contact;
import rokkhi.abrar.rokkhidaroan.R;
import rokkhi.abrar.rokkhidaroan.Signup.Flattype;
import rokkhi.abrar.rokkhidaroan.utils.UniversalImageLoader;

import static android.support.constraint.Constraints.TAG;

public class Mainpage extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FirebaseFirestore firebaseFirestore;
    Query firstQuery;
    TextView name;
    CircleImageView propic;
    RecyclerView recyclerView;
    List<Visitors> list;
    VisitorAdapter visitorAdapter;



    //private List< Visitors> visitors;
    //private FirebaseFirestore firebaseFirestore;
   // private VisitorAdapter visitorAdapter;




    private int limit = 10;
    house_contact house_contact;
    String daroanno=null;

    Sec_house_contact sec_house_contact=new Sec_house_contact();
    String pass=null;
    SecRokkhi secRokkhi=new SecRokkhi();

    private DocumentSnapshot lastVisible=null;
    private boolean isScrolling = false;
    private boolean isLastItemReached = false;
    CollectionReference visitorref;
    LinearLayout linearLayout;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Mainpage() {
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
    public static Mainpage newInstance(String param1, String param2) {
        Mainpage fragment = new Mainpage();
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
            sec_house_contact=getArguments().getParcelable("sec_contact");
            secRokkhi=getArguments().getParcelable("rokkhi");
            pass=getArguments().getString("pass");
            house_contact=getArguments().getParcelable("contact");
            daroanno=getArguments().getString("daroanno");
            if(pass!=null)visitorref = firebaseFirestore.collection("phid").document(sec_house_contact.getPhid()).collection("rvis");
            else visitorref = firebaseFirestore.collection("phid").document(house_contact.getPhid()).collection("rvis");
            firstQuery = visitorref.orderBy("itime", Query.Direction.DESCENDING).limit(limit);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_mainpage, container, false);
        linearLayout=view.findViewById(R.id.lin1);
        propic=view.findViewById(R.id.pro_pic_daroan);
        name=view.findViewById(R.id.daroanname);
        recyclerView=view.findViewById(R.id.recyclerview);
        recyclerView.setNestedScrollingEnabled(false);

        //visitors = new ArrayList<>();


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setAdapter(visitorAdapter);



        if(pass!=null ){
            UniversalImageLoader.setImage(secRokkhi.getPro_pic(), propic, null, "");
            name.setText(secRokkhi.getName());

        }
        else if(daroanno.equals("nodaroan")){
            linearLayout.setVisibility(View.GONE);

        }
        else  if(!daroanno.equals("")){
            Log.d(TAG, "onCreateView: dekhajak "+getDeviceName());
            firebaseFirestore.collection("rokkhi").document(daroanno).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Person daroan=documentSnapshot.toObject(Person.class);
                    UniversalImageLoader.setImage(daroan.getPro_pic(), propic, null, "");
                    name.setText(daroan.getName());
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),"Connection Error",Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        else{
            UniversalImageLoader.setImage("", propic, null, "");
            name.setText(getDeviceName());
            Log.d(TAG, "onCreateView: dekhajak "+getDeviceName());
        }




        firstQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "onComplete: kotoboro "+task.getResult().size());
                    list = new ArrayList<>();
                    for (DocumentSnapshot document : task.getResult()) {
                        Visitors visitors = document.toObject(Visitors.class);
                        Log.d(TAG, "onComplete: koto2 " +visitors.getName());

                        list.add(visitors);
                    }
                    visitorAdapter = new VisitorAdapter(list,getActivity(),house_contact,daroanno);
                    recyclerView.setAdapter(visitorAdapter);
                    visitorAdapter.notifyDataSetChanged();

                    //Log.d(TAG, "onComplete: koto "+  task.getResult().size());
                   if(task.getResult().size()>=1) lastVisible = task.getResult().getDocuments().get(task.getResult().size() - 1);
                    Log.d(TAG, "onComplete: koto5 "+ lastVisible);
                    if (task.getResult().size() < limit) {
                        isLastItemReached = true;
                    }


                }
                else {
                    Log.d(TAG, "onComplete: kotoboro1");
                }
            }
        });


        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //super.onScrollChange
                if ( lastVisible!=null &&
                    // (firstVisibleItemPosition + visibleItemCount == totalItemCount) &&
                        !isLastItemReached) {
                    isScrolling = false;
                    Query nextQuery;
                    nextQuery= visitorref.
                           orderBy("itime", Query.Direction.DESCENDING).startAfter(lastVisible).limit(15);
                    nextQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> t) {
                            if (t.isSuccessful()) {
                                for (DocumentSnapshot d : t.getResult()) {
                                    Visitors productModel = d.toObject(Visitors.class);
                                    Log.d(TAG, "onComplete: koto3 "+ productModel.getName());
                                    list.add(productModel);
                                }
                                Log.d(TAG, "onComplete: koto1 "+ t.getResult().size());

                                visitorAdapter.notifyDataSetChanged();
                                lastVisible = t.getResult().getDocuments().get(t.getResult().size() - 1);

                                if (t.getResult().size() < limit) {
                                    isLastItemReached = true;
                                }
                            }
                        }
                    });
                }

            }
        });













        return view;
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




    // TODO: Rename method, update argument and hook method into UI event




}
