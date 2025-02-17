import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui {
    private int flag = 0;

    gui() {

        Frame frame = new Frame();
        Frame frame2 = new Frame();

        Label username = new Label("Enter User Name:");
        username.setBounds(50, 50, 100, 20);
        TextField uname = new TextField();
        uname.setBounds(50, 90, 100, 20);
        Label password = new Label("Enter Password:");
        password.setBounds(50, 130, 100, 20);
        TextField pass = new TextField();
        pass.setBounds(50, 170, 100, 20);

        Button submit = new Button("SUBMIT");
        submit.setBounds(80, 220, 70, 40);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login m = new Login();
                flag = m.login(uname.getText(), pass.getText());
                uname.setText(null);
                pass.setText(null);

                frame2.setLayout(null);
                frame2.setSize(500, 500);
                frame2.setBackground(Color.blue);
                Label title = new Label();
                if (flag == 1) {
                    title.setText("LIPID PROFILE TEST");
                } else if (flag == 2) {
                    title.setText("VITAMIN B TEST");
                } else if (flag == 3) {
                    title.setText("VITAMIN AND MINERALS TEST");
                } else if (flag == 4) {
                    title.setText("COMPLETE BLOOD COUNT");
                } else if (flag == 5) {
                    title.setText("COMPHENSIVE METABOLIC PANEL");
                } else if (flag == 6) {
                    title.setText("DIABETIC LEVEL TEST");
                } else {
                    title.setText("USERNAME/PASSWORD IS INCORRECT");
                }
                title.setBounds(70, 50, 250, 40);
                Label patid = new Label("Enter Patient ID:");
                patid.setBounds(50, 90, 100, 20);
                TextField pid = new TextField();
                pid.setBounds(50, 130, 100, 20);
                Label choice = new Label("Do you wanna entry data or view data?");
                choice.setBounds(50, 170, 100, 20);
                Choice ch = new Choice();
                ch.add("view");
                ch.add("entry");
                ch.setBounds(50, 210, 100, 20);

                Button submit2 = new Button("SUBMIT");
                submit2.setBounds(80, 260, 70, 40);
                submit2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(ch.getSelectedItem());
                    }
                });

                frame2.add(title);
                frame2.add(patid);
                frame2.add(pid);
                frame2.add(choice);
                frame2.add(ch);

                frame2.setVisible(true);
            }
        });

        frame.add(username);
        frame.add(uname);
        frame.add(password);
        frame.add(pass);
        frame.add(submit);
        frame.setLayout(null);
        frame.setSize(500, 500);
        frame.setBackground(Color.pink);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new gui();
    }
}
