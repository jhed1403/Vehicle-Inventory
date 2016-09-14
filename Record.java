
package vehicle.inventory;

import javafx.beans.property.SimpleStringProperty;

public class Record {

    private final SimpleStringProperty model;
    private final SimpleStringProperty maker;
    private final SimpleStringProperty bodyType;
    private final SimpleStringProperty seats;
    private final SimpleStringProperty year;
    private final SimpleStringProperty price;
    private final SimpleStringProperty doors;

    public Record(String model, String maker, String bodyType, String numSeats, String year, String price, String numDoors) {
        this.model = new SimpleStringProperty(model);
        this.maker = new SimpleStringProperty(maker);
        this.bodyType = new SimpleStringProperty(bodyType);
        this.seats = new SimpleStringProperty(numSeats);
        this.year = new SimpleStringProperty(year);
        this.price = new SimpleStringProperty(price);
        this.doors = new SimpleStringProperty(numDoors);

    }

    public String getModel() {
        return model.get();
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getMaker() {
        return maker.get();
    }

    public void setLastName(String maker) {
        this.maker.set(maker);
    }

    public String getBodyType() {
        return bodyType.get();
    }

    public void setBodyType(String bodyType) {
        this.bodyType.set(bodyType);
    }

    public String getSeats() {
        return seats.get();
    }

    public void setSeats(String numSeats) {
        this.seats.set(numSeats);
    }

    public String getYear() {
        return year.get();
    }

    public void setYear(String year) {
        this.year.set(year);
    }

    public String getPrice() {
        return price.get();
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getDoors() {
        return doors.get();
    }

    public void setDoors(String numDoors) {
        this.doors.set(numDoors);
    }

}
