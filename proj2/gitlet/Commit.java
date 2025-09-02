package gitlet;

// TODO: any imports you need here

import javax.swing.tree.TreeNode;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date; // TODO: You'll likely use this in this class
import java.util.List;

import static gitlet.Repository.*;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author ZanderAn
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     * <p>
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */
    private String parent;

    private String parentForMerge;

    /**
     * The message of this Commit.
     */
    private String message;

    private Date timestamp;

    private TreeNode SnapShot;

    /* TODO: fill in the rest of this class. */

    public Commit() {
        message = "initial commit";
        parent = null;
        timestamp = new Date(0);
        SnapShot = TreeBuilderHelper(CWD);
    }

    public Commit(String m, Commit p) {
        message = m;
        parent = Utils.sha1(p);
        //复制parent的Snap Shot
        SnapShot = TreeBuilderHelper(CWD);
        TreeNode t = p.SnapShot.DeepCopy(p.SnapShot); //太怪了这代码逻辑有点恶心
        timestamp = new Date();
        //在commit文件夹里面寻找parent序列化后的文件（关于文件search，是否需要重写方法？）
    }

    public static String GetParent(Commit c){
        return c.parent;
    }

    public static String GetMessage(Commit c){
        return c.message;
    }

    public static Date GetTime(Commit c){
        return c.timestamp;
    }

    public TreeNode GetSnapShot(){
        return SnapShot;
    }   

    public TreeNode TreeBuilderHelper(File f) {//此方法用于递归的创建一个以文件夹关系为导向的tree
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            ArrayList<TreeNode> children = new ArrayList<>();

            if (files != null) {
                Arrays.sort(files); // 保证遍历顺序一致
                for (File child : files) {
                    TreeNode childNode = TreeBuilderHelper(child);
                    if (childNode != null) {
                        children.add(childNode);
                    }
                }
            }

            // 注意这里：目录节点没有 hash，只用名字和子节点
            return new TreeNode(f.getName(), null, children);

        } else {
            // 是文件时，构造 hash，子节点为 null 或空列表
            String hash = Utils.sha1(Utils.readContents(f));
            return new TreeNode(f.getName(), hash, new ArrayList<>());
        }
    }


    public class TreeNode implements Serializable {
        private final String name;
        private final String sha1;
        private List<TreeNode> ChildList;

        private TreeNode(String n, String s, List<TreeNode> children) {
            name = n;
            sha1 = s;
            ChildList = children;
        }

        private TreeNode(String n, String s) {
            name = n;
            sha1 = s;
            ChildList = new ArrayList<>();
        }

        private void addChild(TreeNode f) {
            ChildList.add(f);
        }

        public String getName(){
            return name;
        }

        public String getSha1(){
            return sha1;
        }

        public List<TreeNode> getChildList(){
            return ChildList;
        }

        private TreeNode DeepCopy(TreeNode tr) {
            TreeNode copy = new TreeNode(tr.name, tr.sha1);
            if (!tr.ChildList.isEmpty()) {
                for (TreeNode items : tr.ChildList) {
                    copy.addChild(DeepCopy(items));
                }
            }
            return copy;
        }

    }
}
