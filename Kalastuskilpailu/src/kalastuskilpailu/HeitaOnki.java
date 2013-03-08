
package kalastuskilpailu;

import java.util.Random;

/**
 *
 * @author Maija
 */
public class HeitaOnki {

    private String syotti;
    private int[] matokalastus = {15, 20, 60};      // { _ , sintti, särki}
    private int[] sinttikalastus = {75, 100, 80};    //{ - , -, ahven}
    private int[] sarkikalastus = {0, 10, 70};     //{ - , hauki, ahven}

    public HeitaOnki() {
        this.syotti = syotti;

    }

    public Kala saalis(String syotti, Random random, Osallistuja osallistuja, String pelaaja, int yksinVaiKaksin) {
        int kalanTodennakoisyys = random.nextInt(101);
        int matoKarkasi = random.nextInt(10);
        String tulostus = "";
        if (matoKarkasi == 1) {
            matokalastus[1] = matokalastus[1] - 10;
            matokalastus[2] = matokalastus[2] - 10;
            tulostus = "OIVOI! Syöttisi putosi. Ei tullut kalaa :( mutta ehkä syötti houkuttelee kaloja lähelle.";
            tulosta(tulostus, osallistuja, pelaaja, yksinVaiKaksin );
            return new Kala(" ");
        }

        if (syotti.equals(Vakioita.MATO)) {
            if (kalanTodennakoisyys > matokalastus[2]) {
                return new Kala("särki");
            } else if (kalanTodennakoisyys > matokalastus[1]) {
                return new Kala("sintti");
            }else if (kalanTodennakoisyys > matokalastus[0]){
                tulostus = "Sait kiisken, ei kelpaa kilpailukalaksi, anna kissalle!";
                tulosta(tulostus, osallistuja, pelaaja, yksinVaiKaksin );
                return new Kala(" ");
            } else {
                tulostus = "Kala ahmatti vei syötin, yritä uudelleen! ;) ";
                tulosta(tulostus, osallistuja, pelaaja, yksinVaiKaksin );
                return new Kala(" ");
            }
        } else if (syotti.equals(Vakioita.SINTTI)) {
            if (kalanTodennakoisyys > sinttikalastus[2]) {
                return new Kala("ahven");
            } else {
                tulostus = "Kala vei syötin :( yritä uudelleen!";
                tulosta(tulostus, osallistuja, pelaaja,yksinVaiKaksin  );
                return new Kala(" ");
            }
        } else if (syotti.equals(Vakioita.SARKI)) {
            if (kalanTodennakoisyys > sarkikalastus[2]) {
                return new Kala("ahven");
            } else if (kalanTodennakoisyys > sarkikalastus[1]) {
                return new Kala("hauki");
            } else if (kalanTodennakoisyys > sarkikalastus[0]) {
                tulostus = "Tuli partahauki, eipä kelpaa kilpailuun, heitä pois!";
                tulosta(tulostus, osallistuja, pelaaja, yksinVaiKaksin );
                return new Kala(" ");
            } else {
                tulostus = "Kala vei syötin :( yritä uudelleen!";
                tulosta(tulostus, osallistuja, pelaaja, yksinVaiKaksin );
                return new Kala(" ");
            }
        } else {
            Kala kala = new Kala(" ");
            return kala;
        }
    }

    private void tulosta(String teksti,  Osallistuja osallistuja, String pelaaja, int yksinVaiKaksin) {
            if (osallistuja.getNimi().equals(pelaaja) || yksinVaiKaksin == 2){
            System.out.println(teksti);
            }
    }
}
