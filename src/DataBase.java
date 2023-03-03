import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.MouseListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DataBase extends JFrame {
    Connection con = null;
    PreparedStatement stm = null;
    String url = "jdbc:mysql://localhost:3306/mysql";
    String user = "root";
    String passwd = "0522";

    ResultSet rs = null;


    private final String information_insert = "insert into information (ID, Password, Name, Gender) values (?,?,?,?)";
    private final String information_get = "select Password from information where ID=?"; // login 용
    private final String information_select = "select Gender from information where ID=?"; // 회원수정용
    private final String information_change = "update information set Password=?, Name=? where ID=?";
    private final String information_delete = "delete from information where Password=?"; // 탈퇴용
    private final String information_kick = "delete from information where ID=?"; // 관리자 강제탈퇴용
    private final String information_list = "select * from information order by ID desc";

    DefaultTableModel model;
    JTable table;
    Vector data = new Vector<String>();
    Vector<String> colName = new Vector<>();

    DataBase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, passwd);
            System.out.println("Server Connected");
        } catch (Exception e) {
            System.out.println("Server Not Connected");
        }
    }

    String infoChange(String i) {
        String s = i;
        String gdInfo = "";

        try {
            stm = con.prepareStatement(information_select);
            stm.setString(1, s);
            rs = stm.executeQuery();
            if (rs.next()) {
                gdInfo = rs.getString("Gender");
                return gdInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    int logincheck(String i, String p) throws SQLException {
        String id_text = i;
        String pw_text = p;
        String get_pw;

        try {
            stm = con.prepareStatement(information_get);
            stm.setString(1, id_text);
            rs = stm.executeQuery();
            if (rs.next()) {
                get_pw = rs.getString("Password");
                if (pw_text.equals(get_pw)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    int joinCheck(String i, String p, String n, String g) {
        //  boolean flag=false;

        String id = i;
        String pw = p;
        String name = n;
        String gender = g;

        try {
            stm = con.prepareStatement(information_insert);
            stm.setString(1, id);
            stm.setString(2, pw);
            stm.setString(3, name);
            stm.setString(4, gender);
            stm.executeUpdate();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    int DeleteInfo(String p) {
        String pw = p;
        try {
            System.out.println("정보삭제 완료.");
            stm = con.prepareStatement(information_delete);
            stm.setString(1, pw);
            stm.executeUpdate();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    int UpdateInfo(String p, String n) {
        String uppw = p;
        String upn = n;

        try {
            stm = con.prepareStatement(information_change);
            stm.setString(1, uppw);
            stm.setString(2, upn);
            stm.setString(3, Login.uid);
            stm.executeUpdate();
            System.out.println("정보 업데이트 완료.");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("실패");
        }
        return 0;
    }

    int Kick(String k) {
        try {
            System.out.println(k);
            stm = con.prepareStatement(information_kick);
            stm.setString(1, k);
            stm.executeUpdate();
            System.out.println("강제탈퇴 완료.");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    List<InfoVO> getIDList() {
        List<InfoVO> list = new ArrayList<InfoVO>();
        try {
            stm = con.prepareStatement(information_list);
            rs = stm.executeQuery();
            while (rs.next()) {
                InfoVO one = new InfoVO();
                one.setId(rs.getString("ID"));
                one.setPw(rs.getString("Password"));
                one.setName(rs.getString("Name"));
                one.setGender(rs.getString("Gender"));
                list.add(one);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
