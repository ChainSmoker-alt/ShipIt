import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class DeliveryApp <T>{

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Parcel> trackableParcel = new ArrayList<>();
    private static List<ParcelBox<StandardParcel>> listOfStandardBox = new ArrayList<>();
    private static List<ParcelBox<FragileParcel>> listOfFragileBox = new ArrayList<>();
    private static List<ParcelBox<PerishableParcel>> listOfPerishableBox = new ArrayList<>();


    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    trackParcel();
                    break;
                case 5:
                    addParcelToBox();
                    break;
                case 6:
                    printParcelsInBox();
                    break;
                case 7:
                    checkIfExpired();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Отследить посылку (доступно только для хрупких посылок)");
        System.out.println("5 — Добавить посылку в коробку");
        System.out.println("6 — Вывести список посылок в коробке");
        System.out.println("7 — Проверить, не испортилась ли посылка (только скоропортящиеся посылки)");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        System.out.println("Введите тип посылки (стандартная, хрупкая, скоропортящаяся), описание, вес, адрес доставки "
                + "и день отправления");
        System.out.println("Вы вводите тип посылки");
        String typeOfParcel = scanner.nextLine();
        System.out.println("Вы вводите описание посылки");
        String descriptionOfParcel = scanner.nextLine();
        System.out.println("Вы вводите вес посылки");
        int weightOfParcel = Integer.parseInt(scanner.nextLine());
        System.out.println("Вы вводите адрес посылки");
        String addressOfParcel = scanner.nextLine();
        System.out.println("Вы вводите день отправки посылки");
        int sendDay = Integer.parseInt(scanner.nextLine());
        if (sendDay <= 0 || sendDay > 31) {
            System.out.println("Неверный день месяца. Он не может быть меньше 1 или больше 31");
            return;
        }
        Parcel newParcel = null;
        boolean isParcelCanBeAdded = false;
        switch (typeOfParcel) {
            case "стандартная":
                newParcel = new StandardParcel(descriptionOfParcel, weightOfParcel, addressOfParcel, sendDay);
                isParcelCanBeAdded = true;
                break;
            case "хрупкая":
                newParcel = new FragileParcel(descriptionOfParcel, weightOfParcel, addressOfParcel, sendDay);
                trackableParcel.add(newParcel);
                isParcelCanBeAdded = true;
                break;
            case "скоропортящаяся":
                System.out.println("Вы вводите количество дней которые посылка может храниться");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите номер сегодняшнего дня");
                int currentDay = Integer.parseInt(scanner.nextLine());
                newParcel = new PerishableParcel(descriptionOfParcel, weightOfParcel, addressOfParcel, sendDay,
                        timeToLive, currentDay);
                isParcelCanBeAdded = true;
                break;
            default:
                System.out.println("Неверно введен тип посылки");
                break;
        }
        if (isParcelCanBeAdded) {
            allParcels.add(newParcel);
            System.out.println("Посылка добавлена");
        }
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }// Пройти по allParcels, вызвать packageItem() и deliver()
    }

    private static void calculateCosts() {
        int deliveryCost = 0;
        for (Parcel parcel : allParcels) {
            deliveryCost += parcel.calculateDeliveryCost();
        }
        System.out.println("Стоимость всех доставок составляет: " + deliveryCost + " руб.");
        // Посчитать общую стоимость всех доставок и вывести на экран
    }

    private static void trackParcel() {
        System.out.println("Введите описание, вес, адрес доставки и день отправления посылки");
        System.out.println("Вы вводите описание посылки");
        String descriptionOfParcel = scanner.nextLine();
        System.out.println("Вы вводите вес посылки");
        int weightOfParcel = Integer.parseInt(scanner.nextLine());
        System.out.println("Вы вводите адрес посылки");
        String addressOfParcel = scanner.nextLine();
        System.out.println("Вы вводите день отправки посылки");
        int sendDay = Integer.parseInt(scanner.nextLine());
        if (sendDay <= 0 || sendDay > 31) {
            System.out.println("Неверный день месяца. Он не может быть меньше 1 или больше 31");
            return;
        }
        FragileParcel parcelToTrack = new FragileParcel(descriptionOfParcel, weightOfParcel, addressOfParcel, sendDay);
        boolean contains = trackableParcel.contains(parcelToTrack);
        if (contains) {
            System.out.println("Введите текущий статус доставки");
            String newLocation = scanner.nextLine();
            parcelToTrack.reportStatus(newLocation, parcelToTrack.getDescription());
        } else System.out.println("Такой посылки нет");
    }

    private static void addParcelToBox() {
        System.out.println("Введите тип посылки (стандартная, хрупкая, скоропортящаяся), описание, вес, адрес доставки "
                + "и день отправления");
        System.out.println("Вы вводите тип посылки");
        String typeOfParcel = scanner.nextLine();
        System.out.println("Вы вводите описание посылки");
        String descriptionOfParcel = scanner.nextLine();
        System.out.println("Вы вводите вес посылки");
        int weightOfParcel = Integer.parseInt(scanner.nextLine());
        System.out.println("Вы вводите адрес посылки");
        String addressOfParcel = scanner.nextLine();
        System.out.println("Вы вводите день отправки посылки");
        int sendDay = Integer.parseInt(scanner.nextLine());
        if (sendDay <= 0 || sendDay > 31) {
            System.out.println("Неверный день месяца. Он не может быть меньше 1 или больше 31");
            return;
        }
        switch (typeOfParcel) {
            case "стандартная":
                StandardParcel newParcel = new StandardParcel(descriptionOfParcel, weightOfParcel,
                        addressOfParcel, sendDay);
                if (allParcels.contains(newParcel)) {
                    System.out.println("Введите вес и название коробки");
                    System.out.println("Вы вводите максимальный вес коробки");
                    int maxWeight = Integer.parseInt(scanner.nextLine());
                    System.out.println("Вы вводите название коробки");
                    String boxName = scanner.nextLine();
                    ParcelBox<StandardParcel> standardBox = new ParcelBox<>(maxWeight, boxName);
                    int totalWeight = standardBox.getCurrentWeight();
                    if (!(totalWeight + newParcel.getWeight() > standardBox.getMaxWeight())) {
                        standardBox.addParcel(newParcel);
                        if (!listOfStandardBox.contains(standardBox)) {
                            listOfStandardBox.add(standardBox);
                        }
                    }else System.out.println("Данная посылка не может быть добавлена в коробку, поскольку " +
                            "превышается максимальный вес коробки");
                } else System.out.println("Посылка не найдена");
                break;
            case "хрупкая":
                FragileParcel newFragileParcel = new FragileParcel(descriptionOfParcel, weightOfParcel,
                        addressOfParcel, sendDay);
                if (allParcels.contains(newFragileParcel)) {
                    System.out.println("Введите вес и название коробки");
                    System.out.println("Вы вводите максимальный вес коробки");
                    int maxWeight = Integer.parseInt(scanner.nextLine());
                    System.out.println("Вы вводите название коробки");
                    String boxName = scanner.nextLine();
                    ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(maxWeight, boxName);
                    int totalWeight = fragileBox.getCurrentWeight();
                    if (!(totalWeight + newFragileParcel.getWeight() > fragileBox.getMaxWeight())) {
                        fragileBox.addParcel(newFragileParcel);
                        if (!listOfFragileBox.contains(fragileBox)) {
                            listOfFragileBox.add(fragileBox);
                        }
                    }else System.out.println("Данная посылка не может быть добавлена в коробку, поскольку " +
                            "превышается максимальный вес коробки");
                } else System.out.println("Посылка не найдена");
                break;
            case "скоропортящаяся":
                System.out.println("Вы вводите количество дней которые посылка может храниться");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите номер сегодняшнего дня");
                int currentDay = Integer.parseInt(scanner.nextLine());
                PerishableParcel newPerishableParcel = new PerishableParcel(descriptionOfParcel, weightOfParcel,
                        addressOfParcel, sendDay, timeToLive, currentDay);
                if (allParcels.contains(newPerishableParcel)) {
                    System.out.println("Введите вес и название коробки");
                    System.out.println("Вы вводите максимальный вес коробки");
                    int maxWeight = Integer.parseInt(scanner.nextLine());
                    System.out.println("Вы вводите название коробки");
                    String boxName = scanner.nextLine();
                    ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(maxWeight, boxName);
                    int totalWeight = perishableBox.getCurrentWeight();
                    if (!(totalWeight + newPerishableParcel.getWeight() > perishableBox.getMaxWeight())) {
                        perishableBox.addParcel(newPerishableParcel);
                        if (!listOfPerishableBox.contains(perishableBox)) {
                            listOfPerishableBox.add(perishableBox);
                        }
                    }else System.out.println("Данная посылка не может быть добавлена в коробку, поскольку " +
                            "превышается максимальный вес коробки");
                } else System.out.println("Посылка не найдена");
                break;
            default:
                System.out.println("Неверно введен тип посылки");
                break;
        }
    }

    private static void printParcelsInBox() {
        System.out.println("Введите тип коробки с посылками (стандартная, хрупкая, скоропортящаяся), вес и название");
        System.out.println("Вы вводите тип коробки");
        String typeOfBox = scanner.nextLine();
        System.out.println("Вы вводите название коробки");
        String boxName = scanner.nextLine();
        System.out.println("Вы вводите максимальный вес коробки");
        int boxWeight = Integer.parseInt(scanner.nextLine());
        switch (typeOfBox){
            case "стандартная":
                printBox(listOfStandardBox, boxWeight, boxName);
                break;
            case "хрупкая":
                printBox(listOfFragileBox, boxWeight, boxName);
                break;
            case "скоропортящаяся":
                printBox(listOfPerishableBox, boxWeight, boxName);
                break;
            default:
                System.out.println("Неверно введен тип посылки");
                break;
        }
    }
    private static <T extends Parcel> void printBox(List<ParcelBox<T>> parcelBoxes, int boxWeight, String boxName){
        ParcelBox<T> box = new ParcelBox<>(boxWeight, boxName);
        int index = -1;
        if(parcelBoxes.contains(box)){
            index = parcelBoxes.indexOf(box);
        }
        if(index!=-1){
            System.out.println(parcelBoxes.get(index).getAllParcels());
        }
    }

    private static void checkIfExpired(){
        System.out.println("Введите описание, вес, адрес доставки, день отправления посылки");
        System.out.println("Вы вводите описание посылки");
        String descriptionOfParcel = scanner.nextLine();
        System.out.println("Вы вводите вес посылки");
        int weightOfParcel = Integer.parseInt(scanner.nextLine());
        System.out.println("Вы вводите адрес посылки");
        String addressOfParcel = scanner.nextLine();
        System.out.println("Вы вводите день отправки посылки");
        int sendDay = Integer.parseInt(scanner.nextLine());
        if (sendDay <= 0 || sendDay > 31) {
            System.out.println("Неверный день месяца. Он не может быть меньше 1 или больше 31");
            return;
        }
        System.out.println("Вы вводите количество дней которые посылка может храниться");
        int timeToLive = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите номер сегодняшнего дня");
        int currentDay = Integer.parseInt(scanner.nextLine());
        PerishableParcel newPerishableParcel = new PerishableParcel(descriptionOfParcel, weightOfParcel,
                addressOfParcel, sendDay, timeToLive, currentDay);
        int index = -1;
        if(allParcels.contains(newPerishableParcel)){
            index = allParcels.indexOf(newPerishableParcel);
        }
        if (index==-1){
            System.out.println("Посылка не найдена");
            return;
        }
        Parcel found = allParcels.get(index); //здесь и ниже использовался ИИ
        if (found instanceof PerishableParcel perishable) {
            System.out.println(perishable.isExpired()
                    ? "Посылка испортилась"
                    : "Посылка не испортилась");
        } else {
            System.out.println("Это не скоропортящаяся посылка");
        }
    }
}
