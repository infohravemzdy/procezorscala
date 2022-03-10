package org.hravemzdy.procezor.service.errors

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.{ITermResultError, ITermTarget}
import org.hravemzdy.procezor.service.types.{ArticleCode, ConceptCode, ContractCode, PositionCode, VariantCode}

class TermResultError(override val period: IPeriod, override val target: ITermTarget, override val message: String) extends ITermResultError {
    override val contract: ContractCode = if (target != null) target.contract else ContractCode.getNew
    override val position: PositionCode = if (target != null) target.position else PositionCode.getNew
    override val article: ArticleCode = if (target != null) target.article else ArticleCode.getNew
    override val concept: ConceptCode = if (target != null) target.concept else ConceptCode.getNew
    override val variant: VariantCode = if (target != null) target.variant else VariantCode.getNew

    override def description(): String = message

    override def articleDescr(): String = if (target != null) target.articleDescr() else s"ArticleCode for ${article.value}"

    override def conceptDescr(): String = if (target != null) target.conceptDescr() else s"ConceptCode for ${concept.value}"
}

object TermResultError {
    def messageContractPosition(contract: Option[ContractCode], position: Option[PositionCode]): String = {
        if (contract.isDefined && position.isDefined) {
            return s", contract=${contract.get.value}, position=${position.get.value}"
        } else if (contract.isDefined) {
            return s", contract=${contract.get.value}"
        } else {
            return ""
        }
    }
}
case class EvalResultError(override val period: IPeriod, override val target: ITermTarget)
  extends TermResultError(period, target, "evaluation failed")
case class ExtractResultError(override val period: IPeriod, override val target: ITermTarget)
  extends TermResultError(period, target, "extract result failed")
case class NoImplementationError(override val period: IPeriod, override val target: ITermTarget)
  extends TermResultError(period, target, "failed with no-implementation")
case class NoResultFuncError(override val period: IPeriod, override val target: ITermTarget)
  extends TermResultError(period, target, "failed with no-result function")
case class InvalidResultError(override val period: IPeriod, override val target: ITermTarget, val typeDescr: String)
  extends TermResultError(period, target, s"invalid result type $typeDescr error!")
case class InvalidRulesetError(override val period: IPeriod, override val target: ITermTarget, val typeDescr: String)
  extends TermResultError(period, target, s"invalid $typeDescr Ruleset error!")
case class InvalidTargetError(override val period: IPeriod, override val target: ITermTarget, val typeDescr: String)
  extends TermResultError(period, target, s"invalid target type $typeDescr error!")
case class NoResultFoundError(override val period: IPeriod, override val target: ITermTarget, val artDescr: String, val contractOpt: Option[ContractCode] = None, val positionOpt: Option[PositionCode] = None)
  extends TermResultError(period, target, s"result for ${artDescr}${TermResultError.messageContractPosition(contractOpt, positionOpt)} Not Found")
case class NullResultFoundError(override val period: IPeriod, override val target: ITermTarget, val artDescr: String, val contractOpt: Option[ContractCode] = None, val positionOpt: Option[PositionCode] = None)
  extends TermResultError(period, target, s"result found for ${artDescr}${TermResultError.messageContractPosition(contractOpt, positionOpt)} but Instance is Null!")