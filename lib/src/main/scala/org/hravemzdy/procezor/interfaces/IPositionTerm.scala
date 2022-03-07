package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.{ContractCode, PositionCode}
import java.time.LocalDate

trait IPositionTerm {
  val position: PositionCode
  val contract: ContractCode
  val baseTerm: Option[IContractTerm]
  val dateFrom: Option[LocalDate]
  val dateStop: Option[LocalDate]
  val termDayFrom: Byte
  val termDayStop: Byte
  def isActive(): Boolean
}
