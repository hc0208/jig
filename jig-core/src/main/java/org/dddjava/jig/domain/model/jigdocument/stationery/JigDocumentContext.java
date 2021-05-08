package org.dddjava.jig.domain.model.jigdocument.stationery;

import org.dddjava.jig.domain.model.models.jigobject.package_.JigPackage;
import org.dddjava.jig.domain.model.parts.classes.type.ClassComment;
import org.dddjava.jig.domain.model.parts.classes.type.TypeIdentifier;
import org.dddjava.jig.domain.model.parts.packages.PackageComment;
import org.dddjava.jig.domain.model.parts.packages.PackageIdentifier;

public interface JigDocumentContext {

    String label(String key);

    LinkPrefix linkPrefix();

    PackageComment packageComment(PackageIdentifier packageIdentifier);

    ClassComment classComment(TypeIdentifier typeIdentifier);

    default JigPackage jigPackage(PackageIdentifier packageIdentifier) {
        return new JigPackage(packageIdentifier, packageComment(packageIdentifier));
    }

    PackageIdentifierFormatter packageIdentifierFormatter();
}
