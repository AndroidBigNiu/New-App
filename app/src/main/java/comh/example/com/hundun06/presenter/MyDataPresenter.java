package comh.example.com.hundun06.presenter;

import java.util.List;

import comh.example.com.hundun06.moudle.Bean.ShopBean;
import comh.example.com.hundun06.moudle.MyDataMoudle;
import comh.example.com.hundun06.view.DataView;

/**
 * Created by ASUS on 2018/3/29.
 */

public class MyDataPresenter implements DataPresenter {

    private final MyDataMoudle dataMoudle;
    DataView dataView;
    public MyDataPresenter(DataView dataView){
        this.dataView = dataView;
        dataMoudle = new MyDataMoudle();
    }
    //m层得到数据,传给p层，传给view层
    @Override
    public void success(List<ShopBean.DataBean> list) {
   dataView.toBackGome(list);
    }

    @Override
    public void error() {

    }

    public void netWork(String dataUrl) {
   dataMoudle.getData(dataUrl,this);
    }
}
