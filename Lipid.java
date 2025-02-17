import java.util.HashMap;
import java.util.Scanner;

public class Lipid {
    Scanner sc = new Scanner(System.in);
    String lab = "Lipid";
    public HashMap<String,String> store(String patid) {
        HashMap<String,String> lipid = new HashMap<>();
        String tot, ldl, hdl, trig, nonhdl, ratio;
        System.out.println("Enter Total Cholesterol level(mg/dL)");
        tot = sc.nextLine();
        lipid.put("Total Cholesterol level",tot);
        System.out.println("Enter LDL level(mg/dL)");
        ldl = sc.nextLine();
        lipid.put("LDL",ldl);
        System.out.println("Enter HDL level(mg/dL)");
        hdl = sc.nextLine();
        lipid.put("HDL",hdl);
        System.out.println("Enter Triglycerides level(mg/dL)");
        trig = sc.nextLine();
        lipid.put("Triglycerides level",trig);
        System.out.println("Enter Non-HDL-C level(mg/dL)");
        nonhdl = sc.nextLine();
        lipid.put("Non-HDL-C level",nonhdl);
        System.out.println("Enter TG to HDL ratio(mg/dL)");
        ratio = sc.nextLine();
        lipid.put("TG to HDL ratio",ratio);
        return lipid; 
    }
    public void makeFile(String patid){
        RSA rsa = new RSA();
        HashMap <String,String> lipinfo = store(patid);
        rsa.StoreEncrypt(patid,lab,lipinfo);  
    }
    public void getFile(String patid){
        RSA rsa = new RSA();
        
        try {
            HashMap<String,String> data = rsa.GetDecrypt(patid, lab);
            System.out.println(data);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
