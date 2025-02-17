import java.util.HashMap;
import java.util.Scanner;

public class CBC {
    Scanner sc = new Scanner(System.in);
    String lab = "CBC";

    public HashMap<String, String> store(String patid) {
        HashMap<String, String> cbc = new HashMap<>();
        String rbc, wbc, pla, hem, baso, lym, easo, mono, neu;
        System.out.println("Enter Hemoglobin values(g/dL)");
        hem = sc.nextLine();
        cbc.put("Hemoglobin", hem);
        System.out.println("Enter RBC Count(mill/cumm)");
        rbc = sc.nextLine();
        cbc.put("RBC Count", rbc);
        System.out.println("Enter WBC Count(mill/cumm)");
        wbc = sc.nextLine();
        cbc.put("WBC Count", wbc);
        System.out.println("Enter Neutrophils Count(mill/cumm)");
        neu = sc.nextLine();
        cbc.put("Neutrophils", neu);
        System.out.println("Enter Lymphocytes Count(mill/cumm)");
        lym = sc.nextLine();
        cbc.put("Lymphocytes", lym);
        System.out.println("Enter Eosinophils Count(mill/cumm)");
        easo = sc.nextLine();
        cbc.put("Eosinophils", easo);
        System.out.println("Enter Monocytes Count(mill/cumm)");
        mono = sc.nextLine();
        cbc.put("Monocytes", mono);
        System.out.println("Enter Basophils Count(mill/cumm)");
        baso = sc.nextLine();
        cbc.put("Basophils", baso);
        System.out.println("Enter Platelets Count(mill/cumm)");
        pla = sc.nextLine();
        cbc.put("Platelets", pla);
        return cbc;
    }

    public void makeFile(String patid) {
        RSA rsa = new RSA();
        HashMap<String, String> cbcinfo = store(patid);
        rsa.StoreEncrypt(patid, lab, cbcinfo);
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
