import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Nam B Nguyen & Simon Manning
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

    /**
     * Test for the CONSTRUCTOR.
     */
    @Test
    public final void testConstructor() {
        Set<String> s = this.constructorTest();
        Set<String> r = this.constructorRef();
        assertEquals(r, s);
    }

    /**
     * Test for the ADD to empty.
     */
    @Test
    public final void testAddEmpty() {
        Set<String> s = this.createFromArgsTest("3");
        Set<String> r = this.createFromArgsRef("3", "4");
        s.add("4");
        assertEquals(r, s);
    }

    /**
     * Test for the ADD to non-empty.
     */
    @Test
    public final void testAddNonEmpty() {
        Set<String> s = this.createFromArgsTest();
        Set<String> r = this.createFromArgsRef("4");
        s.add("4");
        assertEquals(r, s);
    }

    /**
     * Test for the REMOVE leaving empty.
     */
    @Test
    public final void testRemoveEmpty() {
        Set<String> s = this.createFromArgsTest("4");
        Set<String> r = this.constructorRef();
        String result = s.remove("4");
        assertEquals("4", result);
        assertEquals(r, s);
    }

    /**
     * Test for the REMOVE leaving non empty.
     */
    @Test
    public final void testRemoveNonEmpty() {
        Set<String> s = this.createFromArgsTest("3", "4", "5");
        Set<String> r = this.createFromArgsRef("4", "5");
        String result = s.remove("3");
        assertEquals("3", result);
        assertEquals(r, s);
    }

    /**
     * Test for the REMOVE ANY leaving empty.
     */
    @Test
    public final void testRemoveAnyEmpty() {
        Set<String> s = this.createFromArgsTest("4");
        Set<String> r = this.createFromArgsRef("4");
        String result = s.removeAny();
        assertEquals(true, r.contains(result));
        String result2 = r.remove(result);
        assertEquals(result, result2);
        assertEquals(r, s);
    }

    /**
     * Test for the REMOVE ANY leaving non empty.
     */
    @Test
    public final void testRemoveAnyNonEmpty() {
        Set<String> s = this.createFromArgsTest("3", "4", "5");
        Set<String> r = this.createFromArgsRef("3", "4", "5");
        String result = s.removeAny();

        assertEquals(true, r.contains(result));
        String result2 = r.remove(result);
        assertEquals(result, result2);
        assertEquals(r, s);
    }

    /**
     * Test for the CONTAINS empty.
     */
    @Test
    public final void testContainsEmpty() {
        Set<String> s = this.createFromArgsTest();
        Set<String> r = this.createFromArgsRef();

        String test = "4";
        assertEquals(false, s.contains(test));
        assertEquals(s.contains(test), r.contains(test));
        assertEquals(r, s);
    }

    /**
     * Test for the CONTAINS non empty with true result.
     */
    @Test
    public final void testContainsNonEmptyTrue() {
        Set<String> s = this.createFromArgsTest("4");
        Set<String> r = this.createFromArgsRef("4");

        String test = "4";
        assertEquals(true, s.contains(test));
        assertEquals(s.contains(test), r.contains(test));
        assertEquals(r, s);
    }

    /**
     * Test for the CONTAINS empty with false result.
     */
    @Test
    public final void testContainsNonEmptyFalse() {
        Set<String> s = this.createFromArgsTest("3");
        Set<String> r = this.createFromArgsRef("3");

        String test = "4";
        assertEquals(false, s.contains(test));
        assertEquals(s.contains(test), r.contains(test));
        assertEquals(r, s);
    }

    /**
     * Test for the SIZE empty with constructor.
     */
    @Test
    public final void testSizeEmpty() {
        Set<String> s = this.constructorTest();
        Set<String> r = this.constructorRef();
        assertEquals(0, s.size());
        assertEquals(r, s);
    }

    /**
     * Test for the SIZE non empty.
     */
    @Test
    public final void testSizeNonEmpty() {
        Set<String> s = this.createFromArgsTest("3", "4");
        Set<String> r = this.createFromArgsRef("3", "4");
        assertEquals(2, s.size());
        assertEquals(r, s);
    }
}
