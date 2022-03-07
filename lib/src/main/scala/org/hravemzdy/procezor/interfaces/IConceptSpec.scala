package org.hravemzdy.procezor.interfaces

import org.hravemzdy.legalios.interfaces.{IBundleProps, IPeriod}
import org.hravemzdy.procezor.interfaces.TermTargetTypes.ITermTargetList
import org.hravemzdy.procezor.service.types.{ArticleCode, MonthCode, VariantCode}

object BuilderType {
    type BuilderResult = Either[ITermResultError, ITermResult]
    type BuilderResultList = Array[BuilderResult]
    type ResultFunc = (ITermTarget, Option[IArticleSpec], IPeriod, IBundleProps, BuilderResultList) => BuilderResultList
}

trait IConceptSpec extends IConceptDefine {
    val path : Array[ArticleCode]
    val resultDelegate : Option[BuilderType.ResultFunc]
    def defaultTargetList(article: ArticleCode, period: IPeriod, ruleset: IBundleProps, month: MonthCode,
                          contractTerms: Array[IContractTerm], positionTerms: Array[IPositionTerm],
                          targets: ITermTargetList, vars: VariantCode) : ITermTargetList
}

