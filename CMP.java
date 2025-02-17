import java.util.HashMap;
import java.util.Scanner;

public class CMP {
    Scanner sc = new Scanner(System.in);
    String lab = "CMP";

    public HashMap<String, String> store(String patid) {
        HashMap<String, String> cmp = new HashMap<>();
        String ast, alt, bil, alp, prot, alb, gfr, bun, crea;
        System.out.println("Enter AST values(U/L)");
        ast = sc.nextLine();
        cmp.put("AST", ast);
        System.out.println("Enter ALT values(U/L)");
        alt = sc.nextLine();
        cmp.put("ALT values", alt);
        System.out.println("Enter ALP values(U/L)");
        alp = sc.nextLine();
        cmp.put("ALP", alp);
        System.out.println("Enter Bilirubin values(mg/dL)");
        bil = sc.nextLine();
        cmp.put("Bilirubin", bil);
        System.out.println("Enter Total Protein values(g/dL)");
        prot = sc.nextLine();
        cmp.put("Total Protein", prot);
        System.out.println("Enter Albumin values(g/dL)");
        alb = sc.nextLine();
        cmp.put("Albumin", alb);
        System.out.println("Enter GFR values(ml/min)");
        gfr = sc.nextLine();
        cmp.put("GFR values", gfr);
        System.out.println("Enter BUN values(mg/dL)");
        bun = sc.nextLine();
        cmp.put("BUN", bun);
        System.out.println("Enter Creatinine values(mg/dL)");
        crea = sc.nextLine();
        cmp.put("Creatinine", crea);
        return cmp;
    }

    public void makeFile(String patid) {
        RSA rsa = new RSA();
        HashMap<String, String> cmpinfo = store(patid);
        rsa.StoreEncrypt(patid, lab, cmpinfo);
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
