package salessheets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SparseSalesSheet extends SalesSheet {
	
	private record SalesKey(int flavourIndex, int locationIndex, int periodIndex) {}
	
	/**
	 * @invar | 0 <= nbFlavours
	 * @invar | 0 <= nbLocations
	 * @invar | 0 <= nbPeriods
	 * @invar | sales != null
	 * @invar | sales.entrySet().stream().allMatch(e ->
	 *        |     e.getKey() != null &&
	 *        |     0 <= e.getKey().flavourIndex() && e.getKey().flavourIndex() < nbFlavours &&
	 *        |     0 <= e.getKey().locationIndex() && e.getKey().locationIndex() < nbLocations &&
	 *        |     0 <= e.getKey().periodIndex() && e.getKey().periodIndex() < nbPeriods &&
	 *        |     e.getValue().intValue() != 0
	 *        | )
	 */
	private int nbFlavours;
	private int nbLocations;
	private int nbPeriods;
	/**
	 * @representationObject
	 */
	private Map<SalesKey, Integer> sales = new HashMap<>();
	
	
	@Override
	public int getNbFlavours() { return nbFlavours; }
	
	@Override
	public int getNbLocations() { return nbLocations; }
	
	@Override
	public int getNbPeriods() { return nbPeriods; }
	
	@Override
	public int[] getAllSales() {
		int[] result = new int[nbFlavours * nbLocations * nbPeriods];
		for (Map.Entry<SalesKey, Integer> entry : sales.entrySet())
			result[entry.getKey().flavourIndex() + nbFlavours * (entry.getKey().locationIndex() + nbLocations * entry.getKey().periodIndex())] = entry.getValue();
		return result;
	}
	
	@Override
	public int getNbScoopsSold(int flavourIndex, int locationIndex, int periodIndex) {
		return sales.getOrDefault(new SalesKey(flavourIndex, locationIndex, periodIndex), 0);
	}
	
	/**
	 * @throws IllegalArgumentException | nbFlavours < 0 || nbLocations < 0 || nbPeriods < 0
	 * 
	 * @post | getNbFlavours() == nbFlavours
	 * @post | getNbLocations() == nbLocations
	 * @post | getNbPeriods() == nbPeriods
	 * @post | Arrays.stream(getAllSales()).allMatch(n -> n == 0)
	 */
	public SparseSalesSheet(int nbFlavours, int nbLocations, int nbPeriods) {
		if (nbFlavours < 0)
			throw new IllegalArgumentException("`nbFlavours` is less than zero");
		if (nbLocations < 0)
			throw new IllegalArgumentException("`nbLocations` is less than zero");
		if (nbPeriods < 0)
			throw new IllegalArgumentException("`nbPeriods` is less than zero");
		
		this.nbFlavours = nbFlavours;
		this.nbLocations = nbLocations;
		this.nbPeriods = nbPeriods;
	}
	
	@Override
	public void setNbScoopsSold(int flavourIndex, int locationIndex, int periodIndex, int nbScoops) {
		if (nbScoops == 0)
			sales.remove(new SalesKey(flavourIndex, locationIndex, periodIndex));
		else
			sales.put(new SalesKey(flavourIndex, locationIndex, periodIndex), nbScoops);
	}

}
