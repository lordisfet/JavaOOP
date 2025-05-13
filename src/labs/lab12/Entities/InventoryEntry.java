package labs.lab12.Entities;

import labs.lab12.Entities.Phones.Phone;

import java.util.ArrayList;
import java.util.Objects;

public class InventoryEntry {
    private Phone phone;
    private int amount;

    public InventoryEntry(Phone phone, Integer amount) {
        if (phone == null) {
            throw new IllegalArgumentException("Phone cant be null");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Phone amount can't be less than 0");
        }
        this.phone = phone;
        this.amount = amount;
    }

    public InventoryEntry(InventoryEntry other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy from a null InventoryEntry object");
        }

        this.phone = other.phone;
        this.amount = other.amount;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "InventoryEntry{" +
                "phone=" + phone +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InventoryEntry that = (InventoryEntry) o;
        return Objects.equals(phone, that.phone) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, amount);
    }
}
