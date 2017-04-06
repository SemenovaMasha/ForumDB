
package forumdb.EntitiesFrames.Status;

import EntitiesClasses.Status;
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
public class StatusesModel extends AbstractTableModel {

    List<Status> list = new ArrayList<>();

    Connection c;

    public StatusesModel(Connection c) {
        super();
        this.c = c;
        try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM status");
            while (rs.next()) {
                Status item = new Status(rs.getInt("id"), rs.getString("name"));

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
            ResultSet rs = statement.executeQuery("SELECT * FROM status");
            list = new ArrayList<>();

            while (rs.next()) {
                Status item = new Status(rs.getInt("id"), rs.getString("name"));

                list.add(item); 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
        }
        rowsCount = list.size();
    }
    int rowsCount = 5;
    int colCount = 1;

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
                return list.get(rowIndex).getName();

        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Name";
        }
        return null;
    }

    public Status getSelectesItem(int row) {
        return list.get(row);
    }
}
