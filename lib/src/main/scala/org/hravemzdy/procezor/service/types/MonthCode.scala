package org.hravemzdy.procezor.service.types

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.ICodeValue

case class MonthCode(override val value: Int) extends ICodeValue[Int] {
}

object MonthCode {
    def zero: MonthCode = getNew
    def getNew: MonthCode = MonthCode(0)
    def get(value: Int): MonthCode = MonthCode(value)
    def getWithPeriod(period: IPeriod): MonthCode = MonthCode(period.code)
}
