package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.{IArticleSpec, ITermResult, ITermTarget}

class TermResult(override val target: ITermTarget,
                 override val spec: Option[IArticleSpec]) extends TermSymbol(
        if (target != null) target.monthCode else MonthCode.getNew,
        if (target != null) target.contract else ContractCode.getNew,
        if (target != null) target.position else PositionCode.getNew,
        if (target != null) target.variant else VariantCode.getNew,
        if (target != null) target.article else ArticleCode.getNew)
    with ITermResult {
        override val concept: ConceptCode = if (target != null) target.concept else ConceptCode.getNew

        def this() = this(null, None)

        override def conceptDescr(): String = {
            return s"ConceptCode for ${concept.value}"
        }
}
