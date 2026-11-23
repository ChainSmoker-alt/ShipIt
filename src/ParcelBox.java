import java.util.ArrayList;
import java.util.Objects;

// Проверка добавления новой посылки в коробку. Если максимальный вес не превышен, посылка должна добавляться, а если превышен — нет.

public class ParcelBox<T extends Parcel> {
    private int maxWeight;
    private String boxName;
    private ArrayList<T> boxWithParcel = new ArrayList<>();

    public ParcelBox(int maxWeight, String boxName) {
        this.boxName = boxName;
        this.maxWeight = maxWeight;
    }

    public void addParcel(ParcelBox box, T parcel) {
        if (!(parcel.getWeight() + box.maxWeight > box.getMaxWeight())) {
            boxWithParcel.add(parcel);
            System.out.println("Посылка добавлена в коробку");
        }else System.out.println("Данная посылка не может быть добавлена в коробку, поскольку " +
                "превышается максимальный вес коробки");

    }

    public ArrayList<T> getAllParcels() {
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

    public boolean isParcelInBox(T parcel){
        return boxWithParcel.contains(parcel);
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