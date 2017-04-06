
package forumdb.EntitiesFrames.User;

import EntitiesClasses.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Masha
 */
public class UsersModel extends AbstractTableModel {

    List<User> users = new ArrayList<>();
    Connection c;

    public UsersModel(Connection c) {
        super();
        this.c = c;

        try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM forum_user");

            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("login"), 
                        rs.getString("password"), rs.getString("mail"),
                        rs.getString("date_of_registration"), 
                        rs.getInt("status_id"), rs.getString("second_name"), 
                        rs.getString("first_name"), rs.getString("patronymic"));

                users.add(user); 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
        }
        rowsCount = users.size();
    }
    public void updateData() {
        try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM forum_user");
            users = new ArrayList<>();

            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("login"), 
                        rs.getString("password"), rs.getString("mail"),
                        rs.getString("date_of_registration"), 
                        rs.getInt("status_id"), rs.getString("second_name"), 
                        rs.getString("first_name"), rs.getString("patronymic"));

                users.add(user); 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
        }
        rowsCount = users.size();
    }
    int rowsCount = 5;
    int colCount = 8;

    @Override
    public int getRowCount() {
        return rowsCount;
    }

    @Override
    public int getColumnCount() {
        return colCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return users.get(rowIndex).getLogin();
            case 1:
                return users.get(rowIndex).getPassword();
            case 2:
                return users.get(rowIndex).getMail();
            case 3:
                return users.get(rowIndex).getDateOfRegistration();
            case 4:
                String s = "";
                try {
                    Statement statement = c.createStatement();
                    ResultSet rs = statement.executeQuery(
                            "SELECT * FROM status where id=" 
                                    + users.get(rowIndex).getStatusId() + ";");
                    rs.next();
                    s = rs.getString("name");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                }
                return s;
            case 5:
                return users.get(rowIndex).getSecondName();
            case 6:
                return users.get(rowIndex).getFirstName();
            case 7:
                return users.get(rowIndex).getPatronymic();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Login";
            case 1:
                return "Password";
            case 2:
                return "Mail";
            case 3:
                return "Date of registration";
            case 4:
                return "Status name";
            case 5:
                return "Second_name";
            case 6:
                return "First name";
            case 7:
                return "Patronymic";
        }
        return null;
    }

    public User getSelectesUser(int row) {
        return users.get(row);
    }

}
