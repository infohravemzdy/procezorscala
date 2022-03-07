package org.hravemzdy.procezor.example

import org.hravemzdy.procezor.interfaces.{IArticleSpec, ITermTarget}

class TimeshtWorkingResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

class AmountBasisResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

class AmountFixedResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

class HealthInsbaseResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

class SocialInsbaseResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

class HealthInspaymResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

class SocialInspaymResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

class TaxingAdvbaseResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

class TaxingAdvpaymResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

class IncomeGrossResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

class IncomeNettoResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

