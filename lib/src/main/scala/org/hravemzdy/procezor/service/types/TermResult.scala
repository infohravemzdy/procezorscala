package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ITermResult
import org.hravemzdy.procezor.interfaces.ITermTarget

class TermResult(override val target: ITermTarget,
    override val resultValue: Int,
    override val resultBasis: Int,
    override val resultDescr: String) extends TermSymbol(
        if (target != null) target.monthCode else MonthCode.getNew(),
        if (target != null) target.contract else ContractCode.getNew(),
        if (target != null) target.position else PositionCode.getNew(),
        if (target != null) target.variant else VariantCode.getNew(),
        if (target != null) target.article else ArticleCode.getNew())
    with ITermResult {
        override val concept: ConceptCode = if (target != null) target.concept else ConceptCode.getNew()

        def this() = this(null, 0, 0, "")

        override def conceptDescr(): String = {
            return s"ConceptCode for ${concept.value}"
        }
}
