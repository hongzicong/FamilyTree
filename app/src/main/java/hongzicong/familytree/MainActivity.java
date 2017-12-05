package hongzicong.familytree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<PersonData> mPersonDatas=new ArrayList<>();
    private ListView mTree;
    private TreeListViewAdapter mTreeListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initWidget();

        try {
            mTreeListViewAdapter = new SimpleTreeAdapter(mTree, this, mPersonDatas, 10);
            mTree.setAdapter(mTreeListViewAdapter);
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

    }

    private void initWidget(){
        mTree=(ListView)findViewById(R.id.family_tree);
    }

    private void initData(){
        PersonData p=new PersonData(1,0,"路明非爸爸",R.drawable.picture,true);
        mPersonDatas.add(p);
        p=new PersonData(2,1,"路明非",R.drawable.picture,true);
        mPersonDatas.add(p);
        p=new PersonData(3,1,"路鸣泽",R.drawable.picture,true);
        mPersonDatas.add(p);
        //todo get from database
    }

}
