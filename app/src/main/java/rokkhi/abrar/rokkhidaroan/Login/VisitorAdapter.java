package rokkhi.abrar.rokkhidaroan.Login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import rokkhi.abrar.rokkhidaroan.Model.Visitors;
import rokkhi.abrar.rokkhidaroan.Model.house_contact;
import rokkhi.abrar.rokkhidaroan.R;
import rokkhi.abrar.rokkhidaroan.utils.UniversalImageLoader;

public class VisitorAdapter extends RecyclerView.Adapter<VisitorAdapter.VisitorViewHolder> {


    public List<Visitors> list;

    VisitorViewHolder visitorViewHolder;
    Context context;
    FirebaseFirestore firebaseFirestore;
    house_contact house_contact;
    String daroanno;


    VisitorAdapter(List<Visitors> list, Context context,house_contact house_contact,String daroanno) {
        this.list = list;
        this.context=context;
        this.house_contact=house_contact;
        this.daroanno=daroanno;
    }


    @NonNull
    @Override
    public VisitorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_visitors, parent, false);
        firebaseFirestore=FirebaseFirestore.getInstance();
        visitorViewHolder=new VisitorViewHolder(view);

        return visitorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VisitorViewHolder holder, int position) {

        Visitors visitor = list.get(position);
        visitorViewHolder.setVisitor(visitor,position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return  position;
    }

    public class VisitorViewHolder extends RecyclerView.ViewHolder {
        public View view;
        TextView name ;
        TextView flat;
        CircleImageView propic;
        ImageView delete;

        VisitorViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.name);
            flat=view.findViewById(R.id.flatno);
            propic=view.findViewById(R.id.propic);
            delete=view.findViewById(R.id.delete);
        }

        void setVisitor(final Visitors visitor,final int position) {

            name.setText(visitor.getName());
            flat.setText("flat: "+ visitor.getFlat_no());
            UniversalImageLoader.setImage(visitor.getPro_pic(), propic, null, "");

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure you want to remove?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog, int id) {
                                    final String phid=visitor.getPhid();
                                    firebaseFirestore.collection("phid").document(phid).collection("visitors")
                                            .document(visitor.getPhone())
                                            .update("otime",FieldValue.serverTimestamp()).addOnSuccessListener(
                                            new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    firebaseFirestore.collection("phid").document(phid).collection("visitors")
                                                            .document(visitor.getPhone()).update("otime",FieldValue.serverTimestamp())
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {

                                                                    firebaseFirestore.collection("phid").document(phid).collection("rvis")
                                                                            .document(visitor.getPhone()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void aVoid) {
                                                                            dialog.dismiss();
                                                                            Intent intent=new Intent(context,InsideApp.class);
                                                                            intent.putExtra("contact",house_contact);
                                                                            intent.putExtra("daroanno",daroanno);

                                                                            context.startActivity(intent);
                                                                        }
                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            Toast.makeText(context,"Connection Error!",Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    });
                                                                }
                                                            }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(context,"Connection Error!",Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                                }
                                            }
                                    ).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(context,"Connection Error!",Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
    }

}
