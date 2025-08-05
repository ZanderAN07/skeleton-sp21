package gitlet;

import static gitlet.Repository.FileFinder;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        Repository repo = new Repository();
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                // TODO: handle the `init` command
                repo.init();
                break;
            case "add":
                repo.checkInital();
                //把某一个特定的file加入staging area这个file
                repo.add(FileFinder(Repository.CWD, args[1]));//TODO：在cwd里面搜索这个文件
                break;
            // TODO: FILL THE REST IN
            case "commit":
                // 需要检测是否有commit message，throw error
                repo.checkInital();
                validateNumArgs("Please enter a commit message.",args, 2);
                repo.makeNewCommit(args[1]);
                repo.clearStagingArea();
                break;
            case "rm":
                repo.checkInital();
                //check num args?
                repo.remove(FileFinder(Repository.CWD, args[1]));//TODO:Search that file in CWD
            case "branch":
                repo.checkInital();
                //check num args?
                repo.makeNewBranch(args[1]);
                break;

            case "rm-branch":
                repo.checkInital();
                //new method tobe added
                break;

            case "log":
                repo.checkInital();
                break;
            default:
                System.out.println("No command with that name exists.");
            }
        }
    public static void validateNumArgs(String errMessage, String[] args, int n) {
        if (args.length != n) {
            throw new RuntimeException(errMessage);
        }
    }
}

