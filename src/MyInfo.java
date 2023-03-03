import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyInfo extends JFrame implements ActionListener {
    JPanel jpanel = new JPanel();
    JPanel idP = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel idL = new JLabel("ID : " + Login.uid, JLabel.CENTER);
    JPanel newpwP = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel newpwL = new JLabel("NEW PW :", JLabel.CENTER);
    JPasswordField newpw_text = new JPasswordField(10);
    JPanel newnameP = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel newnameL = new JLabel("NEW NAME :", JLabel.CENTER);
    JTextField newname_text = new JTextField(10);
    JPanel gdP = new JPanel(new FlowLayout(FlowLayout.CENTER));
    DataBase db = new DataBase();
    String ugd = db.infoChange(Login.uid);
    JLabel gdL = new JLabel("GENDER : " + ugd, JLabel.CENTER);
    JPanel btnP = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton btn_change = new JButton("CHANGE");
    JButton btn_resign = new JButton("RESIGN");
    JButton btn_logout=new JButton("LOGOUT");
    int alphabet_count; // alphabet check
    int excalm_count; // 특수문자 check
    int num_count; // 숫자 check


    MyInfo() {
        setTitle("CHANGE MY INFORMATION");
        jpanel.setLayout(new GridLayout(5, 1));

        idP.add(idL);
        newpwP.add(newpwL);
        newpwP.add(newpw_text);
        newnameP.add(newnameL);
        newnameP.add(newname_text);
        gdP.add(gdL);
        btnP.add(btn_change);
        btnP.add(btn_resign);
        btnP.add(btn_logout);
        btn_change.setBackground(new Color(245,202,186));
        btn_resign.setBackground(new Color(255,40,60));
        btn_logout.setBackground(new Color(245,202,186));


        jpanel.add(idP);
        jpanel.add(newpwP);
        jpanel.add(newnameP);
        jpanel.add(gdP);
        jpanel.add(btnP);

        btn_change.addActionListener(this);
        btn_resign.addActionListener(this);
        btn_logout.addActionListener(this);

        setSize(400, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        add(jpanel);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        String newpw = newpw_text.getText();
        String newname = newname_text.getText();
        char[] c = newpw.toCharArray();

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

        if (b.getText().equals("RESIGN")) {
            new Resign();
        }
        if (b.getText().equals("CHANGE")) {
            if(newpw.length() != 6 || newpw.contains(" ")){
                JOptionPane.showMessageDialog(null, "Your PW should be 2 numbers & 2 Alphabet & 2 Exclamation Mark");
            } else if(alphabet_count != 2){
            JOptionPane.showMessageDialog(null, "Your PW should be 2 numbers & 2 Alphabet & 2 Exclamation Mark");
            } else if(excalm_count != 2){
            JOptionPane.showMessageDialog(null, "Your PW should be 2 numbers & 2 Alphabet & 2 Exclamation Mark");
            } else if(num_count != 2) {
                JOptionPane.showMessageDialog(null, "Your PW should be 2 numbers & 2 Alphabet & 2 Exclamation Mark");
            }else if (newpw.equals("") || newname.equals("")) {
                JOptionPane.showMessageDialog(null, "Please insert all information");
            } else if(newpw != null && newname != null){
                if(db.UpdateInfo(newpw, newname)==1){
                    JOptionPane.showMessageDialog(null,"UPDATED SUCCESSFULLY 😉");
                }
            }
        }
        if (b.getText().equals("LOGOUT")){
            new Login();
            setVisible(false);
        }
    }
}

