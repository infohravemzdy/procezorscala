package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.ConceptCode

trait ITermTarget extends ITermSymbol {
    val concept: ConceptCode
    def conceptDescr(): String
}

object TermTargetTypes {
    type ITermTargetList = Array[ITermTarget]
}

