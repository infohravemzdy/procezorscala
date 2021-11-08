package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ISpecCode

case class ConceptCode(override val value: Int) extends ISpecCode {
}

object ConceptCode {
    def getNew(): ConceptCode = ConceptCode(0)
    def get(value: Int): ConceptCode = ConceptCode(value)
}
