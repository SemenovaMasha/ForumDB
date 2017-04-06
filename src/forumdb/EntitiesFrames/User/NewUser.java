
package forumdb.EntitiesFrames.User;

import EntitiesClasses.Status;
import EntitiesClasses.User;
import Help.JTextFieldLimit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author Masha
 */
public class NewUser extends javax.swing.JDialog {

    Connection c;
    User editUser;
    List<Status> statuses;

    /**
     * Creates new form NewUser
     * @param parent
     * @param modal
     * @param c
     */
    public NewUser(java.awt.Frame parent, boolean modal, Connection c) {
        super(parent, modal);
        initComponents();

        ((AbstractDocument) login.getDocument()).setDocumentFilter(new JTextFieldLimit(16));
        ((AbstractDocument) password.getDocument()).setDocumentFilter(new JTextFieldLimit(16));
        ((AbstractDocument) mail.getDocument()).setDocumentFilter(new JTextFieldLimit(32));
        ((AbstractDocument) date.getDocument()).setDocumentFilter(new JTextFieldLimit(10));
        ((AbstractDocument) second.getDocument()).setDocumentFilter(new JTextFieldLimit(32));
        ((AbstractDocument) first.getDocument()).setDocumentFilter(new JTextFieldLimit(32));
        ((AbstractDocument) patr.getDocument()).setDocumentFilter(new JTextFieldLimit(32));
        this.c = c;
        statuses = new ArrayList<>();
        try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM status");
            while (rs.next()) {
                Status tmp = new Status(rs.getInt("id"), rs.getString("name"));
                statuses.add(tmp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        status.setModel(new DefaultComboBoxModel(statuses.toArray()));
    }

    public NewUser(java.awt.Frame parent, boolean modal, Connection c, User u) {
        super(parent, modal);
        initComponents();
        ((AbstractDocument) login.getDocument()).setDocumentFilter(new JTextFieldLimit(16));
        ((AbstractDocument) password.getDocument()).setDocumentFilter(new JTextFieldLimit(16));
        ((AbstractDocument) mail.getDocument()).setDocumentFilter(new JTextFieldLimit(32));
        ((AbstractDocument) date.getDocument()).setDocumentFilter(new JTextFieldLimit(10));
        ((AbstractDocument) second.getDocument()).setDocumentFilter(new JTextFieldLimit(32));
        ((AbstractDocument) first.getDocument()).setDocumentFilter(new JTextFieldLimit(32));
        ((AbstractDocument) patr.getDocument()).setDocumentFilter(new JTextFieldLimit(32));
        this.c = c;
        editUser = u;

        statuses = new ArrayList<>();
        try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM status");
            while (rs.next()) {
                Status tmp = new Status(rs.getInt("id"), rs.getString("name"));
                statuses.add(tmp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        status.setModel(new DefaultComboBoxModel(statuses.toArray()));
        fillFields();
    }

    private void fillFields() {
        login.setText(editUser.getLogin());
        password.setText(editUser.getPassword());
        mail.setText(editUser.getMail());
        date.setText(editUser.getDateOfRegistration());

        for (Status s : statuses) {
            if (s.getId() == editUser.getStatusId()) {
                status.setSelectedItem((s));
            }
        }

        second.setText(editUser.getSecondName());
        first.setText(editUser.getFirstName());
        patr.setText(editUser.getPatronymic());
    }

    public boolean check() {
        if ("".equals(login.getText())) {
            JOptionPane.showMessageDialog(new JFrame(), "Login cannot be empty");
            return false;
        }
        if ("".equals(password.getText())) {
            JOptionPane.showMessageDialog(new JFrame(), "Password cannot be empty");
            return false;
        }
        if ("".equals(mail.getText())) {
            JOptionPane.showMessageDialog(new JFrame(), "Mail cannot be empty");
            return false;
        }
        Pattern p = Pattern.compile("[0-9]{4}-([1-9]|1[012])-([1-9]|1[0-9]|2[0-9]|3[01])");
        Matcher m = p.matcher(date.getText());
        if (!m.matches()) {
            JOptionPane.showMessageDialog(new JFrame(), "Date has wrong format");
            return false;
        }
        if ("".equals(second.getText())) {
            JOptionPane.showMessageDialog(new JFrame(), "Second name cannot be empty");
            return false;
        }
        if ("".equals(first.getText())) {
            JOptionPane.showMessageDialog(new JFrame(), "First name cannot be empty");
            return false;
        }
        if ("".equals(patr.getText())) {
            JOptionPane.showMessageDialog(new JFrame(), "Patronymic cannot be empty");
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

        login = new javax.swing.JTextField();
        mail = new javax.swing.JTextField();
        date = new javax.swing.JTextField();
        second = new javax.swing.JTextField();
        first = new javax.swing.JTextField();
        patr = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        status = new javax.swing.JComboBox<>();
        password = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New user");

        mail.setToolTipText("");

        jButton1.setText("Done");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Login");

        jLabel2.setText("Password");

        jLabel3.setText("Mail");

        jLabel4.setText("Date of registration");

        jLabel5.setText("Status");

        jLabel6.setText("Second name");

        jLabel7.setText("First name");

        jLabel8.setText("Patronymic");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(patr, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(first)
                            .addComponent(second)
                            .addComponent(date)
                            .addComponent(mail)
                            .addComponent(login)
                            .addComponent(status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(password))))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(second, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(first, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(patr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            if (editUser == null) {
                statement.executeUpdate(
                        "insert into forum_user (login,password,mail,"
                                + "date_of_registration,status_id,second_name,"
                                + "first_name,patronymic) values ('" 
                                + login.getText() + "','" + password.getText() + "','" 
                                + mail.getText() + "','" + date.getText() + "','" 
                                + ((Status) status.getSelectedItem()).getId() + "','" 
                                + second.getText() + "','" + first.getText() + "','" 
                                + patr.getText() + "');");
            } else {
                statement.executeUpdate("update forum_user set login='" 
                        + login.getText() + "',password='" + password.getText() 
                        + "',mail='" + mail.getText() + "',date_of_registration='" 
                        + date.getText() + "',status_id='" 
                        + ((Status) status.getSelectedItem()).getId() 
                        + "',second_name='" + second.getText() + "',first_name='" 
                        + first.getText() + "',patronymic='" + patr.getText() 
                        + "' where id=" + editUser.getId() + ";");
            }

        } catch (SQLException ex) {
            if (ex.getMessage().contains("User_Login_key")) {
                JOptionPane.showMessageDialog(new JFrame(), "This login already exist");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
            }
            return;
        }
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField date;
    private javax.swing.JTextField first;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField login;
    private javax.swing.JTextField mail;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField patr;
    private javax.swing.JTextField second;
    private javax.swing.JComboBox<String> status;
    // End of variables declaration//GEN-END:variables
}
