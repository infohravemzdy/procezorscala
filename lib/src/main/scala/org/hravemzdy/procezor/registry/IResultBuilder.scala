package org.hravemzdy.procezor.registry

import org.hravemzdy.legalios.interfaces.{IBundleProps, IPeriod}
import org.hravemzdy.procezor.interfaces.BuilderType.BuilderResultList
import org.hravemzdy.procezor.interfaces.{IArticleDefine, ITermTarget}
import org.hravemzdy.procezor.registry.factories.{IArticleSpecFactory, IConceptSpecFactory}
import org.hravemzdy.procezor.service.types.{ArticleCode, VersionCode}


trait IResultBuilder {
    var version: VersionCode
    var periodInit: IPeriod
    def initWithPeriod(version: VersionCode, period: IPeriod, articleFactory: IArticleSpecFactory, conceptFactory: IConceptSpecFactory): Boolean
    def getResults(ruleset: IBundleProps, targets: Array[ITermTarget], finDefs: IArticleDefine): BuilderResultList
    var articleOrder: Array[ArticleCode]
    var articlePaths: Map[ArticleCode, Array[IArticleDefine]]
}