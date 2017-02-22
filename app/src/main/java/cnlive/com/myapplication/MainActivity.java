package cnlive.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements ItemMoveHelperApi {


    private ArrayList<String> arraylistInterest, arraylistOrther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this, OkHttpImagePipelineConfigFactory
                .newBuilder(this, new OkHttpClient())
                .build());

        setContentView(R.layout.activity_main);
        intDate();
        initView();

    }

    private void intDate() {
        arraylistInterest = new ArrayList<>();
        arraylistOrther = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arraylistInterest.add("http://yweb3.cnliveimg.com/img/CMCC_MOVIE/618549134_699022_HSJ1080V.jpg");
            arraylistOrther.add("http://yweb3.cnliveimg.com/img/CMCC_MOVIE/618549134_699022_HSJ1080V.jpg");
        }

    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.tv_rceycl);
        MyAdapter myAdapter = new MyAdapter(recyclerView,MainActivity.this, arraylistInterest, arraylistOrther, this);
        GridLayoutManager layoutManager1 = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(myAdapter);


    }

    @Override
    public void onItemMovedAndAddDabse(ArrayList<String> arraylistInterest, ArrayList<String> arraylistOrther) {
        Log.v("arraylistInterest", "arraylistInterest=" + arraylistInterest.toString() + "     arraylistOrther" + arraylistOrther.toString());
        // TODO: 2017/2/20   改变之后的数据用来操作数据封装


    }

    @Override
    public void addInterestChannel(RecyclerView.ViewHolder viewHolderText) {
        MyAdapter.AdpterHolderType viewHolderText1 = (MyAdapter.AdpterHolderType) viewHolderText;
        viewHolderText1.textView.setText("频道");
    }

    @Override
    public void addOtherChannel(RecyclerView.ViewHolder viewHolderTextOther) {
        MyAdapter.AdpterHolderType viewHolderText1 = (MyAdapter.AdpterHolderType) viewHolderTextOther;
        viewHolderText1.textView.setText("其他");
    }


    // TODO: 2017/2/20    有待优化

    @Override
    public void SetChanelDate(RecyclerView.ViewHolder viewHolderChannel, int position) {
        MyAdapter.AdpterHolder viewHolderText1 = (MyAdapter.AdpterHolder) viewHolderChannel;

        if (0 < position && position < arraylistInterest.size() + 1) {
            viewHolderText1.simpleDraweeView.setImageURI(arraylistInterest.get(position - 1));
        } else {
            viewHolderText1.simpleDraweeView.setImageURI(arraylistOrther.get(position - 2 - arraylistInterest.size()));
        }


    }


}
