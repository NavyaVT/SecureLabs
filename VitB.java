import java.util.HashMap;
import java.util.Scanner;

public class VitB {
    Scanner sc = new Scanner(System.in);
    String lab = "VitB";

    public HashMap<String, String> store(String patid) {
        HashMap<String, String> vitb = new HashMap<>();
        String b1, b2, b3, b5, b12, b6, b7;
        System.out.println("Enter B1 values(mg/dL)");
        b1 = sc.nextLine();
        vitb.put("b1", b1);
        System.out.println("Enter B2 values(mg/dL)");
        b2 = sc.nextLine();
        vitb.put("b2", b2);
        System.out.println("Enter B3 values(mg/dL)");
        b3 = sc.nextLine();
        vitb.put("b3", b3);
        System.out.println("Enter B5 values(mg/dL)");
        b5 = sc.nextLine();
        vitb.put("b5", b5);
        System.out.println("Enter B6 values(mg/dL)");
        b6 = sc.nextLine();
        vitb.put("b6", b6);
        System.out.println("Enter B12 values(mg/dL)");
        b12 = sc.nextLine();
        vitb.put("b12", b12);
        System.out.println("Enter B7 values(mg/dL)");
        b7 = sc.nextLine();
        vitb.put("b7", b7);
        return vitb;
    }

    public void makeFile(String patid) {
        RSA rsa = new RSA();
        HashMap<String, String> vitbinfo = store(patid);
        rsa.StoreEncrypt(patid, lab, vitbinfo);
    }

    public void getFile(String patid) {
        RSA rsa = new RSA();

        try {
            HashMap<String,String> data = rsa.GetDecrypt(patid, lab);
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
