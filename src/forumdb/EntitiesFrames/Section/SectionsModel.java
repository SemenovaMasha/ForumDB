
package forumdb.EntitiesFrames.Section;

import EntitiesClasses.Section;
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
public class SectionsModel extends AbstractTableModel {

    List<Section> list = new ArrayList<>();

    Connection c;

    public SectionsModel(Connection c) {
        super();
        this.c = c;
        try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM section");
            while (rs.next()) {
                Section item = new Section(rs.getInt("id"), rs.getString("name"), 
                        rs.getString("date_of_creation"));

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
            ResultSet rs = statement.executeQuery("SELECT * FROM section");
            list = new ArrayList<>();

            while (rs.next()) {
                Section item = new Section(rs.getInt("id"), rs.getString("name"), 
                        rs.getString("date_of_creation"));

                list.add(item); 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
        }
        rowsCount = list.size();
    }
    int rowsCount = 5;
    int colCount = 2;

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
            case 1:
                return list.get(rowIndex).getDateOfCreation();

        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Name";
            case 1:
                return "Date of creation";
        }
        return null;
    }

    public Section getSelectesItem(int row) {
        return list.get(row);
    }
}
