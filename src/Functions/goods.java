package Functions;

public class goods {
    public String id;       //编号
    public String name;     //品名
    public String price;    //单价
    public String date;     //日期
    public String type;     //类型
    public String place;    //产地
    public String mark;     //备注

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
