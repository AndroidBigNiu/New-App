package comh.example.com.hundun06.moudle;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import comh.example.com.hundun06.Util.OkhtttpUtils;
import comh.example.com.hundun06.moudle.Bean.ShopBean;
import comh.example.com.hundun06.presenter.DataPresenter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ASUS on 2018/3/29.
 */

public class MyDataMoudle implements DataMoudle {
   //请求数据
    @Override
    public void getData(String url, final DataPresenter presenter) {
      //请求数据在Moudle层实体类来做
        OkhtttpUtils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求成功
                String json =  response.body().string();
                Gson gson = new Gson();
                ShopBean shopBean= gson.fromJson(json, ShopBean.class);

                List<ShopBean.DataBean> list = shopBean.getData();

                presenter.success(list) ;

            }
        });
    }
}
