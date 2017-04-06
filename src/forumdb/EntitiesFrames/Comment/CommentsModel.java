
package forumdb.EntitiesFrames.Comment;

import EntitiesClasses.Comment;
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
public class CommentsModel extends AbstractTableModel {

    List<Comment> list = new ArrayList<>();
    Connection c;

    public CommentsModel(Connection c) {
        super();
        this.c = c;
        try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM comment");
            while (rs.next()) {
                Comment item = new Comment(rs.getInt("id"), 
                        rs.getString("comment_text"), rs.getInt("question_id"), 
                        rs.getInt("comment_id"), rs.getInt("user_id"));

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
            ResultSet rs = statement.executeQuery("SELECT * FROM comment");
            list = new ArrayList<>();

            while (rs.next()) {
                Comment item = new Comment(rs.getInt("id"), 
                        rs.getString("comment_text"), rs.getInt("question_id"), 
                        rs.getInt("comment_id"), rs.getInt("user_id"));

                list.add(item); 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
        }
        rowsCount = list.size();
    }
    int rowsCount = 5;
    int colCount = 4;

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
                return list.get(rowIndex).getCommentText();
            case 1:

                try {
                    Statement statement = c.createStatement();
                    ResultSet rs = statement.executeQuery(
                            "SELECT * FROM question where id=" 
                                    + list.get(rowIndex).getQuestionId() + ";");
                    rs.next();
                    s = rs.getString("name");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                }
                return s;
            case 2:
                try {
                    Statement statement = c.createStatement();
                    ResultSet rs = statement.executeQuery(
                            "SELECT * FROM comment where id=" 
                                    + list.get(rowIndex).getCommentId() + ";");
                    if (rs.next()) {
                        s = rs.getString("comment_text");
                    } else {
                        s = null;
                    }

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
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Comment text";
            case 1:
                return "Question";
            case 2:
                return "Comment";
            case 3:
                return "User login";
        }
        return null;
    }

    public Comment getSelectesItem(int row) {
        return list.get(row);
    }
}
