package gitlet;

// TODO: any imports you need here

import java.io.Serializable;
import java.util.Date; // TODO: You'll likely use this in this class
import java.util.TreeMap;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    private String parent1_id;
    private String parent2_id;
    private Date timestamp;
    /** Snapshot tracked by this commit: file name -> blob id. */
    private TreeMap<String, String> snapshot;

    public String getMessage() {
        return message;
    }

    public String getParent1_id() {
        return parent1_id;
    }

    public String getParent2_id() {
        return parent2_id;
    }

    public TreeMap<String, String> getSnapshot(){
        return snapshot;
    }

    public Commit() {
        message = "initial commit";
        parent1_id = null;
        parent2_id = null;
        timestamp = new Date(0);
        snapshot = new TreeMap<>();
    }
    public Commit(String message, String parent1_id,
                  TreeMap<String, String> snapshot){
        this.message = message;
        this.parent1_id = parent1_id;
        this.parent2_id = null;
        this.timestamp = new Date();
        this.snapshot = snapshot;
    }

    /* TODO: fill in the rest of this class. */
}
