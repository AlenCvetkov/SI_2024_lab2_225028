import java.util.List;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {
    @Test
    public void testTrueTrueTrue() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "012345", 350, 0.2f)); // Price > 300, discount > 0, barcode starts with '0'
        assertTrue(SILab2.checkCart(items, 40)); // Discounted price = 70, then subtract 30 = 40
    }

    @Test
    public void testTrueTrueFalse() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "112345", 350, 0.2f)); // Price > 300, discount > 0, barcode does not start with '0'
        assertTrue(SILab2.checkCart(items, 70)); // Discounted price = 70
    }

    @Test
    public void testTrueFalseTrue() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "012345", 350, 0)); // Price > 300, discount = 0, barcode starts with '0'
        assertFalse(SILab2.checkCart(items, 320)); // Price = 350
    }

    @Test
    public void testTrueFalseFalse() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "112345", 350, 0)); // Price > 300, discount = 0, barcode does not start with '0'
        assertFalse(SILab2.checkCart(items, 350)); // Price = 350
    }

    @Test
    public void testFalseTrueTrue() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "012345", 250, 0.2f)); // Price <= 300, discount > 0, barcode starts with '0'
        assertTrue(SILab2.checkCart(items, 50)); // Discounted price = 50
    }

    @Test
    public void testFalseTrueFalse() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "112345", 250, 0.2f)); // Price <= 300, discount > 0, barcode does not start with '0'
        assertTrue(SILab2.checkCart(items, 50)); // Discounted price = 50
    }

    @Test
    public void testFalseFalseTrue() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "012345", 250, 0)); // Price <= 300, discount = 0, barcode starts with '0'
        assertTrue(SILab2.checkCart(items, 250)); // Price = 250
    }

    @Test
    public void testFalseFalseFalse() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "112345", 250, 0)); // Price <= 300, discount = 0, barcode does not start with '0'
        assertTrue(SILab2.checkCart(items, 250)); // Price = 250
    }

}