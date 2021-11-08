package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ICodeValue

case class PositionCode(override val value: Int) extends ICodeValue[Int] {
}

object PositionCode {
    def getNew(): PositionCode = PositionCode(0)
    def get(value: Int): PositionCode = PositionCode(value)
}
