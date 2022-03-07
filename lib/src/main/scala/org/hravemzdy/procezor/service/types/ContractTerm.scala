package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.IContractTerm
import org.hravemzdy.procezor.registry.constants.TermConstants

import java.time.LocalDate

class ContractTerm(override val contract: ContractCode,
                   override val dateFrom: Option[LocalDate], override val dateStop: Option[LocalDate],
                   override val termDayFrom: Byte, override val termDayStop: Byte) extends IContractTerm {

  override def isActive(): Boolean = {
    return (termDayFrom < TermConstants.TERM_BEG_FINISHED
      && termDayStop > TermConstants.TERM_END_FINISHED)
  }
}
