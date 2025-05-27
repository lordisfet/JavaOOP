package labs.lab14.Entities;

import labs.lab14.Exceptions.NoPhonesAvailableException;
import labs.lab14.Entities.Phones.Phone;

import java.util.*;
import java.util.Objects;

/**
 * The {@code Store} class represents a phone store that manages an inventory of phones.
 * Each phone in the store is wrapped in an {@link InventoryEntry} object, which contains a {@link Phone}
 * instance and its corresponding quantity.
 * <p>
 * This class provides functionality for adding new phones (or updating quantities if the phone already exists),
 * as well as retrieving phones based on various criteria such as type, brand, or RAM count.
 * It also allows retrieving the complete inventory and converting the store details to a file-friendly format.
 * </p>
 */
public class Store implements Cloneable{
    /**
     * The name of the store.
     */
    private String name;

    /**
     * The address of the store.
     */
    private String address;

    /**
     * The list of inventory entries representing the phones stored in the store.
     */
    private ArrayList<InventoryEntry> phones;

    /**
     * Constructs a new {@code Store} with the specified name, address, and initial inventory.
     *
     * @param name   the name of the store
     * @param address the address of the store
     * @param phones a list of {@link InventoryEntry} objects representing the store's initial inventory
     */
    public Store(String name, String address, ArrayList<InventoryEntry> phones) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name of store can`t be empty or null");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Name of store can`t be empty or null");
        }
        this.name = name;
        this.address = address;
        this.phones = phones;
    }

    public Store(Store other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy from a null Store object");
        }
        this.name = other.name;
        this.address = other.address;
        this.phones = other.phones;
    }

    /**
     * Returns the name of the store.
     *
     * @return the store's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the store's name.
     *
     * @param name the new name for the store
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the address of the store.
     *
     * @return the store's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the store's address.
     *
     * @param address the new address for the store
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the list of inventory entries in the store.
     *
     * @return an {@link ArrayList} containing all {@link InventoryEntry} objects in the store
     */
    public ArrayList<InventoryEntry> getPhones() {
        return phones;
    }

    /**
     * Sets the inventory of the store.
     *
     * @param phones an {@link ArrayList} of {@link InventoryEntry} objects to be set as the store's inventory
     */
    public void setPhones(ArrayList<InventoryEntry> phones) {
        this.phones = phones;
    }

    /**
     * Returns a string representation of the store that includes its name, address.
     *
     * @return a string describing the store
     */
    @Override
    public String toString() {
        return "Store \"" + name + '\"' + " at the address " + address;
    }

    /**
     * Returns a file-friendly string representation of the store details.
     * The format is: "name:{name};address:{address};"
     *
     * @return a string for file output containing the store's name and address
     */
    public String toStringToFile() {
        return "name:" + name + ';' + "address:" + address + ';';
    }

    /**
     * Compares this store to the specified object for equality.
     * Two stores are considered equal if they have the same name, address, and inventory.
     *
     * @param o the object to compare with
     * @return {@code true} if the specified object is equal to this store; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(name, store.name) &&
                Objects.equals(address, store.address) &&
                Objects.equals(phones, store.phones);
    }

    /**
     * Returns a hash code value for this store based on its name, address, and inventory.
     *
     * @return a hash code value for this store
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, address, phones);
    }

    /**
     * Adds a new {@link InventoryEntry} to the store.
     * <p>
     * If an entry with the same {@link Phone} (as determined by {@code equals} method on {@link Phone})
     * already exists in the store, the method updates the quantity by adding the new amount.
     * Otherwise, the new entry is added to the inventory.
     * </p>
     * <p>
     * Both the {@code InventoryEntry} and its contained quantity must be valid: {@code phone} must not be {@code null}
     * and its amount must be greater than or equal to zero.
     * </p>
     *
     * @param phone the {@link InventoryEntry} to be added to the store
     * @throws IllegalArgumentException if the provided {@code InventoryEntry} is {@code null} or its amount is negative
     */
    public void addNewPhone(InventoryEntry phone) {
        if (phone != null && phone.getAmount() > 0) {
            InventoryEntry temp = samePhoneInStore(phone);
            if (temp != null) {
                temp.setAmount(temp.getAmount() + phone.getAmount());
                return;
            }
            phones.add(phone);
        } else {
            throw new IllegalArgumentException("Phone can`t be null or amount can`t be less then 0");
        }
    }

    /**
     * Searches the store's inventory for an {@link InventoryEntry} that contains a {@link Phone} equal to the one in the given entry.
     * <p>
     * The comparison is based on the {@code equals} method of the {@link Phone} class.
     * </p>
     *
     * @param phone an {@link InventoryEntry} containing the {@link Phone} to search for
     * @return the matching {@link InventoryEntry} from the store's inventory if found; {@code null} otherwise
     */
    private InventoryEntry samePhoneInStore(InventoryEntry phone) {
        for (InventoryEntry element : phones) {
            if (phone.getPhone().equals(element.getPhone())) {
                return element;
            }
        }
        return null;
    }

    /**
     * Retrieves a list of {@link InventoryEntry} objects whose contained {@link Phone} objects match the specified type.
     *
     * @param type the type of phone to filter by
     * @return an {@link ArrayList} of inventory entries with phones matching the given type,
     *         or {@code null} if no matching entries are found
     */
    public ArrayList<InventoryEntry> getPhonesByType(String type) {
        ArrayList<InventoryEntry> list = new ArrayList<>();

        for (InventoryEntry element : phones) {
            if (element.getPhone().getType().equals(type)) {
                list.add(new InventoryEntry(element));
            }
        }
        return list.isEmpty() ? null : list;
    }

    /**
     * Retrieves a list of {@link InventoryEntry} objects whose contained {@link Phone} objects match the specified brand.
     *
     * @param brand the brand of the phone to filter by
     * @return an {@link ArrayList} of inventory entries with phones matching the given brand,
     *         or {@code null} if no matching entries are found
     */
    public ArrayList<InventoryEntry> getPhonesByBrand(String brand) {
        ArrayList<InventoryEntry> list = new ArrayList<>();

        for (InventoryEntry element : phones) {
            if (element.getPhone().getBrand().equals(brand)) {
                list.add(new InventoryEntry(element));
            }
        }
        return list.isEmpty() ? null : list;
    }

    /**
     * Retrieves a list of {@link InventoryEntry} objects whose contained {@link Phone} objects have the specified amount of RAM.
     *
     * @param ramCount the RAM amount to filter by
     * @return an {@link ArrayList} of inventory entries with the specified RAM count,
     *         or {@code null} if no matching entries are found
     */
    public ArrayList<InventoryEntry> getPhonesByRamCount(int ramCount) {
        ArrayList<InventoryEntry> list = new ArrayList<>();

        for (InventoryEntry element : phones) {
            if (element.getPhone().getRamAmount() == ramCount) {
                list.add(new InventoryEntry(element));
            }
        }
        return list.isEmpty() ? null : list;
    }

    /**
     * Retrieves all inventory entries (phones) stored in the store.
     * <p>
     * If the store's inventory is empty, a {@link NoPhonesAvailableException} is thrown.
     * </p>
     *
     * @return a new {@link ArrayList} containing all {@link InventoryEntry} objects in the store
     * @throws NoPhonesAvailableException if there are no phones available in the store
     */
    public ArrayList<InventoryEntry> getAll() {
        if (phones.isEmpty()) {
            throw new NoPhonesAvailableException("List of phones is empty");
        }
        ArrayList<InventoryEntry> list = new ArrayList<>();
        for (InventoryEntry element : phones){
            list.add(new InventoryEntry(element));
        }
        return list;
    }

    /**
     * Creates a clone of this {@code Store} instance.
     * <p>
     * This method performs a shallow copy of the {@code Store} object using {@code super.clone()},
     * ensuring that all inherited attributes are duplicated. Additionally, the {@code phones} list is
     * explicitly cloned to create a new {@code ArrayList} instance, preventing unintended modifications
     * to the original list in the cloned object.
     * </p>
     * <p>
     * Since {@code super.clone()} is used, this method assumes that the parent class properly implements
     * {@link Cloneable}. If cloning fails unexpectedly, an {@link AssertionError} is thrown to indicate
     * an error that should not occur.
     * </p>
     *
     * @return a cloned {@code Store} instance with identical attributes
     * @throws AssertionError if cloning is not supported despite {@link Cloneable} implementation
     */
    @Override
    public Store clone() {
        try {
            Store clone = (Store) super.clone();
            clone.phones = (ArrayList<InventoryEntry>) this.phones.clone(); // Cloning the phones list
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /**
     * Sorts the list of phones by price in ascending order.
     * <p>
     * This method creates a new sorted list of cloned phone objects from the inventory.
     * A comparator is used to compare phone prices to ensure proper ordering.
     * </p>
     * <p>
     * If any phone is {@code null}, {@link Objects#requireNonNull(Object)} will throw a {@link NullPointerException}.
     * </p>
     *
     * @return a sorted list of phones by price
     */
    public List<Phone> sortByPrice() {
        Comparator<Phone> priceComparator = (o1, o2) -> {
            Objects.requireNonNull(o1);
            Objects.requireNonNull(o2);
            return Double.compare(o1.getPrice(), o2.getPrice());
        };
        ArrayList<Phone> sortedPhonelist = new ArrayList<>();
        for (InventoryEntry phone : phones) {
            sortedPhonelist.add(phone.getPhone().clone()); // Cloning each phone to avoid modifying originals
        }
        sortedPhonelist.sort(priceComparator);
        return sortedPhonelist;
    }

    /**
     * Sorts the list of phones by brand name in alphabetical order.
     * <p>
     * This method creates a new sorted list of cloned phone objects from the inventory.
     * A comparator is used to compare brand names lexicographically using {@link CharSequence#compare(CharSequence, CharSequence)}.
     * </p>
     * <p>
     * If any phone is {@code null}, {@link Objects#requireNonNull(Object)} will throw a {@link NullPointerException}.
     * </p>
     *
     * @return a sorted list of phones by brand name
     */
    public List<Phone> sortByBrand() {
        Comparator<Phone> brandComparator = (o1, o2) -> {
            Objects.requireNonNull(o1);
            Objects.requireNonNull(o2);
            return CharSequence.compare(o1.getBrand(), o2.getBrand());
        };
        ArrayList<Phone> sortedPhonelist = new ArrayList<>();
        for (InventoryEntry phone : phones) {
            sortedPhonelist.add(phone.getPhone().clone()); // Cloning each phone to avoid modifying originals
        }
        sortedPhonelist.sort(brandComparator);
        return sortedPhonelist;
    }

    /**
     * Sorts the list of phones by RAM capacity in ascending order.
     * <p>
     * This method creates a new sorted list of cloned phone objects from the inventory.
     * A comparator is used to compare RAM capacities using {@link Integer#compare(int, int)}.
     * </p>
     * <p>
     * If any phone is {@code null}, {@link Objects#requireNonNull(Object)} will throw a {@link NullPointerException}.
     * </p>
     *
     * @return a sorted list of phones by RAM count
     */
    public List<Phone> sortByRAMCount() {
        Comparator<Phone> RAMComparator = (o1, o2) -> {
            Objects.requireNonNull(o1);
            Objects.requireNonNull(o2);
            return Integer.compare(o1.getRamAmount(), o2.getRamAmount());
        };
        ArrayList<Phone> sortedPhonelist = new ArrayList<>();
        for (InventoryEntry phone : phones) {
            sortedPhonelist.add(phone.getPhone().clone()); // Cloning each phone to avoid modifying originals
        }
        sortedPhonelist.sort(RAMComparator);
        return sortedPhonelist;
    }
}