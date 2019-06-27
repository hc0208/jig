package org.dddjava.jig.domain.model.implementation.source.code;

import org.dddjava.jig.domain.model.implementation.analyzed.alias.AliasSource;

import java.util.List;

/**
 * テキストソース一覧
 */
public class CodeSources {

    List<CodeSource> list;

    public CodeSources(List<CodeSource> list) {
        this.list = list;
    }

    public AliasSource aliasSource() {
        return list.stream()
                .map(codeSource -> new AliasSource(
                        codeSource.javaSources,
                        codeSource.kotlinSources,
                        codeSource.packageInfoSources
                ))
                .reduce(new AliasSource(), AliasSource::merge);
    }

    public boolean nothing() {
        return list.isEmpty();
    }
}
