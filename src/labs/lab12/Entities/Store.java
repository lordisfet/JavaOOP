package labs.lab12.Entities;

import labs.lab12.Exceptions.NoPhonesAvailableException;
import labs.lab12.Entities.Phones.Phone;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The {@code PhoneRepository} class manages a collection of {@link Phone} objects.
 * It provides functionality for adding, retrieving, and storing phone instances.
 */
public class Store {
    private String name;
    private String address;
    private ArrayList<InventoryEntry> phones = new ArrayList<>();

    public Store(String name, String address, ArrayList<InventoryEntry> phones) {
        this.name = name;
        this.address = address;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<InventoryEntry> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<InventoryEntry> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phones=" + phones +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(name, store.name) && Objects.equals(address, store.address) && Objects.equals(phones, store.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, phones);
    }

    /**
     * Adds a new {@code Phone} object to the repository.
     * Throws an exception if the provided phone is {@code null}.
     *
     * @param phone the {@code Phone} object to be added
     * @throws IllegalArgumentException if the provided phone is {@code null}
     */
    public void addNewPhone(InventoryEntry phone) {
        if (phone != null && phone.getAmount() >= 0) {
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

    private InventoryEntry samePhoneInStore(InventoryEntry phone) {
        for (InventoryEntry element : phones) {
            if (phone.getPhone().equals(element.getPhone())) {
                return element;
            }
        }

        return null;
    }

    /*private ArrayList<Phone> fromInventoryToCatalog() {
        ArrayList<Phone> catalog = new ArrayList<>();
        for (InventoryEntry element : phones) {
            catalog.add(element.getPhone());
        }

        return catalog;
    }*/

    /**
     * Retrieves a list of {@code Phone} objects that match the specified type.
     * If no matching phones are found, the method returns {@code null}.
     *
     * @param type the type of phone to filter by
     * @return a list of phones matching the given type or {@code null} if none are found
     */
    public ArrayList<InventoryEntry> getPhonesByType(String type) {
        ArrayList<InventoryEntry> list = new ArrayList<>();
//        ArrayList<Phone> catalog = fromInventoryToCatalog();

        for (InventoryEntry element : phones) {
            if (element.getPhone().getType().equals(type)) {
                list.add(element);
            }
        }

        return list.isEmpty() ? null : list;
    }

    /**
     * Retrieves a list of {@code Phone} objects that match the specified brand.
     * If no matching phones are found, the method returns {@code null}.
     *
     * @param brand the brand of phone to filter by
     * @return a list of phones matching the given brand or {@code null} if none are found
     */
    public ArrayList<InventoryEntry> getPhonesByBrand(String brand) {
        ArrayList<InventoryEntry> list = new ArrayList<>();

        for (InventoryEntry element : phones) {
            if (element.getPhone().getBrand().equals(brand)) {
                list.add(element);
            }
        }

        return list.isEmpty() ? null : list;
    }

    /**
     * Retrieves a list of {@code Phone} objects that have the specified amount of RAM.
     * If no matching phones are found, the method returns {@code null}.
     *
     * @param ramCount the RAM amount to filter phones by
     * @return a list of phones with the given RAM amount or {@code null} if none are found
     */
    public ArrayList<InventoryEntry> getPhonesByRamCount(int ramCount) {
        ArrayList<InventoryEntry> list = new ArrayList<>();

        for (InventoryEntry element : phones) {
            if (element.getPhone().getRamAmount() == ramCount) {
                list.add(element);
            }
        }

        return list.isEmpty() ? null : list;
    }

    /**
     * Retrieves all phones stored in the repository.
     * Throws an exception if no phones are available.
     *
     * @return a list of all {@code Phone} objects in the repository
     * @throws NoPhonesAvailableException if the repository is empty
     */
    public ArrayList<InventoryEntry> getAll() {
        if (phones.isEmpty()) {
            throw new NoPhonesAvailableException("List of phones is empty");
        }

        return new ArrayList<>(phones);
    }
}

