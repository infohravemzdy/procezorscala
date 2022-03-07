package org.hravemzdy.procezor.service

import org.hravemzdy.legalios.interfaces.{IBundleProps, IPeriod}
import org.hravemzdy.procezor.interfaces.BuilderType.BuilderResultList
import org.hravemzdy.procezor.interfaces.TermTargetTypes.ITermTargetList
import org.hravemzdy.procezor.interfaces.{IArticleDefine, IArticleSpec, IConceptSpec, IContractTerm, IPositionTerm}
import org.hravemzdy.procezor.service.types.{ArticleCode, ArticleTerm, ConceptCode, VersionCode}

trait IServiceProcezor {
    val version: VersionCode
    val calcArticles: Array[ArticleCode]

    def builderOrder(): Array[ArticleTerm]
    def builderPaths(): Map[ArticleTerm, Array[IArticleDefine]]

    def getContractTerms(period: IPeriod, targets: ITermTargetList) : Array[IContractTerm]
    def getPositionTerms(period: IPeriod, contracts: Array[IContractTerm], targets: ITermTargetList) : Array[IPositionTerm]

    def getResults(period: IPeriod, ruleset: IBundleProps, targets: ITermTargetList): BuilderResultList
    def initWithPeriod(period: IPeriod): Boolean
    def buildFactories(): Boolean
    def getArticleSpec(code: ArticleCode, period: IPeriod, version: VersionCode): IArticleSpec
    def getConceptSpec(code: ConceptCode, period: IPeriod, version: VersionCode): IConceptSpec
}
