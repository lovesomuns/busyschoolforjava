import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.*;

public class Plogon implements ActionListener {

    JFrame jf;
    JTextField jtf;
    JPasswordField jpf;

    public Plogon() {
        jf = new JFrame("login");
        jf.setLayout(new GridLayout(5, 1));
        jf.add(new JPanel());
        JLabel jl1 = new JLabel("user name：");
        jtf = new JTextField(12);
        JPanel jp1 = new JPanel();
        jp1.add(jl1);
        jp1.add(jtf);
        jf.add(jp1);

        JLabel jl2 = new JLabel("passward： ");
        jpf = new JPasswordField(12);
        JPanel jp2 = new JPanel();
        jp2.add(jl2);
        jp2.add(jpf);
        jf.add(jp2);

        JPanel jp3 = new JPanel();
        JButton jb1 = new JButton("create new account");
        jb1.addActionListener(this);
        JButton jb2 = new JButton("login");
        jb2.addActionListener(this);
        JButton jb3 = new JButton("cancel");
        jb3.addActionListener(this);
        jp3.add(jb1);
        jp3.add(jb2);
        jp3.add(jb3);
        jf.add(jp3);

        jf.setResizable(false);
        jf.setVisible(true);
        jf.setSize(300, 200);
        jf.setLocation(300, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Plogon();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comm = e.getActionCommand();
        if (comm.equals("create new account")) { 
            jf.dispose();
            new Pregister();
        } else if (comm.equals("login")) {
            if ("".equals(jtf.getText())
            || "".equals(new String(jpf.getPassword()))
            || jpf.getPassword() == null) {
                JOptionPane.showConfirmDialog(
                    jf, 
                    "user name or password cannot empty!\n please enter again!", "error",
                    JOptionPane.DEFAULT_OPTION);
                jtf.setText(null);
                jpf.setText(null);
                jtf.requestFocus();
            } else {
                String s = jtf.getText() + "&&" + new String(jpf.getPassword());
                String name = jtf.getText() + "&&";
                File file = new File("D:\\reg.txt");
                try {
                    FileInputStream fis = new FileInputStream(file);
                    String s1 = "";
                    byte[] b = new byte[1024];
                    while (true) {
                        int i = fis.read(b);
                        if (i == -1)
                            break;
                        s1 = s1 + new String(b, 0, i);
                    }
                    fis.close();
                    int i = s1.indexOf(name);
                    int j = s1.indexOf(s);
                    if (i == -1) {
                        JOptionPane.showConfirmDialog(
                            jf, 
                            "no find user name!\n please relogin or create new account！", "error",
                            JOptionPane.ERROR_MESSAGE);
                        jtf.setText(null);
                        jpf.setText(null);
                        jtf.requestFocus();
                    } else {
                        if (j == -1) {
                            JOptionPane.showConfirmDialog(
                                jf,
                                "password wrong!\n eneter password agin！", "error",
                                JOptionPane.DEFAULT_OPTION);
                            jpf.setText(null);
                            jpf.requestFocus();
                        } else {
                            JOptionPane.showConfirmDialog(
                                jf,
                                "login now！\n" + "user name ： " + jtf.getText()
                                + "\n passward ： "
                                + new String(jpf.getPassword()),
                                "login result", JOptionPane.DEFAULT_OPTION);
                            new Phomepage();
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        } else if (comm.equals("cannel")) {
            System.exit(0);
        }

    }

}
