package hongzicong.familytree;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

    private Animation mAnimation;
    ColorMatrix cm;
    ColorMatrixColorFilter grayColorFilter;

    private OnTreeNodeClickListener onTreeNodeClickListener;
    private OnChangeSexClickListener mOnChangeSexClickListener;
    private OnExpandClickListener mOnExpandClickListener;
    private OnAddPartnerClickListener mOnAddPartnerClickListener;
    private OnAddChildClickListener mOnAddChildClickListener;
    private OnDieClickListener mOnDieClickListener;
    private OnUnDieClickListener mOnUnDieClickListener;

    public OnExpandClickListener getOnExpandClickListener(){
        return this.mOnExpandClickListener;
    }

    public List<Node> getNodeList(){
        return this.mNodeList;
    }

    public interface OnUnDieClickListener{
        void onClick(Node node,int position);
    }

    public void setOnUnDieClickListener(OnUnDieClickListener onUnDieClickListener){
        this.mOnUnDieClickListener=onUnDieClickListener;
    }

    public interface OnDieClickListener{
        void onClick(Node node,int position);
    }

    public void setOnDieClickListener(OnDieClickListener onDieClickListener){
        this.mOnDieClickListener=onDieClickListener;
    }

    public interface OnAddChildClickListener{
        void onClick(Node node,int position);
    }

    public void setOnAddChildClickListener(OnAddChildClickListener onAddChildClickListener){
        this.mOnAddChildClickListener=onAddChildClickListener;
    }

    public interface OnAddPartnerClickListener{
        void onClick(Node node,int position);
    }

    public void setOnAddPartnerClickListener(OnAddPartnerClickListener onAddPartnerClickListener){
        this.mOnAddPartnerClickListener=onAddPartnerClickListener;
    }

    public interface OnExpandClickListener{
        void onClick(int position);
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
        mAnimation= AnimationUtils.loadAnimation(mContext, R.anim.list_anim);
        cm = new ColorMatrix();
        cm.setSaturation(0);
        grayColorFilter = new ColorMatrixColorFilter(cm);
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
            viewHolder.paddingText=(TextView)convertView.findViewById(R.id.padding_text);
            viewHolder.addPartnerText=(TextView)convertView.findViewById(R.id.add_wife);
            viewHolder.addSonText=(TextView)convertView.findViewById(R.id.add_son);
            viewHolder.dieText=(TextView)convertView.findViewById(R.id.die);
            viewHolder.undieText=(TextView)convertView.findViewById(R.id.undie);
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

        viewHolder.expandIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mOnExpandClickListener.onClick(position);
            }
        });

        viewHolder.addPartnerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnAddPartnerClickListener.onClick(getItem(position),position);
            }
        });

        viewHolder.dieText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnDieClickListener.onClick(getItem(position),position);
            }
        });

        viewHolder.undieText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mOnUnDieClickListener.onClick(getItem(position),position);
            }
        });

        viewHolder.addSonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mOnAddChildClickListener.onClick(getItem(position),position);
            }
        });


        Node node = mNodeList.get(position);
        if(node.getIsAnimate()){
            convertView.startAnimation(mAnimation);
            node.setIsAnimate(false);
        }
        convertView = getConvertView(node, position, convertView, parent);
        StringBuffer paddingString=new StringBuffer();
        for(int i=0;i<node.getLevel();++i){
            paddingString.append("   ");
        }
        viewHolder.paddingText.setText(paddingString);

        if(node.isDie()){
            //todo new picture
            Log.d("HAHA","die");
            viewHolder.picture.setImageResource(R.drawable.dead_picture);
            if(node.getIsMale()){
                viewHolder.sex.setImageResource(R.drawable.dead_male_icon);
            }
            else{
                viewHolder.sex.setImageResource(R.drawable.dead_female_icon);
            }
        }
        else{
            //todo new picture
            Log.d("HAHA","undie");
            viewHolder.picture.setImageResource(R.drawable.picture);
            if(node.getIsMale()){
                viewHolder.sex.setImageResource(R.drawable.male_icon);
            }
            else{
                viewHolder.sex.setImageResource(R.drawable.female_icon);
            }
        }

        return convertView;
    }

    public abstract View getConvertView(Node node, int position, View convertView, ViewGroup parent);

    protected final class ViewHolder {
        ImageView expandIcon;
        TextView label;
        ImageView sex;
        ImageView picture;
        TextView paddingText;
        TextView addPartnerText;
        TextView addSonText;
        TextView dieText;
        TextView undieText;
    }

}
