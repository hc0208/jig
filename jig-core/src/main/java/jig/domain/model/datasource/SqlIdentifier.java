package jig.domain.model.datasource;

import jig.domain.model.identifier.MethodIdentifier;

import java.util.Objects;

public class SqlIdentifier {

    String value;

    public SqlIdentifier(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SqlIdentifier that = (SqlIdentifier) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public boolean matches(MethodIdentifier identifier) {
        // メソッド名から引数部分を除去してマッチングする
        String nameString = identifier.asFullText();
        String substring = nameString.substring(0, nameString.indexOf('('));
        return substring.equals(value);
    }
}
