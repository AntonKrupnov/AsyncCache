package com.github.antonkrupnov;

import java.util.Collections;
import java.util.List;

/**
 * Anton 23.03.2018
 */
public class RightComponent {

    private final List list;

    // Now Component knows nothing about builder
    private RightComponent(List list) {
        this.list = list;
    }

    // Now list can not be changed
    public List getList() {
        return Collections.unmodifiableList(list);
    }

    public static class Builder {

        private List list;

        public Builder withList(List list) {
            this.list = list;
            return this;
        }

        public RightComponent build() {
            return new RightComponent(this.list);
        }
    }
}
