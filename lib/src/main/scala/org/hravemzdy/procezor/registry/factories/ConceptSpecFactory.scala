package org.hravemzdy.procezor.registry.factories

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.IConceptSpec
import org.hravemzdy.procezor.registry.constants.ConceptConst
import org.hravemzdy.procezor.registry.providers.ConceptSpec
import org.hravemzdy.procezor.registry.providers.ConceptSpecProvider
import org.hravemzdy.procezor.registry.providers.IConceptSpecProvider
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.ConceptCode
import org.hravemzdy.procezor.service.types.VersionCode
import org.hravemzdy.procezor.interfaces.BuilderType.ResultFunc

trait IConceptSpecFactory extends ISpecFactory[IConceptSpecProvider, IConceptSpec, ConceptCode] {
}

class NotFoundConceptSpec(override val code: ConceptCode, override val path: Array[ArticleCode], override val resultDelegate: Option[ResultFunc]) extends ConceptSpec(code, path, resultDelegate) {
    def this(_code: ConceptCode) = this(_code, Array[ArticleCode](), None)
}

class NotFoundConceptProvider(override val code: ConceptCode) extends ConceptSpecProvider(code) {
    def this() = this(ConceptCode.get(NotFoundConceptProvider.CONCEPT_CODE))

    override def getSpec(period: IPeriod, version: VersionCode): IConceptSpec = {
        new NotFoundConceptSpec(this.code)
    }
}

object NotFoundConceptProvider {
    val CONCEPT_CODE = ConceptConst.CONCEPT_NOTFOUND
}

abstract class ConceptSpecFactory() extends SpecFactory[IConceptSpecProvider, IConceptSpec, ConceptCode]() with IConceptSpecFactory {
    override val notFoundProvider = new NotFoundConceptProvider()
    override val notFoundSpec = new NotFoundConceptSpec(ConceptCode.get(NotFoundConceptProvider.CONCEPT_CODE))
}
