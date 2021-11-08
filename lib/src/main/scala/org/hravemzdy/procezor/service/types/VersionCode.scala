package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ICodeValue

case class VersionCode(override val value: Int) extends ICodeValue[Int] {
}

object VersionCode {
    def getNew(): VersionCode  = VersionCode(0)
    def get(value: Int): VersionCode  = VersionCode(value)
}
