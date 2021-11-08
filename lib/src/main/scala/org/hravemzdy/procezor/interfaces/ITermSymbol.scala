package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.ContractCode
import org.hravemzdy.procezor.service.types.MonthCode
import org.hravemzdy.procezor.service.types.PositionCode
import org.hravemzdy.procezor.service.types.VariantCode

trait ITermSymbol {
    val monthCode: MonthCode
    val contract: ContractCode
    val position: PositionCode
    val variant: VariantCode
    val article: ArticleCode
    def articleDescr():String
}