package org.acme.types;

import java.io.Serializable;

public class StringWrapper implements Serializable {

    public String field;


    public StringWrapper(final String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(final String field) {
        this.field = field;
    }
}
