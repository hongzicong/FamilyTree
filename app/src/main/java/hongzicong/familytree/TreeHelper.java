package hongzicong.familytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL-PC on 2017/12/4.
 */

public class TreeHelper {

    public static List<Node> getSortedNodes(List<PersonData> datas,int defaultExpandLevel) throws IllegalArgumentException,IllegalAccessException {
        List<Node> result = new ArrayList<Node>();
        List<Node> nodes = convetDataToNode(datas);
        List<Node> rootNodes = getRootNodes(nodes);
        for (Node node : rootNodes) {
            addNode(result, node, defaultExpandLevel, 1);
        }
        return result;
    }

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

    private static List<Node> convetDataToNode(List<PersonData> datas) throws IllegalArgumentException, IllegalAccessException {
        List<Node> nodes = new ArrayList<Node>();
        Node node = null;

        for (PersonData p : datas) {
            node=new Node(p.getName(),p.getIsMale(),p.getId(),p.getParentId());
            nodes.add(node);
        }

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

    private static List<Node> getRootNodes(List<Node> nodes) {
        List<Node> root = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node.isRoot())
                root.add(node);
        }
        return root;
    }

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
