package Logic;

//refferenced from: https://code.google.com/archive/p/mygeoloc/downloads
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Geobytes {

    public static boolean loaded = false;
    private static HashMap<String, String> variables = new HashMap<String, String>();

    public static final String COUNTRY_ID = "CountryId";
    public static final String COUNTRY = "Country";
    public static final String FIPS104 = "Fips104";
    public static final String ISO2 = "Iso2";
    public static final String ISO3 = "Iso3";
    public static final String ISON = "Ison";
    public static final String INTERNET = "Internet";
    public static final String CAPITAL = "Capital";
    public static final String MAP_REFERENCE = "MapReference";
    public static final String NATIONALITY_SINGULAR = "NationalitySingular";
    public static final String NATIONALITY_PLURAL = "NationalityPlural";
    public static final String CURRENCY = "Currency";
    public static final String CURRENCY_CODE = "CurrencyCode";
    public static final String POPULATION = "Population";
    public static final String COUNTRY_TITLE = "CountryTitle";
    public static final String REGION_ID = "RegionId";
    public static final String REGION = "Region";
    public static final String CODE = "Code";
    public static final String ADM1CODE = "Adm1Code";
    public static final String CITY_ID = "CityId";
    public static final String CITY = "City";
    public static final String LATITUDE = "Latitude";
    public static final String LONGITUDE = "Longitude";
    public static final String TIMEZONE = "Timezone";
    public static final String LOCATION_CODE = "LocationCode";
    public static final String DMA = "Dma";
    public static final String CERTAINTY = "Certainty";
    public static final String IS_PROXY_FORWARDER_FOR = "IsProxyForwarderFor";
    public static final String IS_PROXY_NETWORK = "IsProxyNetwork";
    public static final String IP_ADDRESS = "IpAddress";

    public static String get(String var)
            throws MalformedURLException, IllegalArgumentException, IllegalAccessException, IOException {
        if (!loaded) {
            refresh();
            loaded = true;
        }
        return variables.get(var);
    }

    public static void refresh()
            throws MalformedURLException, IOException, IllegalArgumentException, IllegalAccessException {

        String url = "http://gd.geobytes.com/gd?after=-1&variables=Geobytes";
        ArrayList<String> fVals = JavUtil.getFieldsValues(Geobytes.class, String.class, 25);

        for (int f = 0; f < fVals.size(); f++) {
            url += ",Geobytes" + fVals.get(f);
        }

        String res = HTTPRequest.getString(url);

        String regBegin = "^var\\ss";
        String regEnd = ";$";
        String regSplit = ";var\\ss";
        String regParams = "Geobytes(.*)=(.*)";
        String regIsString = "\"(.*)\"";

        res = res.replaceAll(regBegin, "").replaceAll(regEnd, "");
        String[] fields = res.split(regSplit);

        Pattern varPtrn = Pattern.compile(regParams);
        Pattern strPtrn = Pattern.compile(regIsString);

        for (int f = 0; f < fields.length; f++) {
            Matcher varMatchr = varPtrn.matcher(fields[f]);
            if (varMatchr.find()) {
                Matcher strMatchr = strPtrn.matcher(varMatchr.group(2));
                if (strMatchr.find()) {
                    variables.put(varMatchr.group(1), strMatchr.group(1));
                } else {
                    variables.put(varMatchr.group(1), varMatchr.group(2));
                }
            }
        }
    }

    public static String getLongitude() {
        try {
            return Geobytes.get(Geobytes.LONGITUDE);
        } catch (IllegalArgumentException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getLatitude() {
        try {
            return Geobytes.get(Geobytes.LATITUDE);
        } catch (IllegalArgumentException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getExternalIP() {
        try {
            System.out.println(Geobytes.get(Geobytes.IP_ADDRESS));
            return Geobytes.get(Geobytes.IP_ADDRESS);
        } catch (IllegalArgumentException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
