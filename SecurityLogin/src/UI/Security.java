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
import Logic.RiskCalculator;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author chris
 */
public class Security extends javax.swing.JFrame {

    private String[] accountDetails;
    private boolean logginIn = false;

    private char[] surePhraseChars;
    private int[] randomIndices;

    private final DatabaseConnector dbConnector = new DatabaseConnector();
    private final AntiRobot antiRobot = new AntiRobot();
    private final RiskCalculator riskCalc = new RiskCalculator();
    private int riskScore = 0;
    private final JTextField[] letterInputs = new JTextField[10];

    public Security() {
        SetupUIComponents();
    }

    public Security(String sessionID, String accountID, String IDNumber, int roleLevel, int riskScore) {
        SetupUIComponents();
        this.riskScore = riskScore;
        accountDetails = new String[]{accountID, IDNumber, Integer.toString(roleLevel)};
        logginIn = true;
        surePhraseChars = new char[10];
        randomIndices = new int[riskScore];
        RandomiseSurePhrase();

        sliderRiskScore.setValue(10);
        dbConnector.UpdateSessionRiskScore(sessionID, 10);

        JOptionPane.showMessageDialog(null, "Your account has been flagged as at high risk.\nYou have one attempt to correctly enter your SurePhrase.");
    }

    public Security(String[] accountDetails) {
        SetupUIComponents();
        this.accountDetails = accountDetails;

    }

    private void SetupUIComponents() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);

        letterInputs[0] = txtLetter1;
        letterInputs[1] = txtLetter2;
        letterInputs[2] = txtLetter3;
        letterInputs[3] = txtLetter4;
        letterInputs[4] = txtLetter5;
        letterInputs[5] = txtLetter6;
        letterInputs[6] = txtLetter7;
        letterInputs[7] = txtLetter8;
        letterInputs[8] = txtLetter9;
        letterInputs[9] = txtLetter10;

        txtLetter1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtLetter1.getText().length() >= 1) {
                    e.consume();
                } else if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    txtLetter2.requestFocus();
                }
            }
        });
        txtLetter2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtLetter2.getText().length() >= 1) {
                    e.consume();
                } else if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    txtLetter3.requestFocus();
                }
            }
        });
        txtLetter3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtLetter3.getText().length() >= 1) {
                    e.consume();
                } else if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    txtLetter4.requestFocus();
                }
            }
        });
        txtLetter4.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtLetter4.getText().length() >= 1) {
                    e.consume();
                } else if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    txtLetter5.requestFocus();
                }
            }
        });
        txtLetter5.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtLetter5.getText().length() >= 1) {
                    e.consume();
                } else if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    txtLetter6.requestFocus();
                }
            }
        });
        txtLetter6.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtLetter6.getText().length() >= 1) {
                    e.consume();
                } else if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    txtLetter7.requestFocus();
                }
            }
        });
        txtLetter7.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtLetter7.getText().length() >= 1) {
                    e.consume();
                } else if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    txtLetter8.requestFocus();
                }
            }
        });
        txtLetter8.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtLetter8.getText().length() >= 1) {
                    e.consume();
                } else if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    txtLetter9.requestFocus();
                }
            }
        });
        txtLetter9.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtLetter9.getText().length() >= 1) {
                    e.consume();
                } else if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    txtLetter10.requestFocus();
                }
            }
        });
        txtLetter10.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtLetter10.getText().length() >= 1) {
                    e.consume();
                }
            }
        });
    }

    private void RandomiseSurePhrase() {
        String surePhraseString = dbConnector.GetAccountSurePhrase(accountDetails[0]);

        //Capture each character to be used in comparison
        for (int i = 0; i < surePhraseString.length(); i++) {
            surePhraseChars[i] = surePhraseString.charAt(i);
        }

        //Generate 5 random unique indices
        for (int i = 0; i < randomIndices.length; i++) {
            randomIndices[i] = GenerateRandomInt(randomIndices);
        }

        //Sort the array in ascending order
        Arrays.sort(randomIndices);

        //Disable all the text boxes
        for (JTextField letterInput : letterInputs) {
            letterInput.setEnabled(false);
            letterInput.setForeground(Color.white);
            letterInput.setBackground(Color.red);
        }
        //Enable only those that the user must enter text for
        for (int i = 0; i < randomIndices.length; i++) {
            letterInputs[randomIndices[i]].setEnabled(true);
            letterInputs[randomIndices[i]].setForeground(Color.red);
            letterInputs[randomIndices[i]].setBackground(Color.white);
        }
    }

    private int GenerateRandomInt(int[] randomIndices) {
        int randomInt = 0;
        do {
            randomInt = (int) (Math.random() * 10);
        } while (IsIndexUsed(randomInt, randomIndices));

        return randomInt;
    }

    private boolean IsIndexUsed(int randomInt, int[] randomIndices) {
        for (int i = 0; i < randomIndices.length; i++) {
            if (randomIndices[i] == randomInt) {
                return true;
            }
        }

        return false;
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
        txtLetter1 = new javax.swing.JTextField();
        txtLetter2 = new javax.swing.JTextField();
        txtLetter3 = new javax.swing.JTextField();
        txtLetter4 = new javax.swing.JTextField();
        txtLetter5 = new javax.swing.JTextField();
        txtLetter6 = new javax.swing.JTextField();
        txtLetter7 = new javax.swing.JTextField();
        txtLetter8 = new javax.swing.JTextField();
        txtLetter9 = new javax.swing.JTextField();
        txtLetter10 = new javax.swing.JTextField();
        panHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnContinue = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        sliderRiskScore = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Security");
        setResizable(false);

        panContainer.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Enter your SurePhrase password below *");

        txtLetter1.setColumns(1);
        txtLetter1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLetter1.setForeground(new java.awt.Color(255, 0, 0));
        txtLetter1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLetter1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtLetter1.setMaximumSize(new java.awt.Dimension(1, 1));
        txtLetter1.setMinimumSize(new java.awt.Dimension(1, 1));

        txtLetter2.setColumns(1);
        txtLetter2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLetter2.setForeground(new java.awt.Color(255, 0, 0));
        txtLetter2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLetter2.setMaximumSize(new java.awt.Dimension(1, 1));
        txtLetter2.setMinimumSize(new java.awt.Dimension(1, 1));

        txtLetter3.setColumns(1);
        txtLetter3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLetter3.setForeground(new java.awt.Color(255, 0, 0));
        txtLetter3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLetter3.setMaximumSize(new java.awt.Dimension(1, 1));
        txtLetter3.setMinimumSize(new java.awt.Dimension(1, 1));

        txtLetter4.setColumns(1);
        txtLetter4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLetter4.setForeground(new java.awt.Color(255, 0, 0));
        txtLetter4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLetter4.setMaximumSize(new java.awt.Dimension(1, 1));
        txtLetter4.setMinimumSize(new java.awt.Dimension(1, 1));

        txtLetter5.setColumns(1);
        txtLetter5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLetter5.setForeground(new java.awt.Color(255, 0, 0));
        txtLetter5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLetter5.setMaximumSize(new java.awt.Dimension(1, 1));
        txtLetter5.setMinimumSize(new java.awt.Dimension(1, 1));

        txtLetter6.setColumns(1);
        txtLetter6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLetter6.setForeground(new java.awt.Color(255, 0, 0));
        txtLetter6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLetter6.setMaximumSize(new java.awt.Dimension(1, 1));
        txtLetter6.setMinimumSize(new java.awt.Dimension(1, 1));

        txtLetter7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLetter7.setForeground(new java.awt.Color(255, 0, 0));
        txtLetter7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLetter7.setMaximumSize(new java.awt.Dimension(1, 1));
        txtLetter7.setMinimumSize(new java.awt.Dimension(1, 1));

        txtLetter8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLetter8.setForeground(new java.awt.Color(255, 0, 0));
        txtLetter8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLetter8.setMaximumSize(new java.awt.Dimension(1, 1));
        txtLetter8.setMinimumSize(new java.awt.Dimension(1, 1));

        txtLetter9.setColumns(1);
        txtLetter9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLetter9.setForeground(new java.awt.Color(255, 0, 0));
        txtLetter9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLetter9.setMaximumSize(new java.awt.Dimension(1, 1));
        txtLetter9.setMinimumSize(new java.awt.Dimension(1, 1));

        txtLetter10.setColumns(1);
        txtLetter10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLetter10.setForeground(new java.awt.Color(255, 0, 0));
        txtLetter10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLetter10.setMaximumSize(new java.awt.Dimension(1, 1));
        txtLetter10.setMinimumSize(new java.awt.Dimension(1, 1));

        panHeader.setBackground(new java.awt.Color(255, 0, 0));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Security");

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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnContinue.setBackground(new java.awt.Color(255, 0, 0));
        btnContinue.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnContinue.setForeground(new java.awt.Color(255, 255, 255));
        btnContinue.setText("Continue");
        btnContinue.setToolTipText("Complete process");
        btnContinue.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnContinue.setContentAreaFilled(false);
        btnContinue.setOpaque(true);
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
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
                .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panContainerLayout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panContainerLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panContainerLayout.createSequentialGroup()
                        .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panContainerLayout.createSequentialGroup()
                                .addComponent(txtLetter1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtLetter2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtLetter3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtLetter4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtLetter5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtLetter6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtLetter7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtLetter8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtLetter9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtLetter10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(109, 109, 109))))
            .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panContainerLayout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(sliderRiskScore, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panContainerLayout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(jLabel4)))
                    .addContainerGap(67, Short.MAX_VALUE)))
        );
        panContainerLayout.setVerticalGroup(
            panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panContainerLayout.createSequentialGroup()
                .addComponent(panHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLetter1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLetter2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLetter3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLetter4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLetter5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLetter6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLetter7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLetter8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLetter9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLetter10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(277, 277, 277)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(panContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panContainerLayout.createSequentialGroup()
                    .addGap(214, 214, 214)
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(sliderRiskScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(215, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panContainer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        if (!logginIn) {
            String surePhrase = txtLetter1.getText()
                    + txtLetter2.getText()
                    + txtLetter3.getText()
                    + txtLetter4.getText()
                    + txtLetter5.getText()
                    + txtLetter6.getText()
                    + txtLetter7.getText()
                    + txtLetter8.getText()
                    + txtLetter9.getText()
                    + txtLetter10.getText();

            String[] newAccountDetails = {accountDetails[0], accountDetails[1], accountDetails[2], accountDetails[3], accountDetails[4], accountDetails[5], surePhrase};

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
            
            //Create the account
            dbConnector.CreateAccount(newAccountDetails);
            String accountID = dbConnector.GetAccountID(accountDetails[0], accountDetails[1])[0];
            dbConnector.CreateAccountPassword(accountID, accountDetails[1]);

            //Create a new device
            String MACAddress = NetworkTools.getMacAddress();
            String IPAddress = NetworkTools.getExternalIP();
            String loginGeoLocation = Geobytes.getLatitude() + ", " + Geobytes.getLongitude();

            String deviceName = "";
            try {
                InetAddress addr;
                addr = InetAddress.getLocalHost();
                deviceName = addr.getHostName();
            } catch (UnknownHostException ex) {
                System.out.println("Hostname can not be resolved");
            }
            dbConnector.CreateNewAccountDevice(accountID, deviceName, MACAddress);            

            String deviceID = dbConnector.GetDeviceID(accountID, MACAddress);
            dbConnector.CreateNewSession(accountID, loginGeoLocation, IPAddress, deviceID, riskScore);

            Login login = new Login();
            this.dispose();
        } else {
            String surePhraseString = "";
            for (int i = 0; i < randomIndices.length; i++) {
                surePhraseString += letterInputs[randomIndices[i]].getText();
            }

            boolean correctSurePhrase = true;
            for (int i = 0; i < surePhraseString.length(); i++) {
                if (surePhraseString.charAt(i) != surePhraseChars[randomIndices[i]]) {
                    correctSurePhrase = false;
                    break;
                }
            }

            if (correctSurePhrase) {
                if (accountDetails[2].equals("1")) {
                    Administration admin = new Administration();
                } else {
                    Login login = new Login(accountDetails[0], accountDetails[1], true);
                }
                this.dispose();
            } else {
                dbConnector.UpdateAccountAsBlocked(accountDetails[0]);
                JOptionPane.showMessageDialog(null, "Your SurePhrase was entered incorrectly. For security reasons your account will now be locked.\n\nPlease contact the Database Admin to get it unlocked.");
            }
        }

    }//GEN-LAST:event_btnContinueActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            Login login = new Login();
            this.dispose();
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnContinue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panContainer;
    private javax.swing.JPanel panHeader;
    private javax.swing.JSlider sliderRiskScore;
    private javax.swing.JTextField txtLetter1;
    private javax.swing.JTextField txtLetter10;
    private javax.swing.JTextField txtLetter2;
    private javax.swing.JTextField txtLetter3;
    private javax.swing.JTextField txtLetter4;
    private javax.swing.JTextField txtLetter5;
    private javax.swing.JTextField txtLetter6;
    private javax.swing.JTextField txtLetter7;
    private javax.swing.JTextField txtLetter8;
    private javax.swing.JTextField txtLetter9;
    // End of variables declaration//GEN-END:variables
}
