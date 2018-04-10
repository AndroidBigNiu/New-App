package comh.example.com.hundun06.view;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import comh.example.com.hundun06.R;
import comh.example.com.hundun06.adapter.MyAdapter;
import comh.example.com.hundun06.moudle.Bean.ShopBean;
import comh.example.com.hundun06.presenter.MyDataPresenter;

public class MainActivity extends AppCompatActivity implements DataView,MyAdapter.NumInterface {
    private List<ShopBean.DataBean> list;
    private MyAdapter adapter;
 private Handler handler = new Handler(){




     @Override
     public void handleMessage(Message msg) {
         super.handleMessage(msg);
         //把数据传到handler里了  在更新ui
         list = (List<ShopBean.DataBean>) msg.obj;
         adapter = new MyAdapter(list,MainActivity.this);
         listView.setAdapter(adapter);
         //开启接口回调
         adapter.setshoping(MainActivity.this);
 }
 };

    private CheckBox checkBox;
    private ListView listView;
    private Button button;
    private TextView jg;
    private int totalPrice=0;
    private int totalNum=0;
    String dataUrl ="https://www.zhaoapi.cn/product/getProducts?pscid=1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到控件
        checkBox = findViewById(R.id.cb);
        listView = findViewById(R.id.lv);
        jg = findViewById(R.id.jg);
        button = findViewById(R.id.bt);

        MyDataPresenter presenter = new MyDataPresenter(this);
        presenter.netWork(dataUrl);

        //给全选按钮添加点击事件
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击全选按钮 给list属性都赋上true
                if(checkBox.isChecked()){
                for (int x=0;x<list.size();x++){
                    list.get(x).setCheck(true);
                }
                    adapter.notifyDataSetChanged();
                    changePrice();
                }else{
                    for (int x=0;x<list.size();x++){
                        list.get(x).setCheck(false);

                    }
                    adapter.notifyDataSetChanged();
                    changePrice();
                }
            }
        });


    }

//  在子线程里  接口里有访问数据操作 耗时操作在线程里
    @Override
    public void toBackGome(List<ShopBean.DataBean> list) {
        Message message = new Message();
        message.obj = list;
        handler.sendMessage(message);
    }
   //重写自定义接口里的方法
    @Override
    public void jiaNum(int i,TextView tv,boolean isCheck) {
        //得到数量
        int currentNum = list.get(i).getSellerid();
        //加加数量
        currentNum++;
        //把数量赋到bean里
        list.get(i).setSellerid(currentNum);
    //刷新适配器
        adapter.notifyDataSetChanged();
        //改变总价
        changePrice();

    }
    //改变总价的操作
  public void changePrice(){
        //总价要先给这两个变量赋上0  一个是个数 一个是总价  然后要写一个for循环
        totalPrice=0;
        totalNum=0;
        //for循环 找到list集合下的所有对象
  for(int x=0;x<list.size();x++){
      ShopBean.DataBean bean = list.get(x);
      //如果选中的话  总价++ 个数++5
      if(bean.getCheck()){
        totalNum++;
        totalPrice+=list.get(x).getSellerid() * list.get(x).getPrice();

      }
      //设置总价
      jg.setText(totalPrice+"");
  }
  }
    @Override
    public void jianNum(int i,TextView tv,boolean isCheck) {
        int currentNum = list.get(i).getSellerid();
        if(currentNum==1){
            return;
        }
        currentNum--;
        list.get(i).setSellerid(currentNum);
        adapter.notifyDataSetChanged();
        changePrice();



    }
   //子条目点中的话  给对象的负上值 然后更改总价
    @Override
    public void groupCheck(int i,boolean ischeck) {
        ShopBean.DataBean bean = list.get(i);
        bean.setCheck(ischeck);
        changePrice();

    }
}
