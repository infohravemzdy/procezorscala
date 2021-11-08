package org.hravemzdy.procezor.registry.factories

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.interfaces.ISpecCode
import org.hravemzdy.procezor.registry.providers.ArticleSpec
import org.hravemzdy.procezor.registry.providers.ArticleSpecProvider
import org.hravemzdy.procezor.service.types.{ArticleCode, ConceptCode, VersionCode}

case class ArticleSpecConfig(override val code: ArticleCode, override val role: ConceptCode, override val sums: Array[ArticleCode]) extends ArticleSpec(code, role, sums) {
    def this(_code: Int, _role: Int, _sums: Array[Int]) = this(ArticleCode.get(_code), ConceptCode.get(_role), ArticleSpec.constToSumsArray(_sums))
}

class ArticleProviderConfig(override val code: ArticleCode, val articleSpec: ArticleSpecConfig) extends ArticleSpecProvider(code) {
    def this(article: ArticleCode, concept: ConceptCode, sums: Array[ArticleCode]) = this(ArticleCode(article.value),
        ArticleSpecConfig(ArticleCode.get(article.value), ConceptCode.get(concept.value), sums.clone()))
    def this(article: Int, concept: Int, sums: Array[Int]) = this(ArticleCode(article),
        articleSpec = ArticleSpecConfig(ArticleCode.get(article), ConceptCode.get(concept), ArticleSpec.constToSumsArray(sums)))

    override def getSpec(period: IPeriod, version: VersionCode): IArticleSpec = {
        articleSpec
    }
}
