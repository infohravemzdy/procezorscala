package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ITermSymbol

class TermSymbol(override val monthCode: MonthCode,
                 override val contract: ContractCode,
                 override val position: PositionCode,
                 override val variant: VariantCode,
                 override val article: ArticleCode) extends ITermSymbol {

    def this() = this(
        MonthCode.getNew(),
        ContractCode.getNew(),
        PositionCode.getNew(),
        VariantCode.getNew(),
        ArticleCode.getNew())

    override def articleDescr(): String = {
        s"ArticleCode for ${article.value}"
    }
}