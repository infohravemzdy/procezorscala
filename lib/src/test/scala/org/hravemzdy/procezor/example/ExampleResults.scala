package org.hravemzdy.procezor.example

import org.hravemzdy.procezor.interfaces.{IArticleSpec, ITermTarget}

// TimeshtWorking		TIMESHT_WORKING
class TimeshtWorkingResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

// AmountBasis		AMOUNT_BASIS
class AmountBasisResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

// AmountFixed		AMOUNT_FIXED
class AmountFixedResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

// HealthInsbase		HEALTH_INSBASE
class HealthInsbaseResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

// SocialInsbase		SOCIAL_INSBASE
class SocialInsbaseResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

// HealthInspaym		HEALTH_INSPAYM
class HealthInspaymResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

// SocialInspaym		SOCIAL_INSPAYM
class SocialInspaymResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

// TaxingAdvbase		TAXING_ADVBASE
class TaxingAdvbaseResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

// TaxingAdvpaym		TAXING_ADVPAYM
class TaxingAdvpaymResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

// IncomeGross		INCOME_GROSS
class IncomeGrossResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

// IncomeNetto		INCOME_NETTO
class IncomeNettoResult(override val target: ITermTarget, override val spec: Option[IArticleSpec]) extends ExampleTermResult(target, spec) {
}

