package comh.example.com.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcv;
    private List<String> list;
    private Myadaper myadaper;
    private Button but_add;
    private Button but_deldete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //声名控件
        rcv = findViewById(R.id.rv);
        but_add = findViewById(R.id.add);
        but_deldete = findViewById(R.id.delete);
       rcv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcv.setItemAnimator(new DefaultItemAnimator());
        itimdate();
        myadaper = new Myadaper(list);
        rcv.setAdapter(myadaper);
        but_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myadaper.add();
            }
        });
        but_deldete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myadaper.remove();
            }
        });
    }

    private void itimdate() {
        list = new ArrayList<>();
        for (int x=0;x<40;x++){
            list.add("这是条目"+x);
        }
    }

}
