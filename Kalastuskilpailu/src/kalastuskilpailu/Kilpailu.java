package kalastuskilpailu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Maija
 */
public class Kilpailu {

    Scanner lukija = new Scanner(System.in);
    ArrayList<Osallistuja> osallistujat;
    private int aikaa;
    private int kalastusaika;
    private String pelaaja1;
    private List<String> muutKilpailijat;
    private int yksinVaiKaksin;

    public Kilpailu() {
        this.osallistujat = new ArrayList<>();
        this.aikaa = 0;
        this.pelaaja1 = " ";
        this.kalastusaika = 8;
        this.muutKilpailijat = new ArrayList<>();
        this.yksinVaiKaksin = 0;
    }

    public void aloita(Random random) {
        perustaMuutKilpailijat(random);
        annaOhjeet();
        if (yksinVaiKaksin==2){
            this.osallistujat.clear();
        }
        int laskuri = 0;
        while (true) {
            System.out.println("Anna nimi: ");
            String nimi = lukija.nextLine();
            nimi = "* " + nimi + " *";
            Osallistuja kalastaja = new Osallistuja(nimi);
            osallistujat.add(kalastaja);
            this.pelaaja1 = kalastaja.getNimi();
            laskuri++;
            if (yksinVaiKaksin == 1) {
                break;
            }
            if (laskuri == 2) {
                break;
            }
        }
        if (yksinVaiKaksin == 1) {
 // Yksinpeli           
            Osallistuja kalastaja1 = osallistujat.get(osallistujat.size() - 1);
            kalasta(kalastaja1, random);
            laskeTilasto();
        }else{
 // Kaksinpeli    
        kaksinpeli(osallistujat, random);
        laskeTilasto();
        }
    }

    private void annaOhjeet() {
        System.out.println("*** KALASTUSKILPAILU ***");
        System.out.println("");
        System.out.println("Järvessä on sinttejä, särkiä, ahvenia ja haukia.");
        System.out.println("Syötteinä käytetään matoja, joita on aluksi 5 kpl.");
        System.out.println("Sintit ja särjet eivät kelpaa kilpailukaloiksi, mutta niitä voi käyttää syötteinä.");
        System.out.println("");
        System.out.println("Yksinpelissä järvellä on 2-" + muutKilpailijat.size() + " muuta kilpailijaa.");
        System.out.println("Aikaa kuluu 1 min / kalastusyritys. Kalastusaikaa on " + this.kalastusaika + " min.");
        System.out.println("Kaksinpelissä on mukana vain molemmat pelaajat. Kalastus jatkuu, kunnes syötit loppuvat.");
        System.out.println("");
        System.out.println("Yksin- vai kaksinpeli? Vastaa 1 tai 2");
        String vastaus = "";
        while (true){
        vastaus = lukija.nextLine();
        if (vastaus.equals("1") || vastaus.equals("2")){
            break;
        }
            System.out.println("Virheellinen vastaus: " + vastaus +", vastaa 1 tai 2");
        }
        this.yksinVaiKaksin = Integer.parseInt(vastaus);
        System.out.println("Onnea kilpailuun!");
        System.out.println("");
        System.out.println("******************************************");
    }

    private void perustaMuutKilpailijat(Random random) {

        muutKilpailijat.add("Maija");
        muutKilpailijat.add("Eemeli");
        muutKilpailijat.add("UKK");
        muutKilpailijat.add("Stubbi");
        muutKilpailijat.add("Konsta P");
        muutKilpailijat.add("Mökin muija");
        muutKilpailijat.add("Hessu");
        muutKilpailijat.add("Hanski");
        muutKilpailijat.add("Kala-Kalle");
        muutKilpailijat.add("HYLJE");
        Collections.shuffle(muutKilpailijat);
        int muitaKilpailijoita = random.nextInt(muutKilpailijat.size());
        if (muitaKilpailijoita < 2) {
            muitaKilpailijoita = 2;
        }
        for (int i = 0; i < muitaKilpailijoita; i++) {
            Osallistuja osallistuja = new Osallistuja(muutKilpailijat.get(i));
            osallistujat.add(osallistuja);
        }
        for (Osallistuja osallistuja : osallistujat) {
            kalasta(osallistuja, random);
        }
    }

    public void kalasta(Osallistuja osallistuja, Random random) {
        HeitaOnki ongi = new HeitaOnki();
        int[] syotit = osallistuja.getSyottienMaara();
        String tulostus = "";
        this.aikaa = 0;
        if (osallistuja.getNimi().equals(pelaaja1)){
        System.out.println(osallistuja.getNimi() + ": Syötit: " + osallistuja.getSyotit());
        }
        while (true) {
            tulostus = "";
            if (this.aikaa >= kalastusaika || (syotit[0] + syotit[1] + syotit[2]) == 0) {
                tulostus = ("Aikaa jäljellä: " + kelloKay()
                        + " tai madot, sintit ja särjet on loppu.\nKiitos osallistumisestasi.");
                break;
            }
            tulostus = tutkiTilanne(osallistuja, syotit, ongi, random);
            if (osallistuja.getNimi().equals(pelaaja1)) {
                System.out.println(tulostus);
                System.out.println("");
                System.out.println(osallistuja.getNimi() + ": Syötit: " + osallistuja.getSyotit());
            }
        }
        if (osallistuja.getNimi().equals(pelaaja1)) {
            System.out.println(tulostus);
        }
    }

    private String kysySyotti(Osallistuja osallistuja, int[] syotit) {
        if (yksinVaiKaksin == 1) {
            System.out.println("Aikaa jäljellä: " + kelloKay());
        }
        if (osallistuja.getNimi().equals(pelaaja1) || yksinVaiKaksin == 2) {
            System.out.print("Mikä syötti? ma=mato, si=sintti, sa=särki ");
            String syotti = lukija.nextLine();
            return syotti;
        } else {
            kelloKay();
            if (syotit[0] > 0) {
                return Vakioita.MATO;
            } else if (syotit[1] > 0) {
                return Vakioita.SINTTI;
            } else if (syotit[2] > 0) {
                return Vakioita.SARKI;
            }
        }
        return " ";
    }

    private String kelloKay() {
        String aikaaJaljella = kalastusaika - aikaa + " min";
        aikaa++;
        return aikaaJaljella;
    }

    private void laskeTilasto() {
        System.out.println("\n*****************************\n");
        System.out.println("\nTulokset, kilpailijoita oli " + osallistujat.size() + ".");
        HashMap<Integer, String> saaliit = new HashMap<>();
        String tulostus = "";
        int paino = 0;
        List<Integer> painot = new ArrayList<Integer>();
        for (Osallistuja osallistuja : osallistujat) {
            paino = osallistuja.getSaaliinPaino();
            if (paino != 0){
            saaliit.put(paino, osallistuja.getNimi());
            painot.add(paino);
            }
        }
        Collections.sort(painot);
        Collections.reverse(painot);
        int sijaluku = 1;
        for (int kalojenpaino : painot) {
            tulostus = tulostus + "\n" + sijaluku + ". sija " + saaliit.get(kalojenpaino) + " " + kalojenpaino + "g";
            sijaluku++;
        }
        if (painot.get(0).equals(painot.get(1))) {
            System.out.println("!*!*!*! TULI TASAPELI !*!*!*!");
        } else {
            System.out.println("!*!*!*! ONNEA " + saaliit.get(painot.get(0)) + " VOITIT !*!*!*!");
        }

        System.out.println(tulostus + "\n");
        System.out.println("*** Kalansaaliit ***\n");
        for (Osallistuja o : osallistujat) {
            System.out.println(o.getNimi() + " " + o.getKalatJaPainot());
        }
    }

    private void kaksinpeli(ArrayList<Osallistuja> osallistujat, Random random) {
        System.out.println("*** Kaksinpeli alkaa ***\n");
        int peliloppuu1 = 0;
        int ilmoitettuKerran1 = 0;
        int peliloppuu2 = 0;
        int ilmoitettuKerran2 = 0;
        String tulostus = "";
        HeitaOnki ongi = new HeitaOnki();
        System.out.println(osallistujat.get(0).getNimi() + ": Syötit: " + osallistujat.get(0).getSyotit());
        while (true) {

            int[] syotit1 = osallistujat.get(0).getSyottienMaara();
            if (syotit1[0] + syotit1[1] + syotit1[2] == 0 && ilmoitettuKerran1 == 0) {
                tulostus = "Sinulta " + osallistujat.get(0).getNimi() + " loppuivat syötit!";
                ilmoitettuKerran1 = 1;
                peliloppuu1 = 1;
                tulostaKierros(osallistujat.get(1), tulostus);
                tulostus = "";
            }
            if (peliloppuu1 == 0) {
                tulostus = tutkiTilanne(osallistujat.get(0), syotit1, ongi, random);
                tulostaKierros(osallistujat.get(1), tulostus);
                tulostus = "";

            }
            int[] syotit2 = osallistujat.get(1).getSyottienMaara();
            if (syotit2[0] + syotit2[1] + syotit2[2] == 0 && ilmoitettuKerran2 == 0) {
                tulostus = "Sinulta " + osallistujat.get(1).getNimi() + " loppuivat syötit!";
                ilmoitettuKerran2 = 1;
                peliloppuu2 = 1;
                tulostaKierros(osallistujat.get(0), tulostus);
                tulostus = "";
            }
            if (peliloppuu2 == 0) {
                tulostus = tutkiTilanne(osallistujat.get(1), syotit2, ongi, random);
                tulostaKierros(osallistujat.get(0), tulostus);
                tulostus = "";
            }
            if (peliloppuu1 + peliloppuu2 == 2) {
                break;
            }
        }
        
    }
    private String tutkiTilanne(Osallistuja osallistuja, int[] syotit, HeitaOnki ongi, Random random) {
                String syotti = kysySyotti(osallistuja, syotit);
                String tulostus = "";
            if (syotti.equals(Vakioita.MATO) && syotit[0] > 0) {
                Kala saatu = ongi.saalis(Vakioita.MATO, random, osallistuja, pelaaja1, yksinVaiKaksin);
                syotit[0]--;
                osallistuja.lisaaKala(saatu);
                tulostus = saatu.getKala();
                if (saatu.getKalanNimi().equals("sintti")) {
                    syotit[1]++;
                }
                if (saatu.getKalanNimi().equals("särki")) {
                    syotit[2]++;
                }
            } else if (syotti.equals(Vakioita.SINTTI) && syotit[1] > 0) {
                
                Kala saatu = ongi.saalis(Vakioita.SINTTI, random, osallistuja, pelaaja1, yksinVaiKaksin);
                syotit[1]--;
                osallistuja.lisaaKala(saatu);
                 tulostus =  saatu.getKala();
                if (saatu.getKalanNimi().equals("särki")) {
                    syotit[2]++;
                }
            } else if (syotti.equals(Vakioita.SARKI) && syotit[2] > 0) {
                Kala saatu = ongi.saalis(Vakioita.SARKI, random, osallistuja, pelaaja1, yksinVaiKaksin);
                syotit[2]--;
                osallistuja.lisaaKala(saatu);
                 tulostus = saatu.getKala();

            } else if (syotit[1] == 0 && syotti.equals(Vakioita.SINTTI)) {
                tulostus = tulostus + ("Sintit on loppu.");
            } else if (syotit[0] == 0 && syotti.equals(Vakioita.MATO)) {
                tulostus = tulostus + ("Madot on loppu.");
            } else if (syotit[2] == 0 && syotti.equals(Vakioita.SARKI)) {
                tulostus = tulostus + ("Särjet on loppu.");
            } else {
                tulostus = tulostus + (syotti + " ei ole syotti!");
            }

            return tulostus;
    }

    public void tulostaKierros(Osallistuja osallistuja, String tulostus) {
        System.out.println(tulostus);
        System.out.println("");
        System.out.println("*** Kaksinpelin tilanne:");
        System.out.println(osallistujat.get(0).getNimi() + " " + osallistujat.get(0).getKalatJaKoot());
        System.out.println(osallistujat.get(1).getNimi() + " " + osallistujat.get(1).getKalatJaKoot());
        System.out.println("");
        // Kerro seuraavana vuorossa olevan syötit
        int[] syotit = osallistuja.getSyottienMaara();
        if (syotit[0] + syotit[1] + syotit[2] != 0) {
            System.out.println(osallistuja.getNimi() + ": Syötit: " + osallistuja.getSyotit());
        }
    }
}
    

