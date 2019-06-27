package org.dddjava.jig.domain.model.implementation.source.code;

import org.dddjava.jig.domain.model.implementation.source.code.javacode.JavaSourceFile;
import org.dddjava.jig.domain.model.implementation.source.code.kotlincode.KotlinSourceFile;

import java.nio.file.Path;

/**
 * コードのファイル
 */
public class CodeSourceFile {

    Path path;

    public CodeSourceFile(Path path) {
        this.path = path;
    }

    public JavaSourceFile asJava() {
        return new JavaSourceFile(path);
    }

    public KotlinSourceFile asKotlin() {
        return new KotlinSourceFile(path);
    }
}
