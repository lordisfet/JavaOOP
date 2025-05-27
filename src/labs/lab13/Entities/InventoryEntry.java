package labs.lab13.Entities;

import labs.lab13.Entities.Phones.Phone;

import java.util.Objects;

/**
 * Represents a single entry in the phone inventory.
 * <p>
 * An {@code InventoryEntry} encapsulates a {@link Phone} object and the quantity (amount)
 * of that phone available in the inventory. It provides constructors for creating an entry
 * from scratch or by copying another entry, as well as standard getters, setters, and
 * overridden methods for string representation and equality.
 * </p>
 */
public class InventoryEntry implements Cloneable{
    /**
     * The phone associated with this inventory entry.
     */
    private Phone phone;

    /**
     * The number of units available for the specified phone.
     */
    private int amount;

    /**
     * Constructs a new {@code InventoryEntry} with the given phone and amount.
     * <p>
     * The specified phone must not be {@code null} and the amount must be non-negative.
     * Otherwise, an {@link IllegalArgumentException} is thrown.
     * </p>
     *
     * @param phone  the {@link Phone} object for this inventory entry
     * @param amount the number of units available
     * @throws IllegalArgumentException if the phone is {@code null} or amount is negative
     */
    public InventoryEntry(Phone phone, Integer amount) {
        if (phone == null) {
            throw new IllegalArgumentException("Phone can't be null");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Phone amount can't be less than 0");
        }
        this.phone = phone;
        this.amount = amount;
    }

    /**
     * Constructs a new {@code InventoryEntry} as a copy of an existing entry.
     * <p>
     * The {@code other} entry must not be {@code null}; otherwise, an {@link IllegalArgumentException}
     * is thrown.
     * </p>
     *
     * @param other the existing {@code InventoryEntry} to copy
     * @throws IllegalArgumentException if {@code other} is {@code null}
     */
    public InventoryEntry(InventoryEntry other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy from a null InventoryEntry object");
        }
        this.phone = other.phone.clone();
        this.amount = other.amount;
    }

    /**
     * Returns the {@link Phone} associated with this inventory entry.
     *
     * @return the phone object
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Sets the {@link Phone} for this inventory entry.
     *
     * @param phone the new phone object
     */
    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    /**
     * Returns the available amount for this inventory entry.
     *
     * @return the quantity of units available
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Sets the available amount for this inventory entry.
     *
     * @param amount the number of units available
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * Returns a string representation of this inventory entry.
     * The representation includes the {@link Phone} information and the available amount.
     *
     * @return a string representation of the inventory entry
     */
    @Override
    public String toString() {
        return phone + "amount: " + amount + "\n";
    }

    /**
     * Returns a file-friendly string representation of this inventory entry.
     * <p>
     * The output includes the {@link Phone}'s file string representation followed by the amount.
     * </p>
     *
     * @return a string suitable for file output
     */
    public String toStringToFile() {
        return phone.toStringToFile() + "amount:" + amount + ';';
    }

    /**
     * Compares this inventory entry with the specified object for equality.
     * <p>
     * Two inventory entries are considered equal if their underlying {@link Phone} objects and amounts are equal.
     * </p>
     *
     * @param o the object to compare with
     * @return {@code true} if the specified object is equal to this inventory entry; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InventoryEntry that = (InventoryEntry) o;
        return Objects.equals(phone, that.phone) && Objects.equals(amount, that.amount);
    }

    /**
     * Returns a hash code value for this inventory entry based on its phone and amount.
     *
     * @return a hash code value for this inventory entry
     */
    @Override
    public int hashCode() {
        return Objects.hash(phone, amount);
    }

    /**
     * Creates a clone of this {@code InventoryEntry} instance.
     * <p>
     * This method performs a shallow copy of the {@code InventoryEntry} object using {@code super.clone()},
     * ensuring that all inherited attributes are duplicated. Additionally, the {@code phone} field is
     * explicitly cloned to ensure that the cloned entry maintains an independent copy of the phone object.
     * </p>
     * <p>
     * Since {@code super.clone()} is used, this method assumes that the parent class properly implements
     * {@link Cloneable}. If cloning fails, an {@link AssertionError} is thrown to indicate an unexpected failure.
     * </p>
     *
     * @return a cloned {@code InventoryEntry} instance with identical attributes
     * @throws AssertionError if cloning is not supported despite {@link Cloneable} implementation
     */
    @Override
    public InventoryEntry clone() {
        try {
            InventoryEntry clone = (InventoryEntry) super.clone();
            clone.phone = this.phone.clone(); // Manually cloning the phone object
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
