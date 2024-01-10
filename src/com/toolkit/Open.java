package root.toolkit;
import java.io.*;
import java.nio.file.*;
public class Open {
    Path PATH = null;
    File FILE = null;
    BufferedWriter bufferedWriter = null;
    BufferedReader bufferedReader = null;
    public Open(String filePath, char mode) throws IOException {
        FileSystem fs = FileSystems.getDefault();
        PATH = fs.getPath(filePath);
        FILE = new File(PATH.toUri());
        if (mode == 'w') {
            bufferedWriter = new BufferedWriter(new FileWriter(FILE));
        }
        else if (mode == 'r') {
            bufferedReader = new BufferedReader(new FileReader(FILE));
        }
        else {
            bufferedWriter = new BufferedWriter(new FileWriter(FILE));
            bufferedReader = new BufferedReader(new FileReader(FILE));
        }
    }
    public static String getSeparator() {
        FileSystem fs = FileSystems.getDefault();
        return fs.getSeparator();
    }
    public void write(String text) throws IOException {
        bufferedWriter.write(text);
    }
    public String readLine() throws IOException {
        return bufferedReader.readLine();
    }
    public void close() throws IOException {
        if (bufferedReader != null) {bufferedReader.close();}
        if (bufferedWriter != null) {bufferedWriter.close();}
    }
}
