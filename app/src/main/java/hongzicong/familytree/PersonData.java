package hongzicong.familytree;

/**
 * Created by DELL-PC on 2017/12/4.
 */

public class PersonData {

    private String mName;
    private int mId;
    private int parentId;
    private int sex;

    public PersonData(int mId,int parentId,String mName,int sex){
        this.mId=mId;
        this.parentId=parentId;
        this.mName=mName;
        this.sex=sex;
    }

    public int getId(){
        return mId;
    }

    public int getParentId(){
        return parentId;
    }

    public int getSex(){
        return sex;
    }

    public void setId(int id){
        this.mId=id;
    }

    public void setParentId(int id){
        this.parentId=id;
    }

}
