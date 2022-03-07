package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ICodeValue

case class ContractCode(override val value: Int) extends ICodeValue[Int] {
}

object ContractCode{
    def zero: ContractCode = getNew
    def getNew: ContractCode = ContractCode(0)
    def get(value: Int): ContractCode = ContractCode(value)
}
