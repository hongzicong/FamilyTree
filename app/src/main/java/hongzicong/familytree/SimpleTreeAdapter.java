package hongzicong.familytree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DELL-PC on 2017/12/4.
 */

public class SimpleTreeAdapter extends TreeListViewAdapter {

    public SimpleTreeAdapter(ListView mTree, Context context, List<PersonData> datas, int defaultExpandLevel) throws IllegalArgumentException, IllegalAccessException {
        super(mTree, context, datas, defaultExpandLevel);
    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
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

        if (node.getExpandIcon() == -1) {
            viewHolder.expandIcon.setVisibility(View.INVISIBLE);
        }
        else {
            viewHolder.expandIcon.setVisibility(View.VISIBLE);
            viewHolder.expandIcon.setImageResource(node.getExpandIcon());
        }

        if(node.getIsMale()){
            viewHolder.sex.setImageResource(R.drawable.male_icon);
        }
        else{
            viewHolder.sex.setImageResource(R.drawable.female_icon);
        }

        viewHolder.picture.setImageResource(node.getPicture());
        viewHolder.label.setText(node.getName());

        return convertView;
    }

    private final class ViewHolder {
        ImageView expandIcon;
        TextView label;
        ImageView sex;
        ImageView picture;
    }
}
