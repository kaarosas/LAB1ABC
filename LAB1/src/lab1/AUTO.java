package lab1;

class AUTO {
    
    public String Number;
    public String Manufacturer;
    public String Model;
    public String Production;
    public String CheckUp;
    public String Fuel;
    public double FuelUsage;
    
   public AUTO(String number, String manufacturer, String model, String production, 
           String checkUp, String fuel, double fuelUsage) 
   {
       this.Number = number;
       this.Manufacturer = manufacturer;
       this.Model = model;
       this.Production = production;
       this.CheckUp = checkUp;
       this.Fuel = fuel;
       this.FuelUsage = fuelUsage;
   }
   
   @Override
   public String toString() {
       return String.format("Number: " + this.Number + "\nManufacturer: "
               + this.Manufacturer + "\nModel: " + this.Model +
               "\nProduction Date: " + this.Production + "\nCheck Up Date: "
               + this.CheckUp + " \nFuel type: " + this.Fuel + " \nFuel Usage: "
               + this.FuelUsage + "\n\n");
   }          
}
