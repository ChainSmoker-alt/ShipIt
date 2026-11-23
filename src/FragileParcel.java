public class FragileParcel extends Parcel implements Trackable{
    {
        baseCost = 4;
    }
    FragileParcel(String description, int weight, String deliveryAddress, int sendDay){
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    void packageItem(){
        System.out.println("Посылка " +description+ " обёрнута в защитную плёнку");
    }
    public static String getDescription(){
        return description;
    }
}
