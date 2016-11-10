/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DBConnection.DatabaseConnector;
import Logic.AntiRobot;
import Logic.Geobytes;
import Logic.NetworkTools;
import Logic.OneTimePin;
import Logic.RiskCalculator;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author chris
 */
public class Login extends javax.swing.JFrame {

    private final DatabaseConnector dbConnector = new DatabaseConnector();
    private final AntiRobot antiRobot = new AntiRobot();

    private final String MACAddress = NetworkTools.getMacAddress();
    private final String IPAddress = NetworkTools.getExternalIP();
    private final String loginGeoLocation = Geobytes.getLatitude() + ", " + Geobytes.getLongitude();

    private String IDNumber = "";
    private String password = "";
    private String accountID = "";
    private String deviceID = "";
    private String deviceName = "";
    private String sessionID = "";
    private int riskScore = 0;
    private int roleLevel = 0;
    private int failedLoginCount = 0;

    private RiskCalculator riskCalc;
    private OneTimePin otp;

    /**
     * Creates new form Login
     */
    public Login() {
        SetupUIComponents();
        try {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            deviceName = addr.getHostName();
        } catch (UnknownHostException ex) {
            System.out.println("Hostname can not be resolved");
        }
    }

    public Login(String accountID, String IDNumber, boolean successfulLogin) {
        SetupUIComponents();
        try {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            deviceName = addr.getHostName();
        } catch (UnknownHostException ex) {
            System.out.println("Hostname can not be resolved");
        }

        if (successfulLogin) {
            GeneratePopupMessage("Successfully logged in for ID Number " + IDNumber + ". Your account number is " + accountID);
        }

    }

    private void SetupUIComponents() {
        initComponents();
        HideRequiredLabels();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void HideRequiredLabels() {
        lblIDReq.setVisible(false);
        lblPWReq.setVisible(false);
    }

    private void HandleAccountDevices() {
        ArrayList<String> accountMACAddress = dbConnector.GetAccountMACAddresses(accountID);
        if (!accountMACAddress.isEmpty() && accountMACAddress.contains(MACAddress)) {
            dbConnector.UpdateMACAddressCount(accountID, MACAddress);
        } else {
            dbConnector.CreateNewAccountDevice(accountID, deviceName, MACAddress);
        }
        deviceID = dbConnector.GetDeviceID(accountID, MACAddress);
    }

    private void DetermineRiskScore() {
        /*
        Calculate the risk score
         */
        riskCalc = new RiskCalculator(accountID, MACAddress, IPAddress, deviceName, loginGeoLocation);
        riskScore = riskCalc.CalculateRiskScore();
        sliderRiskScore.setValue(riskScore);
    }

    private boolean IsAtRisk() {
        return riskScore > 1;
    }

    private boolean GenerateOneTimePin() {
        sessionID = dbConnector.GetSessionID(accountID, loginGeoLocation, IPAddress, deviceID, riskScore);
        String pin = "";
        String enteredPin = "";
        while (pin.length() == 0 && enteredPin.length() == 0) {
            otp = new OneTimePin();
            do {
                pin = otp.generateOneTimePin();
            } while (dbConnector.GetUsedOneTimePins().contains(pin));
            enteredPin = JOptionPane.showInputDialog("Enter the following pin: " + pin);
            if (!enteredPin.equals(pin)) {
                failedLoginCount++;
                if (failedLoginCount > 2) {
                    break;
                } else {
                    pin = "";
                    enteredPin = "";
                }
            }
        }

        dbConnector.CreateOneTimePin(accountID, pin, sessionID);
        if (failedLoginCount > 2) {
            Security security = new Security(sessionID, accountID, IDNumber, roleLevel, riskScore);
            this.dispose();
            return false;
        }
        return true;
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
        txtIDNumber = new javax.swing.JTextField();
        lblIDReq = new javax.swing.JLabel();
        lblPWReq = new javax.swing.JLabel();
        panHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        sliderRiskScore = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);
        setSize(new java.awt.Dimension(640, 480));

        panContainer.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ID Number *");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Password *");

        txtIDNumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIDNumber.setForeground(new java.awt.Color(255, 0, 0));
        txtIDNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIDNumber.setToolTipText("Can only contain numbers.");

        lblIDReq.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblIDReq.setForeground(new java.awt.Color(255, 0, 0));
        lblIDReq.setText("** REQUIRED **");

        lblPWReq.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblPWReq.setForeground(new java.awt.Color(255, 0, 0));
        lblPWReq.setText("** REQUIRED **");

        panHeader.setBackground(new java.awt.Color(255, 0, 0));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Login");

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

        txtPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnLogin.setBackground(new java.awt.Color(255, 0, 0));
        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.setToolTipText("Login using your ID number and password.");
        btnLogin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnLogin.setContentAreaFilled(false);
        btnLogin.setOpaque(true);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnRegister.setBackground(new java.awt.Color(255, 0, 0));
        btnRegister.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRegister.setForeground(new java.awt.Color(255, 255, 255));
        btnRegister.setText("Register");
        btnRegister.setToolTipText("Don't have an account? Click here to register.");
        btnRegister.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnRegister.setContentAreaFilled(false);
        btnRegister.setOpaque(true);
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(255, 0, 0));
        btnExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("Exit");
        btnExit.setToolTipText("Terminate the current program");
        btnExit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnExit.setContentAreaFilled(false);
        btnExit.setOpaque(true);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        sliderRiskScore.setBackground(new java.awt.Color(255, 255, 255));
        sliderRiskScore.setForeground(new java.awt.Color(0, 204, 51));
        sliderRiskScore.setMajorTickSpacing(1);
        sliderRiskScore.setMaximum(10);
        sliderRiskScore.setPaintLabels(true);
        sliderRiskScore.setPaintTicks(true);
        sliderRiskScore.setSnapToTicks(true);
        sliderRiskScore.setValue(0);
        sliderRiskScore.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Risk Score");

        javax.swing.GroupLayout panContainerLayout = new javax.swing.GroupLayout(panContainer);
        panContainer.setLayout(panContainerLayout);
        panContainerLayout.setHorizontalGroup(
            panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panContainerLayout.createSequentialGroup()
                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panContainerLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sliderRiskScore, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panContainerLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(panContainerLayout.createSequentialGroup()
                                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIDNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                    .addComponent(txtPassword))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblIDReq)
                                    .addComponent(lblPWReq))))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        panContainerLayout.setVerticalGroup(
            panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panContainerLayout.createSequentialGroup()
                .addComponent(panHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIDReq))
                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panContainerLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panContainerLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPWReq)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sliderRiskScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        Registration register = new Registration();
        register.setState(JFrame.NORMAL);
        this.dispose();
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        IDNumber = txtIDNumber.getText();
        password = txtPassword.getText();
        if ((IDNumber.length() > 0 && IDNumber.length() == 13 && IDNumber.matches("[0-9]+")) && password.length() > 0) {
            HideRequiredLabels();

            //Anti robot checking
            boolean antiRobotResult = false;
            int iteration = 0;
            while (!antiRobotResult) {
                antiRobotResult = antiRobot.displayAntiRobot();
                if (iteration > 0) {
                    riskScore += riskCalc.UNKNOWN_IMAGE_TEXT_DISPLACEMENT;
                    sliderRiskScore.setValue(riskScore);
                }
                if (iteration >= 2 && !antiRobotResult) {
                    System.exit(0);
                }
                iteration++;
            }

            String[] accountDetails = dbConnector.GetAccountID(IDNumber, password);
            if (accountDetails[0] != null) {
                accountID = accountDetails[0];
                roleLevel = Integer.parseInt(accountDetails[1]);
                if (dbConnector.IsPasswordExpired(accountID)) {
                    dbConnector.ProcessNewAccountPassword(accountID);
                }

                //Calculates the risk score
                DetermineRiskScore();

                //Update # of logins for used device, or create a new device if it's unknown
                HandleAccountDevices();

                //Create a session
                dbConnector.CreateNewSession(accountID, loginGeoLocation, IPAddress, deviceID, riskScore);

                //If the risk score is too high, ask for a one time pin
                boolean successfulLogin = true;
                if (IsAtRisk()) {
                    successfulLogin = GenerateOneTimePin();
                }

                if (successfulLogin) {
                    GeneratePopupMessage("Successfully logged in for ID Number " + IDNumber + ". Your account number is " + accountID);
                    if (accountDetails[1].equals("1")) {
                        Administration admin = new Administration();
                        this.dispose();
                    }
                }
            } else {
                GeneratePopupMessage("Error loggin in. Possible causes for this problem are:<br>"
                        + "1. The details you entered are incorrect.<br>"
                        + "2. There doesn't exist an account with the following ID Number " + IDNumber + "<br>"
                        + "3. The account has been blocked. Contact the database administrator if this is your problem.<br>"
                        + "Please try again.");
            }
        } else {
            if (IDNumber.length() != 13 || !IDNumber.matches("[0-9]+")) {
                lblIDReq.setVisible(true);
            }
            if (password.length() == 0) {
                lblPWReq.setVisible(true);
            }
            GeneratePopupMessage("Unsuccessful login. Please enter both your ID and password");
        }

        txtPassword.setText("");
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblIDReq;
    private javax.swing.JLabel lblPWReq;
    private javax.swing.JPanel panContainer;
    private javax.swing.JPanel panHeader;
    private javax.swing.JSlider sliderRiskScore;
    private javax.swing.JTextField txtIDNumber;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
