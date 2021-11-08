package org.hravemzdy.procezor.example

import org.hravemzdy.procezor.interfaces.ITermTarget

object ExampleResultConst {
    val VALUE_ZERO: Int = 0
    val BASIS_ZERO: Int = 0
    val DESCRIPTION_EMPTY: String = "result from input value"
}

class TimeshtWorkingResult(override val target: ITermTarget,
                           override val resultValue: Int,
                           override val resultBasis: Int,
                           override val resultDescr: String) extends ExampleTermResult(target, resultValue, resultBasis, resultDescr) {
    def this(target: ITermTarget) = this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class AmountBasisResult(override val target: ITermTarget,
                        override val resultValue: Int,
                        override val resultBasis: Int,
                        override val resultDescr: String) extends ExampleTermResult(target, resultValue, resultBasis, resultDescr) {
    def this(target: ITermTarget) = this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class AmountFixedResult(override val target: ITermTarget,
                        override val resultValue: Int,
                        override val resultBasis: Int,
                        override val resultDescr: String) extends ExampleTermResult(target, resultValue, resultBasis, resultDescr) {
    def this(target: ITermTarget) = this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class HealthInsbaseResult(override val target: ITermTarget,
                          override val resultValue: Int,
                          override val resultBasis: Int,
                          override val resultDescr: String) extends ExampleTermResult(target, resultValue, resultBasis, resultDescr) {
    def this(target: ITermTarget) = this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class SocialInsbaseResult(override val target: ITermTarget,
                          override val resultValue: Int,
                          override val resultBasis: Int,
                          override val resultDescr: String) extends ExampleTermResult(target, resultValue, resultBasis, resultDescr) {
    def this(target: ITermTarget) = this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class HealthInspaymResult(override val target: ITermTarget,
                          override val resultValue: Int,
                          override val resultBasis: Int,
                          override val resultDescr: String) extends ExampleTermResult(target, resultValue, resultBasis, resultDescr) {
    def this(target: ITermTarget) = this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class SocialInspaymResult(override val target: ITermTarget,
                          override val resultValue: Int,
                          override val resultBasis: Int,
                          override val resultDescr: String) extends ExampleTermResult(target, resultValue, resultBasis, resultDescr) {
    def this(target: ITermTarget) = this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class TaxingAdvbaseResult(override val target: ITermTarget,
                          override val resultValue: Int,
                          override val resultBasis: Int,
                          override val resultDescr: String) extends ExampleTermResult(target, resultValue, resultBasis, resultDescr) {
    def this(target: ITermTarget) = this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class TaxingAdvpaymResult(override val target: ITermTarget,
                          override val resultValue: Int,
                          override val resultBasis: Int,
                          override val resultDescr: String) extends ExampleTermResult(target, resultValue, resultBasis, resultDescr) {
    def this(target: ITermTarget) = this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class IncomeGrossResult(override val target: ITermTarget,
                        override val resultValue: Int,
                        override val resultBasis: Int,
                        override val resultDescr: String) extends ExampleTermResult(target, resultValue, resultBasis, resultDescr) {
    def this(target: ITermTarget) = this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class IncomeNettoResult(override val target: ITermTarget,
                        override val resultValue: Int,
                        override val resultBasis: Int,
                        override val resultDescr: String) extends ExampleTermResult(target, resultValue, resultBasis, resultDescr) {
    def this(target: ITermTarget) = this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

