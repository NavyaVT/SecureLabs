import java.util.HashMap;
import java.util.Scanner;

public class VitMin {
    Scanner sc = new Scanner(System.in);
    String lab = "VitMin";

    public HashMap<String, String> store(String patid) {
        HashMap<String, String> vitmin = new HashMap<>();
        String c, d, k, cal, pot, a, e, sod;
        System.out.println("Enter Vitamin C values(mg/dL)");
        c = sc.nextLine();
        vitmin.put("Vitamin C", c);
        System.out.println("Enter Vitamin A values(mg/dL)");
        a = sc.nextLine();
        vitmin.put("Vitamin A", a);
        System.out.println("Enter Vitamin D values(mg/dL)");
        d = sc.nextLine();
        vitmin.put("Vitamin D", d);
        System.out.println("Enter Vitamin K values(mg/dL)");
        k = sc.nextLine();
        vitmin.put("Vitamin K", k);
        System.out.println("Enter Vitamin E values(mg/dL)");
        e = sc.nextLine();
        vitmin.put("Vitamin E", e);
        System.out.println("Enter Potasium values(mmol/L)");
        pot = sc.nextLine();
        vitmin.put("Potasium", pot);
        System.out.println("Enter Calsium values(mmol/L)");
        cal = sc.nextLine();
        vitmin.put("Calsium", cal);
        System.out.println("Enter Sodium values(mmol/L)");
        sod = sc.nextLine();
        vitmin.put("Sodium", sod);
        return vitmin;
    }

    public void makeFile(String patid) {
        RSA rsa = new RSA();
        HashMap<String, String> vitmininfo = store(patid);
        rsa.StoreEncrypt(patid, lab, vitmininfo);
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
