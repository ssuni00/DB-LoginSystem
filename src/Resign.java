import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Resign extends JFrame implements ActionListener {

    JPanel panel = new JPanel();
    JPanel resignP = new JPanel();
    JLabel repwL= new JLabel("PASSWORD: ", JLabel.CENTER);
    JTextField repwT=new JTextField(10);
    JPanel btnP = new JPanel();
    JButton btn_okay= new JButton("OKAY");
    DataBase db = new DataBase();

    Resign(){
        setTitle("RESIGNING");
        panel.setLayout(new GridLayout(2,1));

        resignP.add(repwL);
        resignP.add(repwT);
        btnP.add(btn_okay);
        btn_okay.setBackground(new Color(245,202,186));


        panel.add(resignP);
        panel.add(btnP);

        btn_okay.addActionListener(this);

        setSize(300,150);
        setLocationRelativeTo(null);
        setResizable(false);
        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        String upw = repwT.getText();

        if(b.getText().equals("OKAY")){
            if(db.DeleteInfo(upw)==1){
                JOptionPane.showMessageDialog(null, "RESIGN CONFIRMED. GOOD-BYE 🖐");
            }else{
                JOptionPane.showMessageDialog(null,"NOT READY FOR SAYING GOOD-BYE 😭");
            }
        }

    }
}
