package org.dddjava.jig.domain.model.jigsource.bytecode;

import org.dddjava.jig.domain.model.jigsource.file.binary.ClassSources;

/**
 * 対象から実装を取得するファクトリ
 */
public interface ByteCodeFactory {

    TypeByteCodes readFrom(ClassSources classSources);
}
