package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.{IContractTerm, IPositionTerm}
import org.hravemzdy.procezor.registry.constants.TermConstants

import java.time.LocalDate

class PositionTerm(override val position: PositionCode, override val contract: ContractCode,
                   override val baseTerm: Option[IContractTerm],
                   override val dateFrom: Option[LocalDate], override val dateStop: Option[LocalDate],
                   override val termDayFrom: Byte, override val termDayStop: Byte) extends IPositionTerm {

  private def isPositionActive(): Boolean = {
    return (termDayFrom < TermConstants.TERM_BEG_FINISHED
      && termDayStop > TermConstants.TERM_END_FINISHED)
  }
  override def isActive(): Boolean = {
    if (baseTerm.isDefined) {
      return (baseTerm.get.isActive() && isPositionActive())
    }
    return isPositionActive()
  }
}
