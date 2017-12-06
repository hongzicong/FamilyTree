package hongzicong.familytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL-PC on 2017/12/4.
 */

public class Node {

    private int id;
    private int fatherId;

    private int mLevel;
    private List<Node> mChildList=new ArrayList<>();
    private Node mFather;
    private int mAge;

    private int expandIcon;
    private boolean isMale;

    private int picture;
    private String name;
    private boolean expand=false;
    private boolean isAnimate=false;

    public Node(){

    }

    public Node(String name){
        this.name=name;
    }

    public Node(String name,boolean isMale,int id,int fatherId){
        this.name=name;
        this.fatherId=fatherId;
        this.id=id;
        this.isMale=isMale;
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
        return expand;
    }

    public boolean isParentExpand(){
        if(mFather!=null){
            return mFather.isExpand();
        }
        return true;
    }

    public boolean isDie(){
        return mAge==-1;
    }

    public void setIsAnimate(boolean isAnimate){
        for(Node node:mChildList){
            node.setIsAnimate(true);
        }
        this.isAnimate=isAnimate;
    }

    public boolean getIsAnimate(){
        return this.isAnimate;
    }

    public void setExpandIcon(int icon){
        this.expandIcon=icon;
    }

    public int getExpandIcon(){
        return expandIcon;
    }

    public void setIsMale(boolean isMale){
        this.isMale=isMale;
    }

    public boolean getIsMale(){
        return this.isMale;
    }

    public void setExpand(boolean isExpand){
        this.expand=isExpand;
        if(!isExpand){
            for(Node node:mChildList){
                node.setIsAnimate(true);
                node.setExpand(isExpand);
            }
        }
    }

    public int getLevel(){
        return (mFather==null)?0:mFather.getLevel()+1;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setFatherId(int id){
        this.fatherId=id;
    }

    public int getId(){
        return id;
    }

    public int getFatherId(){
        return fatherId;
    }

    public void setFather(Node father){
        this.mFather=father;
    }

    public void setPicture(int picture){
        this.picture=picture;
    }

    public int getPicture(){
        return picture;
    }

    public int getAge(){
        return mAge;
    }

    public void setAge(int age){
        this.mAge=age;
    }
}
