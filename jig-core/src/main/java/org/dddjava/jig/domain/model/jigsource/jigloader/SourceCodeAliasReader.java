package org.dddjava.jig.domain.model.jigsource.jigloader;

import org.dddjava.jig.domain.model.jigsource.file.text.javacode.JavaSources;
import org.dddjava.jig.domain.model.jigsource.file.text.javacode.PackageInfoSources;
import org.dddjava.jig.domain.model.jigsource.file.text.kotlincode.KotlinSources;
import org.dddjava.jig.domain.model.jigsource.file.text.scalacode.ScalaSources;
import org.dddjava.jig.domain.model.parts.alias.PackageAliases;
import org.dddjava.jig.domain.model.parts.alias.TypeAliases;

/**
 * コードを使用する別名別名読み取り機
 */
public class SourceCodeAliasReader {

    JavaSourceAliasReader javaSourceAliasReader;
    KotlinSourceAliasReader kotlinSourceAliasReader;
    ScalaSourceAliasReader scalaSourceAliasReader;

    public SourceCodeAliasReader(JavaSourceAliasReader javaSourceAliasReader) {
        this(javaSourceAliasReader, sources -> TypeAliases.empty(), sources -> TypeAliases.empty());
    }

    public SourceCodeAliasReader(JavaSourceAliasReader javaSourceAliasReader, KotlinSourceAliasReader kotlinSourceAliasReader) {
        this(javaSourceAliasReader, kotlinSourceAliasReader, sources -> TypeAliases.empty());
    }

    public SourceCodeAliasReader(JavaSourceAliasReader javaSourceAliasReader, ScalaSourceAliasReader scalaSourceAliasReader) {
        this(javaSourceAliasReader, sources -> TypeAliases.empty(), scalaSourceAliasReader);
    }

    private SourceCodeAliasReader(JavaSourceAliasReader javaSourceAliasReader, KotlinSourceAliasReader kotlinSourceAliasReader, ScalaSourceAliasReader scalaSourceAliasReader) {
        this.javaSourceAliasReader = javaSourceAliasReader;
        this.kotlinSourceAliasReader = kotlinSourceAliasReader;
        this.scalaSourceAliasReader = scalaSourceAliasReader;
    }

    public PackageAliases readPackages(PackageInfoSources packageInfoSources) {
        return javaSourceAliasReader.readPackages(packageInfoSources);
    }

    public TypeAliases readJavaSources(JavaSources javaSources) {
        return javaSourceAliasReader.readAlias(javaSources);
    }

    public TypeAliases readKotlinSources(KotlinSources kotlinSources) {
        return kotlinSourceAliasReader.readAlias(kotlinSources);
    }

    public TypeAliases readScalaSources(ScalaSources scalaSources) {
        return scalaSourceAliasReader.readAlias(scalaSources);
    }
}
