import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class Login extends JFrame implements ActionListener {
    // various 변수
    JPanel jp1 = new JPanel();
    JTextField id_text = new JTextField(10);
    JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel idLabel = new JLabel("ID : ", JLabel.CENTER);
    JPanel pwPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel pwLabel = new JLabel("PASSWORD : ", JLabel.CENTER);
    JPasswordField pw_text = new JPasswordField(10);
    JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton btn_login = new JButton("LOGIN");     //로그인 버튼 만들기
    JButton btn_join = new JButton("JOIN");  //회원가입 버튼 만들기
    JButton btn_admin = new JButton("ADMIN");  //admin page 버튼 만들기
    DataBase db = new DataBase();
    static String  uid;

    Main m = null;

    Login() {

        setTitle("SSUNI'S LOG-IN SYSTEM");
        jp1.setLayout(new GridLayout(3, 1));

        idPanel.add(idLabel); // panel 에 label 붙히기
        idPanel.add(id_text);

        jp1.add(idPanel);

        pwPanel.add(pwLabel);
        pwPanel.add(pw_text);

        jp1.add(pwPanel);

        btnPanel.add(btn_login);
        btnPanel.add(btn_join);
        btnPanel.add(btn_admin);
        btn_login.setBackground(new Color(245,202,186));
        btn_join.setBackground(new Color(245,202,186));
        btn_admin.setBackground(new Color(252,119,92));



        jp1.add(btnPanel);

        setLayout(new BorderLayout());
        add(jp1, BorderLayout.NORTH);

        btn_login.addActionListener(this);
        btn_join.addActionListener(this);
        btn_admin.addActionListener(this);


        setBounds(500, 200, 400, 150);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();

        //text-field 에 입력된 아이디&비번을 변수에 초기화
        uid = id_text.getText();
        String tmp = uid;
        String upw=pw_text.getText();
//        String upw = "";
//        for (int i = 0; i < pw_text.getPassword().length; i++) {
//            upw += pw_text.getPassword()[i];
//        }
        if (b.getText().equals("LOGIN")) {
            if (uid.equals("") || upw.equals("")) {
                JOptionPane.showMessageDialog(null, "Please insert your ID & Password 😔");
            } else if (uid != null && upw != null) {
                try {
                    if (db.logincheck(uid, upw)==1) {
                        JOptionPane.showMessageDialog(null, "Login Successes 😉");
                        new MyInfo();
                    } else {
                        JOptionPane.showMessageDialog(null, "Login Fail 😥");
                        System.out.println(uid);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else if(b.getText().equals("JOIN")){
            new Joining();
        }else if (b.getText().equals("ADMIN")){
            if(uid.equals("ssuni")&&upw.equals("0522")){
                new Admin();
            }
        }
    }
}