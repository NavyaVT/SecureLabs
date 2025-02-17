import java.util.Scanner;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Login {
    int userlogin(String uname, String pass) {
        int flag = 1;

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("login.json"));
            JSONObject json = (JSONObject) obj;

            String og = (String) json.get(uname);
            
            if (og.equals(SHA.toHexString(SHA.getSHA(pass)))) {
                System.out.print("Login success");
                return 1;
            }
        } catch (Exception e) {
            System.out.println(e);
            flag = -1;
        }
        return flag;
    }

    int login(String uname, String pass){
        Login m = new Login();
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int flag = m.userlogin(uname, pass);
        if (flag == 1) {
            if (uname.equals("Lipid") ) {
                
                String patid,choice;
                System.out.println("-------------LIPID PROFILE TEST-------------");
                System.out.println("Enter Patient ID:");
                patid = sc.nextLine();
                System.out.println("Do you wanna entry data or view data? :");
                choice = sc.nextLine();
                Lipid lipid = new Lipid();
                if(choice.equals("entry")) {
                    lipid.makeFile(patid);
                }
                else if (choice.equals("view")) {
                    lipid.getFile(patid);
                }
                return 1;
            }
            else if (uname.equals("VitB")) {
                String patid,choice;
                System.out.println("-------------VITAMIN B TEST-------------");
                System.out.println("Enter Patient ID:");
                patid = sc.nextLine();
                System.out.println("Do you wanna entry data or view data? :");
                choice = sc.nextLine();
                VitB vitb = new VitB();
                if(choice.equals("entry")) {
                    vitb.makeFile(patid);
                }
                else if (choice.equals("view")) {
                    vitb.getFile(patid);
                }
                return 2;
            }
            else if (uname.equals("VitMin")) {
                String patid,choice;
                System.out.println("-------------VITAMIN AND MINERALS TEST-------------");
                System.out.println("Enter Patient ID:");
                patid = sc.nextLine();
                System.out.println("Do you wanna entry data or view data? :");
                choice = sc.nextLine();
                VitMin vitmin = new VitMin();
                if(choice.equals("entry")) {
                    vitmin.makeFile(patid);
                }
                else if (choice.equals("view")) {
                    vitmin.getFile(patid);
                }
                return 3;
            }
            else if (uname.equals("CBC")) {
                String patid,choice;
                System.out.println("-------------COMPLETE BLOOD COUNT-------------");
                System.out.println("Enter Patient ID:");
                patid = sc.nextLine();
                System.out.println("Do you wanna entry data or view data? :");
                choice = sc.nextLine();
                CBC cbc = new CBC();
                if(choice.equals("entry")) {
                    cbc.makeFile(patid);
                }
                else if (choice.equals("view")) {
                    cbc.getFile(patid);
                }
                return 4;
            }
            else if (uname.equals("CMP")) {
                String patid,choice;
                System.out.println("-------------COMPHENSIVE METABOLIC PANEL-------------");
                System.out.println("Enter Patient ID:");
                patid = sc.nextLine();
                System.out.println("Do you wanna entry data or view data? :");
                choice = sc.nextLine();
                CMP cmp = new CMP();
                if(choice.equals("entry")) {
                    cmp.makeFile(patid);
                }
                else if (choice.equals("view")) {
                    cmp.getFile(patid);
                }
                return 5;
            }
            else if (uname.equals("Diabetic")) {
                String patid,choice;
                System.out.println("-------------DIABETIC LEVEL TEST-------------");
                System.out.println("Enter Patient ID:");
                patid = sc.nextLine();
                System.out.println("Do you wanna entry data or view data? :");
                choice = sc.nextLine();
                Diabetic dia= new Diabetic();
                if(choice.equals("entry")) {
                    dia.makeFile(patid);
                }
                else if (choice.equals("view")) {
                    dia.getFile(patid);
                }
                return 6;
            }
        } else {
            System.out.println("User not found");
            return -1;
        }
        return flag;

    }
    public static void main(String args[]) {
        Login m = new Login();
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        //String uname, pass;

        System.out.println("Enter username:");
        //uname = sc.nextLine();
        System.out.println("Enter password:");
        //pass = sc.nextLine();
        //int flag = m.userlogin(uname, pass);
        //System.out.println(flag);
        
    }
}
