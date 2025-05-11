package labs.lab9_11.Entities;

import labs.lab9_11.Exceptions.NoPhonesAvailableException;
import labs.lab9_11.Entities.Phones.Phone;

import java.util.ArrayList;

/**
 * The {@code PhoneRepository} class manages a collection of {@link Phone} objects.
 * It provides functionality for adding, retrieving, and storing phone instances.
 */
public class PhoneRepository {
    private ArrayList<Phone> phones = new ArrayList<>();

    /**
     * Adds a new {@code Phone} object to the repository.
     * Throws an exception if the provided phone is {@code null}.
     *
     * @param phone the {@code Phone} object to be added
     * @throws IllegalArgumentException if the provided phone is {@code null}
     */
    public void add(Phone phone) {
        if (phone != null) {
            phones.add(phone);
        } else {
            throw new IllegalArgumentException("Phone can`t be null");
        }
    }

    /**
     * Retrieves a {@code Phone} object by its unique index.
     * Throws an exception if the provided ID is out of bounds.
     *
     * @param id the index of the phone in the repository
     * @return the {@code Phone} object corresponding to the given index
     * @throws IllegalArgumentException if the ID is invalid or does not exist
     */
    public Phone getPhoneByID(int id) {
        if (id >= phones.size() || id < 0) {
            throw new IllegalArgumentException("Phone with id = " + id +
                    " not exist. Size of repository is " + phones.size() + " phone(s)");
        }

        return phones.get(id);
    }

    /**
     * Retrieves a list of {@code Phone} objects that match the specified type.
     * If no matching phones are found, the method returns {@code null}.
     *
     * @param type the type of phone to filter by
     * @return a list of phones matching the given type or {@code null} if none are found
     */
    public ArrayList<Phone> getPhonesByType(String type) {
        ArrayList<Phone> list = new ArrayList<>();

        for (Phone phone : phones) {
            if (phone.getType().equals(type)) {
                list.add(phone);
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

        for (Phone phone : phones) {
            if (phone.getBrand().equals(brand)) {
                list.add(phone);
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

        for (Phone phone : phones) {
            if (phone.getRamAmount() == ramCount) {
                list.add(phone);
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

        return phones;
    }
}

