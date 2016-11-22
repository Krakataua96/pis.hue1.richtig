package pis.hue1;

/**
 * Created by Henrik on 18.11.2016.
 */
import java.lang.StringBuilder;

public class Wuerfel implements Codec{

    /*  Klasseninvariante

        Die Losung darf niemals Leer sein, Leerzeichen oder Umlaute enthalten.

     */

    private String losung; //Losungswort
    private int[] permutation;

    public String kodiere(String klartext){

        String ausgabe = "";
        for(int j = 1; j <= permutation.length; j++) {
            int position = position(j);   //index der 1 ausgeben lassen
            for (int i = position; i < klartext.length(); i += permutation.length) { //an der position des indexes der zahl starten und immer um die länge der perlmutationm weiterrücken
                ausgabe += klartext.charAt(i); //buchstaben an ausgabestring anhängen
            }
        }
        return(ausgabe); //ausgabestring zurückgeben
    }


    public String dekodiere(String geheimtext){

        StringBuilder builder = new StringBuilder("");
        builder.setLength(geheimtext.length());

        int k = 0;
        for(int j = 1; j <= permutation.length; j++) {
            int position = position(j);   //index der 1 ausgeben lassen
            for (int i = position; i < geheimtext.length(); i += permutation.length) {   //an der Stelle "position" beginnen und chararray an den richtigenstellen mit den Werten füllen
                builder.delete(i,i+1);
                builder.insert(i, geheimtext.charAt(k));
                k++;
            }
        }
        return(builder.toString());  //Chararray zu String
    }


    public String gibLosung(){
        return(losung);
    }


    public void setzeLosung(String schluessel) throws IllegalArgumentException{
        this.losung = schluessel.toLowerCase();
        this.permutation = permutation();
        if (schluessel.equals("")) throw new IllegalArgumentException("Es muss ein Losungswort geben!");
        if (schluessel.contains("Ä")||losung.contains("Ö")||losung.contains("Ü")||schluessel.contains("ä")||losung.contains("ö")||losung.contains("ü")) throw new IllegalArgumentException("Umlaute müssen umgeformt werden (B.s.p: \"Ä/ä\" zu \"ae\")!");
        if (schluessel.contains(" ")) throw new IllegalArgumentException("Keine Leerzeichen in der Losung!");
    }



    int[] permutation(){

        char[] buchstaben = losung.toCharArray();   //losung in chararray um danach zu sortieren

        java.util.Arrays.sort(buchstaben);  //chararray "buchstaben" sortieren

        String sortiert = new String(buchstaben);

        permutation = new int[sortiert.length()];    //Array der selben länge wie das Losungswort erstellen

        //Perlmutation erstellen
        for(int j = 0;j < sortiert.length();j++){
            for(int i= 0;i < sortiert.length();i++){
                if(sortiert.charAt(j) == losung.charAt(i)&&permutation[i]==0){
                    permutation[i] = j+1;
                    break;
                }
            }
        }
        return permutation;
    }

    //gibt die position einer zahl im array zurück
    int position(int zahl){

        for(int i = 0; i < permutation.length; i++){
            if(permutation[i]==zahl) {
                return i;
            }
        }

        return 0; //wird nie erreicht da die if bedingung immer irgendwan zutrifft
    }
}
