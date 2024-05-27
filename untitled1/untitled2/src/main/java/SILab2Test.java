import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
public class SILab2Test {
    @Test
    public void testNullItemList() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(null, 100);
        });
        assertEquals("allItems list can't be null!", exception.getMessage());
    }

    @Test
    public void testEmptyItemList() {
        List<Item> items = new ArrayList<>();
        assertTrue(SILab2.checkCart(items, 0));
    }

    @Test
    public void testItemNameNullOrEmpty() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(null, "123456", 100, 0));
        items.add(new Item("", "789012", 200, 0.1f));
        assertTrue(SILab2.checkCart(items, 300));
        assertEquals("unknown", items.get(0).getName());
        assertEquals("unknown", items.get(1).getName());
    }

    @Test
    public void testItemBarcodeNull() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", null, 100, 0));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(items, 100);
        });
        assertEquals("No barcode!", exception.getMessage());
    }

    @Test
    public void testItemBarcodeInvalidCharacter() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "123a56", 100, 0));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(items, 100);
        });
        assertEquals("Invalid character in item barcode!", exception.getMessage());
    }

    @Test
    public void testItemWithDiscount() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "123456", 100, 0.2f));
        assertTrue(SILab2.checkCart(items, 80)); // Price with discount is 80
    }

    @Test
    public void testItemWithoutDiscount() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "123456", 100, 0));
        assertTrue(SILab2.checkCart(items, 100)); // Price without discount is 100
    }

    @Test
    public void testItemPriceGreaterThan300WithDiscountAndBarcodeStartsWithZero() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "012345", 350, 0.2f)); // Price with discount is 70, then subtract 30
        assertTrue(SILab2.checkCart(items, 40)); // 70 - 30 = 40, which is equal to payment
    }

    @Test
    public void testItemPriceLessThanOrEqualToPayment() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "123456", 150, 0.1f)); // Price with discount is 15
        items.add(new Item("Item2", "789012", 200, 0)); // Price without discount is 200
        assertTrue(SILab2.checkCart(items, 215)); // Total 215, which is equal to payment
    }

    @Test
    public void testItemPriceGreaterThanPayment() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "123456", 150, 0.1f)); // Price with discount is 15
        items.add(new Item("Item2", "789012", 200, 0)); // Price without discount is 200
        assertFalse(SILab2.checkCart(items, 200)); // Total 215, which is greater than payment
    }
}