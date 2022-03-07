package org.hravemzdy.procezor.example

import org.hravemzdy.procezor.interfaces.BuilderType.ResultFunc
import org.hravemzdy.procezor.interfaces.{IArticleSpec, ITermTarget}
import org.hravemzdy.procezor.registry.providers.ConceptSpec
import org.hravemzdy.procezor.service.types.{ArticleCode, ConceptCode, ContractCode, MonthCode, PositionCode, TermResult, TermTarget, VariantCode}

class ExampleConceptSpec(override val code: ConceptCode, override val path: Array[ArticleCode], override val resultDelegate: Option[ResultFunc]) extends ConceptSpec(code, path, resultDelegate) {
  def this(code: Int) = this(ConceptCode.get(code), Array[ArticleCode](), None)
}

class ExampleTermTarget(override val monthCode: MonthCode,
                        override val contract: ContractCode,
                        override val position: PositionCode,
                        override val variant: VariantCode,
                        override val article: ArticleCode,
                        override val concept: ConceptCode)
  extends TermTarget(monthCode, contract, position, variant, article, concept) {
  override def articleDescr(): String = {
    ExampleArticleConst.getArticleSymbol(article.value)
  }
  override def conceptDescr(): String = {
    ExampleConceptConst.getConceptSymbol(concept.value)
  }
}

class ExampleTermResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends TermResult(target, spec) {
  override def articleDescr(): String = {
    ExampleArticleConst.getArticleSymbol(article.value)
  }
  override def conceptDescr(): String = {
    ExampleConceptConst.getConceptSymbol(concept.value)
  }
}

