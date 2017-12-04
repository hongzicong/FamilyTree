package hongzicong.familytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL-PC on 2017/12/4.
 */

public class Node {

    private int mLevel;
    private List<Node> mChildList=new ArrayList<>();
    private Node mFather;

    private int expandIcon;
    private int sexIcon;

    private String name;
    private boolean isExpand=false;
    private int level;

    public Node(){

    }

    public Node(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public List<Node> getChildList(){
        return mChildList;
    }

    public boolean isRoot(){
        return mFather==null;
    }

    public boolean isLeaf(){
        return mChildList.size()==0;
    }

    public boolean isExpand(){
        return isExpand;
    }

    public void setExpandIcon(int icon){
        this.expandIcon=icon;
    }

    public int getExpandIcon(){
        return expandIcon;
    }

    public void setSexIcon(int icon){
        this.sexIcon=icon;
    }

    public void setExpand(boolean isExpand){
        this.isExpand=isExpand;
        if(!isExpand){
            for(Node node:mChildList){
                node.setExpand(isExpand);
            }
        }
    }

    public void setLevel(int level){
        this.level=level;
    }

    public int getLevel(){
        return (mFather==null)?0:mFather.getLevel()+1;
    }

}
