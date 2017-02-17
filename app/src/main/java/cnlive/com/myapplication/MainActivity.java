package cnlive.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemMoveHelperApi {

    private RecyclerView recyclerView;
    private ArrayList<String> arraylistInterest, arraylistOrther;
    private TextView textView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv_different);
        intDate();
        initView();

    }

    private void intDate() {
        arraylistInterest = new ArrayList<>();
        arraylistOrther = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            arraylistInterest.add("兴趣" + i);
            arraylistOrther.add("其他" + i);
        }

    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.tv_rceycl);
        myAdapter = new MyAdapter(MainActivity.this, arraylistInterest, arraylistOrther) {
            @Override
            protected void OnItemClickListener(int position) {
                if (position > (arraylistInterest.size() + 1)) {
                    String stringArrayListOrther = arraylistOrther.get(position - 2 - arraylistInterest.size());
                    arraylistOrther.remove(position - 2 - arraylistInterest.size());
                    arraylistInterest.add(stringArrayListOrther);
                    notifyDataSetChanged();

                } else if (0 < position && position < arraylistInterest.size() + 1) {
                    String item = arraylistInterest.get(position - 1);
                    arraylistInterest.remove(position - 1);
                    arraylistOrther.add(item);
                    notifyItemRemoved(position);
                    notifyItemChanged(position, getItemCount());

                }

                Log.v("position", "position=" + position);


            }
        };
        GridLayoutManager layoutManager1 = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(myAdapter);


        ItemMoveCallBackImpl mMoveCallBack = new ItemMoveCallBackImpl(this, MainActivity.this, arraylistInterest, arraylistOrther);
        ItemTouchHelper touchHelper = new ItemTouchHelper(mMoveCallBack);
        touchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public void onItemMoved(int fromPosition, int toPosition) {
        myAdapter.notifyItemMoved(fromPosition, toPosition);
        String stringArrayListIntersting = arraylistInterest.get(fromPosition - 1);
        arraylistInterest.remove(fromPosition - 1);
        arraylistInterest.add(toPosition - 1, stringArrayListIntersting);

    }

    @Override
    public void onMoveStart() {

    }

    @Override
    public void onMoveEnd() {

    }

    @Override
    public void onItemDismiss(int position) {

    }

    @Override
    public void onItemMovedAndAddLast(int fromPosition, int toPosition) {
        String item = arraylistInterest.get(fromPosition - 1);
        arraylistInterest.remove(fromPosition - 1);
        arraylistOrther.add(item);
        myAdapter.notifyDataSetChanged();
    }


}
