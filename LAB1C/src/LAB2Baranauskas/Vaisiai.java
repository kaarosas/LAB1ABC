package LAB2Baranauskas;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import studijosKTU.*;

public class Vaisiai implements KTUable<Vaisiai>
{
    //bendri duomenys visiems vaisiams
    final static private double maxSvoris = 50.00;
    final static private double minSvoris = 0.05;
    final static private double maxKaina = 100;
    final static private double minKaina = 0.10;
    
    
    //kiekvieno vaisio individualūs duomenys
    private String pavadinimas;
    private String spalva;
    private double svoris;
    private double kaina;
    private Boolean arPrinokes;

    
    public Vaisiai() { }
    
    public Vaisiai(String pav, String spal, double svor, double kain, Boolean prin)
    {
        this.pavadinimas = pav;
        this.spalva = spal;
        this.svoris = svor;
        this.kaina = kain;
        this.arPrinokes = prin;
    }
    public Vaisiai(String dataString)
    {
        this.parse(dataString);
    }
    
    @Override
    public Vaisiai create(String dataString)
    {
        Vaisiai a = new Vaisiai();
        a.parse(dataString);
        return a;
    }

    @Override
    public String validate()
    {
        String klaidosTipas = "";
        if (svoris > maxSvoris || svoris < minSvoris)
        {
            klaidosTipas = ("Netinkamas svoris, turėtu būti tarp " + minSvoris +
                    " ir " + maxSvoris);
        }
        if (kaina > maxKaina || kaina < minKaina)
        {
            klaidosTipas = ("Netinkama kaina, turėtų būti tarp " + minKaina +
                    " ir " + maxKaina);
        }
        return klaidosTipas;
    }

    @Override
    public void parse(String dataString)
    {
        try {
            Scanner ed = new Scanner(dataString);
            pavadinimas = ed.next();
            spalva = ed.next();
            svoris = ed.nextDouble();
            kaina = ed.nextDouble();
            arPrinokes = ed.hasNextBoolean();
        }
        catch (InputMismatchException e)
        {
            Ks.ern("Blogas duomenų formatas apie vaisiu -> " + dataString);
        }
        catch (NoSuchElementException e)
        {
            Ks.ern("Trūksta duomenų apie vaisiu -> " + dataString);
        }
    }
    @Override
    public String toString() {
        return String.format("%-10s %-8s %8.1f %8.1f  %-5s",
                pavadinimas, spalva, svoris, kaina, arPrinokes, validate());
    }
    
    public String getPav()
    {
        return pavadinimas;
    }
    public String getSpalva()
    {
        return spalva;
    }
    public Double getSvoris()
    {
        return svoris;
    }
    public Double getKaina()
    {
        return kaina;
    }
    public Boolean getArPrinokes()
    {
        return arPrinokes;
    }
    public void setKaina(double kaina)
    {
        this.kaina = kaina;
    }

    
    @Override
    public int compareTo(Vaisiai a) {
        //lyginame pagal svarbiausią požymį - kainą
        double kitaKaina = a.getKaina();
        if (kaina < kitaKaina) return -1;
        if (kaina > kitaKaina) return +1;                   
        return 0;
    }
    
    public final static Comparator<Vaisiai> pagalSvoriSpalva = 
            new Comparator<Vaisiai>() {          
        @Override
        public int compare(Vaisiai o1, Vaisiai o2) {        
            int cmp = o1.getSvoris().compareTo(o2.getSvoris());
            if (cmp != 0) return cmp;
            return o1.getSpalva().compareTo(o2.getSpalva());
        }
                
    };
    
    public final static Comparator pagalKainą = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            double k1 = ((Vaisiai) o1).getKaina();
            double k2 = ((Vaisiai) o2).getKaina();
            
            if (k1<k2) return -1;
            if (k1>k2) return 1;                
            return 0;
        }
    };
    
    public final static Comparator pagalSvoriKaina = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Vaisiai a1 = (Vaisiai) o1;
            Vaisiai a2 = (Vaisiai) o2;
            if(a1.getSvoris() < a2.getSvoris()) return 1;
            if(a1.getSvoris() > a2.getSvoris()) return -1;
            if(a1.getKaina() < a2.getKaina()) return 1;
            if(a1.getKaina() > a2.getKaina()) return -1;
            return 0;
        }     
    };
    
    
    public static void main(String... args) {
        
        Locale.setDefault(new Locale("EN"));
        Vaisiai a1 = new Vaisiai("Apelsinas", "Oranžinė", 0.20, 0.59, Boolean.TRUE);
        Vaisiai a2 = new Vaisiai("Bananas", "Geltona", 0.14, 0.39, Boolean.FALSE);
        Vaisiai a3 = new Vaisiai();
        Vaisiai a4 = new Vaisiai();     
        a3.parse("Obuolys Raudona 0.67 0.25 TRUE");
        a4.parse("Kriaušė Žalia 0.53 0.34 FALSE");
        
        Ks.oun(a1);
        Ks.oun(a2);
        Ks.oun(a3);
        Ks.oun(a4);
    }
}
