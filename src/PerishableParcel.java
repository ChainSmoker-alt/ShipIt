public class PerishableParcel extends Parcel{
    {
        baseCost = 3;
    }
    int timeToLive;
    int currentDay;
    PerishableParcel (String description, int weight, String deliveryAddress, int sendDay, int timeToLive, int currentDay){
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
        this.currentDay = currentDay;
    }
    void timeToLive(){

    }
    boolean isExpired(){
        if(timeToLive+currentDay<=sendDay){
            return false;
        } else return true;
    }
}
