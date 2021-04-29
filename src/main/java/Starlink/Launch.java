package Starlink;

//The only purpose of this file is to get around a bug involving extending the JavaFX's Application class
//preventing javafx from loading, we get around this by having another main method in a class that does not inherit
//the Application class, this fake main class will just call the real main method
public class Launch {
    public static void main(String[] args) {
        Starlink.main(args);
    }
}
