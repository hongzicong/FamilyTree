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
            mTreeListViewAdapter = new SimpleTreeAdapter<>(mTree, this, mDatas, 10);
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

    }


}
