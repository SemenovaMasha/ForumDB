
package forumdb.EntitiesFrames.Question;

import EntitiesClasses.Question;
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
public class QuestionsModel extends AbstractTableModel {

    List<Question> list = new ArrayList<>();
    Connection c;

    public QuestionsModel(Connection c) {
        super();
        this.c = c;

        try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM question");

            while (rs.next()) {
                Question item = new Question(rs.getInt("id"), 
                        rs.getString("name"), rs.getString("question_text"), 
                        rs.getInt("section_id"), rs.getInt("user_id"), 
                        rs.getInt("state_id"));

                list.add(item); 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
        }
        rowsCount = list.size();
    }

    public void updateData() {
        try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM question");
            list = new ArrayList<>();

            while (rs.next()) {
                Question item = new Question(rs.getInt("id"), 
                        rs.getString("name"), rs.getString("question_text"), 
                        rs.getInt("section_id"), rs.getInt("user_id"), 
                        rs.getInt("state_id"));

                list.add(item); 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
        }
        rowsCount = list.size();
    }
    int rowsCount = 5;
    int colCount = 5;

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
        String s = "";
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getName();
            case 1:
                return list.get(rowIndex).getQuestionText();
            case 2:
                try {
                    Statement statement = c.createStatement();
                    ResultSet rs = statement.executeQuery(
                            "SELECT * FROM section where id=" 
                                    + list.get(rowIndex).getSectionId() + ";");
                    rs.next();
                    s = rs.getString("name");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                }
                return s;
            case 3:

                try {
                    Statement statement = c.createStatement();
                    ResultSet rs = statement.executeQuery(
                            "SELECT * FROM forum_user where id=" 
                                    + list.get(rowIndex).getUserId() + ";");
                    rs.next();
                    s = rs.getString("login");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                }
                return s;
            case 4:
                try {
                    Statement statement = c.createStatement();
                    ResultSet rs = statement.executeQuery(
                            "SELECT * FROM state where id=" 
                                    + list.get(rowIndex).getStateId() + ";");
                    rs.next();
                    s = rs.getString("name");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                }
                return s;
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Name";
            case 1:
                return "Question text";
            case 2:
                return "Section name";
            case 3:
                return "User login";
            case 4:
                return "State name";
        }
        return null;
    }

    public Question getSelectesItem(int row) {
        return list.get(row);
    }
}
