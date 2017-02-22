package cnlive.com.myapplication;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * @author chenshuo
 * @time 2017/2/13  17:05
 * @desc ${TODD}
 */
 interface ItemMoveHelperApi {



/**
 * 移动排序，点击喜欢和其他频道之间的转换
 *
 * */

    void onItemMovedAndAddDabse(ArrayList<String> arraylistInterest, ArrayList<String> arraylistOrther);
    /**
     *设置喜欢频道的文字   “频道”
     *
     * */
    void addInterestChannel(RecyclerView.ViewHolder viewHolderText);
    /**
     *
     *设置其他频道的文字   “其他”
     * */
    void  addOtherChannel(RecyclerView.ViewHolder  viewHolderTextOther);
    /**
     *设置频道图片的数据
     *
     * */
    void SetChanelDate(RecyclerView.ViewHolder  viewHolderChannel,int position);

}

