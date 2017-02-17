package cnlive.com.myapplication;

/**
 * @author chenshuo
 * @time 2017/2/13  17:05
 * @desc ${TODD}
 */
public interface ItemMoveHelperApi {
    /**
     * Item 切换位置
     *
     * @param fromPosition 开始位置
     * @param toPosition   结束位置
     */
    void onItemMoved(int fromPosition, int toPosition);

    /**
     * 开始移动
     */
    void onMoveStart();
    /**
     * 停止移动
     */
    void onMoveEnd();


    void onItemDismiss(int position);

    void onItemMovedAndAddLast(int fromPosition, int toPosition);
}

