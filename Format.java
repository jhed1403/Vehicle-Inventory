package vehicle.inventory;

import java.text.DecimalFormat;

public class Format {

    public static String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

    public static String addDollar(String price) {
        //DecimalFormat object that formats a number to two decimal places
        double d = Double.parseDouble(price);
        DecimalFormat df = new DecimalFormat("0.00");
        String formatted = df.format(d); //Implementing the DecimalFormat
        return ("$") + formatted;
    }

    public static String removeDollar(String price) {
        String result = price.replaceAll("\\.0*$", "");
        String p = result.replaceAll("[$]", "");
        return p;
    }
    
    public static String currencyFormat(String price){
        double d = Double.parseDouble(price);
        DecimalFormat df = new DecimalFormat("0.00");
        String formatted = df.format(d); //Implementing the DecimalFormat
        return formatted;
    }
}
