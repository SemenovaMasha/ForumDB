
package forumdb.EntitiesFrames.Question;

import EntitiesClasses.Question;
import EntitiesClasses.Section;
import EntitiesClasses.State;
import EntitiesClasses.User;
import Help.JTextFieldLimit;
import forumdb.EntitiesFrames.User.NewUser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author Masha
 */
public class NewQuestion extends javax.swing.JDialog {

    Connection c;
    Question editItem;
    List<Section> list;
    List<User> list1;
    List<State> list2;

    /**
     * Creates new form NewQuestion
     * @param parent
     * @param modal
     * @param c
     */
    public NewQuestion(java.awt.Frame parent, boolean modal, Connection c) {
        super(parent, modal);
        initComponents();
        ((AbstractDocument) name.getDocument()).setDocumentFilter(new JTextFieldLimit(32));
        ((AbstractDocument) text.getDocument()).setDocumentFilter(new JTextFieldLimit(1024));
        this.c = c;
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM section");
            while (rs.next()) {
                Section item = new Section(rs.getInt("id"), rs.getString("name"), 
                        rs.getString("date_of_creation"));
                list.add(item);
            }
            section.setModel(new DefaultComboBoxModel(list.toArray()));

            rs = statement.executeQuery("SELECT * FROM forum_user");
            while (rs.next()) {
                User item = new User(rs.getInt("id"), rs.getString("login"), 
                        rs.getString("password"), rs.getString("mail"),
                        rs.getString("date_of_registration"), rs.getInt("status_id"), 
                        rs.getString("second_name"), rs.getString("first_name"), 
                        rs.getString("patronymic"));
                list1.add(item);
            }
            user.setModel(new DefaultComboBoxModel(list1.toArray()));

            rs = statement.executeQuery("SELECT * FROM state");
            while (rs.next()) {
                State item = new State(rs.getInt("id"), rs.getString("name"));
                list2.add(item);
            }
            state.setModel(new DefaultComboBoxModel(list2.toArray()));
        } catch (SQLException ex) {
            Logger.getLogger(NewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public NewQuestion(java.awt.Frame parent, boolean modal, Connection c, Question u) {
        super(parent, modal);
        initComponents();
        ((AbstractDocument) name.getDocument()).setDocumentFilter(new JTextFieldLimit(32));
        ((AbstractDocument) text.getDocument()).setDocumentFilter(new JTextFieldLimit(1024));
        this.c = c;
        editItem = u;
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM section");
            while (rs.next()) {
                Section item = new Section(rs.getInt("id"), rs.getString("name"), 
                        rs.getString("date_of_creation"));
                list.add(item);
            }
            section.setModel(new DefaultComboBoxModel(list.toArray()));

            rs = statement.executeQuery("SELECT * FROM forum_user");
            while (rs.next()) {
                User item = new User(rs.getInt("id"), rs.getString("login"), 
                        rs.getString("password"), rs.getString("mail"),
                        rs.getString("date_of_registration"), 
                        rs.getInt("status_id"), rs.getString("second_name"), 
                        rs.getString("first_name"), rs.getString("patronymic"));
                list1.add(item);
            }
            user.setModel(new DefaultComboBoxModel(list1.toArray()));

            rs = statement.executeQuery("SELECT * FROM state");
            while (rs.next()) {
                State item = new State(rs.getInt("id"), rs.getString("name"));
                list2.add(item);
            }
            state.setModel(new DefaultComboBoxModel(list2.toArray()));
        } catch (SQLException ex) {
            Logger.getLogger(NewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        fillFields();
    }

    private void fillFields() {
        name.setText(editItem.getName());
        text.setText(editItem.getQuestionText());
        for (Section s : list) {
            if (s.getId() == editItem.getSectionId()) {
                section.setSelectedItem((s));
            }
        }
        for (User s : list1) {
            if (s.getId() == editItem.getUserId()) {
                user.setSelectedItem((s));
            }
        }
        for (State s : list2) {
            if (s.getId() == editItem.getStateId()) {
                state.setSelectedItem((s));
            }
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        text = new javax.swing.JTextArea();
        section = new javax.swing.JComboBox<>();
        user = new javax.swing.JComboBox<>();
        state = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New question");

        text.setColumns(20);
        text.setRows(5);
        jScrollPane1.setViewportView(text);

        state.setToolTipText("");

        jButton1.setText("Done");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Title");

        jLabel2.setText("Question text");

        jLabel3.setText("Section");

        jLabel4.setText("User");

        jLabel5.setText("State");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(63, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(section, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(74, 74, 74)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(section, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (!check()) {
            return;
        }
        try {
            // TODO add your handling code here:
            Statement statement = c.createStatement();
            if (editItem == null) {
                statement.executeUpdate("insert into question (name,question_text,section_id,user_id,state_id) values ('" + name.getText() + "','" + text.getText() + "','" + ((Section) section.getSelectedItem()).getId() + "','" + ((User) user.getSelectedItem()).getId() + "','" + ((State) state.getSelectedItem()).getId() + "');");
            } else {
                statement.executeUpdate("update question set name='" + name.getText() + "',question_text='" + text.getText() + "',section_id='" + ((Section) section.getSelectedItem()).getId() + "',user_id='" + ((User) user.getSelectedItem()).getId() + "',state_id='" + ((State) state.getSelectedItem()).getId() + "' where id=" + editItem.getId() + ";");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
            return;
        }
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField name;
    private javax.swing.JComboBox<String> section;
    private javax.swing.JComboBox<String> state;
    private javax.swing.JTextArea text;
    private javax.swing.JComboBox<String> user;
    // End of variables declaration//GEN-END:variables
}
