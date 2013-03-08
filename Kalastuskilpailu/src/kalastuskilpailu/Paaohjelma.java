package kalastuskilpailu;

import java.util.Random;

/**
 *
 * @author Maija
 */
public class Paaohjelma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random random = new Random();
        Kilpailu k = new Kilpailu();
        k.aloita(random);
    }
}
