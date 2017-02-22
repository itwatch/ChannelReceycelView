package cnlive.com.myapplication;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * @author chenshuo
 * @time 2017/2/15  16:16
 * @desc ${TODD}
 */
class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<String> arrayListtop;
    private ArrayList<String> arrayListbottom;
    private final LayoutInflater mLayoutInflater;
    private ItemMoveCallBackImpl mMoveCallBack;
    private ItemMoveHelperApi itemMoveHelperApi;

    MyAdapter(RecyclerView recyclerView,Context context, ArrayList<String> arrayListtop,
              ArrayList<String> arrayListbottom, ItemMoveHelperApi itemMoveHelperApi
    ) {
        this.context = context;
        this.arrayListtop = arrayListtop;
        this.arrayListbottom = arrayListbottom;
        mLayoutInflater = LayoutInflater.from(context);
        this.itemMoveHelperApi = itemMoveHelperApi;
        ItemMoveCallBackImpl mMoveCallBack = new ItemMoveCallBackImpl(itemMoveHelperApi, context, arrayListtop, arrayListbottom, this);
        this.setmMoveCallBack(mMoveCallBack);
        ItemTouchHelper touchHelper = new ItemTouchHelper(mMoveCallBack);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    public void setmMoveCallBack(ItemMoveCallBackImpl mMoveCallBack) {
        this.mMoveCallBack = mMoveCallBack;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
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
        if (position == 0 | position == arrayListtop.size() + 1) {
            return 0;
        } else {
            return 1;
        }
    }

    //type  有两种   第一个  和 中间的是0   其他的是1

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = mLayoutInflater.inflate(R.layout.receyceltpye, parent, false);
            return new AdpterHolderType(inflate);
        } else {
            View inflate = mLayoutInflater.inflate(R.layout.receycelhold1, parent, false);
            return new AdpterHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMoveCallBack.OnItemClickListener(holder.getAdapterPosition());
            }
        });
        if (holder instanceof AdpterHolderType & position == 0) {
            // AdpterHolderType holder1 = (AdpterHolderType) holder;
            itemMoveHelperApi.addInterestChannel(holder);

        } else if (holder instanceof AdpterHolderType & position != 0) {
            //AdpterHolderType holder1 = (AdpterHolderType) holder;
            itemMoveHelperApi.addOtherChannel(holder);
            //holder1.textView.setText("其他");
        } else if (0 < position && position < arrayListtop.size() + 1) {
            AdpterHolder holder1 = (AdpterHolder) holder;
            // holder1.textView.setText(arrayListtop.get(position - 1));
            itemMoveHelperApi.SetChanelDate(holder, position);
        } else {
            AdpterHolder holder1 = (AdpterHolder) holder;
            //holder1.textView.setText(arrayListbottom.get(position - 2 - arrayListtop.size()));
            itemMoveHelperApi.SetChanelDate(holder, position);

        }
    }

    @Override
    public int getItemCount() {
        return arrayListtop.size() + 2 + arrayListbottom.size();
    }


    class AdpterHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;

        AdpterHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.friend_face_img);

        }
    }

    class AdpterHolderType extends RecyclerView.ViewHolder {
        TextView textView;

        AdpterHolderType(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_type);
        }
    }
}


