/**
 * Author:      Daniel Choi 
**/

import java.util.Scanner;

public class Module2BMI
{
    public static void main( String [] args )
    {
        // Define and initialize variables for height and weight values to be input
        int height = 0;      // Height value to be input
        int weight = 0;      // Weight value to be input
        
        // Define BMI, lb_kg, and in_m variables as double values
        double BMI, lb_kg, in_m ; 		 // Define BMI, lb_kg, in_m as double values    
    	lb_kg = 0.45359237; 			 // Conversion of one pound to kilograms
        in_m = 0.0254; 			         // Conversion of one inch to meters
        
        // Output name of the Program
        System.out.println( "Body Mass Calculator")
        
    
        // Use a Scanner to input integer values
        Scanner input = new Scanner( System.in );
        System.out.println( "\n\n" );
        System.out.print( "Enter height in inches:" );
        height = input.nextInt();     // Input height in inches
        System.out.print( "Enter weight in pounds:" );
        weight = input.nextInt();     // Input weight in pounds 
        
        //Calculate BMI
        BMI= (weight * lb_kg) / ((height * in_m) * (height * in_m))
        
        // Output BMI
        System.out.println ("Body Mass Index: " + BMI );
    
    
        // Output information from the Department of Health and Human Services / NIH 
        System.out.println( "\n" );
        System.out.println( "\t" + "Underweight: less than 18.5");
        System.out.println( "\t" + "Normal: 18.5-24.9");
        System.out.println( "\t" + "Overweight: 25-29.9");
        System.out.println( "\t" + "Obese: 20 or greater");
        System.out.println( "\n\n" );
    }
}