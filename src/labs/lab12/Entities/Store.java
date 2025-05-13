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
    private ArrayList<InventoryEntry> phones;

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
        if (phone != null || phone.getAmount() >= 0) {
            phones.add(phone);
        } else {
            throw new IllegalArgumentException("Phone can`t be null or amount can`t be less then 0");
        }
    }

    /*
    /**
     * Retrieves a {@code Phone} object by its unique index.
     * Throws an exception if the provided ID is out of bounds.
     *
     * @param id the index of the phone in the repository
     * @return the {@code Phone} object corresponding to the given index
     * @throws IllegalArgumentException if the ID is invalid or does not exist
     */
    /*public Phone getPhoneByID(int id) {
        if (id >= phones.size() || id < 0) {
            throw new IllegalArgumentException("Phone with id = " + id +
                    " not exist. Size of repository is " + phones.size() + " phone(s)");
        }

        return phones.get(id);
    }*/

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
    public ArrayList<Phone> getPhonesByType(String type) {
        ArrayList<Phone> list = new ArrayList<>();
//        ArrayList<Phone> catalog = fromInventoryToCatalog();

        for (InventoryEntry element : phones) {
            if (element.getPhone().getType().equals(type)) {
                list.add(element.getPhone());
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
    public ArrayList<Phone> getPhonesByBrand(String brand) {
        ArrayList<Phone> list = new ArrayList<>();

        for (InventoryEntry element : phones) {
            if (element.getPhone().getBrand().equals(brand)) {
                list.add(element.getPhone());
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
    public ArrayList<Phone> getPhonesByRamCount(int ramCount) {
        ArrayList<Phone> list = new ArrayList<>();

        for (InventoryEntry element : phones) {
            if (element.getPhone().getRamAmount() == ramCount) {
                list.add(element.getPhone());
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
    public ArrayList<Phone> getAll() {
        if (phones.isEmpty()) {
            throw new NoPhonesAvailableException("List of phones is empty");
        }

        ArrayList<Phone> catalog = new ArrayList<>();
        for (InventoryEntry element : phones) {
            catalog.add(element.getPhone());
        }

        return catalog;
    }
}

