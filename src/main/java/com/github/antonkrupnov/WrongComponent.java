package com.github.antonkrupnov;

import java.util.List;

public class WrongComponent {

    private final List list;

    // It is not good to use builder itself as parameter af target class (Component)
    // Builder pattern should take full responsibility of object creation including mapping fields from builder to object.
    // Object should not know about builder anything.
    // In the future builder may change mapping or add additional verification and Component should not be changed
    private WrongComponent(Builder builder) {
        this.list = builder.list;
    }

    // If the suppose that object is immutable - we should not expose collections like that.
    // Because now we can only prove that collection reference will be the same but not the elements.
    // Client of this class can easily remove or add an element.
    public List getList() {
        return list;
    }

    public static class Builder {

        private List list;

        public Builder withList(List list) {
            this.list = list;
            return this;
        }

        public WrongComponent build() {
            return new WrongComponent(this);
        }
    }
}
