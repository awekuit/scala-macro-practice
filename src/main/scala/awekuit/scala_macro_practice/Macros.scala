package awekuit.scala_macro_practice

import scala.reflect.macros.blackbox.Context
import scala.language.experimental.macros

object Macros {

  def ops[A](a: A, b: A): A = macro Macros.opsImpl[A]

  def opsImpl[A](c: Context)(a: c.Expr[A], b: c.Expr[A]): c.Expr[A] = {
    import c.universe._

    println("*" * 80)

    println(s"c.prefix: ${c.prefix}")
    println(s"c.prefix.tree: ${c.prefix.tree}")

    println("*" * 80)

    println(s"a: ${a}")
    println(s"a.toString: ${a.toString()}")
    println(s"a.actualType: ${a.actualType}")
    println(s"a.staticType: ${a.staticType}")

    println("*" * 80)

    println(s"a.tree: ${a.tree}")
    println(s"a.tree.isDef = ${a.tree.isDef}")
    println(s"a.tree.isEmpty = ${a.tree.isEmpty}")
    println(s"a.tree.nonEmpty = ${a.tree.nonEmpty}")
    println(s"a.tree.canHaveAttrs = ${a.tree.canHaveAttrs}")
    println(s"a.tree.isTerm = ${a.tree.isTerm}")
    println(s"a.tree.isType = ${a.tree.isType}")
    println(s"a.tree.pos = ${a.tree.pos}")
    println(s"a.tree.tpe = ${a.tree.tpe}")
    println(s"a.tree.symbol = ${a.tree.symbol}")

    println("*" * 80)

    println(s"a.tree.children = ${a.tree.children}")

    val expr: c.universe.Expr[A] = reify {
      if (a.splice == b.splice) {
        a.splice
      } else {
        b.splice
      }
    }

    println("*" * 80)
    println(s"result expr:")
    println(expr)
    println("*" * 80)
    println(s"result showCode(expr.tree):")
    println(showCode(expr.tree))
    println("*" * 80)

    expr
  }
}
/*
$ sbt console
scala> import awekuit.scala_macro_practice.Macros._; import scala.math._; val res = ops(max(1,2), min(3,4))
********************************************************************************
c.prefix: Expr[Nothing](awekuit.scala_macro_practice.Macros)
c.prefix.tree: awekuit.scala_macro_practice.Macros
********************************************************************************
a: Expr[Nothing](scala.math.`package`.max(1, 2))
a.toString: Expr[Nothing](scala.math.`package`.max(1, 2))
a.actualType: Int
a.staticType: Nothing
********************************************************************************
a.tree: scala.math.`package`.max(1, 2)
a.tree.isDef = false
a.tree.isEmpty = false
a.tree.nonEmpty = true
a.tree.canHaveAttrs = true
a.tree.isTerm = true
a.tree.isType = false
a.tree.pos = source-<console>,line-11,offset=149
a.tree.tpe = Int
a.tree.symbol = method max
********************************************************************************
a.tree.children = List(scala.math.`package`.max, 1, 2)
********************************************************************************
result expr:
Expr[A](if (scala.math.`package`.max(1, 2).$eq$eq(scala.math.`package`.min(3, 4)))
  scala.math.`package`.max(1, 2)
else
  scala.math.`package`.min(3, 4))
********************************************************************************
result showCode(expr.tree):
if (scala.math.`package`.max(1, 2).==(scala.math.`package`.min(3, 4)))
  scala.math.`package`.max(1, 2)
else
  scala.math.`package`.min(3, 4)
********************************************************************************
import awekuit.scala_macro_practice.Macros._
import scala.math._
res: Int = 3
 */
/*
> scalac -Ymacro-debug-lite src/main/scala/awekuit/scala_macro_practice/Macros.scala

performing macro expansion c.universe.reify[A](if (a.==(b))
  a.splice
else
  b.splice) at source-/Users/cw-akatsu/ghq/src/github.com/awekuit/scala-macro-practice/src/main/scala/awekuit/scala_macro_practice/Macros.scala,line-44,offset=1233
{
  val $u: c.universe.type = c.universe;
  val $m: $u.Mirror = c.universe.rootMirror;
  $u.Expr.apply[A]($m, {
    final class $treecreator1 extends TreeCreator {
      def <init>(): $treecreator1 = {
        $treecreator1.super.<init>();
        ()
      };
      def apply[U <: scala.reflect.api.Universe with Singleton]($m$untyped: scala.reflect.api.Mirror[U]): U#Tree = {
        val $u: U = $m$untyped.universe;
        val $m: $u.Mirror = $m$untyped.asInstanceOf[$u.Mirror];
        val free$a1: $u.FreeTermSymbol = $u.internal.reificationSupport.newFreeTerm("a", a, $u.internal.reificationSupport.FlagsRepr.apply(17592190246912L), "defined by opsImpl in Macros.scala:10:30");
        val free$b1: $u.FreeTermSymbol = $u.internal.reificationSupport.newFreeTerm("b", b, $u.internal.reificationSupport.FlagsRepr.apply(17592190246912L), "defined by opsImpl in Macros.scala:10:44");
        val free$c1: $u.FreeTermSymbol = $u.internal.reificationSupport.newFreeTerm("c", c, $u.internal.reificationSupport.FlagsRepr.apply(17592190246912L), "defined by opsImpl in Macros.scala:10:18");
        val free$A2: $u.FreeTypeSymbol = $u.internal.reificationSupport.newFreeType("A", $u.internal.reificationSupport.FlagsRepr.apply(8208L), "defined by opsImpl in Macros.scala:10:15");
        $u.internal.reificationSupport.setInfo[$u.FreeTermSymbol](free$a1, $u.internal.reificationSupport.TypeRef($u.internal.reificationSupport.SingleType($u.NoPrefix, free$c1), $u.internal.reificationSupport.selectType($m.staticClass("scala.reflect.macros.Aliases"), "Expr"), scala.collection.immutable.List.apply[$u.Type]($u.internal.reificationSupport.TypeRef($u.NoPrefix, free$A2, scala.collection.immutable.Nil))));
        $u.internal.reificationSupport.setInfo[$u.FreeTermSymbol](free$b1, $u.internal.reificationSupport.TypeRef($u.internal.reificationSupport.SingleType($u.NoPrefix, free$c1), $u.internal.reificationSupport.selectType($m.staticClass("scala.reflect.macros.Aliases"), "Expr"), scala.collection.immutable.List.apply[$u.Type]($u.internal.reificationSupport.TypeRef($u.NoPrefix, free$A2, scala.collection.immutable.Nil))));
        $u.internal.reificationSupport.setInfo[$u.FreeTermSymbol](free$c1, $m.staticClass("scala.reflect.macros.blackbox.Context").asType.toTypeConstructor);
        $u.internal.reificationSupport.setInfo[$u.FreeTypeSymbol](free$A2, $u.internal.reificationSupport.TypeBounds($m.staticClass("scala.Nothing").asType.toTypeConstructor, $m.staticClass("scala.Any").asType.toTypeConstructor));
        $u.If.apply($u.Apply.apply($u.Select.apply($u.internal.reificationSupport.mkIdent(free$a1), $u.TermName.apply("$eq$eq")), scala.collection.immutable.List.apply[$u.Ident]($u.internal.reificationSupport.mkIdent(free$b1))), a.in[$u.type]($m).tree, b.in[$u.type]($m).tree)
      }
    };
    new $treecreator1()
  })($u.WeakTypeTag.apply[A]($m, {
    final class $typecreator2 extends TypeCreator {
      def <init>(): $typecreator2 = {
        $typecreator2.super.<init>();
        ()
      };
      def apply[U <: scala.reflect.api.Universe with Singleton]($m$untyped: scala.reflect.api.Mirror[U]): U#Type = {
        val $u: U = $m$untyped.universe;
        val $m: $u.Mirror = $m$untyped.asInstanceOf[$u.Mirror];
        val free$A1: $u.FreeTypeSymbol = $u.internal.reificationSupport.newFreeType("A", $u.internal.reificationSupport.FlagsRepr.apply(8208L), "defined by opsImpl in Macros.scala:10:15");
        $u.internal.reificationSupport.setInfo[$u.FreeTypeSymbol](free$A1, $u.internal.reificationSupport.TypeBounds($m.staticClass("scala.Nothing").asType.toTypeConstructor, $m.staticClass("scala.Any").asType.toTypeConstructor));
        $u.internal.reificationSupport.TypeRef($u.NoPrefix, free$A1, scala.collection.immutable.Nil)
      }
    };
    new $typecreator2()
  }))
}
Block(List(ValDef(Modifiers(), TermName("$u"), TypeTree().setOriginal(SingletonTypeTree(Select(Ident(TermName("c")), TermName("universe")))), Select(Ident(TermName("c")), TermName("universe"))), ValDef(Modifiers(), TermName("$m"), TypeTree().setOriginal(Select(Ident(TermName("$u")), TypeName("Mirror"))), Select(Select(Ident(TermName("c")), TermName("universe")), TermName("rootMirror")))), Apply(Apply(TypeApply(Select(Select(Ident(TermName("$u")), TermName("Expr")), TermName("apply")), List(TypeTree())), List(Ident(TermName("$m")), Block(List(ClassDef(Modifiers(FINAL), TypeName("$treecreator1"), List(), Template(List(Ident(scala.reflect.api.TreeCreator)), noSelfType, List(DefDef(Modifiers(), termNames.CONSTRUCTOR, List(), List(List()), TypeTree(), Block(List(Apply(Select(Super(This(TypeName("$treecreator1")), typeNames.EMPTY), termNames.CONSTRUCTOR), List())), Literal(Constant(())))), DefDef(Modifiers(), TermName("apply"), List(TypeDef(Modifiers(PARAM), TypeName("U"), List(), TypeTree().setOriginal(TypeBoundsTree(TypeTree().setOriginal(Ident(scala.Nothing)), TypeTree().setOriginal(CompoundTypeTree(Template(List(Ident(scala.reflect.api.Universe), Ident(scala.Singleton)), noSelfType, List()))))))), List(List(ValDef(Modifiers(PARAM), TermName("$m$untyped"), TypeTree().setOriginal(AppliedTypeTree(Ident(scala.reflect.api.Mirror), List(TypeTree().setOriginal(Ident(TypeName("U")))))), EmptyTree))), TypeTree().setOriginal(SelectFromTypeTree(TypeTree().setOriginal(Ident(TypeName("U"))), TypeName("Tree"))), Block(List(ValDef(Modifiers(), TermName("$u"), TypeTree().setOriginal(Ident(TypeName("U"))), Select(Ident(TermName("$m$untyped")), TermName("universe"))), ValDef(Modifiers(), TermName("$m"), TypeTree().setOriginal(Select(Ident(TermName("$u")), TypeName("Mirror"))), TypeApply(Select(Ident(TermName("$m$untyped")), TermName("asInstanceOf")), List(TypeTree().setOriginal(Select(Ident(TermName("$u")), TypeName("Mirror")))))), ValDef(Modifiers(), TermName("free$a1"), TypeTree(), Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("newFreeTerm")), List(Literal(Constant("a")), Ident(TermName("a")), Apply(Select(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("FlagsRepr")), TermName("apply")), List(Literal(Constant(17592190246912)))), Literal(Constant("defined by opsImpl in Macros.scala:10:30"))))), ValDef(Modifiers(), TermName("free$b1"), TypeTree(), Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("newFreeTerm")), List(Literal(Constant("b")), Ident(TermName("b")), Apply(Select(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("FlagsRepr")), TermName("apply")), List(Literal(Constant(17592190246912)))), Literal(Constant("defined by opsImpl in Macros.scala:10:44"))))), ValDef(Modifiers(), TermName("free$c1"), TypeTree(), Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("newFreeTerm")), List(Literal(Constant("c")), Ident(TermName("c")), Apply(Select(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("FlagsRepr")), TermName("apply")), List(Literal(Constant(17592190246912)))), Literal(Constant("defined by opsImpl in Macros.scala:10:18"))))), ValDef(Modifiers(), TermName("free$A2"), TypeTree(), Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("newFreeType")), List(Literal(Constant("A")), Apply(Select(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("FlagsRepr")), TermName("apply")), List(Literal(Constant(8208)))), Literal(Constant("defined by opsImpl in Macros.scala:10:15"))))), Apply(TypeApply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("setInfo")), List(TypeTree())), List(Ident(TermName("free$a1")), Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("TypeRef")), List(Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("SingleType")), List(Select(Ident(TermName("$u")), TermName("NoPrefix")), Ident(TermName("free$c1")))), Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("selectType")), List(Apply(Select(Ident(TermName("$m")), TermName("staticClass")), List(Literal(Constant("scala.reflect.macros.Aliases")))), Literal(Constant("Expr")))), Apply(TypeApply(Select(Select(Select(Select(Ident(scala), scala.collection), scala.collection.immutable), scala.collection.immutable.List), TermName("apply")), List(TypeTree())), List(Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("TypeRef")), List(Select(Ident(TermName("$u")), TermName("NoPrefix")), Ident(TermName("free$A2")), Select(Select(Select(Ident(scala), scala.collection), scala.collection.immutable), scala.collection.immutable.Nil))))))))), Apply(TypeApply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("setInfo")), List(TypeTree())), List(Ident(TermName("free$b1")), Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("TypeRef")), List(Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("SingleType")), List(Select(Ident(TermName("$u")), TermName("NoPrefix")), Ident(TermName("free$c1")))), Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("selectType")), List(Apply(Select(Ident(TermName("$m")), TermName("staticClass")), List(Literal(Constant("scala.reflect.macros.Aliases")))), Literal(Constant("Expr")))), Apply(TypeApply(Select(Select(Select(Select(Ident(scala), scala.collection), scala.collection.immutable), scala.collection.immutable.List), TermName("apply")), List(TypeTree())), List(Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("TypeRef")), List(Select(Ident(TermName("$u")), TermName("NoPrefix")), Ident(TermName("free$A2")), Select(Select(Select(Ident(scala), scala.collection), scala.collection.immutable), scala.collection.immutable.Nil))))))))), Apply(TypeApply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("setInfo")), List(TypeTree())), List(Ident(TermName("free$c1")), Select(Select(Apply(Select(Ident(TermName("$m")), TermName("staticClass")), List(Literal(Constant("scala.reflect.macros.blackbox.Context")))), TermName("asType")), TermName("toTypeConstructor")))), Apply(TypeApply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("setInfo")), List(TypeTree())), List(Ident(TermName("free$A2")), Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("TypeBounds")), List(Select(Select(Apply(Select(Ident(TermName("$m")), TermName("staticClass")), List(Literal(Constant("scala.Nothing")))), TermName("asType")), TermName("toTypeConstructor")), Select(Select(Apply(Select(Ident(TermName("$m")), TermName("staticClass")), List(Literal(Constant("scala.Any")))), TermName("asType")), TermName("toTypeConstructor"))))))), Apply(Select(Select(Ident(TermName("$u")), TermName("If")), TermName("apply")), List(Apply(Select(Select(Ident(TermName("$u")), TermName("Apply")), TermName("apply")), List(Apply(Select(Select(Ident(TermName("$u")), TermName("Select")), TermName("apply")), List(Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("mkIdent")), List(Ident(TermName("free$a1")))), Apply(Select(Select(Ident(TermName("$u")), TermName("TermName")), TermName("apply")), List(Literal(Constant("$eq$eq")))))), Apply(TypeApply(Select(Select(Select(Select(Ident(scala), scala.collection), scala.collection.immutable), scala.collection.immutable.List), TermName("apply")), List(TypeTree())), List(Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("mkIdent")), List(Ident(TermName("free$b1")))))))), Select(Apply(TypeApply(Select(Ident(TermName("a")), TermName("in")), List(TypeTree())), List(Ident(TermName("$m")))), TermName("tree")), Select(Apply(TypeApply(Select(Ident(TermName("b")), TermName("in")), List(TypeTree())), List(Ident(TermName("$m")))), TermName("tree")))))))))), Apply(Select(New(Ident(TypeName("$treecreator1"))), termNames.CONSTRUCTOR), List())))), List(Apply(TypeApply(Select(Select(Ident(TermName("$u")), TermName("WeakTypeTag")), TermName("apply")), List(TypeTree())), List(Ident(TermName("$m")), Block(List(ClassDef(Modifiers(FINAL), TypeName("$typecreator2"), List(), Template(List(Ident(scala.reflect.api.TypeCreator)), noSelfType, List(DefDef(Modifiers(), termNames.CONSTRUCTOR, List(), List(List()), TypeTree(), Block(List(Apply(Select(Super(This(TypeName("$typecreator2")), typeNames.EMPTY), termNames.CONSTRUCTOR), List())), Literal(Constant(())))), DefDef(Modifiers(), TermName("apply"), List(TypeDef(Modifiers(PARAM), TypeName("U"), List(), TypeTree().setOriginal(TypeBoundsTree(TypeTree().setOriginal(Ident(scala.Nothing)), TypeTree().setOriginal(CompoundTypeTree(Template(List(Ident(scala.reflect.api.Universe), Ident(scala.Singleton)), noSelfType, List()))))))), List(List(ValDef(Modifiers(PARAM), TermName("$m$untyped"), TypeTree().setOriginal(AppliedTypeTree(Ident(scala.reflect.api.Mirror), List(TypeTree().setOriginal(Ident(TypeName("U")))))), EmptyTree))), TypeTree().setOriginal(SelectFromTypeTree(TypeTree().setOriginal(Ident(TypeName("U"))), TypeName("Type"))), Block(List(ValDef(Modifiers(), TermName("$u"), TypeTree().setOriginal(Ident(TypeName("U"))), Select(Ident(TermName("$m$untyped")), TermName("universe"))), ValDef(Modifiers(), TermName("$m"), TypeTree().setOriginal(Select(Ident(TermName("$u")), TypeName("Mirror"))), TypeApply(Select(Ident(TermName("$m$untyped")), TermName("asInstanceOf")), List(TypeTree().setOriginal(Select(Ident(TermName("$u")), TypeName("Mirror")))))), ValDef(Modifiers(), TermName("free$A1"), TypeTree(), Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("newFreeType")), List(Literal(Constant("A")), Apply(Select(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("FlagsRepr")), TermName("apply")), List(Literal(Constant(8208)))), Literal(Constant("defined by opsImpl in Macros.scala:10:15"))))), Apply(TypeApply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("setInfo")), List(TypeTree())), List(Ident(TermName("free$A1")), Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("TypeBounds")), List(Select(Select(Apply(Select(Ident(TermName("$m")), TermName("staticClass")), List(Literal(Constant("scala.Nothing")))), TermName("asType")), TermName("toTypeConstructor")), Select(Select(Apply(Select(Ident(TermName("$m")), TermName("staticClass")), List(Literal(Constant("scala.Any")))), TermName("asType")), TermName("toTypeConstructor"))))))), Apply(Select(Select(Select(Ident(TermName("$u")), TermName("internal")), TermName("reificationSupport")), TermName("TypeRef")), List(Select(Ident(TermName("$u")), TermName("NoPrefix")), Ident(TermName("free$A1")), Select(Select(Select(Ident(scala), scala.collection), scala.collection.immutable), scala.collection.immutable.Nil))))))))), Apply(Select(New(Ident(TypeName("$typecreator2"))), termNames.CONSTRUCTOR), List()))))))
 */
