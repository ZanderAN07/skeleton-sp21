package gitlet;

import javax.swing.tree.TreeNode;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static gitlet.Commit.*;
import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author ZanderAN
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    public Map<String, String> stagedForAdd = new HashMap<>();

    public Map<String, String> stagedForRemove = new HashMap<>();

    public static String HEAD;

    public static String currentCommit;

    public static File BRANCHES = join(GITLET_DIR, ".branches");

    public static final File BLOBS = join(GITLET_DIR, ".blobs");

    public static final File COMMITS = join(GITLET_DIR, ".commits");
    /*实现了head之后，该怎么追踪parents？可以把commit写入head，但是怎么把head写入新的commit呢？难不成把parents的性质改变？
    但是这就违背了初衷（使用hash code代替其他文件储存模式旨在节约空间）。如何通过hashing把parents储存是一个问题（这里，使用Util里的
    readContentAs String可以解决。
    主要难点在于，通过hash code追踪.git文件夹里面commit，并通过序列码还原这个对象（这一步可以使用Util里面的方法）
     */

    /* TODO: fill in the rest of this class. */
    public void init(){
        if(GITLET_DIR.exists()){
            System.err.println("A Gitlet version-control system already exists in the current directory.");
            return;
        }
        initializedFile(GITLET_DIR,BRANCHES,BLOBS,COMMITS);

        //create master branch
        File DefaultBranch = Utils.join(BRANCHES, "master");
        DefaultBranch.mkdir();


        Commit initialCommit = new Commit();
        String initialCommitHash = Utils.sha1(initialCommit);
        File initialCommitFile = Utils.join(COMMITS, initialCommitHash);
        Utils.writeObject(initialCommitFile, initialCommit);
        HEAD = "master";
        File masterBranchFile = Utils.join(BRANCHES, "master");
        Utils.writeContents(masterBranchFile, initialCommitHash);// 写入 commit 的文件名
    }

    public void makeNewCommit(String message){
        Commit parent = Utils.readObject(HEAD, Commit.class); //获取当前指针指向的commit的parent commit
        Commit c = new Commit(message, parent);
        File newCommit = Utils.join(COMMITS, Utils.sha1(c));//commit对象以hashcode命名
        Utils.writeObject(newCommit, c);
    }

    public void makeNewBranch(String name){
        //是否改变HEAD？待解决
        File newBranchFile = Utils.join(BRANCHES, name);
        Utils.writeContents(newBranchFile, currentCommit);
    }

    public void add(File f){
        //把file f的hash加入staging area,文档内容写入blob。staging area是hash map，对应hash值和文档名
        //这个文件的名字是SHA-1CODE形式
        //在staging area里干了这些：学习对应关系 - -- - - - -hello.txt -> blob 3a45b...
        String hash = Utils.sha1(Utils.readContents(f));
        if(stagedForRemove.containsKey(hash)){
            stagedForRemove.remove(hash);
        }
        else {
            File adder = Utils.join(BLOBS, hash);
            //暂定；blob直接存储代码块（也就是数据/file的结构）和hash（作为名字），也就是名字叫sha-1code的代码块钱
            Utils.writeContents(adder, Utils.readContents(f));
            //staging area的数据结构是hash map，也就是对应关系-文档名对应hash code。
            stagedForAdd.put(f.getName(), hash);
        }
    }

    public void remove(File f){
        String hash = Utils.sha1(f);
        if(stagedForAdd.containsKey(hash)){
            stagedForAdd.remove(hash);
        }
        else{
            stagedForRemove.put(f.getName(), hash);
        }
    }

    public void log(Commit c){
        //TODO： 还没有实现有merge parents的case（在搞明白merge实现原理之后修改）
        //date使用java,utl.date(c.timestamp). ID is commit's filename message is c.messsage
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z", Locale.US);//CHATGPT
        Date commitDate = GetTime(c); // 如果你本身存的就是 Date 类型
        System.out.println("===");
        System.out.println("commit " + sha1(c)); // 假设你已经存了 commit 的 SHA-1 id
        System.out.println("Date: " + sdf.format(commitDate));
        System.out.println(GetMessage(c));
        System.out.println(); // 空行
        if(GetParent(c) != null){
            log(Utils.readObject(FileFinder(COMMITS, GetParent(c)), Commit.class));
        }
    }

    public void status(){

    }

    public void checkInital() {
        if (!(GITLET_DIR.exists())) {
            System.err.println("Not in an initialized Gitlet directory.");
        }
    }

    public void clearStagingArea(){
        stagedForAdd.clear();
        stagedForRemove.clear();
    }

    public void checkoutCommit(String commitID){ //理论上来说永不出错
        //reset CWD to a certain Snapshot.
        Commit targetCommit = Utils.readObject(FileFinder(COMMITS, commitID),Commit.class);
        //File ReplacedCWD = new File();
        //iterate a tree and rebuild every file
        restoreWorkingDirectory(targetCommit);
    }

    private void restoreWorkingDirectory(Commit targetCommit){
        iterate(targetCommit.getSnapShot(), CWD);
        currentCommit = commitID;
    }

    private File BuildFile(Commit.TreeNode Snapshot){
        String name = Snapshot.getName();
        String sha1 = Snapshot.getSha1();
        File blob = Utils.join(BLOBS, sha1);
        byte[] content = Utils.readContents(blob);
        File output = new File(name);
        Utils.writeContents(output, content); // 内容恢复，完全一致
        return output;
    }
    //提前给我新的cwd！
    private void iterate(Commit.TreeNode x, File parent) {
        if (parent == null) {
            File newFile = new File(x.getName());
        } else {
            File newFile = Utils.join(parent, x.getName());
            Utils.writeContents(newFile, BuildFile(x));
            if (!x.getChildList().isEmpty()) {
                for (Commit.TreeNode items : x.getChildList()) {
                    iterate(items, newFile);
                }
            }
        }
    }

    public static File FileFinder(File WorkingDir, String name) {//如果文件存在，我可以直接使用join进行连接，这个其实不必
        File[] files = WorkingDir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.getName().equals(name)) {
                    return f; // 找到目标文件
                }
            }
        }
        return null;
    }

    private static void initializedFile(File... dirs){
        for (File dir : dirs) {
            dir.mkdir();
        }
    }

}
