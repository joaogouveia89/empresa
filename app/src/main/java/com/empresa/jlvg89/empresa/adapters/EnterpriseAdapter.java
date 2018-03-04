package com.empresa.jlvg89.empresa.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.empresa.jlvg89.empresa.R;
import com.empresa.jlvg89.empresa.interfaces.RecyclerViewOnClickLIstenerHack;
import com.empresa.jlvg89.empresa.models.Enterprise;

import java.util.List;

/**
 * Created by jlvg89 on 03/03/18.
 */

public class EnterpriseAdapter extends RecyclerView.Adapter<EnterpriseAdapter.MyViewHolder>{

    private List<Enterprise> enterpriseList;
    private LayoutInflater layoutInflater;
    private RecyclerViewOnClickLIstenerHack mRecyclerViewOnClickLIstenerHack;

    public EnterpriseAdapter(Context ctx, List<Enterprise> list){
        enterpriseList = list;
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_enterprise, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.enterpriseName.setText(enterpriseList.get(position).getEnterprise_name());
        holder.enterpriseBusiness.setText(enterpriseList.get(position).getEnterprise_type().getEnterprise_type_name());
        holder.enterpriseContry.setText(enterpriseList.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        return enterpriseList.size();
    }

    public void addListItem(Enterprise e, int position){
         enterpriseList.add(e);
         notifyItemInserted(position);
    }

    public void setmRecyclerViewOnClickLIstenerHack(RecyclerViewOnClickLIstenerHack mRecyclerViewOnClickLIstenerHack){
        this.mRecyclerViewOnClickLIstenerHack = mRecyclerViewOnClickLIstenerHack;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView enterprisePhoto;
        public TextView enterpriseName;
        public TextView enterpriseBusiness;
        public TextView enterpriseContry;

        public MyViewHolder(View itemView) {
            super(itemView);

            enterprisePhoto         = (ImageView) itemView.findViewById(R.id.iv_logo_enterprise);
            enterpriseName          = (TextView) itemView.findViewById(R.id.tv_enterprise_name);
            enterpriseBusiness      = (TextView) itemView.findViewById(R.id.tv_enterprise_business);
            enterpriseContry        = (TextView) itemView.findViewById(R.id.tv_enterprise_country);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mRecyclerViewOnClickLIstenerHack != null){
                mRecyclerViewOnClickLIstenerHack.onClickListener(view, getAdapterPosition());
            }
        }
    }
}
