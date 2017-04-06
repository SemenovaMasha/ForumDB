
package forumdb.EntitiesFrames.State;

import EntitiesClasses.State;
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
public class StatesModel extends AbstractTableModel {

    List<State> states = new ArrayList<>();
    Connection c;

    public StatesModel(Connection c) {
        super();
        this.c = c;

        try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM state");

            while (rs.next()) {
                State state = new State(rs.getInt("id"), rs.getString("name"));
                states.add(state); 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
        }
        rowsCount = states.size();
    }
    public void updateData() {
        try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM state");
            states = new ArrayList<>();

            while (rs.next()) {
                State state = new State(rs.getInt("id"), rs.getString("name"));

                states.add(state); 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
        }
        rowsCount = states.size();
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
                return states.get(rowIndex).getName();
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

    public State getSelectesState(int row) {
        return states.get(row);
    }
}
