package org.dddjava.jig.domain.model.values;

import org.dddjava.jig.domain.model.businessrules.BusinessRule;
import org.dddjava.jig.domain.model.businessrules.BusinessRules;
import org.dddjava.jig.domain.model.implementation.analyzed.bytecode.TypeByteCode;
import org.dddjava.jig.domain.model.implementation.analyzed.bytecode.TypeByteCodes;

import java.util.ArrayList;
import java.util.List;

/**
 * 値の型一覧
 */
public class ValueTypes {
    private List<ValueType> list;

    public ValueTypes(BusinessRules businessRules, TypeByteCodes typeByteCodes, ValueKind valueKind) {
        list = new ArrayList<>();
        for (BusinessRule businessRule: businessRules.list()) {
            TypeByteCode typeByteCode = typeByteCodes.typeByteCodeOf(businessRule.type().identifier());
            if (typeByteCode.isEnum()) {
                continue;
            }
            if (valueKind.matches(typeByteCode.fieldDeclarations())) {
                list.add(new ValueType(typeByteCode.typeIdentifier()));
            }
        }
    }

    public List<ValueType> list() {
        return list;
    }
}
