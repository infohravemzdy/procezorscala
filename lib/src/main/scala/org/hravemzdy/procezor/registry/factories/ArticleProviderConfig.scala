package org.hravemzdy.procezor.registry.factories

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.interfaces.ISpecCode
import org.hravemzdy.procezor.registry.providers.ArticleSpec
import org.hravemzdy.procezor.registry.providers.ArticleSpecProvider
import org.hravemzdy.procezor.service.types.{ArticleCode, ArticleSeqs, ConceptCode, VersionCode}

case class ArticleSpecConfig(override val code: ArticleCode, override val seqs: ArticleSeqs, override val role: ConceptCode, override val sums: Array[ArticleCode]) extends ArticleSpec(code, seqs, role, sums) {
    def this(_code: Int, _seqs: Int, _role: Int, _sums: Array[Int]) = this(ArticleCode.get(_code), ArticleSeqs.get(_seqs), ConceptCode.get(_role), ArticleSpec.constToSumsArray(_sums))
}

class ArticleProviderConfig(private val articleSpec: ArticleSpecConfig) extends ArticleSpecProvider(articleSpec.code) {
    def this(article: ArticleCode, sequens: ArticleSeqs, concept: ConceptCode, sums: Array[ArticleCode]) =
        this(ArticleSpecConfig(ArticleCode.get(article.value), ArticleSeqs.get(sequens.value), ConceptCode.get(concept.value), sums.clone()))
    def this(article: Int, sequens: Int, concept: Int, sums: Array[Int]) =
        this(ArticleSpecConfig(ArticleCode.get(article), ArticleSeqs.get(sequens), ConceptCode.get(concept), ArticleSpec.constToSumsArray(sums)))

    override def getSpec(period: IPeriod, version: VersionCode): IArticleSpec = {
        articleSpec
    }
}
