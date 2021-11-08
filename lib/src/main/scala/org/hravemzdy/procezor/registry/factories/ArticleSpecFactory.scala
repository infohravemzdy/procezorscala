package org.hravemzdy.procezor.registry.factories

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.registry.constants.ArticleConst
import org.hravemzdy.procezor.registry.constants.ConceptConst
import org.hravemzdy.procezor.registry.factories.FactoryTypes.CODE
import org.hravemzdy.procezor.registry.providers.ArticleSpec
import org.hravemzdy.procezor.registry.providers.ArticleSpecProvider
import org.hravemzdy.procezor.registry.providers.IArticleSpecProvider
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.ConceptCode
import org.hravemzdy.procezor.service.types.VersionCode


trait IArticleSpecFactory extends ISpecFactory[IArticleSpecProvider, IArticleSpec, ArticleCode] {
}

class ProviderRecord(val article: Int, val concept: Int, val sums: Array[Int])

class NotFoundArticleSpec(override val code: ArticleCode, override val role: ConceptCode, override val sums: Array[ArticleCode]) extends ArticleSpec(code, role, sums) {
    def this(_code: ArticleCode) = this(_code, ConceptCode.get(NotFoundArticleSpec.CONCEPT_CODE), Array[ArticleCode]())
}

object NotFoundArticleSpec {
    val CONCEPT_CODE = ConceptConst.CONCEPT_NOTFOUND
}

class NotFoundArticleProvider(override val code: ArticleCode) extends ArticleSpecProvider(code) {
    def this() = this(ArticleCode.get(NotFoundArticleProvider.ARTICLE_CODE))

    override def getSpec(period: IPeriod, version: VersionCode): IArticleSpec = {
        new NotFoundArticleSpec(this.code)
    }
}

object NotFoundArticleProvider {
    val ARTICLE_CODE = ArticleConst.ARTICLE_NOTFOUND
}

abstract class ArticleSpecFactory() extends SpecFactory[IArticleSpecProvider, IArticleSpec, ArticleCode]() with IArticleSpecFactory {
    override val notFoundProvider = new NotFoundArticleProvider()
    override val notFoundSpec = new NotFoundArticleSpec(ArticleCode.get(NotFoundArticleProvider.ARTICLE_CODE))
}

object ArticleSpecFactory {
    def buildProvidersFromRecords(records: Array[ProviderRecord]): Map[CODE, IArticleSpecProvider] = {
        var providers: Map[CODE, IArticleSpecProvider] = records.map(x => new ArticleProviderConfig(x.article, x.concept, x.sums))
          .map(p => (p.code.value, p)).toMap

        providers
    }
}
