package driches.feelsbook.data.disk;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class FileManager {

    // FileManager singleton
    private static final FileManager fileManager = new FileManager();

    // Private constructor to prevent more than one FileManager from being created
    private FileManager() {};

    // Static method fro retrieving FileManager singleton
    public static FileManager getFileManager() {
        return fileManager;
    }

    // Reads entire file into a string object
    public String loadFile(File fileToRead) {
        StringBuilder fileStringBuilder = new StringBuilder();

        if (fileToRead.exists()) {
            String lastReadLine = null;

            try {
                FileReader fileReader = new FileReader(fileToRead);
                BufferedReader fileBufferedReader = new BufferedReader(fileReader);
                
                while ((lastReadLine = fileBufferedReader.readLine()) != null) {
                    fileStringBuilder.append(lastReadLine + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            return null;
        }

        return fileStringBuilder.toString();
    }

    // Takes String object and save to file
    //  - If file exsists if will be overwritten
    public boolean saveFile(File fileToWrite, String stringToWrite) {

        try {
            FileOutputStream out = new FileOutputStream(fileToWrite);

            out.write(stringToWrite.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
}