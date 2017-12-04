package hongzicong.familytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL-PC on 2017/12/4.
 */

public class TreeHelper {

    public static <T> List<Node> getSortedNodes(List<T> datas,int defaultExpandLevel) throws IllegalArgumentException,IllegalAccessException {
        List<Node> result = new ArrayList<Node>();
        List<Node> nodes = convetData2Node(datas);
        List<Node> rootNodes = getRootNodes(nodes);
        for (Node node : rootNodes) {
            addNode(result, node, defaultExpandLevel, 1);
        }
        return result;
    }

    public static List<Node> filterVisibleNode(List<Node> nodes) {
        List<Node> result = new ArrayList<Node>();

        for (Node node : nodes) {
            // 如果为跟节点，或者上层目录为展开状态
            if (node.isRoot() || node.isParentExpand())
            {
                setNodeIcon(node);
                result.add(node);
            }
        }
        return result;
    }

    private static <T> List<Node> convetData2Node(List<T> datas) throws IllegalArgumentException, IllegalAccessException {
        List<Node> nodes = new ArrayList<Node>();
        Node node = null;

        for (T t : datas) {
            int id = -1;
            int pId = -1;
            String label = null;
            Class<? extends Object> clazz = t.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field f : declaredFields)
            {
                if (f.getAnnotation(TreeNodeId.class) != null)
                {
                    f.setAccessible(true);
                    id = f.getInt(t);
                }
                if (f.getAnnotation(TreeNodePid.class) != null)
                {
                    f.setAccessible(true);
                    pId = f.getInt(t);
                }
                if (f.getAnnotation(TreeNodeLabel.class) != null)
                {
                    f.setAccessible(true);
                    label = (String) f.get(t);
                }
                if (id != -1 && pId != -1 && label != null)
                {
                    break;
                }
            }
            node = new Node(id, pId, label);
            nodes.add(node);
        }

        for (int i = 0; i < nodes.size(); i++)
        {
            Node n = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j++)
            {
                Node m = nodes.get(j);
                if (m.getpId() == n.getId())
                {
                    n.getChildren().add(m);
                    m.setParent(n);
                } else if (m.getId() == n.getpId())
                {
                    m.getChildren().add(n);
                    n.setParent(m);
                }
            }
        }

        // 设置图片
        for (Node n : nodes)
        {
            setNodeIcon(n);
        }
        return nodes;
    }

    private static List<Node> getRootNodes(List<Node> nodes)
    {
        List<Node> root = new ArrayList<Node>();
        for (Node node : nodes)
        {
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
