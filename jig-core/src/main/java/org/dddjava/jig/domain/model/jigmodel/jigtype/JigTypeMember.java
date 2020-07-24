package org.dddjava.jig.domain.model.jigmodel.jigtype;

import org.dddjava.jig.domain.model.jigmodel.lowmodel.declaration.field.StaticFieldDeclarations;
import org.dddjava.jig.domain.model.jigmodel.lowmodel.richmethod.Methods;

/**
 * クラスメンバ
 *
 * コンストラクタはメンバではないが、実装上はstaticファクトリメソッドと同等の役割を担うのでここで扱う。
 */
public class JigTypeMember {
    Methods constructors;
    Methods staticMethods;
    StaticFieldDeclarations staticFieldDeclarations;

    public JigTypeMember(Methods constructors, Methods staticMethods, StaticFieldDeclarations staticFieldDeclarations) {
        this.constructors = constructors;
        this.staticMethods = staticMethods;
        this.staticFieldDeclarations = staticFieldDeclarations;
    }
}
