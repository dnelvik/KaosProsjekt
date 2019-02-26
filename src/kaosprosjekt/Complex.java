package kaosprosjekt;

/**
 * Klasse for Complexe tall
 * @author Danay Nelvik
 */
public class Complex {
    
    double imagin, reel;

    /**
     * Konstruktør for et Complex-tall objekt.
     * @param imagin Double verdi for det imaginære tallet
     * @param reel Double verdi for det reele tallet
     */
    public Complex(double imagin, double reel) {
        this.imagin = imagin;
        this.reel = reel;
    }

    /**
     * Metode for å gange sammen to komplekse tall.
     * @param c1 Complex parameter som skal ganges med
     * @return Returnerer et nytt Complex-tall som er summen av gangingen
     */
    public Complex gange(Complex c1) {
        Complex c = this;
        double re = (c.reel*c1.reel) - (c.imagin*c1.imagin);
        double im = c.reel*c1.imagin + c.imagin*c1.reel;
        return new Complex(re, im);
    }

    /**
     * Metode som brukes for å addere to Complexe tall
     * @param c1 Complex parameter som skal adderes
     * @return Returnerer et Complex som er summen av de som adderes
     */
    public Complex addere(Complex c1){
        Complex c = this;
        double re = c.reel + c1.reel;
        double im = c.imagin + c1.imagin;
        return new Complex(re, im);
    }

    /**
     * Metode som regner ut lengden av et Complex
     * @return Lengden av et Complex
     */
    public double lengde() {
        return (reel*reel)+(imagin*imagin);
    }
}
