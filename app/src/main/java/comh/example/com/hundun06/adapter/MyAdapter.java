package comh.example.com.hundun06.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import comh.example.com.hundun06.R;
import comh.example.com.hundun06.moudle.Bean.ShopBean;

/**
 * Created by ASUS on 2018/3/29.
 */

public class MyAdapter extends BaseAdapter{
   private List<ShopBean.DataBean> list;
   private Context context;
   private NumInterface numInterface;
    public MyAdapter(List<ShopBean.DataBean> list,Context context) {
    this.list = list;
    this.context = context;
   }
   public void setshoping(NumInterface numInterface){
        this.numInterface = numInterface;
   }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
       if(convertView == null){
           convertView= View.inflate(context, R.layout.item,null);
           holder = new ViewHolder(convertView);
           convertView.setTag(holder);
       }else{
           holder = (ViewHolder) convertView.getTag();
       }
        //图片的路径
        String imageUrl ="http://img.my.csdn.net/uploads/201407/26/1406383264_4787.jpg";
        //设置值的操作
        Glide.with(context).load(imageUrl).into(holder.image);
        holder.price.setText(list.get(i).getPrice()+"");
        holder.num.setText(list.get(i).getSellerid()+"");
        holder.checkBox.setChecked(list.get(i).getCheck());


        //给按钮点击事件
        holder.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 numInterface.jiaNum(i,holder.jia,holder.checkBox.isChecked());
            }
        });
      holder.jian.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              numInterface.jianNum(i,holder.jian,holder.checkBox.isChecked());
          }
      });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numInterface.groupCheck(i,holder.checkBox.isChecked());
            }
        });
        return convertView;
    }
    class ViewHolder{

        private final CheckBox checkBox;
        private final TextView num;
        private final ImageView image;
        private final Button jia;
        private final Button jian;
        private final TextView name;
        private final TextView price;
//viewHolder 的构造方法  把找到控件都写在这个方法里
        public ViewHolder(View convertView){
                //找到自条目上的空间
            checkBox = convertView.findViewById(R.id.item_cb);
            num = convertView.findViewById(R.id.item_num);
            image = convertView.findViewById(R.id.item_image);
            jia = convertView.findViewById(R.id.item_jia);
            jian = convertView.findViewById(R.id.item_jian);
            name = convertView.findViewById(R.id.item_name);
            price = convertView.findViewById(R.id.item_price);
        }

    }
    public interface  NumInterface{
        void jiaNum(int i,TextView tv,boolean isCheck);
        void jianNum(int i,TextView tv,boolean isCheck);
        void groupCheck(int i,boolean ischeck);
    }
}
