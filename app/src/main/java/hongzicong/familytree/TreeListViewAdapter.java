package hongzicong.familytree;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DELL-PC on 2017/12/4.
 */

public abstract class TreeListViewAdapter extends BaseAdapter {

    protected Context mContext;
    //All can see node
    protected List<Node> mNodeList;
    protected LayoutInflater mLayoutInflater;
    //All node
    protected List<Node> mAllNodes;

    private OnTreeNodeClickListener onTreeNodeClickListener;
    private OnChangeSexClickListener mOnChangeSexClickListener;
    private OnExpandClickListener mOnExpandClickListener;

    public interface OnExpandClickListener{
        void onClick(Node node,int position);
    }

    public void setOnExpandClickListner(OnExpandClickListener onExpandClickListner){
        this.mOnExpandClickListener=onExpandClickListner;
    }

    public interface OnChangeSexClickListener{
        void onClick(Node node,int position);
    }

    public void setOnChangeSexClickListener(OnChangeSexClickListener onChangeSexClickListener){
        this.mOnChangeSexClickListener=onChangeSexClickListener;
    }

    public interface OnTreeNodeClickListener{
        void onClick(Node node,int position);
    }

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener onTreeNodeClickListener) {
        this.onTreeNodeClickListener = onTreeNodeClickListener;
    }

    public TreeListViewAdapter(ListView mTree, Context context, List<PersonData> datas, int defaultExpandLevel) throws IllegalArgumentException, IllegalAccessException {
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
    public Node getItem(int position) {
        return mNodeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.sex=(ImageView)convertView.findViewById(R.id.is_sex);
            viewHolder.expandIcon = (ImageView) convertView.findViewById(R.id.is_expand);
            viewHolder.label = (TextView) convertView.findViewById(R.id.name);
            viewHolder.picture=(ImageView)convertView.findViewById(R.id.picture);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnChangeSexClickListener.onClick(getItem(position),position);
            }
        });


        Node node = mNodeList.get(position);
        convertView = getConvertView(node, position, convertView, parent);
        convertView.setPadding(node.getLevel() * 50, 3, 3, 3);
        return convertView;
    }

    public abstract View getConvertView(Node node, int position, View convertView, ViewGroup parent);

    protected final class ViewHolder {
        ImageView expandIcon;
        TextView label;
        ImageView sex;
        ImageView picture;
    }

}
