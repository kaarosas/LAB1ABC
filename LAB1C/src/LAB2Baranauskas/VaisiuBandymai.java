
package LAB2Baranauskas;
import java.util.Comparator;
import java.util.Locale;
import studijosKTU.*;

public class VaisiuBandymai {
    ListKTUx<Vaisiai> bandomieji = new ListKTUx<>(new Vaisiai());
    ListKTUx<Vaisiai> bandomieji2 =  new ListKTUx<>(new Vaisiai());
    
    void metodoParinkimas()
    {
        tikrintiAtskirusVaisius();
//        bandymai();
        formuotiVaisiuSąrašą();
//        peržiūrėtiSąrašą();
//        papildytiSąrašą();
//        PatikrintiParduotuvėsApskaitą();
//        patikrintiRikiavima();
    }
    
    void tikrintiAtskirusVaisius()
    {
        Vaisiai a1 = new Vaisiai("Apelsinas", "Oranžinė", 0.20, 0.59, Boolean.TRUE);
        Vaisiai a2 = new Vaisiai("Bananas", "Geltona", 0.14, 0.39, Boolean.FALSE);
        Vaisiai a3 = new Vaisiai("Vyšnia", "Raudona", 2.20, 1.29, Boolean.TRUE);
        Vaisiai a4 = new Vaisiai();
        Vaisiai a5 = new Vaisiai();
        Vaisiai a6 = new Vaisiai();       
        a4.parse("Obuolys Raudona 0.67 0.25 TRUE");
        a5.parse("Kriaušė Žalia 0.53 0.34 FALSE");
        a6.parse("Mandarinas Oranžinė 0.09 0.15 TRUE");
        
        Ks.oun(a1);
        Ks.oun(a2);
        Ks.oun(a3);
        Ks.oun(a4);
        Ks.oun(a5);
        Ks.oun(a6);      
        
    }
    
    void bandymai()
    {
        Locale.setDefault(new Locale("EN"));
        Vaisiai a1 = new Vaisiai("Apelsinas", "Oranžinė", 0.20, 0.59, Boolean.TRUE);
        Vaisiai a2 = new Vaisiai("Bananas", "Geltona", 0.14, 0.39, Boolean.FALSE);
        Vaisiai a3 = new Vaisiai("Vyšnia", "Raudona", 2.20, 1.29, Boolean.TRUE);
        bandomieji.add(a1);
        bandomieji.add(a2);
        bandomieji.add(a3);
        bandomieji.addFirst(a2);
        bandomieji.add("Obuolys Raudona 0.67 0.25 TRUE");
        bandomieji.add("Kriaušė Žalia 0.53 0.34 FALSE");
        bandomieji.add("Mandarinas Oranžinė 0.09 0.15 TRUE");
 
    }
    
    void formuotiVaisiuSąrašą() 
    {
        Locale.setDefault(new Locale("EN"));
        Vaisiai a1 = new Vaisiai("Apelsinas", "Oranžinė", 0.20, 0.59, Boolean.TRUE);
        Vaisiai a2 = new Vaisiai("Bananas", "Geltona", 0.14, 0.39, Boolean.FALSE);
        Vaisiai a3 = new Vaisiai("Vyšnia", "Raudona", 2.20, 1.29, Boolean.TRUE);
        bandomieji.add(a1);
        bandomieji.add(a2);
        bandomieji.add(a3);
        bandomieji2.add(a1);
        bandomieji2.add(a2);
        bandomieji.addFirst(a3);
        Ks.oun(bandomieji.lastIndexOf(a3));
        Ks.oun(bandomieji.containsAll(bandomieji2));
        bandomieji.println("Pirmi 3 vaisiai");
        bandomieji.add("Obuolys Raudona 0.67 0.25 TRUE");
        bandomieji.add("Kriaušė Žalia 0.53 0.34 FALSE");
        bandomieji.add("Mandarinas Oranžinė 0.09 0.15 TRUE");
        
        bandomieji.println("Visi 6 vaisiai");
        bandomieji.forEach(System.out::println);
        
        Ks.oun("Pirmų 3 vaisių svorių vidurkis = " +
                (bandomieji.get(0).getSvoris() + bandomieji.get(1).getSvoris() +
                bandomieji.get(2).getSvoris()) /3);
        Ks.oun("Kitų 3 vaisių kainų suma = " + 
                (bandomieji.get(3).getKaina() + bandomieji.get(4).getKaina() +
                bandomieji.get(5).getKaina()));
        
        bandomieji.add(0, new Vaisiai("Obuolys", "Raudona", 0.67, 0.25, Boolean.TRUE));
        bandomieji.add(6, new Vaisiai("Kriaušė", "Žalia", 0.53, 0.34, Boolean.TRUE));
        bandomieji.set(4, a3);
        bandomieji.println("Po įterpimų");
        bandomieji.remove(7);
        bandomieji.remove(0);
        bandomieji.println("Po išmetimų");
        bandomieji.remove(0); bandomieji.remove(0); bandomieji.remove(0); 
        bandomieji.remove(0); bandomieji.remove(0); bandomieji.remove(0);
        bandomieji.println("Po visų išmetimų");
        bandomieji.remove(0);
        bandomieji.println("Po visų išmetimų");
    }
    
    void peržiūrėtiSąrašą() 
    {
        int sk = 0;
        for (Vaisiai a: bandomieji) {
            if (a.getSpalva().compareTo("Raudona") == 0)
                sk++;
        }
        Ks.oun("Raudonų vaisių yra = "+sk);
    }
    
    void papildytiSąrašą() 
    {
        for (int i = 0; i < 8; i++)
            bandomieji.add(new Vaisiai("Obuolys", "Raudona", 0.55*i, 0.25*i, Boolean.TRUE));
        bandomieji.add("Ananasas Geltona 2.14 1.59 TRUE");
        bandomieji.add("Mangas Raudona 1.01 1.12 TRUE");
        bandomieji.add("Kivis Žalia 0.20 0.89 FALSE");
        bandomieji.add("Abrikosas Geltona 1.20 0.75 TRUE");
        bandomieji.println("Testuojamų vaisių sąrašas");
        bandomieji.save("ban.txt");
    }
    
    void PatikrintiParduotuvėsApskaitą() {
        VaisiuParduotuvė aParduotuve = new VaisiuParduotuvė();
        
        aParduotuve.visiVaisiai.load("ban.txt");
        aParduotuve.visiVaisiai.println("Bandomasis rinkinys");
        
        bandomieji = aParduotuve.atrinktiLengviausius(1.50);
        bandomieji.println("Vaisiai lengvesni už 1.50kg");
        
        bandomieji = aParduotuve.atrinktiPagalKainą(0.20, 2.00);
        bandomieji.println("Kaina tarp 0.20 ir 2.00");
        
        bandomieji = aParduotuve.maksimaliosKainosVaisiai();
        bandomieji.println("Patys brangiausi vaisiai");
        
        bandomieji = aParduotuve.atrinktiSpalvą("Raudona");
        bandomieji.println("Raudoni vaisiai");
        
        int sk = 0;
        for(Vaisiai a : bandomieji) {
            sk++;
        }
        Ks.oun("Raudonų vaisių kiekis = "+sk);
    }
    
    void patikrintiRikiavima() {
        VaisiuParduotuvė vp = new VaisiuParduotuvė();
        
        vp.visiVaisiai.load("ban.txt");
        Ks.oun("========"+vp.visiVaisiai.get(0));
        vp.visiVaisiai.println("Bandomasis rinkinys");
        vp.visiVaisiai.sortBuble(Vaisiai.pagalSvoriSpalva);
        vp.visiVaisiai.println("Rūšiavimas pagal svorį ir spalvą");
        vp.visiVaisiai.sortBuble(Vaisiai.pagalKainą);
        vp.visiVaisiai.println("Rūšiavimas pagal kainą");
        vp.visiVaisiai.sortBuble(Vaisiai.pagalSvoriKaina);
        vp.visiVaisiai.println("Rūšiavimas pagal svorį ir kainą");
        vp.visiVaisiai.sortBuble(tvarkaPagalSvori);
        vp.visiVaisiai.sortBuble((a, b) -> Double.compare(a.getSvoris(), b.getSvoris()));
        vp.visiVaisiai.println("Rūšiavimas pagal svorį");
        vp.visiVaisiai.sortBuble();
        vp.visiVaisiai.println("Rūšiavimas pagal compareTo");
    }
    
    static Comparator tvarkaPagalSvori = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            double r1 = ((Vaisiai) o1).getSvoris();
            double r2 = ((Vaisiai) o2).getSvoris();
            
            if(r1<r2) return 1;
            if(r1>r2) return -1;
            return 0;
        }
    };
    
    
    public static void main(String... args)
    {
        Locale.setDefault(new Locale("EN"));
        new VaisiuBandymai().metodoParinkimas();
    }
    
}
