package salessheets;

import java.util.Arrays;

public class SimpleSalesSheet extends SalesSheet {
	
	/**
	 * @invar | 0 <= nbFlavours
	 * @invar | 0 <= nbLocations
	 * @invar | 0 <= nbPeriods
	 * @invar | sales != null
	 * @invar | sales.length == nbFlavours * nbLocations * nbPeriods
	 * @invar | Arrays.stream(sales).allMatch(n -> 0 <= n)
	 */
	private int nbFlavours;
	private int nbLocations;
	private int nbPeriods;
	/**
	 * @representationObject
	 */
	private int[] sales;
	
	@Override
	public int getNbFlavours() { return nbFlavours; }
	
	@Override
	public int getNbLocations() { return nbLocations; }
	
	@Override
	public int getNbPeriods() { return nbPeriods; }
	
	@Override
	public int[] getAllSales() { return sales.clone(); }
	
	@Override
	public int getNbScoopsSold(int flavourIndex, int locationIndex, int periodIndex) {
		return sales[flavourIndex + nbFlavours * (locationIndex + nbLocations * periodIndex)];
	}
	
	/**
	 * @throws IllegalArgumentException | nbFlavours < 0 || nbLocations < 0 || nbPeriods < 0
	 * @throws IllegalArgumentException | sales == null || sales.length != nbFlavours * nbLocations * nbPeriods
	 * @throws IllegalArgumentException | Arrays.stream(sales).anyMatch(n -> n < 0)
	 * 
	 * @inspects | sales
	 * 
	 * @post | getNbFlavours() == nbFlavours
	 * @post | getNbLocations() == nbLocations
	 * @post | getNbPeriods() == nbPeriods
	 * @post | Arrays.equals(getAllSales(), sales)
	 */
	public SimpleSalesSheet(int nbFlavours, int nbLocations, int nbPeriods, int[] sales) {
		if (nbFlavours < 0)
			throw new IllegalArgumentException("`nbFlavours` is less than zero");
		if (nbLocations < 0)
			throw new IllegalArgumentException("`nbLocations` is less than zero");
		if (nbPeriods < 0)
			throw new IllegalArgumentException("`nbPeriods` is less than zero");
		if (sales == null)
			throw new IllegalArgumentException("`sales` is null");
		if (sales.length != nbFlavours * nbLocations * nbPeriods)
			throw new IllegalArgumentException("The length of `sales` is wrong");
		if (Arrays.stream(sales).anyMatch(n -> n < 0))
			throw new IllegalArgumentException("`sales` has a negative element");
		
		this.nbFlavours = nbFlavours;
		this.nbLocations = nbLocations;
		this.nbPeriods = nbPeriods;
		this.sales = sales.clone();
	}
	
	@Override
	public void setNbScoopsSold(int flavourIndex, int locationIndex, int periodIndex, int nbScoops) {
		sales[flavourIndex + nbFlavours * (locationIndex + nbLocations * periodIndex)] = nbScoops;
	}

}
