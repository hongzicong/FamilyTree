package hongzicong.familytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL-PC on 2017/12/4.
 */

public class TreeHelper {

    //输出有顺序的node集合
    public static List<Node> getSortedNodes(List<PersonData> datas,int defaultExpandLevel) throws IllegalArgumentException,IllegalAccessException {
        List<Node> result = new ArrayList<Node>();
        //单纯的node
        List<Node> nodes = convetDataToNode(datas);
        //所有是root的node集合
        List<Node> rootNodes = getRootNodes(nodes);
        //从root开始将所有的node加入进去result中，并将是否打开的信息加入进去
        for (Node node : rootNodes) {
            addNode(result, node, defaultExpandLevel, 1);
        }
        return result;
    }

    public static void sortNodesInPlace(List<Node> allNode,int defaultExpandLevel){
        List<Node> rootNodes=getRootNodes(allNode);
        allNode.clear();
        for(Node node:rootNodes) {
            addNode(allNode, node, defaultExpandLevel, 1);
        }
    }

    //返回所有可见的node集合
    public static List<Node> filterVisibleNode(List<Node> nodes) {
        List<Node> result = new ArrayList<Node>();

        for (Node node : nodes) {
            if (node.isRoot() || node.isParentExpand()) {
                setNodeIcon(node);
                result.add(node);
            }
        }
        return result;
    }

    //将一个persondata变成node，并返回
        public static Node convertADataToANode(PersonData p){
        Node node=new Node(p.getName(),p.getIsMale(),p.getId(),p.getParentId());
        node.setPicture(p.getPicture());
        node.setAge(p.getAge());
        return node;
    }

    //生成单纯的list（无顺序），但是所有的node加了child了
    private static List<Node> convetDataToNode(List<PersonData> datas) throws IllegalArgumentException, IllegalAccessException {
        List<Node> nodes = new ArrayList<Node>();
        Node node = null;

        for (PersonData p : datas) {
            nodes.add(convertADataToANode(p));
        }

        //将每个node的childlist更新
        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j++) {
                Node m = nodes.get(j);
                if (m.getFatherId() == n.getId()) {
                    n.getChildList().add(m);
                    m.setFather(n);
                }
                else if (m.getId() == n.getFatherId()) {
                    m.getChildList().add(n);
                    n.setFather(m);
                }
            }
        }

        for (Node n : nodes) {
            setNodeIcon(n);
        }
        return nodes;
    }

    //将所有的root点加入到一个新的list，再输出来
    private static List<Node> getRootNodes(List<Node> nodes) {
        List<Node> root = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node.isRoot())
                root.add(node);
        }
        return root;
    }

    //从root点的集合开始，输出一个有顺序的list（父后跟着子）
    private static void addNode(List<Node> nodes, Node node, int defaultExpandLeval, int currentLevel) {
        nodes.add(node);
        if (defaultExpandLeval >= currentLevel) {
            node.setExpand(true);
        }
        if (node.isLeaf())
            return;
        for (int i = 0; i < node.getChildList().size(); i++) {
            addNode(nodes, node.getChildList().get(i), defaultExpandLeval, currentLevel + 1);
        }
    }

    private static void setNodeIcon(Node node) {
        if (node.getChildList().size() > 0 && node.isExpand()) {
            node.setExpandIcon(R.drawable.expand);
        }
        else if (node.getChildList().size() > 0 && !node.isExpand()) {
            node.setExpandIcon(R.drawable.already_expand);
        }
        else{
            node.setExpandIcon(-1);
        }
    }



}
