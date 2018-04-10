package comh.example.com.hundun06.presenter;

import java.util.List;

import comh.example.com.hundun06.moudle.Bean.ShopBean;

/**
 * Created by ASUS on 2018/3/29.
 */

public interface DataPresenter {

    void success(List<ShopBean.DataBean> list);
    void error();

}
