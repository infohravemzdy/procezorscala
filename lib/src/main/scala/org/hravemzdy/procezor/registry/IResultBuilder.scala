package org.hravemzdy.procezor.registry

import org.hravemzdy.legalios.interfaces.{IBundleProps, IPeriod}
import org.hravemzdy.procezor.interfaces.BuilderType.BuilderResultList
import org.hravemzdy.procezor.interfaces.TermTargetTypes.ITermTargetList
import org.hravemzdy.procezor.interfaces.{IArticleDefine, IContractTerm, IPositionTerm}
import org.hravemzdy.procezor.registry.factories.{IArticleSpecFactory, IConceptSpecFactory}
import org.hravemzdy.procezor.service.types.{ArticleCode, ArticleTerm, VersionCode}


trait IResultBuilder {
    var version: VersionCode
    var periodInit: IPeriod
    def initWithPeriod(version: VersionCode, period: IPeriod, articleFactory: IArticleSpecFactory, conceptFactory: IConceptSpecFactory): Boolean
    def getResults(ruleset: IBundleProps,
                   contractTerms: Array[IContractTerm], positionTerms: Array[IPositionTerm],
                   targets: ITermTargetList, calcArticles: Array[ArticleCode]): BuilderResultList
    var articleOrder: Array[ArticleTerm]
    var articlePaths: Map[ArticleTerm, Array[IArticleDefine]]
}