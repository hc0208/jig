package org.dddjava.jig.domain.model.models.jigobject.package_;

import org.dddjava.jig.domain.model.parts.packages.PackageComment;
import org.dddjava.jig.domain.model.parts.packages.PackageIdentifier;

public class JigPackage {
    PackageIdentifier packageIdentifier;
    PackageComment packageComment;

    public JigPackage(PackageIdentifier packageIdentifier, PackageComment packageComment) {
        this.packageIdentifier = packageIdentifier;
        this.packageComment = packageComment;
    }

    public PackageIdentifier packageIdentifier() {
        return packageIdentifier;
    }

    public String simpleName() {
        return packageIdentifier.simpleName();
    }

    /**
     * FullQualifiedName
     */
    public String fqn() {
        return packageIdentifier.asText();
    }

    public String label() {
        return packageComment.summaryOrSimpleName();
    }

    public JigPackageDescription description() {
        return JigPackageDescription.from(packageComment.descriptionComment());
    }
}
