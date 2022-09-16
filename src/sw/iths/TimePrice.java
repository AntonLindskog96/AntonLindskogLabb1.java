package sw.iths;


public class TimePrice {

    private int time;
    private int price;


    public TimePrice (int time, int price) {
        this.price = price;
        this.time = time;

    }


    public void setTime(int time) {
        this.time = time;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice(){
        return this.price;
    }

    public int getTime() {
        return this.time;
    }
}



