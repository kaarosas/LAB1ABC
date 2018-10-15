
package LAB2Baranauskas;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;
import studijosKTU.*;

public class GreitiVaisiai {
    
    Vaisiai[] vaisiuPard;
    ListKTU<Vaisiai> vaisSar = new ListKTU<>();
    Random ag = new Random();
    int[] tiriamiKiekiai = {2_000, 4_000, 8_000, 16_000};
//    static int[] tiriamiKiekiai = {10_000, 20_000, 40_000, 80_000};
    void generuotiVaisius(int kiekis) {
        String [][] mas = {
            {"Geltona", "Abrikosas", "Raudona", "Melsva", "Apelsinas" },
            {"Žalia", "Mandarinas", "Mėlyna", "Ruda", "Gelsva", "Obuolys"},
            {"Raudona", "bananas", "Kivis"}
            
        };
        vaisiuPard = new Vaisiai[kiekis];
        ag.setSeed(2017);
        for (int i = 0; i < kiekis; i++)
        {
            int ma = ag.nextInt(mas.length);
            int mo = ag.nextInt(mas[ma].length - 1)+1;
            vaisiuPard[i] = new Vaisiai(mas[ma][0], mas[ma][mo],
                0.1 + ag.nextInt(20),
                0.20 + ag.nextInt(50),
                Boolean.TRUE);
                
        }
        Collections.shuffle(Arrays.asList(vaisiuPard));
        vaisSar.clear();
        for(Vaisiai a: vaisiuPard) vaisSar.add(a);
        
    }
    
    void paprastasTyrimas(int elemKiek) 
    {
        long t0 = System.nanoTime();
        generuotiVaisius(elemKiek);
        ListKTU<Vaisiai> vaisSar2 = vaisSar.clone();
        ListKTU<Vaisiai> vaisSar3 = vaisSar.clone();
        ListKTU<Vaisiai> vaisSar4 = vaisSar.clone();
        long t1=System.nanoTime();
        System.gc(); System.gc(); System.gc();
        long t2=System.nanoTime();
        
        vaisSar.sortSystem();
        long t3=System.nanoTime();
        vaisSar2.sortSystem(Vaisiai.pagalKainą);
        long t4=System.nanoTime();
        vaisSar3.sortBuble();
        long t5=System.nanoTime();
        vaisSar4.sortBuble(Vaisiai.pagalKainą);
        long t6=System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f \n",
                elemKiek,
                (t1-t0)/1e9, (t2-t1)/1e9, (t3-t2)/1e9,
                (t4-t3)/1e9, (t5-t4)/1e9, (t6-t5)/1e9 );

    }
    
    
    void sisteminisTyrimas()
    {
        Timekeeper tk = new Timekeeper(tiriamiKiekiai);
        for (int kiekis: tiriamiKiekiai)
        {
            generuotiVaisius(kiekis);
            ListKTU<Vaisiai> vaisSar2 = vaisSar.clone();
            ListKTU<Vaisiai> vaisSar3 = vaisSar.clone();
            ListKTU<Vaisiai> vaisSar4 = vaisSar.clone();
            
            tk.start();
            vaisSar.sortSystem();
            tk.finish("SysBeCom");
            vaisSar2.sortSystem(Vaisiai.pagalKainą);
            tk.finish("SysSuCom");
            vaisSar3.sortBuble();
            tk.finish("BurBeCom");
            vaisSar4.sortBuble(Vaisiai.pagalKainą);
            tk.finish("BurSuCom");
            tk.seriesFinish();    
        }
    }
    
    void tyrimoPasirinkimas()
    {
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= "+memTotal);
        
        generuotiVaisius(20);
        for (Vaisiai a : vaisSar) Ks.oun(a); 
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Rūšiavimas sisteminiu greitu būdu be Comparator");
        Ks.oun("4 - Rūšiavimas sisteminiu greitu būdu su Comparator");
        Ks.oun("5 - Rūšiavimas List burbuliuku be Comparator");
        Ks.oun("6 - Rūšiavimas List burbuliuku su Comparator");
        Ks.ouf("%6d %7d %7d %7d %7d %7d %7d \n", 0,1,2,3,4,5,6);
        
        for (int n: tiriamiKiekiai)
            paprastasTyrimas(n);
        sisteminisTyrimas();
    }
    
    public static void main(String[] args)
    {
        Locale.setDefault(Locale.ENGLISH);
        new GreitiVaisiai().tyrimoPasirinkimas();
    }
    
    
}
