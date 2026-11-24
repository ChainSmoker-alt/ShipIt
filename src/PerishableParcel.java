public class PerishableParcel extends Parcel{
    {
        baseCost = 3;
    }
    private int timeToLive;
    private int currentDay;
    public PerishableParcel (String description, int weight, String deliveryAddress, int sendDay, int timeToLive, int currentDay){
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
        this.currentDay = currentDay;
    }
    public boolean isExpired(){
        if(timeToLive+currentDay<=sendDay){
            return false;
        } else return true;
    }
}
