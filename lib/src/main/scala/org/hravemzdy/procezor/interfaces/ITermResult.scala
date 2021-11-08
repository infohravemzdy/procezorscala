package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.ConceptCode

trait ITermResult extends ITermSymbol {
    val target: ITermTarget
    val concept: ConceptCode
    val resultDescr: String
    val resultBasis: Int
    val resultValue: Int
    def conceptDescr():String
}

