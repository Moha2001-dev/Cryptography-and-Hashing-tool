package computersecurityproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    private byte[] writeBytes;
    private byte[] reedBytes;
    private SecretKeySpec AESKey;
    private Cipher encryptionCipher;
    private Cipher decryptionCipher;
    private FileInputStream inputFile;
    private FileOutputStream outputFile;
    private File in;
    private File out;
    public int counter = 0;
    public boolean badKey = false;

    public AES(String hexStringKey, String urlIn) throws Exception {
        in = new File(urlIn);
        inputFile = new FileInputStream(in);
        reedBytes = new byte[(int)in.length()];
        Read();
        initializeKey(hexStringKey);

        encryptionCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, AESKey);

        decryptionCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        decryptionCipher.init(Cipher.DECRYPT_MODE, AESKey);
    }

    public void encryption() throws Exception {
        do {
            out = new File(in.getParent() +"\\" +  in.getName().substring(0, in.getName().lastIndexOf(".")) + (counter) + ".encrypted");
            counter++;
        } while (out.exists());
        outputFile = new FileOutputStream(out);
        writeBytes = encryptionCipher.doFinal(reedBytes);
        write();
    }

    public void decryption() {
        badKey = false;
        try{
            writeBytes = decryptionCipher.doFinal(reedBytes);
            do {
                out = new File(in.getParent() +"\\" +  in.getName().substring(0, in.getName().lastIndexOf("."))+(counter) + ".txt");
                counter++;
            } while (out.exists());
            outputFile = new FileOutputStream(out);
        write();
    }catch(Exception e){
        badKey = true;
        }
    }

    private void write() throws IOException {
        outputFile.write(writeBytes);
        outputFile.close();
    }

    private void Read() throws Exception {
        inputFile.read(reedBytes);
        inputFile.close();
    }

    private void initializeKey(String key) throws Exception {
        byte[] hexKey = key.getBytes();
        AESKey = new SecretKeySpec(hexKey, "AES");
    }

    public String getOut(){
        return out.getName();
    }
}
