package lab1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LAB1 {

    public static final int MaxCarNumber = 10;
    
    public static void main(String[] args) throws ParseException
    {
        AUTO[] Cars = carArray();
        Cars = countMostCars(Cars);
        int Count = count(Cars);
        printMostCars(Cars, Count);
        
        Cars = carArray();
        Cars = volvoCount(Cars);
        Count = count(Cars);
        printVolvoCars(Cars, Count);
        
        Cars = carArray();
        Cars = countOldCars(Cars);
        Count = count(Cars);
        printOldCars(Cars, Count);
        
        Cars = carArray();
        Cars = lateCheckup(Cars);
        Count = count(Cars);
        printDueCars(Cars, Count);
        
        Cars = carArray();
        Cars = checkup(Cars);
        Count = count(Cars);
        printAlmostDueCars(Cars, Count);
        
    }
    
    public static AUTO[] carArray()
    {
        AUTO[] Cars = {
            new AUTO("ISK483","Audi","A4","1/11/2012","3/11/2018","Diesel",7.22),
            new AUTO("OAK185","Volvo","S7","1/12/2012","21/5/2013","Gasoline",8.13),
            new AUTO("KAU349","Toyota","V6","1/8/2003","16/7/2014","Gasoline",8.84),
            new AUTO("MGN154","Volvo","S5","1/11/2001","9/11/2015","Diesel",7.67),
            new AUTO("AHP659","Audi","A6","1/5/2016","30/9/2018","Gasoline",8.51),
            new AUTO("MZS224","Audi","A3","1/9/2007","21/10/2018","Diesel",7.97)
        };
        return Cars;        
    }   
    
//    public static void printCars(AUTO[] Cars, int Count)
//    {
//        for (int i = 0; i < Count; i++) 
//        {
//            System.out.print(Cars[i].toString());
//        }
//    }  
     
    public static void printMostCars(AUTO[] Cars, int Count)
    {
        System.out.print("Daugiausiai automobilių turintis gamintojas: " + Cars[0].Manufacturer + "\n");
        System.out.print(Cars[0].Manufacturer + " gamintojo automobilių kiekis " + Count);
        System.out.print("\n\n");
    }   

    public static void printVolvoCars(AUTO[] Cars, int Count)
    {
        System.out.print("Volvo automobilių sąrašas: \n");
        for (int i = 0; i < Count; i++)                   
        {
            System.out.print("Numeris: " + Cars[i].Number + " Modelis: " + Cars[i].Model + " Pagaminimo Data: " + Cars[i].Production + "\n");
        }
        System.out.print("\n");
    }
    
    public static void printOldCars(AUTO[] Cars, int Count)
    {
        System.out.print("Automobiliai senesni nei 10 metų: \n");
        for (int i = 0; i < Count; i++)
        {
            System.out.print(Cars[i].toString() + "\n");
        }
    }
    
    public static void printDueCars(AUTO[] Cars, int Count)
    {
        System.out.print("Automobiliai, kurių techninė apžiūra pasibaigė: \n");
        for (int i = 0; i < Count; i++)
        {
            System.out.print("Gamintojas: " + Cars[i].Manufacturer + "\nModelis: " + Cars[i].Model + "\nValstybinis Numeris: " + Cars[i].Number + 
                    "\nTechninės apžiūros galiojimo data: " + Cars[i].CheckUp + "\nSKUBIAI\n");
            System.out.print("\n");
        }
    }
    
    public static void printAlmostDueCars(AUTO[] Cars, int Count)
    {
        System.out.print("Automobiliai, kurių techninė apžiūra baigiasi po mažiau nei mėnesio: \n");
        for (int i = 0; i < Count; i++)
        {
            System.out.print("Gamintojas: " + Cars[i].Manufacturer + "\nModelis: " + Cars[i].Model + "\nValstybinis Numeris: " + Cars[i].Number + 
                    "\nTechninės apžiūros galiojimo data: " + Cars[i].CheckUp + "\n");
            System.out.print("\n");
        }
    }
    
    public static int count(AUTO[] array)
    {
        int c = 0;
        for(AUTO el: array) { if(el != null) c++; }
        return c;
    }
    
    public static AUTO[] countMostCars(AUTO[] cars)
    {
        AUTO[] MostCars = new AUTO[MaxCarNumber];
        int Max = 0;

        for (int i = 0; i < cars.length; i++) 
        {
            int MostCarCount = 0;
            int Count = 0;
            for (int j = i; j < cars.length; j++) 
            {
                if (cars[i].Manufacturer.equals(cars[j].Manufacturer)) 
                {             
                    Count++;
                    if (Count > Max) 
                    {                        
                        MostCars[MostCarCount++] = cars[j];
                        Max = Count;
                    }
                }
            }
        }
        return MostCars;
    }
  
    public static AUTO[] volvoCount(AUTO[] Cars)
    {
        AUTO[] Volvo = new AUTO[MaxCarNumber];
        int count = 0;
        
        for (AUTO car : Cars) 
        {
            if (car.Manufacturer.equals("Volvo")) 
            {
                Volvo[count++] = car;
            }
        }
        return Volvo;
    }
    
    public static AUTO[] countOldCars(AUTO[] Cars) throws ParseException
    {
        AUTO[] OldCars = new AUTO[MaxCarNumber];
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, - 10);
        Date CurrentDate = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        int count = 0;
        
        for (AUTO car : Cars)
        {
            Date CarDate = dateFormat.parse(car.Production);
            if (CarDate.before(CurrentDate)) 
            {
                OldCars[count++] = car;               
            }
        }
        return OldCars;
    }
    
    public static AUTO[] checkup(AUTO[] Cars) throws ParseException
    {
        AUTO[] DueCars = new AUTO[MaxCarNumber];
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date CurrentDate = new Date();
        int count = 0;
        
        for (AUTO car : Cars)
        {
            Date CarDate = dateFormat.parse(car.CheckUp);
            cal.setTime(CarDate);
            cal.add(Calendar.DATE, -30);
            Date DueDate = cal.getTime();
            if (CurrentDate.before(CarDate) && CurrentDate.after(DueDate)) 
            {
                DueCars[count++] = car;
            }
        }
        return DueCars;
    }

    public static AUTO[] lateCheckup(AUTO[] Cars) throws ParseException
    {
        AUTO[] LateCars = new AUTO[MaxCarNumber];
        Date CurrentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        int count = 0;
        
        for (AUTO car : Cars)
        {
            Date CarDate = dateFormat.parse(car.CheckUp);
            if (CurrentDate.after(CarDate)) 
            {
                LateCars[count++] = car;
            }
        }
        return LateCars;
    }
}