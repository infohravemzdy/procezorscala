package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.IArticleDefine
import org.hravemzdy.procezor.interfaces.ITermTarget

class TermTarget(override val monthCode: MonthCode,
                 override val contract: ContractCode,
                 override val position: PositionCode,
                 override val variant: VariantCode,
                 override val article: ArticleCode,
                 override val concept: ConceptCode,
                 override val targetBasis: Int,
                 override val targetDescr: String) extends TermSymbol(monthCode, contract, position, variant, article) with ITermTarget {
    def this(_month: MonthCode,
             _contract: ContractCode,
             _position: PositionCode,
             _variant: VariantCode,
             _article: ArticleCode,
             _concept: ConceptCode) =
            this(_month,
                _contract,
                _position,
                _variant,
                _article,
                _concept,
                0, "")

    override val defs: IArticleDefine = ArticleDefine(this.article, this.concept)

    override def conceptDescr(): String = {
        s"ConceptCode for ${concept.value}"
    }
}