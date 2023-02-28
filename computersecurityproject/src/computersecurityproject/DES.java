package computersecurityproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES {

    private byte[] writeBytes;
    private byte[] reedBytes;
    private SecretKey DESKey;
    private Cipher encryptionCipher;
    private Cipher decryptionCipher;
    private FileInputStream inputFile;
    private FileOutputStream outputFile;
    private File in;
    private File out;
    public int counter = 0;
    public boolean badKey = false;
    

    public DES(String hexStringKey, String urlIn) throws Exception {
        in = new File(urlIn); 
        inputFile = new FileInputStream(in); 
        reedBytes = new byte[(int)in.length()];
        Read();
        initializeKey(hexStringKey);

        encryptionCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, DESKey);

        decryptionCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        decryptionCipher.init(Cipher.DECRYPT_MODE, DESKey);
    }

    public void encryption() throws Exception {
        writeBytes = encryptionCipher.doFinal(reedBytes);
        do {
            out = new File(in.getParent() +"\\" + in.getName().substring(0, in.getName().lastIndexOf(".")) + (counter) + ".encrypted");
            counter++;
        } while (out.exists());
        outputFile = new FileOutputStream(out);
        write();
    }

    public void decryption() throws IOException, IllegalBlockSizeException {
        badKey = false;
        try{
            writeBytes = decryptionCipher.doFinal(reedBytes);
            do {
                out = new File(in.getParent() +"\\" + in.getName().substring(0, in.getName().lastIndexOf(".")) + (counter) + ".txt");
                counter++;
            } while (out.exists());
            outputFile = new FileOutputStream(out);
            write();
        }catch(BadPaddingException e){
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
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        DESKey = skf.generateSecret(new DESKeySpec(hexKey));
    }

    public String getOut(){
        return out.getName();
    }
}