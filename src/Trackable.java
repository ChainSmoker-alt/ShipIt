public interface Trackable {
    default void reportStatus(String newLocation, String description){
        System.out.println("Хрупкая посылка " + description + " изменила местоположение на" + " '" + newLocation + "'.");
    }
}
