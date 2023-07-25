package org.acme.usertypes;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Locale;
import java.util.Objects;
import org.acme.types.StringWrapper;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.EnhancedUserType;

public class StringWrapperUserType implements EnhancedUserType<StringWrapper> {

    @Override
    public int getSqlType() {
        return java.sql.Types.VARCHAR;
    }

    @Override
    public Class<StringWrapper> returnedClass() {
        return StringWrapper.class;
    }

    @Override
    public boolean equals(
            final StringWrapper x,
            final StringWrapper y
    ) {
        return Objects.equals(x, y);
    }

    @Override
    public int hashCode(final StringWrapper x) {
        return Objects.hashCode(x);
    }

    @Override
    public StringWrapper nullSafeGet(
            final ResultSet rs,
            final int position,
            final SharedSessionContractImplementor session,
            final Object owner
    ) throws SQLException {
        String columnValue = rs.getString(position);
        if (rs.wasNull()) {
            return null;
        }
        return new StringWrapper(columnValue);
    }

    @Override
    public void nullSafeSet(
            final PreparedStatement st,
            final StringWrapper value,
            final int index,
            final SharedSessionContractImplementor session
    ) throws SQLException {
        if (value == null) {
            st.setNull(index, Types.VARCHAR);
        } else {
            st.setString(index, value.getField());
        }
    }

    @Override
    public StringWrapper deepCopy(final StringWrapper value) {
        return value == null ? null : new StringWrapper(value.getField());
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(final StringWrapper value) {
        return deepCopy(value);
    }

    @Override
    public StringWrapper assemble(
            final Serializable cached,
            final Object owner
    ) {
        return deepCopy((StringWrapper) cached);
    }

    @Override
    public String toSqlLiteral(final StringWrapper value) {
        final var stringValue = value != null ? value.getField() : null;
        return String.format(Locale.ROOT, "'%s'", stringValue);
    }

    @Override
    public String toString(final StringWrapper value) throws HibernateException {
        return value != null ? value.getField() : null;
    }

    @Override
    public StringWrapper fromStringValue(final CharSequence sequence) throws HibernateException {
        return new StringWrapper(sequence.toString());
    }
}
