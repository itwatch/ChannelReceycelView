package cnlive.com.myapplication;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author chenshuo
 * @time 2017/2/15  16:16
 * @desc ${TODD}
 */
 abstract class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
   private Context context;
   private ArrayList<String> arrayListtop;
   private ArrayList<String> arrayListbottom;
    private final LayoutInflater mLayoutInflater;

    public MyAdapter(Context context, ArrayList<String> arrayListtop, ArrayList<String> arrayListbottom) {
        this.context = context;
        this.arrayListtop = arrayListtop;
        this.arrayListbottom = arrayListbottom;
        mLayoutInflater = LayoutInflater.from(context);
    }


    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == 0
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }



    @Override
    public int getItemViewType(int position) {
        if (position == 0 |position==arrayListtop.size()+1) {
            return 0;
        } else {
            return 1;
        }

    }

    //type  有两种   第一个  和 中间的是0   其他的是1

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            View inflate = mLayoutInflater.inflate(R.layout.receyceltpye, parent, false);
            return new AdpterHolderType(inflate);
        }else {
            View inflate = mLayoutInflater.inflate(R.layout.receycelhold1, parent, false);
            return new AdpterHolder(inflate);
        }
    }
    protected abstract void OnItemClickListener(int position);
    private  int i=0;
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnItemClickListener(holder.getAdapterPosition());
            }
        });


        if(holder instanceof AdpterHolderType &position==0 ){
            AdpterHolderType holder1 = (AdpterHolderType) holder;
            holder1.textView.setText("频道");
        }else  if(holder instanceof AdpterHolderType &position!=0 ){
            AdpterHolderType holder1 = (AdpterHolderType) holder;
            holder1.textView.setText("其他");
        } else if(0<position&&position<arrayListtop.size()+1){
            AdpterHolder holder1 = (AdpterHolder) holder;
            holder1.textView.setText(arrayListtop.get(position-1));
        }else {
            AdpterHolder holder1 = (AdpterHolder) holder;
            holder1.textView.setText(arrayListbottom.get(position-2-arrayListtop.size()));
        }

    }

    @Override
    public int getItemCount() {
        return arrayListtop.size()+1+1+arrayListbottom.size();
    }


    private class AdpterHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;


        AdpterHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_test);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }

    }


   private   class AdpterHolderType extends RecyclerView.ViewHolder {

        public TextView textView;

        AdpterHolderType(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_type);
        }

    }
}
