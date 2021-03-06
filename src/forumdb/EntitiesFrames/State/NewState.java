
package forumdb.EntitiesFrames.State;

import EntitiesClasses.State;
import Help.JTextFieldLimit;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author Masha
 */
public class NewState extends javax.swing.JDialog {

    Connection c;
    State editState;

    /**
     * Creates new form NewState
     * @param parent
     * @param c
     * @param modal
     */
    public NewState(java.awt.Frame parent, boolean modal, Connection c) {
        super(parent, modal);
        initComponents();

        ((AbstractDocument) name.getDocument()).setDocumentFilter(new JTextFieldLimit(16));
        this.c = c;
    }

    public NewState(java.awt.Frame parent, boolean modal, Connection c, State s) {
        super(parent, modal);
        initComponents();
        ((AbstractDocument) name.getDocument()).setDocumentFilter(new JTextFieldLimit(16));
        this.c = c;
        editState = s;
        fillFields();
    }

    private void fillFields() {
        name.setText(editState.getName());
    }

    public boolean check() {
        if ("".equals(name.getText())) {
            JOptionPane.showMessageDialog(new JFrame(), "Name cannot be empty");
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        name = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New state");

        jButton1.setText("Done");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!check()) {
            return;
        }
        try {
            Statement statement = c.createStatement();
            if (editState == null) {
                statement.executeUpdate("insert into state (name) values ('" 
                        + name.getText() + "');");
            } else {
                statement.executeUpdate("update state set name='" 
                        + name.getText() + "' where id=" + editState.getId() + ";");
            }

        } catch (SQLException ex) {
            if (ex.getMessage().contains("State_Name_key")) {
                JOptionPane.showMessageDialog(new JFrame(), "This name already exist");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
            }
            return;
        }
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField name;
    // End of variables declaration//GEN-END:variables
}
