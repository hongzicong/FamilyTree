package hongzicong.familytree;

import android.content.Intent;
import android.os.Build;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<PersonData> mPersonDatas=new ArrayList<>();
    private ListView mTree;
    private TreeListViewAdapter mTreeListViewAdapter;
    private Toolbar toolBar;
    private SearchView mSearchView;
    private int newId;

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
            mTreeListViewAdapter = new SimpleTreeAdapter(mTree, this, mPersonDatas, 2);
            mTree.setAdapter(mTreeListViewAdapter);
            setAllClickListener();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

    }

    private void setAllClickListener(){
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
        mTreeListViewAdapter.setOnDieClickListener(new SimpleTreeAdapter.OnDieClickListener() {
            @Override
            public void onClick(Node node,int position) {
                node.setAge(-1);
                mTreeListViewAdapter.notifyDataSetChanged();
            }
        });
        mTreeListViewAdapter.setOnAddChildClickListener(new SimpleTreeAdapter.OnAddChildClickListener(){
            @Override
            public void onClick(Node node, List<Node> allNodes) {
                PersonData p=new PersonData(newId++,node.getId(),"无名氏",R.drawable.picture,true,1);
                Node newNode=TreeHelper.convertADataToANode(p);
                newNode.setFather(node);
                node.getChildList().add(newNode);
                node.setExpand(false);
                allNodes.add(newNode);
                TreeHelper.sortNodesInPlace(allNodes,3);
                mTreeListViewAdapter.notifyDataSetChanged();
            }
        });
        mTreeListViewAdapter.setOnAddPartnerClickListener(new SimpleTreeAdapter.OnAddPartnerClickListener() {
            @Override
            public void onClick(Node node,List<Node> allNodes) {
                //todo
                Toast.makeText(MainActivity.this,"Hello,world",Toast.LENGTH_LONG).show();
            }
        });
        mTreeListViewAdapter.setOnUnDieClickListener(new SimpleTreeAdapter.OnUnDieClickListener(){
            @Override
            public void onClick(Node node, int position) {
                node.setAge(1);
                mTreeListViewAdapter.notifyDataSetChanged();
            }
        });
        mTree.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,EditData.class);
                /*intent.putExtra("member_name",);
                intent.putExtra("member_age",);
                intent.putExtra("member_picture",);*/
                startActivityForResult(intent,1);
            }
        });
    }

    private void initWidget(){
        mTree=(ListView)findViewById(R.id.family_tree);
        toolBar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
    }

    private void setMyActionBar(){
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        actionBar.setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.search);
        mSearchView=(SearchView) MenuItemCompat.getActionView(menuItem);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("查找");
        mSearchView.setIconifiedByDefault(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //todo
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initData(){
        PersonData p=new PersonData(11,0,"贾演",R.drawable.picture_a,true,-1);
        mPersonDatas.add(p);
        p=new PersonData(1,0,"贾源",R.drawable.picture_b,true,-1);
        mPersonDatas.add(p);
        p=new PersonData(2,1,"贾代善",R.drawable.picture_c,true,43);
        mPersonDatas.add(p);
        p=new PersonData(3,2,"贾政",R.drawable.picture_d,true,43);
        mPersonDatas.add(p);
        p=new PersonData(4,2,"贾敏",R.drawable.picture_e,true,56);
        mPersonDatas.add(p);
        p=new PersonData(5,2,"贾赦",R.drawable.picture_f,true,34);
        mPersonDatas.add(p);
        p=new PersonData(6,3,"贾宝玉",R.drawable.picture_g,true,43);
        mPersonDatas.add(p);
        p=new PersonData(7,3,"贾元春",R.drawable.picture_h,true,23);
        mPersonDatas.add(p);
        p=new PersonData(8,3,"贾珠",R.drawable.picture_i,true,54);
        mPersonDatas.add(p);
        p=new PersonData(9,4,"林黛玉",R.drawable.picture_j,true,23);
        mPersonDatas.add(p);
        newId=10;
        //todo get from database
    }

}
