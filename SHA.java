import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import org.json.JSONObject;
//import java.io.FileWriter; 

public class SHA {
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        try {
            map.put("Lipid", toHexString(getSHA("LipoLove")));
            map.put("VitB", toHexString(getSHA("VitbBoom")));
            map.put("VitMin", toHexString(getSHA("VitManic")));
            map.put("CBC", toHexString(getSHA("TheBloodOnes")));
            map.put("CMP", toHexString(getSHA("ProteinPow")));
            map.put("Diabetic", toHexString(getSHA("DiaSweet")));
            map.put("Admin", toHexString(getSHA("Labalaba")));
        } catch (Exception e) {
            System.out.println(e);
        }
        JSONObject json = new JSONObject(map);
        System.out.println(json.toString());
/*
        try (FileWriter writer = new FileWriter("myfile.json")) {
            writer.write(json.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
