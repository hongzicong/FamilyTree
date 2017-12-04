package hongzicong.familytree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by DELL-PC on 2017/12/4.
 */

public abstract class TreeListViewAdapter<T> extends BaseAdapter {

    protected Context mContext;
    //All can see node
    protected List<Node> mNodeList;
    protected LayoutInflater mLayoutInflater;
    //All node
    protected List<Node> mAllNodes;

    private OnTreeNodeClickListener onTreeNodeClickListener;

    public interface OnTreeNodeClickListener{
        void onClick(Node node,int position);
    }

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener onTreeNodeClickListener) {
        this.onTreeNodeClickListener = onTreeNodeClickListener;
    }

    public TreeListViewAdapter(ListView mTree, Context context, List<T> datas, int defaultExpandLevel) throws IllegalArgumentException, IllegalAccessException {
        mContext = context;
        mAllNodes = TreeHelper.getSortedNodes(datas, defaultExpandLevel);
        mNodeList = TreeHelper.filterVisibleNode(mAllNodes);
        mLayoutInflater = LayoutInflater.from(context);

        mTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                expandOrCollapse(position);
                if (onTreeNodeClickListener != null) {
                    onTreeNodeClickListener.onClick(mNodeList.get(position),position);
                }
            }
        });
    }

    public void expandOrCollapse(int position) {
        Node n = mNodeList.get(position);
        if (n != null) {
            if (!n.isLeaf()) {
                n.setExpand(!n.isExpand());
                mNodeList = TreeHelper.filterVisibleNode(mAllNodes);
                notifyDataSetChanged();
            }
        }
    }

    @Override
    public int getCount() {
        return mNodeList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNodeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Node node = mNodeList.get(position);
        convertView = getConvertView(node, position, convertView, parent);
        convertView.setPadding(node.getLevel() * 30, 3, 3, 3);
        return convertView;
    }

    public abstract View getConvertView(Node node, int position, View convertView, ViewGroup parent);

}
