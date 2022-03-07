package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.ContractCode

import java.time.LocalDate

trait IContractTerm {
  val contract: ContractCode
  val dateFrom: Option[LocalDate]
  val dateStop: Option[LocalDate]
  val termDayFrom: Byte
  val termDayStop: Byte
  def isActive(): Boolean
}
