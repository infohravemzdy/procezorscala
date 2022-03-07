package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.IArticleDefine
import org.hravemzdy.procezor.interfaces.ITermTarget

class TermTarget(override val monthCode: MonthCode,
                 override val contract: ContractCode,
                 override val position: PositionCode,
                 override val variant: VariantCode,
                 override val article: ArticleCode,
                 override val concept: ConceptCode) extends TermSymbol(monthCode, contract, position, variant, article) with ITermTarget {

    override def conceptDescr(): String = {
        s"ConceptCode for ${concept.value}"
    }
}