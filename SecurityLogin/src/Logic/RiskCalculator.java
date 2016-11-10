/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import DBConnection.DatabaseConnector;

/**
 *
 * @author Simon
 */
public class RiskCalculator {
    
    private final int UNKNOWN_GEO_LOCATION_DISPLACEMENT = 2;
    private final int UNKNOWN_MAC_ADDRESS_DISPLACEMENT = 3;
    private final int UNKNOWN_IP_ADDRESS_DISPLACEMENT = 1;
    private final int UNKNOWN_DEVICE_NAME_DISPLACEMENT = 2;
    public final int UNKNOWN_IMAGE_TEXT_DISPLACEMENT = 1;
    
    private int riskScore = 0;
    private String accountID = "";
    private String MACAddress = "";
    private String IPAddress = "";
    private String deviceName = "";
    private String geoLocation = "";
    
    private DatabaseConnector dbConnector;
    
    public RiskCalculator(){
    }
    
    public RiskCalculator(String accountID, String MACAddress, String IPAddress, String deviceName, String geoLocation){
        this.accountID = accountID;
        this.MACAddress = MACAddress;
        this.IPAddress = IPAddress;
        this.deviceName = deviceName;
        this.geoLocation = geoLocation;
    }
    
    public int CalculateRiskScore(){
        dbConnector = new DatabaseConnector();
        
        if (!dbConnector.GetAccountGeoLocations(accountID).contains(geoLocation)){
            riskScore += UNKNOWN_GEO_LOCATION_DISPLACEMENT; 
        }
        if (!dbConnector.GetAccountMACAddresses(accountID).contains(MACAddress)){
            riskScore += UNKNOWN_MAC_ADDRESS_DISPLACEMENT;
        }
        if (!dbConnector.GetAccountIPAddresses(accountID).contains(IPAddress)){
            riskScore += UNKNOWN_IP_ADDRESS_DISPLACEMENT;
        }
        if (!dbConnector.GetAccountDeviceNames(accountID).contains(deviceName)){
            riskScore += UNKNOWN_DEVICE_NAME_DISPLACEMENT;
        }
        
        if(riskScore == 10)
            return riskScore-1;
        
        return riskScore;
    }
}
