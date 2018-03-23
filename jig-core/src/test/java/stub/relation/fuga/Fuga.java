package stub.relation.fuga;

import stub.relation.fuga.foo.Baz;
import stub.relation.fuga.foo.Foo;
import stub.relation.fuga.qux.Qux;

import java.util.List;

/**
 * 使用しているクラスを抽出するテスト対象
 */
public class Fuga {
    // フィールド
    Foo foo;

    // 配列
    Corge[] array;
    // ジェネリクス
    List<Grault> list;

    // 戻り値と例外
    Baz foo() throws FugaException {
        return foo
                // メソッドチェーンの途中で使う
                .toBar()
                .toBaz();
    }

    void qux() {
        // ネストされたクラス
        new Qux.Quuz();
    }
}
