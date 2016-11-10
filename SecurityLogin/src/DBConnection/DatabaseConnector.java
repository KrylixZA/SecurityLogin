/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author Simon
 */
public class DatabaseConnector {

    //Use this if on local network of server
    protected String connString = "jdbc:sqlserver://10.0.0.101\\Inst2;database=Security_login;user=Security_Login;password=COMP306#2016";
    //Use this if on external network
    //protected String connString = "jdbc:sqlserver://projects.quantecore.com:1493;database=Security_login;user=Security_Login;password=COMP306#2016";
    private Connection conn;
    private CallableStatement stmt;
    ResultSet rs;

    public DatabaseConnector() {
    }

    public void CreateAccount(String[] accountDetails) {
        //Generates password into hashcode
        accountDetails[1] = Integer.toString(accountDetails[1].hashCode());

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_CreateAccount (?,?,?,?,?,?,?)}";
            stmt = conn.prepareCall(sql);

            stmt.setString("IDNumber", accountDetails[0]);
            stmt.setString("Password", accountDetails[1]);
            stmt.setString("Name", accountDetails[2]);
            stmt.setString("Surname", accountDetails[3]);
            stmt.setString("CellphoneNumber", accountDetails[4]);
            stmt.setString("Email", accountDetails[5]);
            stmt.setString("ShurePhrase", accountDetails[6]);

            stmt.executeUpdate();
            conn.close();
            GeneratePopupMessage("Account created successfully for ID Number " + accountDetails[0]);
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch
    }

    public void CreateAccountPassword(String accountID, String password) {
        //Generates password into hashcode
        password = Integer.toString(password.hashCode());

        //Generate password expiry date
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 2;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String date = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);
        java.sql.Date expiryDate = java.sql.Date.valueOf(date);

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_CreateAccountPassword (?,?,?)}";
            stmt = conn.prepareCall(sql);

            stmt.setString("AccountID", accountID);
            stmt.setString("Password", password);
            stmt.setDate("ExpiryDate", expiryDate);

            stmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch
    }
    
    public void CreateNewAccountDevice(String accountID, String deviceName, String MACAddress) {
        int numOfLogins = 1;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_CreateAccountDevice (?,?,?,?)}";
            stmt = conn.prepareCall(sql);

            stmt.setString("AccountID", accountID);
            stmt.setString("DeviceName", deviceName);
            stmt.setString("MACAddress", MACAddress);
            stmt.setInt("NumberOfLogins", numOfLogins);

            stmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch
    }

    public void CreateNewSession(String accountID, String geoLocation, String IPAddress, String deviceID, int riskScore) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_CreateSession (?,?,?,?,?)}";
            stmt = conn.prepareCall(sql);

            stmt.setString("AccountID", accountID);
            stmt.setString("LoginGeoLocation", geoLocation);
            stmt.setString("IPAddress", IPAddress);
            stmt.setString("DeviceID", deviceID);
            stmt.setInt("SessionRiskScore", riskScore);

            stmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch
    }
    
    public void CreateOneTimePin(String accountID, String pin, String sessionID) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_CreateOneTimePin (?,?,?)}";
            stmt = conn.prepareCall(sql);

            stmt.setString("AccountID", accountID);
            stmt.setString("Pin", pin);
            stmt.setString("SessionID", sessionID);

            stmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch
    }

    public void UpdateAccountPassword(String accountID, String password) {
        //Generates password into hashcode
        password = Integer.toString(password.hashCode());

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_UpdateAccountPassword (?, ?)}";
            stmt = conn.prepareCall(sql);

            stmt.setString("AccountID", accountID);
            stmt.setString("Password", password);

            stmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch
    }
    
    public void UpdateAccountAsBlocked(String accountID){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_MarkAccountAsBlocked (?)}";
            stmt = conn.prepareCall(sql);

            stmt.setString("AccountID", accountID);

            stmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch
    }
    
    public void UpdateMACAddressCount(String accountID, String MACAddress) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_IncreaseMACAddressLoginCount (?, ?)}";
            stmt = conn.prepareCall(sql);

            stmt.setString("AccountID", accountID);
            stmt.setString("MACAddress", MACAddress);

            stmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch
    }
    
    public void UpdateSessionRiskScore(String sessionID, int riskScore) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_UpdateSessionRiskScore (?, ?)}";
            stmt = conn.prepareCall(sql);

            stmt.setString("SessionID", sessionID);
            stmt.setInt("RiskScore", riskScore);

            stmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch
    }
    
    public ArrayList<String> GetUsedAccountIDs() {
        ArrayList<String> usedAccountIDs = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_SelectUsedAccountIDs (?)}";
            stmt = conn.prepareCall(sql);

            //Do not forget this!!!
            stmt.registerOutParameter("AccountID", java.sql.Types.NVARCHAR);

            rs = stmt.executeQuery();
            while (rs.next()) {
                usedAccountIDs.add(rs.getString("AccountID"));
            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch

        return usedAccountIDs;
    }

    public String[] GetAccountID(String IDNumber, String password) {
        //Generates password into hashcode
        password = Integer.toString(password.hashCode());

        String[] accountDetails = new String[2];
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_SelectAccountIDAndRoleLevel (?,?,?,?)}";
            stmt = conn.prepareCall(sql);
            stmt.setString("IDNumber", IDNumber);
            stmt.setString("Password", password);

            stmt.registerOutParameter("AccountID", java.sql.Types.NVARCHAR);
            stmt.registerOutParameter("RoleLevel", java.sql.Types.INTEGER);

            rs = stmt.executeQuery();
            while (rs.next()) {
                accountDetails[0] = rs.getString("AccountID");
                accountDetails[1] = rs.getString("RoleLevel");
            }

            rs.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch
        return accountDetails;
    }

    private String GetAccountLatestPasswordExpiryDate(String accountID) {
        String expiryDate = "";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_SelectAccountLatestPasswordExpiryDate (?,?)}";
            stmt = conn.prepareCall(sql);
            stmt.setString("AccountID", accountID);

            stmt.registerOutParameter("PasswordExpiryDate", java.sql.Types.NVARCHAR);

            rs = stmt.executeQuery();
            while (rs.next()) {
                expiryDate = rs.getString("PasswordExpiryDate");
            }

            rs.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch

        return expiryDate;
    }
    
    public String GetAccountSurePhrase(String accountID) {
        String surePhrase = "";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            //NB:   The first 3 ? = the input parameters
            //      The final ? = the output parameter
            //To develop a stored proc that returns multiple columns we will need one ? per output column and one ? per input column
            String sql = "{ call pr_SelectAccountSurePhrase (?, ?)}";
            stmt = conn.prepareCall(sql);
            stmt.setString("AccountID", accountID);

            stmt.registerOutParameter("ShurePhrase", java.sql.Types.NVARCHAR);

            rs = stmt.executeQuery();
            while (rs.next()) {
                surePhrase = rs.getString("ShurePhrase");
            }

            rs.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch
        return surePhrase;
    }

    public ArrayList<String> GetAccountMACAddresses(String accountID) {
        ArrayList<String> accountMACAddresses = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_SelectAccountMACAddresses (?,?)}";
            stmt = conn.prepareCall(sql);

            stmt.setString("AccountID", accountID);

            //Do not forget this!!!
            stmt.registerOutParameter("MACAddress", java.sql.Types.NVARCHAR);

            rs = stmt.executeQuery();
            while (rs.next()) {
                accountMACAddresses.add(rs.getString("MACAddress"));
            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch
        return accountMACAddresses;
    }

    public ArrayList<String> GetAccountDeviceNames(String accountID) {
        ArrayList<String> accountDeviceNames = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_SelectAccountDeviceName (?,?)}";
            stmt = conn.prepareCall(sql);

            stmt.setString("AccountID", accountID);

            //Do not forget this!!!
            stmt.registerOutParameter("DeviceName", java.sql.Types.NVARCHAR);

            rs = stmt.executeQuery();
            while (rs.next()) {
                accountDeviceNames.add(rs.getString("DeviceName"));
            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch

        return accountDeviceNames;
    }

    public ArrayList<String> GetAccountIPAddresses(String accountID) {
        ArrayList<String> accountIPAddresses = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_SelectAccountIPAddresses (?,?)}";
            stmt = conn.prepareCall(sql);

            stmt.setString("AccountID", accountID);

            //Do not forget this!!!
            stmt.registerOutParameter("IPAddress", java.sql.Types.NVARCHAR);

            rs = stmt.executeQuery();
            while (rs.next()) {
                accountIPAddresses.add(rs.getString("IPAddress"));
            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch

        return accountIPAddresses;
    }

    public ArrayList<String> GetAccountGeoLocations(String accountID) {
        ArrayList<String> knownGeoLocations = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_SelectAccountGeoLocations (?,?)}";
            stmt = conn.prepareCall(sql);

            stmt.setString("AccountID", accountID);

            //Do not forget this!!!
            stmt.registerOutParameter("LoginGeoLocation", java.sql.Types.NVARCHAR);

            rs = stmt.executeQuery();
            while (rs.next()) {
                knownGeoLocations.add(rs.getString("LoginGeoLocation"));
            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch

        return knownGeoLocations;
    }

    public ArrayList<String> GetUsedOneTimePins() {
        ArrayList<String> usedOneTimePins = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_SelectUsedOneTimePins (?)}";
            stmt = conn.prepareCall(sql);

            //Do not forget this!!!
            stmt.registerOutParameter("PIN", java.sql.Types.NVARCHAR);

            rs = stmt.executeQuery();
            while (rs.next()) {
                usedOneTimePins.add(rs.getString("PIN"));
            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch
        return usedOneTimePins;
    }

    public ArrayList<String> GetUsedIDs() {
        ArrayList<String> usedIDs = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_SelectUsedIDs (?)}";
            stmt = conn.prepareCall(sql);

            //Do not forget this!!!
            stmt.registerOutParameter("IDNumber", java.sql.Types.NVARCHAR);

            rs = stmt.executeQuery();
            while (rs.next()) {
                usedIDs.add(rs.getString("IDNumber"));
            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch
        return usedIDs;
    }

    public ArrayList<String> GetAccountUsedPasswords(String accountID) {
        ArrayList<String> usedPasswords = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_SelectAccountUsedPasswords (?,?)}";
            stmt = conn.prepareCall(sql);

            stmt.setString("AccountID", accountID);

            //Do not forget this!!!
            stmt.registerOutParameter("Password", java.sql.Types.NVARCHAR);

            rs = stmt.executeQuery();
            while (rs.next()) {
                usedPasswords.add(rs.getString("Password"));
            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch

        return usedPasswords;
    }
    
    public ArrayList<String[]> GetSessionDetails(){
        ArrayList<String[]> sessionDetails = new ArrayList();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_SelectSessionDetails (?,?,?,?,?,?,?,?)}";
            stmt = conn.prepareCall(sql);

            stmt.registerOutParameter("IDNumber", java.sql.Types.NVARCHAR);
            stmt.registerOutParameter("IsBlocked", java.sql.Types.NVARCHAR);
            stmt.registerOutParameter("LoginGeoLocation", java.sql.Types.NVARCHAR);
            stmt.registerOutParameter("IPAddress", java.sql.Types.NVARCHAR);
            stmt.registerOutParameter("DeviceName", java.sql.Types.NVARCHAR);
            stmt.registerOutParameter("MACAddress", java.sql.Types.NVARCHAR);
            stmt.registerOutParameter("TimeIn", java.sql.Types.NVARCHAR);
            stmt.registerOutParameter("SessionRiskScore", java.sql.Types.NVARCHAR);

            rs = stmt.executeQuery();
            while (rs.next()){
                String[] rowInformation = new String[8];
                rowInformation[0] = rs.getString("IDNumber");
                rowInformation[1] = rs.getString("IsBlocked");
                rowInformation[2] = rs.getString("LoginGeoLocation");
                rowInformation[3] = rs.getString("IPAddress");
                rowInformation[4] = rs.getString("DeviceName");
                rowInformation[5] = rs.getString("MACAddress");
                rowInformation[6] = rs.getString("TimeIn");
                rowInformation[7] = rs.getString("SessionRiskScore");
                
                sessionDetails.add(rowInformation);
            }
            
            
            rs.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch
        return sessionDetails;
    }
    
    public String GetSessionID(String accountID, String geoLocation, String IPAddress, String deviceID, int riskScore) {
        String sessionID = "";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            String sql = "{ call pr_SelectSessionID (?,?,?,?,?)}";
            stmt = conn.prepareCall(sql);

            stmt.setString("AccountID", accountID);
            stmt.setString("LoginGeoLocation", geoLocation);
            stmt.setString("DeviceID", deviceID);
            stmt.setInt("SessionRiskScore", riskScore);

            stmt.registerOutParameter("SessionID", java.sql.Types.NVARCHAR);

            rs = stmt.executeQuery();
            while (rs.next()) {
                sessionID = rs.getString("SessionID");
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch

        return sessionID;
    }
    
    public String GetDeviceID(String accountID, String MACAddress) {
        String deviceID = "";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connString);

            //NB:   The first 3 ? = the input parameters
            //      The final ? = the output parameter
            //To develop a stored proc that returns multiple columns we will need one ? per output column and one ? per input column
            String sql = "{ call pr_SelectDeviceID (?, ?, ?)}";
            stmt = conn.prepareCall(sql);
            stmt.setString("AccountID", accountID);
            stmt.setString("MACAddress", MACAddress);

            //Do not forget this!!!
            stmt.registerOutParameter("DeviceID", java.sql.Types.NVARCHAR);

            rs = stmt.executeQuery();
            while (rs.next()) {
                deviceID = rs.getString("DeviceID");
            }

            rs.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            GeneratePopupMessage("Class not found.\n\n" + ex);
        } catch (SQLException ex) {
            GeneratePopupMessage("SQL Server error.\n\n" + ex);
        }// end try catch catch
        return deviceID;
    }
    
    public boolean IsPasswordExpired(String accountID) {
        Date currentDateTime = new Date();
        SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String currentDate = ymd.format(currentDateTime);
        String expiryDate = GetAccountLatestPasswordExpiryDate(accountID);

        return expiryDate.compareTo(currentDate) < 0;
    }

    private void GeneratePopupMessage(String popupMessage) {
        final String message = popupMessage + "\n\n";
        final String html1 = "<html><body style='width: ";
        final String html2 = "px'>";
        JOptionPane.showMessageDialog(null, new JLabel(html1 + "400" + html2 + message));
    }

    public void ProcessNewAccountPassword(String accountID) {
        String password = "";
        ArrayList<String> usedPasswords = GetAccountUsedPasswords(accountID);
        do {
            JPasswordField txtPassword = new JPasswordField();
            final JComponent[] components = {
                new JLabel("Your account password has expired. Please enter a new password. \n\nYou cannot re-use a password."),
                txtPassword
            };
            JOptionPane.showMessageDialog(null, components, "Enter new password", JOptionPane.WARNING_MESSAGE);
            password = txtPassword.getText();
        } while (usedPasswords.contains(Integer.toString(password.hashCode())));

        CreateAccountPassword(accountID, password);
        UpdateAccountPassword(accountID, password);
    }
}
