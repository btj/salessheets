package salessheets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SalesSheetTest {
	
	SimpleSalesSheet makeSimpleSalesSheet() {
		return new SimpleSalesSheet(2, 3, 5, new int[] {
			0, 1, 0, 0, 2, 0,
			0, 0, 3, 0, 0, 0,
			0, 4, 0, 0, 0, 0,
			0, 5, 0, 0, 0, 0,
			0, 0, 6, 0, 0, 0
		});
	}
	
	SparseSalesSheet makeSparseSalesSheet() {
		SparseSalesSheet sparseSheet = new SparseSalesSheet(2, 3, 5);
		sparseSheet.setNbScoopsSold(1, 0, 0, 1);
		sparseSheet.setNbScoopsSold(0, 2, 0, 2);
		sparseSheet.setNbScoopsSold(0, 1, 1, 3);
		sparseSheet.setNbScoopsSold(1, 0, 2, 4);
		sparseSheet.setNbScoopsSold(1, 0, 3, 5);
		sparseSheet.setNbScoopsSold(0, 1, 4, 6);
		return sparseSheet;
	}

	@Test
	void test() {
		testSalesSheet(makeSimpleSalesSheet());
		testSalesSheet(makeSparseSalesSheet());
	}
	
	void testSalesSheet(SalesSheet sheet) {
		assertEquals(2, sheet.getNbFlavours());
		assertEquals(3, sheet.getNbLocations());
		assertEquals(5, sheet.getNbPeriods());
		assertArrayEquals(new int[] {
			0, 1, 0, 0, 2, 0,
			0, 0, 3, 0, 0, 0,
			0, 4, 0, 0, 0, 0,
			0, 5, 0, 0, 0, 0,
			0, 0, 6, 0, 0, 0
		}, sheet.getAllSales());
		assertEquals(1, sheet.getNbScoopsSold(1, 0, 0));
		assertEquals(2, sheet.getNbScoopsSold(0, 2, 0));
		assertEquals(3, sheet.getNbScoopsSold(0, 1, 1));
		assertEquals(0, sheet.getNbScoopsSold(1, 2, 3));
		sheet.setNbScoopsSold(1, 2, 3, 10);
		assertArrayEquals(new int[] {
			0, 1, 0, 0, 2, 0,
			0, 0, 3, 0, 0, 0,
			0, 4, 0, 0, 0, 0,
			0, 5, 0, 0, 0, 10,
			0, 0, 6, 0, 0, 0
		}, sheet.getAllSales());
		assertNotEquals(sheet, makeSimpleSalesSheet());
		assertNotEquals(sheet, makeSparseSalesSheet());
		
		sheet.setNbScoopsSold(1, 2, 3, 0);
		
		assertEquals(sheet, makeSimpleSalesSheet());
		assertEquals(sheet, makeSparseSalesSheet());
	}

}
