package org.hravemzdy.procezor.service.errors

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.{ITermResultError, ITermTarget}
import org.hravemzdy.procezor.service.types.{ArticleCode, ConceptCode, ContractCode, PositionCode, VariantCode}

class TermResultError(override val period: IPeriod, override val target: ITermTarget, override val message: String) extends ITermResultError {
    override val contract: ContractCode = if (target != null) target.contract else ContractCode.getNew()
    override val position: PositionCode = if (target != null) target.position else PositionCode.getNew()
    override val article: ArticleCode = if (target != null) target.article else ArticleCode.getNew()
    override val concept: ConceptCode = if (target != null) target.concept else ConceptCode.getNew()
    override val variant: VariantCode = if (target != null) target.variant else VariantCode.getNew()

    override def description(): String = message

    override def articleDescr(): String = if (target != null) target.articleDescr() else s"ArticleCode for ${article.value}"

    override def conceptDescr(): String = if (target != null) target.conceptDescr() else s"ConceptCode for ${concept.value}"
}

case class EvalResultError(override val period: IPeriod, override val target: ITermTarget)
  extends TermResultError(period, target, "evaluation failed")
case class ExtractResultError(override val period: IPeriod, override val target: ITermTarget)
  extends TermResultError(period, target, "extract result failed")
case class NoImplementationError(override val period: IPeriod, override val target: ITermTarget)
  extends TermResultError(period, target, "failed with no-implementation")
case class NoResultFuncError(override val period: IPeriod, override val target: ITermTarget)
  extends TermResultError(period, target, "failed with no-result function")

