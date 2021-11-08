package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ICodeValue

case class VariantCode(override val value: Int) extends ICodeValue[Int] {
}

object VariantCode {
    def getNew(): VariantCode = VariantCode(0)
    def get(value: Int): VariantCode = VariantCode(value)
}

