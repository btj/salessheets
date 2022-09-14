package salessheets;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @invar | 0 <= getNbFlavours()
 * @invar | 0 <= getNbLocations()
 * @invar | 0 <= getNbPeriods()
 * @invar | getAllSales() != null
 * @invar | getAllSales().length == getNbFlavours() * getNbLocations() * getNbPeriods()
 * @invar | Arrays.stream(getAllSales()).allMatch(n -> 0 <= n)
 */
public abstract class SalesSheet {
	
	/**
	 * @immutable
	 */
	public abstract int getNbFlavours();
	
	/**
	 * @immutable
	 */
	public abstract int getNbLocations();
	
	/**
	 * @immutable
	 */
	public abstract int getNbPeriods();
	
	/**
	 * @creates | result
	 */
	public abstract int[] getAllSales();
	
	/**
	 * @pre | 0 <= flavourIndex && flavourIndex < getNbFlavours()
	 * @pre | 0 <= locationIndex && locationIndex < getNbLocations()
	 * @pre | 0 <= periodIndex && periodIndex < getNbPeriods()
	 * 
	 * @post | result == getAllSales()[flavourIndex + getNbFlavours() * (locationIndex + getNbLocations() * periodIndex)]
	 */
	public abstract int getNbScoopsSold(int flavourIndex, int locationIndex, int periodIndex);
	
	
	/**
	 * @pre | 0 <= flavourIndex && flavourIndex < getNbFlavours()
	 * @pre | 0 <= locationIndex && locationIndex < getNbLocations()
	 * @pre | 0 <= periodIndex && periodIndex < getNbPeriods()
	 * @pre | 0 <= nbScoops
	 * 
	 * @mutates | this
	 * 
	 * @post | IntStream.range(0, getNbFlavours()).allMatch(i ->
	 *       |     IntStream.range(0, getNbLocations()).allMatch(j ->
	 *       |          IntStream.range(0, getNbPeriods()).allMatch(k ->
	 *       |              getAllSales()[i + getNbFlavours() * (j + getNbLocations() * k)] == (
	 *       |                  i == flavourIndex && j == locationIndex && k == periodIndex ?
	 *       |                      nbScoops
	 *       |                  :
	 *       |                      old(getAllSales())[i + getNbFlavours() * (j + getNbLocations() * k)]
	 *       |              )
	 *       |          )
	 *       |     )
	 *       | )
	 */
	public abstract void setNbScoopsSold(int flavourIndex, int locationIndex, int periodIndex, int nbScoops);

	@Override
	public boolean equals(Object obj) {
		return obj instanceof SalesSheet s &&
				getNbFlavours() == s.getNbFlavours() &&
				getNbLocations() == s.getNbLocations() &&
				getNbPeriods() == s.getNbPeriods() &&
				Arrays.equals(getAllSales(), s.getAllSales());
	}
	
	@Override
	public int hashCode() {
		return Arrays.hashCode(getAllSales());
	}
	
}
