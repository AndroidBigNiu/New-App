package comh.example.com.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ASUS on 2018/4/2.
 */
//写Reyclerview的适配器 要继承RecyclerView.Adapter重写三个方法  onCreateViewHolder onBindViewHolder getItemCount
public class Myadaper extends RecyclerView.Adapter {
    private List<String> list;
    public Myadaper(List<String> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //找到布局
        View view = View.inflate(parent.getContext(), R.layout.item, null);
        Myviewholder myviewholder = new Myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Myviewholder myviewholder= (Myviewholder) holder;
        myviewholder.textone.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    //添加和删除的方法
    public void add(){
        list.add(0,"这是你要添加的数据");
        notifyItemInserted(0);
    }
    public void remove(){
        if(list == null){
            return;
        }else{
            list.remove(0);
           notifyItemChanged(0);
        }

    }
    //内部类要继承ViewHolder
    class Myviewholder extends RecyclerView.ViewHolder{

        private final TextView textone;

        public Myviewholder(View itemView) {

            super(itemView);
            textone = itemView.findViewById(R.id.item_tv);
        }
    }

}
