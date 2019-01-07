package country;

import java.util.Comparator;

/**
A country with a name and area.
*/
public class Country implements Comparable<Country>
{
/**
   Constructs a country.
   @param aName the name of the country
   @param anArea the area of the country
*/
public Country(String aName, double anArea)
{
   name = aName;
   area = anArea;
}

/**
   Gets the name of the country.
   @return the name
*/
public String getName()
{
   return name;
}

/**
   Gets the area of the country.
   @return the area
*/
public double getArea()
{
   return area;
}

public static Comparator<Country> createComparatorByName(final boolean increasing)
{
	Comparator<Country> name = new 
			Comparator<Country>()
	{
		   public int compare(Country country1, Country country2)
		   {
		      if(increasing)
		      {
			   return country1.getName().compareTo(country2.getName());
		      }
		      return country2.getName().compareTo(country1.getName());
		   }
	};
		return name;
}
public static Comparator<Country> createComparatorByArea(final boolean increasing)
{
	Comparator<Country> area = new 
			Comparator<Country>()
	{
		   public int compare(Country country1, Country country2)
		   {
		      if(increasing)
		      {
			   return (int)(country1.getArea() - (country2.getArea()));
		      }
		      return (int)(country2.getArea() - (country1.getArea()));
		   }
	};
		return area;
}


/**
   Compares two countries by area.
   @param other the other country
   @return a negative number if this country has a smaller
   area than otherCountry, 0 if the areas are the same,
   a positive number otherwise
*/
public int compareTo(Country other)
{
   if (area < other.area) return -1;
   if (area > other.area) return 1;
   return 0;
}

private String name;
private double area;
}

