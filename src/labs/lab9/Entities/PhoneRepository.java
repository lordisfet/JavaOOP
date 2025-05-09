package labs.lab9.Entities;

import labs.lab9.Entities.Phones.FoldablePhone;
import labs.lab9.Entities.Phones.GamingPhone;
import labs.lab9.Entities.Phones.KeypadPhone;
import labs.lab9.Entities.Phones.SmartPhone;
import labs.lab9.Enums.ScreenResolution;
import labs.lab9.Exceptions.NoPhonesAvailableException;
import labs.lab9.Entities.Phones.Phone;

import java.util.ArrayList;

import static labs.lab9.Entities.Validator.*;
import static labs.lab9.Entities.Validator.setIntWithValidation;

public class PhoneRepository {
    private ArrayList<Phone> phones = new ArrayList<>();

    public void add(Phone phone) {
        if (phone != null) {
            phones.add(phone);
        }
        else {
            throw new IllegalArgumentException("Phone can`t be null");
        }
    }

    public Phone getPhoneByID(int id) {
        if (id >= phones.size() || id < 0) {
            throw new IllegalArgumentException("Phone with id = " + id +
                    " not exist. Size of repository is " + phones.size() + " phone(s)");
        }

        return phones.get(id);
    }

    public ArrayList<Phone> getAll() {
        if (phones.isEmpty()) {
            throw new NoPhonesAvailableException("list of phones is empty");
        }

        return phones;
    }
}
