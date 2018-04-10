package comh.example.com.hundun06.moudle;

import comh.example.com.hundun06.presenter.DataPresenter;

/**
 * Created by ASUS on 2018/3/29.
 */

public interface DataMoudle {
    //请求数据
    void getData(String url,DataPresenter presenter);
}
