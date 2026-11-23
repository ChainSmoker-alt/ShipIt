import java.util.Objects;

abstract public class Parcel {
    protected String description;
    protected int weight;
    protected String deliveryAddress;
    protected int sendDay;
    protected int baseCost = 2;


    Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    void packageItem() {
        System.out.println("Посылка " + description + " упакована");
    }

    void deliver() {
        System.out.println("Посылка " + description + " доставлена по адресу " + deliveryAddress);
    }

    int calculateDeliveryCost() {
        int cost = weight * baseCost;
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return weight == parcel.weight && sendDay == parcel.sendDay && baseCost == parcel.baseCost && Objects.equals(deliveryAddress, parcel.deliveryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, deliveryAddress, sendDay, baseCost);
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "weight=" + weight +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", sendDay=" + sendDay +
                ", baseCost=" + baseCost +
                '}';
    }
}