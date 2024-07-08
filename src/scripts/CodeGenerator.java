import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CodeGenerator {
    public static void main(String[] args) throws IOException {
        String path = args[0];
        System.out.println("Generating Code files in the following directory: " + path);
        File file = new File(path, "Testw.java");
        FileUtils.writeStringToFile(file, "public class Testw { }", "UTF-8");
    }
}
