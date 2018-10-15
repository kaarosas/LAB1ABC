
package LAB2Baranauskas;
import studijosKTU.*;

public class VaisiuParduotuvė {
    
    public ListKTUx<Vaisiai> visiVaisiai = new ListKTUx<>(new Vaisiai());
    private static final Vaisiai bazinisVaisius = new Vaisiai();
    
    
    
    public ListKTUx<Vaisiai> atrinktiLengviausius(double riba) {
        ListKTUx<Vaisiai> naujiVaisiai = new ListKTUx<>(bazinisVaisius);
        for (Vaisiai a : visiVaisiai) {
            if(a.getSvoris() <= riba) naujiVaisiai.add(a);
        }
        return naujiVaisiai;
    }
    
    public ListKTUx<Vaisiai> atrinktiPagalKainą(double riba1, double riba2) {
        ListKTUx<Vaisiai> vidutiniaiVaisiai = new ListKTUx(bazinisVaisius);
        for (Vaisiai a : visiVaisiai) {
            if (a.getKaina() >= riba1 && a.getKaina() <= riba2) {
                vidutiniaiVaisiai.add(a);
            }
        }
        return vidutiniaiVaisiai; 
    }
    
    public ListKTUx<Vaisiai> maksimaliosKainosVaisiai() {
        ListKTUx<Vaisiai> brangiausiVaisiai = new ListKTUx(bazinisVaisius);       
        double maxKaina = 0;
        for (Vaisiai a : visiVaisiai) {
            double kaina = a.getKaina();
            if (kaina >= maxKaina) {
                if (kaina > maxKaina) {
                    brangiausiVaisiai.clear();
                    maxKaina = kaina;
                }
                brangiausiVaisiai.add(a);
            }
        }
        return brangiausiVaisiai;
    }
    
    public ListKTUx<Vaisiai> atrinktiSpalvą(String spalva) {
        ListKTUx<Vaisiai> spalvotiVaisiai = new ListKTUx(bazinisVaisius);
        for (Vaisiai a : visiVaisiai) {
            String vaisiausSpalva = a.getSpalva();
            if (vaisiausSpalva.startsWith(spalva)) {
                spalvotiVaisiai.add(a);
            }
        }
        return spalvotiVaisiai;
    }
    // metodo main nėra -> demo bandymai klasėje VaisiuBandymai
}
