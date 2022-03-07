package org.hravemzdy.procezor.service

import org.hravemzdy.legalios.interfaces.{IBundleProps, IPeriod}
import org.hravemzdy.procezor.interfaces.BuilderType.{BuilderResult, BuilderResultList}
import org.hravemzdy.procezor.interfaces.TermTargetTypes.ITermTargetList
import org.hravemzdy.procezor.interfaces.{IArticleDefine, IArticleSpec, IConceptSpec, IContractTerm, IPositionTerm}
import org.hravemzdy.procezor.registry.IResultBuilder
import org.hravemzdy.procezor.registry.ResultBuilder
import org.hravemzdy.procezor.registry.factories.IArticleSpecFactory
import org.hravemzdy.procezor.registry.factories.IConceptSpecFactory
import org.hravemzdy.procezor.service.types.{ArticleCode, ArticleDefine, ArticleTerm, ConceptCode, VersionCode}

abstract class ServiceProcezor(override val version: VersionCode, _calcArticles: Array[ArticleCode]) extends IServiceProcezor {
    override val calcArticles: Array[ArticleCode] =  _calcArticles.toArray
    private val builder: IResultBuilder = new ResultBuilder()
    protected var articleFactory: IArticleSpecFactory = null
    protected var conceptFactory: IConceptSpecFactory = null

    def builderOrder(): Array[ArticleTerm] = {
        return builder.articleOrder
    }
    def builderPaths(): Map[ArticleTerm, Array[IArticleDefine]] = {
        return builder.articlePaths
    }

    def getContractTerms(period: IPeriod, targets: ITermTargetList) : Array[IContractTerm] = {
        return Array[IContractTerm]()
    }
    def getPositionTerms(period: IPeriod, contracts: Array[IContractTerm], targets: ITermTargetList) : Array[IPositionTerm] = {
        return Array[IPositionTerm]()
    }

    override def getResults(period: IPeriod, ruleset: IBundleProps, targets: ITermTargetList): BuilderResultList = {
        var results: BuilderResultList = Array[BuilderResult]()

        val success: Boolean = initWithPeriod(period)

        if (!success) {
            return results
        }
        val contractTerms = getContractTerms(period, targets)
        val positionTerms = getPositionTerms(period, contractTerms, targets)

        if (builder != null) {
            results = builder.getResults(ruleset,
                contractTerms, positionTerms, targets, calcArticles)
        }
        return results
    }
    override def initWithPeriod(period: IPeriod): Boolean = {
        var initResult: Boolean = false

        if (builder != null) {
            initResult = true
        }

        var initBuilder: Boolean = false

        if (builder != null) {
            initBuilder = builder.periodInit != period
        }

        if (initBuilder && articleFactory != null && conceptFactory != null) {
            initResult = builder.initWithPeriod(version, period, articleFactory, conceptFactory)
        }

        return initResult
    }
    override def buildFactories(): Boolean = {
        val articleFactorySuccess: Boolean = buildArticleFactory()

        val conceptFactorySuccess: Boolean = buildConceptFactory()

        return articleFactorySuccess && conceptFactorySuccess
    }
    override def getArticleSpec(code: ArticleCode, period: IPeriod, version: VersionCode): IArticleSpec = {
        if (articleFactory == null) {
            return null
        }
        return articleFactory.getSpec(code, period, version)
    }
    override def getConceptSpec(code: ConceptCode, period: IPeriod, version: VersionCode): IConceptSpec = {
        if (conceptFactory == null) {
            return null
        }
        return conceptFactory.getSpec(code, period, version)
    }

    def buildArticleFactory(): Boolean

    def buildConceptFactory(): Boolean
}