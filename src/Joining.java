import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Joining extends JFrame implements ActionListener {
    //variables (=field)
    JPanel panel = new JPanel();
    JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel idL = new JLabel("ID", JLabel.CENTER); // id
    JTextField id = new JTextField(10);
    JPanel pwPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel pwL = new JLabel("PASSWORD", JLabel.CENTER); //pw
    JPanel exP = new JPanel();
    JLabel pwex = new JLabel("(PW: 2 exclamation mark + 2 number + 2 alphabet)", JLabel.CENTER);
    JPasswordField pw = new JPasswordField(10);
    JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel nameL = new JLabel("USER NAME", JLabel.CENTER); //name
    JTextField name = new JTextField(10);
    JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel genderL = new JLabel("GENDER");
    JRadioButton rb1 = new JRadioButton("F");
    JRadioButton rb2 = new JRadioButton("M");
    JPanel btP=new JPanel();
    JButton bt_confirm = new JButton("CONFIRM");
    Main m = null;
    String ugender = "";
    DataBase jdb = new DataBase();
    int alphabet_count; // alphabet check
    int excalm_count; // 특수문자 check
    int num_count; // 숫자 check

    Joining() {

        setTitle("SIGNING-UP");
        panel.setLayout(new GridLayout(6, 1));

        idPanel.add(idL);
        idPanel.add(id);

        pwPanel.add(pwL);
        exP.add(pwex);
        pwPanel.add(pw);

        namePanel.add(nameL);
        namePanel.add(name);

        genderPanel.add(genderL);
        ButtonGroup selected_g = new ButtonGroup();
        selected_g.add(rb1);
        selected_g.add(rb2);
        genderPanel.add(rb1);
        genderPanel.add(rb2);

        btP.add(bt_confirm);
        bt_confirm.setBackground(new Color(204,160,240));


        panel.add(idPanel);
        panel.add(pwPanel);
        panel.add(exP);
        panel.add(namePanel);
        panel.add(genderPanel);
        panel.add(btP);
        bt_confirm.addActionListener(this);
        rb1.addActionListener(this);
        rb2.addActionListener(this);

        setSize(300, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        String uid = id.getText();
        String upw = pw.getText();
        char[] c = upw.toCharArray();

        alphabet_count = 0;
        for (int i=0; i<c.length; i++){
            if((c[i]>='A' && c[i]<='Z')||(c[i]>='a' && c[i]<='z'))
                alphabet_count++;
        }
        System.out.println(c);

        excalm_count = 0;
        for (int i=0; i<c.length; i++){
            if((c[i]>='!' && c[i]<='/')||(c[i]>='^' && c[i]<='_'))
                excalm_count++;
        }

        num_count=0;
        for (int i=0; i<c.length; i++){
            if(c[i]>='0' && c[i]<='9')
                num_count++;
        }

        String uname = name.getText();
        if (s.equals("F") || s.equals("M")) {
            ugender = s;
        }

        if (s.equals("CONFIRM")) {
            if(upw.length() != 6 || upw.contains(" ")) {
                JOptionPane.showMessageDialog(null, "Your PW should be 2 numbers & 2 Alphabet & 2 Exclamation Mark");
            }else if(alphabet_count != 2){
                JOptionPane.showMessageDialog(null, "Your PW should be 2 numbers & 2 Alphabet & 2 Exclamation Mark");
            } else if(excalm_count != 2){
                JOptionPane.showMessageDialog(null, "Your PW should be 2 numbers & 2 Alphabet & 2 Exclamation Mark");
            } else if(num_count != 2){
                JOptionPane.showMessageDialog(null, "Your PW should be 2 numbers & 2 Alphabet & 2 Exclamation Mark");
            } else if (uid.equals("") || upw.equals("") || uname.equals("") || ugender.equals("")) {
                JOptionPane.showMessageDialog(null, "Please insert all information.");
            } else if (!uid.equals("") && !upw.equals("") && !uname.equals("") && !ugender.equals("")) {
                if (jdb.joinCheck(uid, upw, uname, ugender) == 1) {
                    JOptionPane.showMessageDialog(null, "CONFIRMED");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "PLEASE TRY AGAIN");
                }
            }
        }
    }
}

