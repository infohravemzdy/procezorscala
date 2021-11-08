package org.hravemzdy.procezor.registry.providers

import org.hravemzdy.procezor.interfaces.{IArticleDefine, IArticleSpec, ISpecCode}
import org.hravemzdy.procezor.service.types.{ArticleCode, ArticleDefine, ConceptCode}

class ArticleSpec(override val code: ArticleCode, override val role: ConceptCode, override val sums: Array[ArticleCode]) extends IArticleSpec {
    def this(_code: Int,  _role: Int, _sums: Array[Int]) =
    this(ArticleCode.get(_code), ConceptCode.get(_role), ArticleSpec.constToSumsArray(_sums))

    override def defs(): IArticleDefine = {
        ArticleDefine.fromInt(code.value, role.value)
    }
}

object ArticleSpec {
    def constToSumsArray(sums: Array[Int]): Array[ArticleCode] = {
        sums.map(x => ArticleCode.get(x))
    }

    def specsToSumsArray(sums: Array[ISpecCode]): Array[ArticleCode] = {
        sums.map(x => ArticleCode.get(x.value))
    }
}
