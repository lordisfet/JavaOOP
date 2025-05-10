package labs.lab9.Entities;

import labs.lab9.Entities.Phones.FoldablePhone;
import labs.lab9.Entities.Phones.GamingPhone;
import labs.lab9.Entities.Phones.KeypadPhone;
import labs.lab9.Entities.Phones.SmartPhone;
import labs.lab9.Enums.ScreenResolution;
import labs.lab9.Exceptions.NoPhonesAvailableException;
import labs.lab9.Entities.Phones.Phone;

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

