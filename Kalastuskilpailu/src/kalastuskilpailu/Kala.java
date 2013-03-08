
package kalastuskilpailu;

import java.util.Random;

/**
 *
 * @author Maija
 */
public class Kala {

    private String kalanNimi;
    private int paino;
    private Random random;
    private String koko;

    public Kala(String kalanNimi) {
        this.kalanNimi = kalanNimi;
        this.paino = 0;
        this.random = new Random();
        arvoPaino(kalanNimi);
        this.koko = koko;
    }

    private void arvoPaino(String kalanNimi) {
        if (kalanNimi.equals(" ")) {
//eli ei tullut kalaa
            this.paino = 0;
            return;
        }
        if (kalanNimi.equalsIgnoreCase("sintti")) {
            this.paino = 5 + this.random.nextInt(10);
            asetaKoko("sintti");
        } else if (kalanNimi.equals("särki")) {
            this.paino = 20 + this.random.nextInt(180);
            asetaKoko("särki");
        } else if (kalanNimi.equalsIgnoreCase("ahven")) {
            this.paino = 100 + this.random.nextInt(500);
            asetaKoko("ahven");
        } else if (kalanNimi.equalsIgnoreCase("hauki")) {
            this.paino = 500 + this.random.nextInt(2000);
            asetaKoko("hauki");
        }
    }

    public String getKalanNimi() {
        return kalanNimi;
    }

    public int getPaino() {
        return paino;
    }
    
    public String getKala() {
        if (kalanNimi.equals(" ")){
            return "";
        }
        return "Saalis: " +kalanNimi + ", koko=" + koko;
    }

    public String asetaKoko(String kala) {
        if (kala.equals("sintti")) {
            if (this.paino < 7) {
                koko = "pieni";
            } else if (this.paino < 12) {
                koko = "kohtalainen";
            } else {
                koko = "iso";
            }
        } else if (kala.equals("särki")) {
            if (this.paino < 60) {
                koko = "pieni";
            } else if (this.paino < 120) {
                koko = "kohtalainen";
            } else {
                koko = "iso";
            }

        } else if (kala.equals("ahven")) {
            if (this.paino < 150) {
                koko = "pieni";
            } else if (this.paino < 250) {
                koko = "kohtalainen";
            } else {
                koko = "vonkale";
            }
        } else if (kala.equals("hauki")) {
            if (this.paino < 600) {
                koko = "pieni";
            } else if (this.paino < 2000) {
                koko = "kohtalainen";
            } else {
                koko = "jättipeto";
            }

        }
        return koko;
    }

    @Override
    public String toString() {
        return "Kala{" + "kalanNimi=" + kalanNimi + '}';
    }
    
}
