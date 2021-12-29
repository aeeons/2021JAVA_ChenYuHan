package Functions;

public class goods {
    String id;
    String name;
    String price;
    String date;
    String type;
    String place;
    String mark;

    goods() {

    }

    public goods(String id, String name, String price, String date, String type, String place, String mark) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
        this.type = type;
        this.place = place;
        this.mark = mark;
    }

    public String[] getInfo() {
        return new String[]{id, name, price, date, type, place, mark};
    }
}
