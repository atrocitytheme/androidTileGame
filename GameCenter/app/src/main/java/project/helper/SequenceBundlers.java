/*
class used for sorting values of integer hashmap in IOHelper
mainly used for sorting keys according to values in pair
*/
package project.helper;

import android.support.annotation.NonNull;

public class SequenceBundlers implements Comparable<SequenceBundlers> {
    private String key;
    private int value;

    /**
     * To new a SequenceBundlers
     * this constructor is public for convenience of unit test.
     */
    public SequenceBundlers(String key, Object value) {
        this.key = key;
        this.value = (int) value;
    }

    /**
     * sort the bundlers according to value
     */
    @Override
    public int compareTo(@NonNull SequenceBundlers other) {
        return this.value - other.value;
    }

    /**
     * @return String the key value of this bundler
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return int the int value of this bundler
     */
    public int getValue() {
        return this.value;
    }
}
