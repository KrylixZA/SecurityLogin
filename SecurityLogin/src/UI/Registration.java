/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DBConnection.DatabaseConnector;
import Logic.Geobytes;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author chris
 */
public class Registration extends javax.swing.JFrame {

    /**
     * Creates new form Registration
     */
    public Registration() {
        SetupUIComponents();
    }

    private void SetupUIComponents() {
        initComponents();
        HideRequiredLabels();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void HideRequiredLabels() {
        lblIDReq.setVisible(false);
        lblPWReq1.setVisible(false);
        lblPWReq2.setVisible(false);
        lblFNameReq.setVisible(false);
        lblSNameReq.setVisible(false);
        lblCellNumReq.setVisible(false);
        lblEmailReq.setVisible(false);
    }

    private void GeneratePopupMessage(String popupMessage) {
        final String message = popupMessage + "\n\n";
        final String html1 = "<html><body style='width: ";
        final String html2 = "px'>";
        JOptionPane.showMessageDialog(null, new JLabel(html1 + "400" + html2 + message));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panContainer = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtIDNumber = new javax.swing.JTextField();
        txtFName = new javax.swing.JTextField();
        txtSName = new javax.swing.JTextField();
        txtCellNumber = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        lblIDReq = new javax.swing.JLabel();
        lblFNameReq = new javax.swing.JLabel();
        lblSNameReq = new javax.swing.JLabel();
        lblCellNumReq = new javax.swing.JLabel();
        lblEmailReq = new javax.swing.JLabel();
        lblPWReq1 = new javax.swing.JLabel();
        panHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPassword1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        btnRegister = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtPassword2 = new javax.swing.JPasswordField();
        lblPWReq2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registration");
        setResizable(false);
        setSize(new java.awt.Dimension(640, 480));

        panContainer.setBackground(new java.awt.Color(255, 255, 255));
        panContainer.setPreferredSize(new java.awt.Dimension(640, 480));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ID Number *");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("First Name *");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Surname *");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Cell Number *");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Email *");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Password *");

        txtIDNumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIDNumber.setForeground(new java.awt.Color(255, 0, 0));
        txtIDNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIDNumber.setToolTipText("Can only contain numbers");

        txtFName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFName.setForeground(new java.awt.Color(255, 0, 0));
        txtFName.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtSName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSName.setForeground(new java.awt.Color(255, 0, 0));
        txtSName.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtCellNumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCellNumber.setForeground(new java.awt.Color(255, 0, 0));
        txtCellNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCellNumber.setToolTipText("Can only contain numbers");

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(255, 0, 0));
        txtEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmail.setToolTipText("Must contain @ sign");

        lblIDReq.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblIDReq.setForeground(new java.awt.Color(255, 0, 0));
        lblIDReq.setText("** REQUIRED **");

        lblFNameReq.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblFNameReq.setForeground(new java.awt.Color(255, 0, 0));
        lblFNameReq.setText("** REQUIRED **");

        lblSNameReq.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblSNameReq.setForeground(new java.awt.Color(255, 0, 0));
        lblSNameReq.setText("** REQUIRED **");

        lblCellNumReq.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblCellNumReq.setForeground(new java.awt.Color(255, 0, 0));
        lblCellNumReq.setText("** REQUIRED **");

        lblEmailReq.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblEmailReq.setForeground(new java.awt.Color(255, 0, 0));
        lblEmailReq.setText("** REQUIRED **");

        lblPWReq1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblPWReq1.setForeground(new java.awt.Color(255, 0, 0));
        lblPWReq1.setText("** REQUIRED **");

        panHeader.setBackground(new java.awt.Color(255, 0, 0));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registration");

        javax.swing.GroupLayout panHeaderLayout = new javax.swing.GroupLayout(panHeader);
        panHeader.setLayout(panHeaderLayout);
        panHeaderLayout.setHorizontalGroup(
            panHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panHeaderLayout.setVerticalGroup(
            panHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtPassword1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnRegister.setBackground(new java.awt.Color(255, 0, 0));
        btnRegister.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRegister.setForeground(new java.awt.Color(255, 255, 255));
        btnRegister.setText("Register");
        btnRegister.setToolTipText("Register your account");
        btnRegister.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnRegister.setContentAreaFilled(false);
        btnRegister.setOpaque(true);
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(255, 0, 0));
        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancel");
        btnCancel.setToolTipText("Return to the login frame");
        btnCancel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnCancel.setContentAreaFilled(false);
        btnCancel.setOpaque(true);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(262, 262, 262)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Re-enter Password *");

        txtPassword2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblPWReq2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblPWReq2.setForeground(new java.awt.Color(255, 0, 0));
        lblPWReq2.setText("** REQUIRED **");

        javax.swing.GroupLayout panContainerLayout = new javax.swing.GroupLayout(panContainer);
        panContainer.setLayout(panContainerLayout);
        panContainerLayout.setHorizontalGroup(
            panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panContainerLayout.createSequentialGroup()
                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panContainerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panContainerLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panContainerLayout.createSequentialGroup()
                                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtPassword1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtCellNumber, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtSName, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtFName))
                                    .addComponent(txtIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblIDReq)
                                    .addComponent(lblFNameReq)
                                    .addComponent(lblSNameReq)
                                    .addComponent(lblCellNumReq)
                                    .addComponent(lblEmailReq)
                                    .addComponent(lblPWReq1)))
                            .addGroup(panContainerLayout.createSequentialGroup()
                                .addComponent(txtPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(lblPWReq2)))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        panContainerLayout.setVerticalGroup(
            panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panContainerLayout.createSequentialGroup()
                .addComponent(panHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIDReq))
                .addGap(19, 19, 19)
                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFNameReq))
                .addGap(18, 18, 18)
                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSNameReq))
                .addGap(18, 18, 18)
                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCellNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCellNumReq))
                .addGap(18, 18, 18)
                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmailReq))
                .addGap(18, 18, 18)
                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPWReq1)
                    .addComponent(txtPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPWReq2)
                    .addComponent(txtPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        String IDNumber = txtIDNumber.getText();
        String password = txtPassword1.getText();
        String secondPassword = txtPassword2.getText();
        String name = txtFName.getText();
        String sName = txtSName.getText();
        String cellNo = txtCellNumber.getText();
        String email = txtEmail.getText();
        String loginGeoLocation = Geobytes.getLatitude() + ", " + Geobytes.getLongitude();

        if ((IDNumber.length() > 0 && IDNumber.length() == 13 && IDNumber.matches("[0-9]+")) && (password.length() > 0 && password.equals(secondPassword)) && name.length() > 0 && sName.length() > 0 && (cellNo.length() > 0 && cellNo.matches("[0-9]+")) && (email.length() > 0 && email.contains("@"))) {
            ArrayList<String> usedIDs = new DatabaseConnector().GetUsedIDs();
            if (!usedIDs.contains(IDNumber)) {
                String[] accountDetails = {IDNumber, password, name, sName, cellNo, email, loginGeoLocation};
                Security security = new Security(accountDetails);
            } else {
                GeneratePopupMessage("ID Number is already in database. Please use this to log in");
                Login login = new Login();
            }
            this.dispose();
        } else {
            if (IDNumber.length() != 13 || !IDNumber.matches("[0-9]+")) {
                lblIDReq.setVisible(true);
            }
            if (password.length() == 0) {
                lblPWReq1.setVisible(true);
            }
            if (secondPassword.length() == 0){
                lblPWReq2.setVisible(true);
            }
            if (!password.equals(secondPassword)){
                lblPWReq2.setVisible(true);
                lblPWReq2.setText("* Does not match *");
            }
            if (name.length() == 0) {
                lblFNameReq.setVisible(true);
            }
            if (sName.length() == 0) {
                lblSNameReq.setVisible(true);
            }
            if (cellNo.length() == 0 || !cellNo.matches("[0-9]+")) {
                lblCellNumReq.setVisible(true);
            }
            if (email.length() == 0 || !email.contains("@")) {
                lblEmailReq.setVisible(true);
            }
            GeneratePopupMessage("Unsuccessful account creation. Please ensure that all required fields are filled.");
        }

    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            Login login = new Login();
            this.dispose();
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCellNumReq;
    private javax.swing.JLabel lblEmailReq;
    private javax.swing.JLabel lblFNameReq;
    private javax.swing.JLabel lblIDReq;
    private javax.swing.JLabel lblPWReq1;
    private javax.swing.JLabel lblPWReq2;
    private javax.swing.JLabel lblSNameReq;
    private javax.swing.JPanel panContainer;
    private javax.swing.JPanel panHeader;
    private javax.swing.JTextField txtCellNumber;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFName;
    private javax.swing.JTextField txtIDNumber;
    private javax.swing.JPasswordField txtPassword1;
    private javax.swing.JPasswordField txtPassword2;
    private javax.swing.JTextField txtSName;
    // End of variables declaration//GEN-END:variables
}