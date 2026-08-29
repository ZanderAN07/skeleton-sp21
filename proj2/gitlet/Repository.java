package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Repository implements Serializable {
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
    /** Serialized repository state. */
    public static final File STATE = join(GITLET_DIR, ".state");

    /* TODO: fill in the rest of this class. */
    /** Files staged for addition: file name -> blob id. */
    public Map<String, String> stagedForAdd = new TreeMap<>();

    /** Files staged for removal, stored by file name only. */
    public TreeSet<String> stagedForRemove = new TreeSet<>();

    /** Current branch name. */
    public String HEAD;

    public String currentCommit;

    /** Branch pointers: branch name -> commit id. */
    public Map<String, String> branches = new TreeMap<>();

    public static final File BLOBS = join(GITLET_DIR, ".blobs");

    public static final File COMMITS = join(GITLET_DIR, ".commits");

    public Repository() {
        if (STATE.isFile()) {
            Repository saved = Utils.readObject(STATE, Repository.class);
            this.stagedForAdd = saved.stagedForAdd;
            this.stagedForRemove = saved.stagedForRemove;
            this.HEAD = saved.HEAD;
            this.currentCommit = saved.currentCommit;
            this.branches = saved.branches;
        }
    }

    public void init(){
        //if already existed
        if(GITLET_DIR.exists()){
            System.err.println("A Gitlet version-control system already exists in the current directory.");
            return;
        }
        GITLET_DIR.mkdir();
        COMMITS.mkdir();
        BLOBS.mkdir();
        //create new commit then write it to the COMMIT file
        Commit initial_commit = new Commit();
        String commitID = Utils.sha1(Utils.serialize(initial_commit));
        File initialCommitFile = Utils.join(COMMITS,commitID);
        Utils.writeObject(initialCommitFile, initial_commit);
        HEAD = "master";
        currentCommit = commitID;
        branches.put("master", commitID);
        save();
    }

    public void makeNewCommit(String message){
        String parentId = branches.get(HEAD);
        Commit parent = Utils.readObject(Utils.join(COMMITS, parentId),Commit.class);
        TreeMap<String, String> newSnapshot = new TreeMap<>(parent.getSnapshot());
        //add files from staging area to git
        for (String fileName : stagedForAdd.keySet()) {
            String blobId = stagedForAdd.get(fileName);
            newSnapshot.put(fileName, blobId);
        }
        for (String fileName : stagedForRemove) {
            newSnapshot.remove(fileName);
        }
        Commit commit = new Commit(message, parentId, newSnapshot);
        String commitID = Utils.sha1(Utils.serialize(commit));
        File CommitFile = Utils.join(COMMITS,commitID);
        Utils.writeObject(CommitFile, commit);
        branches.put(HEAD, commitID);
        currentCommit = commitID;
        save();
    }

    public void makeNewBranch(String name){
        if (branches.containsKey(name)) {
            // A branch with that name already exists.
            return;
        }

        String currentCommitId = branches.get(HEAD);
        branches.put(name, currentCommitId);
        save();
    }

    public void add(File f){
        String id = Utils.sha1(Utils.readContents(f));
        // Adding a file cancels a pending removal for that same file name.
        stagedForRemove.remove(f.getName());
        File adder = Utils.join(BLOBS, id);
        Utils.writeContents(adder, Utils.readContents(f));
        stagedForAdd.put(f.getName(), id);
        save();
    }

    public void remove(File f){
        String fileName = f.getName();
        if (stagedForAdd.containsKey(fileName)) {
            // stagedForAdd is keyed by file name, not by blob id.
            stagedForAdd.remove(fileName);
        } else {
            stagedForRemove.add(fileName);
        }
        save();
    }

    public void recoverHead(String filename){
        String currentCommitId = branches.get(HEAD);
        File currentCommitFile = Utils.join(COMMITS, currentCommitId);
        Commit currentCommit = Utils.readObject(currentCommitFile, Commit.class);
        TreeMap<String, String> snapshot = currentCommit.getSnapshot();
        String blobId = snapshot.get(filename);
        if (blobId == null) {
            Utils.message("File does not exist in that commit.");
            return;
        }
        File adder = Utils.join(BLOBS, blobId);//id == blob id
        byte[] contents = Utils.readContents(adder);
        File targetFile = Utils.join(CWD, filename);
        Utils.writeContents(targetFile, contents);
    }

    public void recoverCommit(String commitId, String filename){
        File CommitFile = Utils.join(COMMITS, commitId);
        Commit currentCommit = Utils.readObject(CommitFile, Commit.class);
        TreeMap<String, String> snapshot = currentCommit.getSnapshot();
        String blobId = snapshot.get(filename);
        if (blobId == null) {
            Utils.message("File does not exist in that commit.");
            return;
        }
        File adder = Utils.join(BLOBS, blobId);//id == blob id
        byte[] contents = Utils.readContents(adder);
        File targetFile = Utils.join(CWD, filename);
        Utils.writeContents(targetFile, contents);
    }

    public void printAllCommit(){
        String currentCommitId = branches.get(HEAD);

        while (currentCommitId != null) {
            File currentCommitFile = Utils.join(COMMITS, currentCommitId);
            Commit c = Utils.readObject(currentCommitFile, Commit.class);

            System.out.println("===");
            System.out.println("commit " + currentCommitId);
            System.out.println(c.getMessage());
            System.out.println();

            currentCommitId = c.getParent1_id();
        }
    }

    public void  merge(){
        for 
    }

    public void checkInitial() {
        if (!(GITLET_DIR.exists())) {
            System.err.println("Not in an initialized Gitlet directory.");
        }
    }

    public void clearStagingArea(){
        stagedForAdd.clear();
        stagedForRemove.clear();
        save();
    }

    private void save() {
        if (GITLET_DIR.exists()) {
            Utils.writeObject(STATE, this);
        }
    }
}
