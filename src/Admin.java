import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends JFrame implements ActionListener {

    JPanel jp = new JPanel();
    JPanel adP= new JPanel();
    JLabel delL= new JLabel("WRITE USER ID WHO U WANT TO DELETE : ", JLabel.LEFT);
    JPanel delP= new JPanel();
    JTextField delT = new JTextField(10);
    JPanel btnP = new JPanel();
    JButton btn_okay = new JButton("KICK");
    JButton btn_showIDs= new JButton("SHOW USERS' ID");
    DataBase db = new DataBase();

    Admin(){
        setTitle("ADMIN PAGE");
        jp.setLayout(new GridLayout(3,1));

        adP.add(delL);
        delP.add(delT);
        btnP.add(btn_okay);
        btnP.add(btn_showIDs);
        btn_showIDs.setBackground(new Color(245,202,186));
        btn_okay.setBackground(Color.RED);



        jp.add(adP);
        jp.add(delP);
        jp.add(btnP);

        btn_okay.addActionListener(this);
        btn_showIDs.addActionListener(this);
        setSize(300,150);
        setLocationRelativeTo(null);
        setResizable(false);
        add(jp);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        String kick = delT.getText();

        if (b.getText().equals("SHOW USERS' ID")) {
            db.getIDList();
            new ShowList();
        }else if(b.getText().equals("KICK")) {
            if (db.Kick(kick) == 1) {
                JOptionPane.showMessageDialog(null, "SUCCESS 👍");
            } else if (delT.getText().equals("") || (db.Kick(kick) == 0)){
                JOptionPane.showMessageDialog(null, "CHECK USER ID AGAIN PLEASE.");

            }
        }
    }
}
