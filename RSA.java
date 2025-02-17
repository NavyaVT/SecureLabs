import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
//import org.json.simple.parser.*;
import org.json.simple.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.annotation.*;

import javax.crypto.Cipher;

public class RSA {
    public static void main(String[] args) {
        System.out.println("RSA FILE");
        
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public static void storeKeyPair(KeyPair keyPair, String publicKeyFile, String privateKeyFile) throws IOException {
        try (FileOutputStream publicKeyStream = new FileOutputStream(publicKeyFile);
                FileOutputStream privateKeyStream = new FileOutputStream(privateKeyFile)) {
            byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
            byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
            publicKeyStream.write(publicKeyBytes);
            privateKeyStream.write(privateKeyBytes);
        }
    }

    private String getPrivateKeyff(String privateKeyFile) throws Exception {
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(privateKeyFile));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return Base64.getEncoder().encodeToString(keyFactory.generatePrivate(keySpec).getEncoded());
    }

    /*private String getPublicKeyff(String publicKeyFile) throws Exception {
        String publicKeyBytes = Files.readString(Paths.get(publicKeyFile));
        PublicKey key = getPublicKey(publicKeyBytes);
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }*/

    public String encrypt(String data, String publicKeyStr) throws Exception {
        PublicKey publicKey = getPublicKey(publicKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedData, String privateKeyStr) throws Exception {
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private static PublicKey getPublicKey(String publicKeyStr) throws Exception {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    private static PrivateKey getPrivateKey(String privateKeyStr) throws Exception {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    private static String getKeyAsString(Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public void StoreEncrypt(String patid, String lab, HashMap<String, String> dict) {
        RSA rsa = new RSA();
        HashMap<String, String> dictt = new HashMap<>();

        if (!Files.exists(Paths.get("Files//" + lab + patid + ".json"))) {
            try {
                KeyPair keyPair = generateKeyPair();
                storeKeyPair(keyPair, "Keys//" + lab + patid + "pub.key", "Keys//" + lab + patid + "priv.key");
                String publicKeyStr = getKeyAsString(keyPair.getPublic());
                for (Map.Entry<String, String> e : dict.entrySet()) {
                    String enckey = rsa.encrypt(e.getKey(), publicKeyStr);
                    String encValue = rsa.encrypt(e.getValue(), publicKeyStr);
                    dictt.put(enckey, encValue);
                }
                // System.out.println(dictt);

                HashMap<String, HashMap<String, String>> jsonn = new HashMap<>();
                jsonn.put(lab, dictt);
                JSONObject json = new JSONObject(jsonn);
                try (FileWriter writer = new FileWriter("Files//" + lab + patid + ".json")) {
                    writer.write(json.toString());
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
    }

    public HashMap<String,String> GetDecrypt(String patid, String lab) throws FileNotFoundException, IOException {
        RSA rsa = new RSA();
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> LabMap = new HashMap<>();
        HashMap<String, String> newLabMap = new HashMap<>();

        if (Files.exists(Paths.get("Keys//" + lab + patid + "priv.key")) && Files.exists(Paths.get("Keys//" + lab + patid + "pub.key"))) {
            try {
                HashMap<String, HashMap<String, String>> outerMap = mapper.readValue(new File("Files//" + lab + patid + ".json"), new TypeReference<HashMap<String, HashMap<String, String>>>(){});
                LabMap = outerMap.get(lab);
            } catch (Exception e) {
               System.out.println(e);
            }
            
            try {
                for (Map.Entry<String, String> e : LabMap.entrySet()) {
                    String privateKeyStr = rsa.getPrivateKeyff("Keys//" + lab + patid + "priv.key");
                    String decryptedKey = rsa.decrypt(e.getKey(), privateKeyStr);
                    String decryptedValue = rsa.decrypt(e.getValue(), privateKeyStr);
                    newLabMap.put(decryptedKey, decryptedValue);
                    // LabMap.remove(e.getKey());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return newLabMap;
        }
        else {
            System.out.println("The key files are not found!");
        }
        return newLabMap;
    }
    
}
