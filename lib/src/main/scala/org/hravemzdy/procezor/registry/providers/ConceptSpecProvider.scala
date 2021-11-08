package org.hravemzdy.procezor.registry.providers

import org.hravemzdy.procezor.interfaces.IConceptSpec
import org.hravemzdy.procezor.service.types.ConceptCode

trait IConceptSpecProvider extends ISpecProvider[IConceptSpec, ConceptCode] {
}

abstract class ConceptSpecProvider(override val code: ConceptCode) extends IConceptSpecProvider {
    def this(_code: Int) = this(ConceptCode(_code))
}
