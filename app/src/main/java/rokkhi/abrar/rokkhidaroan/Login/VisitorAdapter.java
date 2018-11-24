package rokkhi.abrar.rokkhidaroan.Login;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import rokkhi.abrar.rokkhidaroan.Model.Visitors;
import rokkhi.abrar.rokkhidaroan.R;
import rokkhi.abrar.rokkhidaroan.utils.UniversalImageLoader;

public class VisitorAdapter extends RecyclerView.Adapter<VisitorAdapter.VisitorViewHolder> {


    public List<Visitors> list;

    VisitorViewHolder visitorViewHolder;


    VisitorAdapter(List<Visitors> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public VisitorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_visitors, parent, false);
        visitorViewHolder=new VisitorViewHolder(view);
        return visitorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VisitorViewHolder holder, int position) {

        Visitors visitor = list.get(position);
        visitorViewHolder.setVisitor(visitor);

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

        void setVisitor(Visitors visitor) {

            name.setText(visitor.getName());
            flat.setText("flat: "+ visitor.getFlat_no());
            UniversalImageLoader.setImage(visitor.getPro_pic(), propic, null, "");
        }
    }

}
