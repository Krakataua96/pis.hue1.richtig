package pis.hue1;

/**
 * Created by Henrik on 20.11.2016.
 */
interface Codec{
    public String kodiere(String klartext);
    public String dekodiere(String geheimtext);
    public String gibLosung();
    public void setzeLosung(String schluessel) throws IllegalArgumentException; //bei ungeeignetem Schlüssel
}