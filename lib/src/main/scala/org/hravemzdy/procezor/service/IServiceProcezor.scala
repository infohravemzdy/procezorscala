package org.hravemzdy.procezor.service

import org.hravemzdy.legalios.interfaces.{IBundleProps, IPeriod}
import org.hravemzdy.procezor.interfaces.BuilderType.BuilderResultList
import org.hravemzdy.procezor.interfaces.TermTargetTypes.ITermTargetList
import org.hravemzdy.procezor.interfaces.{IArticleDefine, IArticleSpec, IConceptSpec}
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.ConceptCode
import org.hravemzdy.procezor.service.types.VersionCode

trait IServiceProcezor {
    val version: VersionCode
    val finDefs: IArticleDefine

    def getResults(period: IPeriod, ruleset: IBundleProps, targets: ITermTargetList): BuilderResultList
    def initWithPeriod(period: IPeriod): Boolean
    def buildFactories(): Boolean
    def getArticleSpec(code: ArticleCode, period: IPeriod, version: VersionCode): IArticleSpec
    def getConceptSpec(code: ConceptCode, period: IPeriod, version: VersionCode): IConceptSpec
}
