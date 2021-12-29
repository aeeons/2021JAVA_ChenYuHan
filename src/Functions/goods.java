package Functions;

public class goods {
    public String id;
    public String name;
    public String price;
    public String date;
    public String type;
    public String place;
    public String mark;

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
