package org.hravemzdy.procezor.registry.providers

import org.hravemzdy.legalios.interfaces.{IBundleProps, IPeriod}
import org.hravemzdy.procezor.interfaces.BuilderType.ResultFunc
import org.hravemzdy.procezor.interfaces.{IConceptSpec, IContractTerm, IPositionTerm, ITermTarget}
import org.hravemzdy.procezor.interfaces.TermTargetTypes.ITermTargetList
import org.hravemzdy.procezor.service.types.{ArticleCode, ConceptCode, ContractCode, MonthCode, PositionCode, TermTarget, VariantCode}

abstract case class ConceptSpec(override val code: ConceptCode, override val path: Array[ArticleCode],
                                override val resultDelegate: Option[ResultFunc]) extends IConceptSpec {
    def this(codeId: Int) = this(ConceptCode.get(codeId), Array.empty[ArticleCode], null)

    def getMonthCode(period: IPeriod) : MonthCode = {
        MonthCode.get(period.code)
    }
    def defaultTargetList(article: ArticleCode, period: IPeriod, ruleset: IBundleProps, month: MonthCode,
                          contractTerms: Array[IContractTerm], positionTerms: Array[IPositionTerm],
                          targets: ITermTargetList, vars: VariantCode) : ITermTargetList = {
        val con = ContractCode.zero
        val pos = PositionCode.zero

        if (targets.length!=0) {
            return Array[ITermTarget]()
        }
        return Array[ITermTarget](new TermTarget(month, con, pos, vars, article, code))

    }

}
object ConceptSpec {
    def constToPathArray(path: Array[Int]): Array[ArticleCode] = {
        path.map(x => ArticleCode.get(x))
    }
}
