import java.util.ArrayList;
import java.util.Objects;

public class ParcelBox<T extends Parcel> {
    private int maxWeight;
    private String boxName;
    private ArrayList<T> boxWithParcel = new ArrayList<>();

    public ParcelBox(int maxWeight, String boxName) {
        this.boxName = boxName;
        this.maxWeight = maxWeight;
    }
    public void addParcel(T parcel){
boxWithParcel.add(parcel);
        System.out.println("Посылка добавлена в коробку");
    }
    public ArrayList<T> getAllParcels(){
return boxWithParcel;
    }

    public int getCurrentWeight() {
        int sum = 0;
        for (T parcel : boxWithParcel) {
            sum += parcel.getWeight();
        }
        return sum;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ParcelBox<?> parcelBox = (ParcelBox<?>) o;
        return maxWeight == parcelBox.maxWeight && Objects.equals(boxName, parcelBox.boxName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxWeight, boxName);
    }

    @Override
    public String toString() {
        return "ParcelBox{" +
                "maxWeight=" + maxWeight +
                ", boxName='" + boxName + '\'' +
                ", boxWithParcel=" + boxWithParcel +
                '}';
    }
}