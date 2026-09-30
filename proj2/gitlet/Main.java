package gitlet;

import java.io.File;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */


    public static void main(String[] args) {
        // TODO: what if args is empty?
        String firstArg = args[0];
        Repository repo = new Repository();
        switch(firstArg) {
            case "init":
                // TODO: handle the `init` command
                repo.init();
                break;
            case "add":
                repo.checkInitial();
                validateNumArgs("Incorrect operands.", args, 2);
                repo.add(new File(args[1]));
                // TODO: handle the `add [filename]` command
                break;
            case "rm":
                repo.checkInitial();
                validateNumArgs("Incorrect operands.", args, 2);
                repo.remove(new File(args[1]));
                break;
            case "commit":
                repo.checkInitial();
                validateNumArgs("Please enter a commit message.",args, 2);
                repo.makeNewCommit(args[1]);
                repo.clearStagingArea();
                break;
            case "checkout":
                repo.checkInitial();
                if (args.length == 3 && args[1].equals("--")) {
                    // checkout -- file
                    repo.recoverHead(args[2]);
                } else if (args.length == 4 && args[2].equals("--")) {
                    // checkout commitId -- file
                    repo.recoverCommit(args[1],args[3]);
                } else if (args.length == 2) {
                    // checkout branch
                    repo.makeNewBranch(args[1]);
                } else {
                    throw new RuntimeException("Incorrect operands");
                }
                break;
            case "log":
                repo.checkInitial();
                repo.printAllCommit();
                break;
            case "merge":
                break;
        }
    }

    public static void validateNumArgs(String errMessage, String[] args, int n) {
        if (args.length != n) {
            throw new RuntimeException(errMessage);
        }
    }
}
