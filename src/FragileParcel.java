public class FragileParcel extends Parcel implements Trackable{
    {
        baseCost = 4;
    }
    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay){
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem(){
        System.out.println("Посылка " +description+ " обёрнута в защитную плёнку");
    }
    public String getDescription(){
        return description;
    }
}
