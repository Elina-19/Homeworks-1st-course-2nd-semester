package Test;

import com.company.ArraySet;
import com.company.Product;
import com.company.ProductComparator;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@RunWith(JUnit4.class)
class ArraySetTest {

    private ArraySet<Product> mySet;
    private TreeSet<Product> expSet;
    private ProductComparator comparator;

    public ArraySetTest() {
        comparator = new ProductComparator();
        mySet = new ArraySet<>(comparator);
        expSet = new TreeSet<>(comparator);
    }

    static Stream<Arguments> getParameters() {
        return Stream.of(
                arguments(new ArrayList<Product>(Arrays.asList(new Product("milk", 70), new Product("milk", 70), new Product("apple", 73), new Product("juice", 91)))),
                arguments(new ArrayList<Product>(Arrays.asList(new Product("apple", 115), new Product("juice", 82), new Product("banana", 70), new Product("fish", 200)))),
                arguments(new ArrayList<Product>(Arrays.asList(new Product("fish", 552), new Product("juice", 552), new Product("milk", 73), new Product("apple", 91), new Product("water", 30))))
                );
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    void testHeadSet(ArrayList<Product> arrayList) {
        mySet.addAll(arrayList);
        expSet.addAll(arrayList);
        Product product = new Product("chicken", 200);
        mySet.add(product);
        expSet.add(product);
        Assert.assertEquals(expSet.headSet(product), mySet.headSet(product));
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    void testSubSet(ArrayList<Product> arrayList) {
        mySet.addAll(arrayList);
        expSet.addAll(arrayList);
        Product product = new Product("chicken", 200);
        Product product1 = new Product("fish", 300);
        mySet.add(product);
        expSet.add(product);
        mySet.add(product1);
        expSet.add(product1);
        Assert.assertEquals(expSet.subSet(product, product1), mySet.subSet(product, product1));
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    void testSubSetEmptySet(ArrayList<Product> arrayList) {
        mySet.addAll(arrayList);
        expSet.addAll(arrayList);
        Product product = new Product("chicken", 200);
        mySet.add(product);
        expSet.add(product);
        Assert.assertEquals(expSet.subSet(product, product), mySet.subSet(product, product));
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    void testSubSetEmptySetNullException(ArrayList<Product> arrayList) {
        mySet.addAll(arrayList);
        Product product = new Product("chicken", 200);
        mySet.add(product);
        Assertions.assertThrows(NullPointerException.class, () -> {
            mySet.subSet(null, product);
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            mySet.subSet(product, null);
        });
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    void testSubSetEmptySetIllegalArgumentException(ArrayList<Product> arrayList) {
        mySet.addAll(arrayList);
        Product product = new Product("chicken", 200);
        Product product1 = new Product("fish", 300);
        mySet.add(product);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            mySet.subSet(product, product1);
        });
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    void testSubSetEmptySetIllegalArgumentExceptionWrongOrder(ArrayList<Product> arrayList) {
        mySet.addAll(arrayList);
        Product product = new Product("chicken", 200);
        Product product1 = new Product("fish", 300);
        mySet.add(product);
        mySet.add(product1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            mySet.subSet(product1, product);
        });
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    void testTailSet(ArrayList<Product> arrayList) {
        mySet.addAll(arrayList);
        expSet.addAll(arrayList);
        Product product = new Product("chicken", 200);
        mySet.add(product);
        expSet.add(product);
        Assert.assertEquals(expSet.tailSet(product), mySet.tailSet(product));
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    public void testAddAll(ArrayList<Product> arrayList) {
        mySet.addAll(arrayList);
        expSet.addAll(arrayList);
        Assert.assertEquals(expSet, mySet);
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    public void testFirst(ArrayList<Product> arrayList) {
        mySet.addAll(arrayList);
        expSet.addAll(arrayList);
        Assert.assertEquals(expSet.first(), mySet.first());
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    public void testLast(ArrayList<Product> arrayList) {
        mySet.addAll(arrayList);
        expSet.addAll(arrayList);
        Assert.assertEquals(expSet.last(), mySet.last());
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    void testSize(ArrayList<Product> arrayList) {
        mySet.addAll(arrayList);
        expSet.addAll(arrayList);
        Assert.assertEquals(expSet.size(), mySet.size());
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    void contains(ArrayList<Product> arrayList) {
        mySet.addAll(arrayList);
        expSet.addAll(arrayList);
        Product product = new Product("chicken", 200);
        mySet.add(product);
        expSet.add(product);
        Assert.assertEquals(expSet.contains(product), mySet.contains(product));
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    public void testAdd(ArrayList arrayList) {
        mySet.addAll(arrayList);
        expSet.addAll(arrayList);
        Product product = new Product("chicken", 200);
        mySet.add(product);
        expSet.add(product);
        Assert.assertEquals(expSet, mySet);
    }

    @Test
    public void testAddNull(){
        Assertions.assertThrows(NullPointerException.class, () -> {
            mySet.add(null);});
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    public void testEqualsTrue(ArrayList arrayList) {
        mySet.addAll(arrayList);
        ArraySet<Product> newSet = new ArraySet<>(comparator);
        newSet.addAll(arrayList);
        Assert.assertTrue(newSet.equals(mySet));
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    public void testEqualsFalse(ArrayList arrayList) {
        mySet.addAll(arrayList);
        ArraySet<Product> newSet = new ArraySet<>(comparator);
        newSet.addAll(arrayList);
        newSet.add(new Product("tea", 150));
        Assert.assertEquals(false, newSet.equals(mySet));
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    public void testRemove(ArrayList arrayList) {
        mySet.addAll(arrayList);
        ArraySet<Product> newSet = new ArraySet<>(comparator);
        newSet.addAll(arrayList);
        Product product = new Product("tea", 150);
        mySet.add(product);
        mySet.remove(product);
        Assert.assertEquals(mySet, newSet);
    }

    @Test
    public void testRemoveFalse() {
        Assert.assertEquals(false, mySet.remove(new Product("apple", 150)));
    }

    @Test
    public void testRemoveNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            mySet.remove(null);
        });
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    public void testContainsAll(ArrayList arrayList) {
        mySet.addAll(arrayList);
        expSet.addAll(arrayList);
        Assert.assertTrue(mySet.containsAll(expSet));
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    public void testContainsAllFalse(ArrayList arrayList) {
        mySet.addAll(arrayList);
        expSet.addAll(arrayList);
        expSet.add(new Product("tea", 100));
        Assert.assertFalse(mySet.containsAll(expSet));
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    public void testRetainAll(ArrayList arrayList) {
        Product product = new Product("fish", 150);
        ArraySet<Product> newSet = new ArraySet(comparator);
        mySet.addAll(arrayList);
        mySet.add(product);
        newSet.addAll(arrayList);
        mySet.retainAll(newSet);
        Assert.assertEquals(newSet, mySet);
    }

    @Test
    public void testIteratorHasNextTrue() {
        mySet.add(new Product("tea", 150));
        mySet.add(new Product("apple", 72));
        Assert.assertTrue(mySet.iterator().hasNext());
    }

    @Test
    public void testIteratorHasNextFalse() {
        Assert.assertEquals(false, mySet.iterator().hasNext());
    }

    @Test
    public void testIteratorNext() {
        Product product = new Product("apple", 72);
        mySet.add(product);
        mySet.add(new Product("tea", 150));
        Assert.assertEquals(product, mySet.iterator().next());
    }

    @Test
    public void testIteratorNextException() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            mySet.iterator().next();
        });
    }

    @Test
    public void testIteratorRemove() {
        Product product = new Product("tea", 72);
        ArraySet newSet = new ArraySet(comparator);
        newSet.add(product);
        mySet.add(new Product("apple", 150));
        mySet.add(product);
        Iterator iterator = mySet.iterator();
        iterator.next();
        iterator.remove();
        Assert.assertEquals(newSet, mySet);
    }

    @Test
    public void testIteratorRemoveException() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            mySet.iterator().remove();
        });
    }

    @Test
    public void testIteratorTwoRemoveException() {
        Product product = new Product("tea", 72);
        mySet.add(new Product("apple", 150));
        mySet.add(product);
        Iterator iterator = mySet.iterator();
        iterator.next();
        iterator.remove();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }

    @Test
    public void testIsEmptyTrue() {
        Assert.assertEquals(true, mySet.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        mySet.add(new Product("apple", 150));
        Assert.assertFalse(mySet.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    public void testToArray(ArrayList arrayList) {
        mySet.addAll(arrayList);
        expSet.addAll(arrayList);
        Assert.assertArrayEquals(expSet.toArray(), mySet.toArray());
    }

    @Test
    public void clear() {
        mySet.add(new Product("apple", 150));
        mySet.add(new Product("tea", 200));
        mySet.clear();
        Assertions.assertTrue( mySet.isEmpty());
    }
}