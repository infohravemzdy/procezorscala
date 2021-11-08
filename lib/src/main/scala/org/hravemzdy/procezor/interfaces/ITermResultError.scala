package org.hravemzdy.procezor.interfaces

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.ConceptCode
import org.hravemzdy.procezor.service.types.ContractCode
import org.hravemzdy.procezor.service.types.PositionCode
import org.hravemzdy.procezor.service.types.VariantCode

trait ITermResultError {
  val period: IPeriod
  val target: ITermTarget
  val message: String
  val contract: ContractCode
  val position: PositionCode
  val article: ArticleCode
  val concept: ConceptCode
  val variant: VariantCode

  def description(): String
  def articleDescr(): String
  def conceptDescr(): String
}

