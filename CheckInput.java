package vehicle.inventory;

import java.util.Arrays;

public class CheckInput {

    public static boolean checkCarModel(String model) throws Exception {
        boolean valid = false;
        if (model.equals("")) {
            throw new Exception("Please enter CarModel");
        } else {
            valid = true;
        }
        return valid;
    }

    public static boolean checkMaker(String maker) throws Exception {
        boolean valid = false;
        if (maker.equals("")) {
            throw new Exception("Please enter CarMaker");
        } else {
            valid = true;
        }
        return valid;
    }
    
    public static boolean checkBodyType(String bType) throws Exception {
        String bodyType[] = {"Hatchback", "Coupe", "Sedan", "Minivan", "SUV", "Truck", "Wagon", "Convertible"};
        boolean valid = false;
        if (bType.equals("")) {
            throw new Exception("Please enter BodyType");
        } else {
            for(String a : bodyType){
                if(bType.equalsIgnoreCase(a)){
                    valid = true;
                    break;
                }
            }
        }
        if(valid == false){
            throw new Exception("Invalid BodyType");
        }
        return valid;
    }
    
    public static boolean checkNumberSeats(String numSeats) throws Exception{
        boolean valid = false;
        if(numSeats.equals("")){
            throw new Exception("Please enter number of seats");
        }else if(numSeats.matches("\\d+")){
            int num = Integer.parseInt(numSeats);
            if(num <= 1 || num > 7){
                throw new Exception("Number of seats must be greater than 1");
            }else{
                valid = true;
            }
        }else{
            throw new Exception("Number of seats must be an integer");
        }
        return valid;
    }
    
    public static boolean checkYear(String year) throws Exception{
        boolean valid = false;
        if(year.equals("")){
            throw new Exception("Please enter the year of the car");
        }else if(year.matches("\\d+")){
            int y = Integer.parseInt(year);
            int length = (int)(Math.log10(y)+1);
            if(length < 4 || y < 0 || y < 1980 || y > 2016){
                throw new Exception("Invalid Year");
            }else{
                valid = true;
            }  
        }else{
            throw new Exception("Year must be 4-digit integer");
        }
        return valid;
    }
    public static boolean checkPrice(String price) throws Exception{
        boolean valid = false;
        if(price.equals("")){
            throw new Exception("Please enter Price");
        }else if(price.matches("[0-9]{1,13}(\\.[0-9]{2})?")){
            double p = Double.parseDouble(price);
            if(p < 0){
                throw new Exception("Invalid Price");
            }else{
                valid = true;
            }
        }else{
            throw new Exception("Price must be a double");
        }
        return valid;
    }
    
    public static boolean checkNumberDoors(String doors) throws Exception{
        boolean valid = false;
        if(doors.equals("")){
            throw new Exception("Please enter number of doors");
        }else if(doors.matches("\\d+")){
            int d = Integer.parseInt(doors);
            if(d <= 1 || d > 5){
                throw new Exception("Invalid number of doors");
            }else{
                valid = true;
            }
        }else{
            throw new Exception("Number of doors must be an integer");
        }
        return valid;
    }
}
