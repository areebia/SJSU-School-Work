package country;

import java.util.*;

public class CountryComparatorTester
{
   public static void main(String[] args)
   {
      ArrayList<Country> countries = new ArrayList<Country>();
      countries.add(new Country("Uruguay", 176220));
      countries.add(new Country("Thailand", 514000));
      countries.add(new Country("Belgium", 30510));

      Collections.sort(countries, Country.createComparatorByName(true));
      System.out.println("Increasing:");
      // Now the array list is sorted by name
      for (Country c : countries)
         System.out.println(c.getName() + " " + c.getArea());
      
      System.out.println();
      System.out.println("Decreasing:");
      Collections.sort(countries, Country.createComparatorByName(false));
      
      for (Country c : countries)
          System.out.println(c.getName() + " " + c.getArea());
      
      System.out.println();
      System.out.println("Increasing:");
      Collections.sort(countries, Country.createComparatorByArea(true));
      
      for (Country c : countries)
          System.out.println(c.getName() + " " + c.getArea());
      
      System.out.println();
      System.out.println("Decreasing:");
      Collections.sort(countries, Country.createComparatorByArea(false));
      
      for (Country c : countries)
          System.out.println(c.getName() + " " + c.getArea());
   }
   
   
}