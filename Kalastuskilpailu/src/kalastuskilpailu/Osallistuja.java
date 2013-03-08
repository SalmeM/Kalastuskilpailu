/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kalastuskilpailu;

import java.util.ArrayList;

/**
 *
 * @author Maija
 */
public class Osallistuja {
    private String nimi;
    private ArrayList<Kala> kalat;
    private int[]syotit;
    public Osallistuja (String nimi){
        this. nimi = nimi;
        this.kalat = new ArrayList<>();
        this.syotit = new int[3];
        syotit[0]=5;
        syotit[1]=0;
        syotit[2]=0;
        
    }
    public void lisaaKala(Kala kala){
        if (kala.equals(" ") || kala.getKalanNimi().equals("sintti") || kala.getKalanNimi().equals("särki")){
            return;
        }
        kalat.add(kala);
    }

    public String getNimi() {
        return nimi;
    }

    public String getKalat() {
        if (kalat.isEmpty()) {
            return null;
        }
        String saaliskalat = "";
        for (Kala kala : kalat) {
            String koko = "";
            saaliskalat = kala.getKala();
        }
        return saaliskalat;
    }

    public String getSyotit() {
        return "matoja: " + syotit[0] + ", sintteja: " + syotit[1] + ", särkiä: " + syotit[2];
    }

    public int[] getSyottienMaara() {
        return syotit;
    }

    public int getSaaliinPaino() {
        int paino = 0;
        if (kalat.isEmpty()) {
            return 0;
        }
        for (Kala kala : kalat) {
            paino = paino + kala.getPaino();
        }
        return paino;
    }

    public String getKalatJaPainot() {
        String vastaus = "";
        if (kalat.isEmpty()) {
            return null;
        }
        for (Kala kala : kalat) {
            if (kala.getPaino()!=0){
            vastaus = vastaus + kala.getKalanNimi() + " " + kala.getPaino() + "g ";
            }
        }
        return vastaus;
    }
    
        public String getKalatJaKoot() {
        String vastaus = "";
        if (kalat.isEmpty()) {
            return null;
        }
        for (Kala kala : kalat) {
            if (kala.getPaino()!=0){
            vastaus = vastaus +" * " + kala.getKalanNimi() + " " + kala.asetaKoko(kala.getKalanNimi());
            }
        }
        return vastaus;
    }
}
