package computersecurityproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Scanner;

import javax.xml.bind.DatatypeConverter;

public class Hash {

    private MessageDigest digest;
    private String input;
    private byte[] hash;
    private File inputFile;
    private File outputFile;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    public static int counter = 0;

    public Hash(String url) throws Exception {
        inputFile = new File(url);

        fileInputStream = new FileInputStream(inputFile);

        Read();
    }

    public void SHA256() throws Exception {
        digest = MessageDigest.getInstance("SHA-256");
        hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        write();
    }

    public void SHA512() throws Exception {
        digest = MessageDigest.getInstance("SHA-512");
        hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        write();
    }

    private void Read() throws Exception {
        Scanner fileInput = new Scanner(fileInputStream);
        input = "";
        while(fileInput.hasNextLine()){
            input = input.concat(fileInput.nextLine());
            if(fileInput.hasNextLine()){
                input = input.concat("\n");
            }
        }
        fileInput.close();
    }


    private void write() throws Exception {
        do {
            outputFile = new File(inputFile.getParent() + "\\"+ inputFile.getName().substring(0, inputFile.getName().lastIndexOf('.')) + counter + ".msgdigest");
            counter++;
        } while (outputFile.exists());
        fileOutputStream = new FileOutputStream(outputFile);
        byte[] output = DatatypeConverter.printHexBinary(hash).getBytes();
        fileOutputStream.write(output);
        fileOutputStream.close();
    }

    public String getOut(){
        return outputFile.getName();
    }
}