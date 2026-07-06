import java.io.*;

class FileManager {

    public static String readFile(String path) {

        StringBuilder content = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;


            while ((line = br.readLine()) != null) {
                content.append(line);
                
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        return content.toString();
    }

    public static void writeFile(String path, String content) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }
}