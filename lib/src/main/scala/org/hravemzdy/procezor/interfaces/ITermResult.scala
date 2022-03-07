package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.ConceptCode

trait ITermResult extends ITermSymbol {
    val target: ITermTarget
    val concept: ConceptCode
    val spec: Option[IArticleSpec]
    def conceptDescr(): String
}
object TermResultTypes {
    type ITermResultList = Array[ITermResult]
}



