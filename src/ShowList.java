import javax.swing.*;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class ShowList extends JFrame {
    //JPanel tableP=new JPanel();
    List<InfoVO> infolist = new ArrayList<>();

    DataBase db = new DataBase();


    ShowList(){
        setSize(600,600);
        setLocationRelativeTo(null);
        setResizable(false);
    //    add(tableP);

        setVisible(true);

        infolist = db.getIDList();
        String colname[] = {"ID", "PASSWORD", "NAME", "GENDER"};
        String [][] contents = new String[infolist.size()][4];

        for (int i = 0; i<infolist.size(); i++){
            contents[i][0]=infolist.get(i).getId();
            contents[i][1]=infolist.get(i).getPw();
            contents[i][2]=infolist.get(i).getName();
            contents[i][3]=infolist.get(i).getGender();
        }

        JTable table = new JTable(contents, colname);
        JScrollPane scroll = new JScrollPane(table);

        scroll.setBounds(50,50,500,500);
        add(scroll);
    }
}
