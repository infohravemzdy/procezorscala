package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.ConceptCode

trait ITermTarget extends ITermSymbol {

    val concept: ConceptCode
    val targetBasis: Int
    val targetDescr: String
    val defs: IArticleDefine
    def conceptDescr(): String
}

object TermTargetTypes {
    type ITermTargetList = Array[ITermTarget]
}

