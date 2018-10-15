
package LAB2Baranauskas;
import java.util.Locale;
import studijosKTU.*;


public class DialogasSuVaisiu {
    VaisiuParduotuvė aParduotuve = new VaisiuParduotuvė();
    
    void bendravimasSuKlientu() {
        ListKTUx<Vaisiai> atranka = null;
        int varNr;
        String dialogMeniu = "Pasirinkimas: \n"
                + "1-skaityti iš failo; 2-papildyti sąrašą; 3-atranka pagal kainą;\n   "
                + "4-atranka pagal spalvą; 5-brangiausias vaisius; 6-pagal svori;\n  "
                + "0-baigti skaičiavimus > ";
        while((varNr = Ks.giveInt(dialogMeniu,0,6)) != 0) {
            if (varNr == 1) {
                aParduotuve.visiVaisiai.load(Ks.giveFileName());
                aParduotuve.visiVaisiai.println("Visų vaisių sąrašas");
            } else {
                if (varNr == 2)
                {
                    String vaisiuDuomenys = Ks.giveString("Nurodykite vaisiaus"
                    + "pavadinimą, spalvą, svori, kainą ir ar vaisius prinokes ar ne");
                    Vaisiai a = new Vaisiai();
                    a.parse(vaisiuDuomenys);
                    String klaida = a.validate();
                    if (klaida.isEmpty()) aParduotuve.visiVaisiai.add(a);  
                    else Ks.oun("!!! Vaisius į sarašą nepriimtas " + klaida);
                }
                else {
                    switch (varNr) {
                        case 3:
                            double r1 = Ks.giveDouble("Nurodykite apatinę kainos ribą: ");
                            double r2 = Ks.giveDouble("Nurodykite viršutinę kainos ribą: ");
                            atranka = aParduotuve.atrinktiPagalKainą(r1, r2);
                            break;
                        case 4:
                            String sp = Ks.giveString("Nurodykite spalvą: ");
                            atranka = aParduotuve.atrinktiSpalvą(sp);
                            break;
                        case 5:
                            atranka = aParduotuve.maksimaliosKainosVaisiai();
                            break;
                        case 6:
                            double sv = Ks.giveDouble("Nurodykite iki kokio svorio rinktu vaisius");
                            atranka = aParduotuve.atrinktiLengviausius(sv);
                            break;
                    }
                    atranka.println("Štai atrinktų vaisių sąrašąas");
                    atranka.save(Ks.giveString("Kur saugoti atrinktus vaisius (jei ne-tai ENTER) ?"));
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        new DialogasSuVaisiu().bendravimasSuKlientu();
    }
}
