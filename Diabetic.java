import java.util.HashMap;
import java.util.Scanner;

public class Diabetic {
    Scanner sc = new Scanner(System.in);
    String lab = "Diabetic";

    public HashMap<String, String> store(String patid) {
        HashMap<String, String> diabetic = new HashMap<>();
        String fru, aglu, suc, bglu, ins, liver;

        System.out.println("Enter Fructose values(ml/min)");
        fru = sc.nextLine();
        diabetic.put("Fructose", fru);
        System.out.println("Enter Sucrose values(ml/min)");
                suc = sc.nextLine();
        diabetic.put("Sucrose", suc);
        System.out.println("Enter Glucose after food values(ml/min)");
                aglu = sc.nextLine();
        diabetic.put("Glucose after food", aglu);
        System.out.println("Enter Glucose before food values(ml/min)");
                bglu = sc.nextLine();
        diabetic.put("Glucose before food", bglu);
        System.out.println("Enter Insuline values (IU/dl)");
                ins = sc.nextLine();
        diabetic.put("Insuline", ins);
        System.out.println("Is the Liver functioning Good? Y/N");
                liver = sc.nextLine();
        diabetic.put("Liver functioning", liver);
        return diabetic;
    }

    public void makeFile(String patid) {
        RSA rsa = new RSA();
        HashMap<String, String> diainfo = store(patid);
        rsa.StoreEncrypt(patid, lab, diainfo);
    }

    public void getFile(String patid) {
        RSA rsa = new RSA();

        try {
            HashMap<String, String> data = rsa.GetDecrypt(patid, lab);
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}