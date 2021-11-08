package org.hravemzdy.procezor.registry.providers

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.BuilderType.ResultFunc
import org.hravemzdy.procezor.interfaces.IConceptSpec
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.ConceptCode
import org.hravemzdy.procezor.service.types.MonthCode

abstract case class ConceptSpec(override val code: ConceptCode, override val path: Array[ArticleCode],
                                override val resultDelegate: Option[ResultFunc]) extends IConceptSpec {
    def this(codeId: Int) = this(ConceptCode.get(codeId), Array.empty[ArticleCode], null)

    def getMonthCode(period: IPeriod) : MonthCode = {
        MonthCode.get(period.code)
    }

}
object ConceptSpec {
    def constToPathArray(path: Array[Int]): Array[ArticleCode] = {
        path.map(x => ArticleCode.get(x))
    }
}
