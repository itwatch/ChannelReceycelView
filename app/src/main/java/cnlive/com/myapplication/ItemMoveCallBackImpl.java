package cnlive.com.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;

/**
 * @author chenshuo
 * @time 2017/2/16  16:05
 * @desc ${TODD}
 */
public class ItemMoveCallBackImpl extends ItemTouchHelper.Callback {

    private ItemMoveHelperApi mHelperApi;
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.ViewHolder holder;
    private ArrayList<String> arraylistInterest, arraylistOrther;
    private RecyclerView.ViewHolder viewHolder;
    //private int adapterPosition;
    private MyAdapter myAdapter;

    public ItemMoveCallBackImpl(ItemMoveHelperApi helperApi,
                                Context context,
                                ArrayList<String> arraylistInterest,
                                ArrayList<String> arraylistOrther,
                                MyAdapter myAdapter) {
        this.mHelperApi = helperApi;
        this.context = context;
        this.arraylistInterest = arraylistInterest;
        this.arraylistOrther = arraylistOrther;
        this.myAdapter = myAdapter;
    }

    void OnItemClickListener(int position) {
        if (position > (arraylistInterest.size() + 1)) {
            String stringArrayListOrther = arraylistOrther.get(position - 2 - arraylistInterest.size());
            arraylistOrther.remove(position - 2 - arraylistInterest.size());
            arraylistInterest.add(stringArrayListOrther);
            myAdapter.notifyDataSetChanged();

        } else if (0 < position && position < arraylistInterest.size() + 1) {
            String item = arraylistInterest.get(position - 1);
            arraylistInterest.remove(position - 1);
            arraylistOrther.add(item);
            myAdapter.notifyItemRemoved(position);
            myAdapter.notifyItemChanged(position, myAdapter.getItemCount());

        }
        mHelperApi.onItemMovedAndAddDabse(arraylistInterest, arraylistOrther);
    }


    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }


    @Override
    public boolean isLongPressDragEnabled() {
        //  adapterPosition = viewHolder.getAdapterPosition();


        if (0 < viewHolder.getAdapterPosition() && viewHolder.getAdapterPosition() < arraylistInterest.size() + 1) {
            return true;
        } else {
            return false;
        }


    }
//  此处的Viewholder   系统内部为搞明白

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

        if (mHelperApi != null && 0 < target.getAdapterPosition() && target.getAdapterPosition() < arraylistInterest.size() + 1) {

            String stringArrayListIntersting = arraylistInterest.get(viewHolder.getAdapterPosition() - 1);
            arraylistInterest.remove(viewHolder.getAdapterPosition() - 1);
            arraylistInterest.add(target.getAdapterPosition() - 1, stringArrayListIntersting);
            myAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            mHelperApi.onItemMovedAndAddDabse(arraylistInterest, arraylistOrther);
        }


        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }


    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        this.viewHolder = viewHolder;
        this.recyclerView = recyclerView;
        this.holder = viewHolder;
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }
}
