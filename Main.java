
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
	    
	    Scanner reader = new Scanner(System.in);
	    System.out.println("Enter the mass of the water, in grams");
	    double mass = reader.nextDouble();
	    System.out.println("Enter the initial Tempurature, in Celsius");
	    double initialTemp = reader.nextDouble();
	    if(initialTemp<-273.14)
	        initialTemp =-273.14;
	    System.out.println("Enter the Final Tempurature, in Celsius");
	    double finalTemp = reader.nextDouble();
	    if(finalTemp<-273.14)
	        finalTemp =-273.14;
	        
	    String initialPhase = "liquid";
	    if (initialTemp < 0)
	        initialPhase = "solid";
	    if(initialTemp > 100)
	        initialPhase = "vapor";
	        
	    String FinalPhase = "liquid";
	    if (finalTemp < 0)
	        FinalPhase = "solid";
	    if(finalTemp > 100)
	        FinalPhase = "vapor";
	    
	    
	    
	    System.out.println("Mass: "+mass+ "g");
	    System.out.println("Initial Tempurature: " + initialTemp + "C " + initialPhase);
	    System.out.println("Final Temp: " + finalTemp + "C " + FinalPhase);
	    
	    
	    boolean endothermic = false;
	    if(finalTemp > initialTemp)
	        endothermic = true;
	    double heatEnergy =0;
	    
	    if(initialPhase.equals("solid")){
	        heatEnergy += tempChangeSolid(mass, initialTemp, finalTemp, FinalPhase, endothermic);
	        if(!FinalPhase.equals("solid")){
	            heatEnergy +=fusion(mass,endothermic);
	            heatEnergy += tempChangeLiquid(mass, 0, finalTemp, FinalPhase, endothermic);}
	        if(FinalPhase.equals("vapor")){
	            heatEnergy +=vaporization(mass,endothermic);
	            heatEnergy += tempChangeVapor(mass, 100, finalTemp, FinalPhase, endothermic); }
	    }
	    
	    if(initialPhase.equals("liquid")){
	        heatEnergy += tempChangeLiquid(mass, initialTemp, finalTemp, FinalPhase, endothermic);
	        if(FinalPhase.equals("solid")){
	            heatEnergy +=fusion(mass,endothermic);
	            heatEnergy += tempChangeLiquid(mass, 0, finalTemp, FinalPhase, endothermic);}
	        if(FinalPhase.equals("vapor")){
	            heatEnergy +=vaporization(mass,endothermic);
	            heatEnergy += tempChangeVapor(mass, 100, finalTemp, FinalPhase, endothermic); }
	    }
	    
	    if(initialPhase.equals("vapor")){
	        heatEnergy += tempChangeVapor(mass, initialTemp, finalTemp, FinalPhase, endothermic);
	        if(!FinalPhase.equals("vapor")){
	            heatEnergy +=vaporization(mass,endothermic);
	            heatEnergy += tempChangeLiquid(mass, 100, finalTemp, FinalPhase, endothermic);}
	        if(FinalPhase.equals("solid")){
	            heatEnergy +=fusion(mass,endothermic);
	            heatEnergy += tempChangeSolid(mass, 0, finalTemp, FinalPhase, endothermic); }
	    }
	    System.out.println("Total Heat Energy: " + round(heatEnergy) + "kJ");
	    
	}
	// supporting methods
    public static double tempChangeSolid(double mass,double startTemp, double endTemp,String endPhase, boolean endothermic){
	    if(!endPhase.equals("solid"))
	        endTemp = 0;
	    double energyChange = round(mass*0.002108*(endTemp - startTemp));
	    if(endothermic)
	        System.out.println("Heating (solid): " + energyChange + "kJ");
	    else
	        System.out.println("Cooling (solid): " + energyChange + "kJ");
	    return energyChange;
	}
	public static double tempChangeVapor(double mass,double startTemp, double endTemp,String endPhase, boolean endothermic){
	    if(!endPhase.equals("vapor"))
	        endTemp = 100;
	    double energyChange = round(mass*0.001996*(endTemp - startTemp));
	    if(endothermic)
	        System.out.println("Heating (vapor): " + energyChange + "kJ");
	    else
	        System.out.println("Cooling (vapor): " + energyChange + "kJ");
	    return energyChange;
	}
	public static double tempChangeLiquid(double mass,double startTemp, double endTemp,String endPhase, boolean endothermic){
	    if(!endPhase.equals("solid"))
	        endTemp = 0;
	    if(!endPhase.equals("vapor"))
	        endTemp = 100;
	    double energyChange = round(mass*0.004184*(endTemp - startTemp));
	    if(endothermic)
	        System.out.println("Heating (liquid): " + energyChange + "kJ");
	    else
	        System.out.println("Cooling (liquid): " + energyChange + "kJ");
	    return energyChange;
	}
	
	public static double fusion(double mass, boolean endothermic){
	    double energyChange;
	    if(endothermic){
	        energyChange = round(0.333*mass);
	        System.out.println("Melting: " + energyChange + "kJ");}
	   else{
	        energyChange = round(-0.333*mass);
	        System.out.println("Melting: " + energyChange + "kJ");}
	    return energyChange;
	    
	}
	
	public static double vaporization(double mass, boolean endothermic){
	    double energyChange;
	    if(endothermic){
	        energyChange = round(2.257*mass);
	        System.out.println("Evaporation: " + energyChange + "kJ");}
	    else{
	        energyChange = round(-2.257*mass);
	        System.out.println("Condensation: " + energyChange + "kJ");}
	    return energyChange;
	    
	}
	
	public static double round(double x){
	    x*=1000;
	    if (x>0)
	        return (int)(x+0.5)/1000.0;
	    else
	        return (int)(x-0.5)/1000.0;
	}
	
	
}