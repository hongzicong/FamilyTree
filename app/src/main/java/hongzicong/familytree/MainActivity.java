package hongzicong.familytree;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<PersonData> mPersonDatas=new ArrayList<>();
    private ListView mTree;
    private TreeListViewAdapter mTreeListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        initData();
        initWidget();

        try {
            mTreeListViewAdapter = new SimpleTreeAdapter(mTree, this, mPersonDatas, 10);
            mTree.setAdapter(mTreeListViewAdapter);
            mTreeListViewAdapter.setOnChangeSexClickListener(new SimpleTreeAdapter.OnChangeSexClickListener(){
                @Override
                public void onClick(Node node, int position) {
                    node.setIsMale(!node.getIsMale());
                    mTreeListViewAdapter.notifyDataSetChanged();
                }
            });
            mTreeListViewAdapter.setOnExpandClickListner(new SimpleTreeAdapter.OnExpandClickListener(){
                @Override
                public void onClick(int position) {
                    mTreeListViewAdapter.expandOrCollapse(position);
                }
            });
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

    }

    private void initWidget(){
        mTree=(ListView)findViewById(R.id.family_tree);
    }

    private void initData(){
        PersonData p=new PersonData(1,0,"A",R.drawable.picture,true);
        mPersonDatas.add(p);
        p=new PersonData(2,1,"B",R.drawable.picture,true);
        mPersonDatas.add(p);
        p=new PersonData(3,1,"C",R.drawable.picture,true);
        mPersonDatas.add(p);
        p=new PersonData(4,2,"D",R.drawable.picture,true);
        mPersonDatas.add(p);
        p=new PersonData(5,4,"E",R.drawable.picture,true);
        mPersonDatas.add(p);
        p=new PersonData(6,5,"F",R.drawable.picture,true);
        mPersonDatas.add(p);
        p=new PersonData(7,4,"G",R.drawable.picture,true);
        mPersonDatas.add(p);
        p=new PersonData(8,1,"H",R.drawable.picture,true);
        mPersonDatas.add(p);
        p=new PersonData(9,2,"I",R.drawable.picture,true);
        mPersonDatas.add(p);
        //todo get from database
    }

}
