package capers;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;

import static capers.Utils.*;

/** A repository for Capers 
 * @author TODO
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 * TODO: change the above structure if you do something different.
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = join( ".capers"); // TODO Hint: look at the `join`
                                            //      function in Utils

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {
        // 确保capers/.capers目录存在
        CAPERS_FOLDER.mkdirs();
        File fol1 = new File(CAPERS_FOLDER,"dogs");
        File fol2 = new File(CAPERS_FOLDER,"story.txt");
        try{
            fol1.mkdir();
            fol2.createNewFile();
        }
        catch (IOException e){
            System.out.println("文件操作失败: " + e.getMessage());
        }
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        //复写？
        File f = new File(CAPERS_FOLDER,"story.txt");
        Utils.writeContents(f, Utils.readContentsAsString(f) + text + "\n");
        System.out.println(Utils.readContentsAsString(f));
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        Dog current = new Dog(name, breed, age);
        current.saveDog();
        System.out.println(current.toString());

    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        Dog.fromFile(name).haveBirthday();
        // TODO

    }
}
