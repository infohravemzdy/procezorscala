package org.hravemzdy.procezor.example

import org.hravemzdy.legalios.interfaces.{IBundleProps, IPeriod}
import org.hravemzdy.procezor.interfaces.BuilderType.{BuilderResultList, ResultFunc}
import org.hravemzdy.procezor.interfaces.{IArticleSpec, IConceptSpec, ITermResult, ITermTarget}
import org.hravemzdy.procezor.registry.providers.ConceptSpec.constToPathArray
import org.hravemzdy.procezor.registry.providers.{ConceptSpec, ConceptSpecProvider}
import org.hravemzdy.procezor.service.types.{ArticleCode, ConceptCode, VersionCode}

// TimeshtWorking		TIMESHT_WORKING
class TimeshtWorkingConProv(override val code: ConceptCode) extends ConceptSpecProvider(code) {
    def this() = this(ConceptCode.get(TimeshtWorkingConProv.CONCEPT_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IConceptSpec = {
        new TimeshtWorkingConSpec(this.code)
    }
}

object TimeshtWorkingConProv {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_TIMESHT_WORKING
}

class TimeshtWorkingConSpec(override val code: ConceptCode, override val path: Array[ArticleCode],
                            override val resultDelegate: Option[ResultFunc]) extends ConceptSpec(code, path, resultDelegate) {
    def this(_code: ConceptCode) = this(_code, Array[ArticleCode](), Some(TimeshtWorkingConSpec.conceptEval))
}

object TimeshtWorkingConSpec {
    def conceptEval(target: ITermTarget, spec: Option[IArticleSpec], period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new TimeshtWorkingResult(target, spec)

        Array(Right(resultsValues))
    }
}

// AmountBasis		AMOUNT_BASIS
class AmountBasisConProv(override val code: ConceptCode) extends ConceptSpecProvider(code) {
    def this() = this(ConceptCode.get(AmountBasisConProv.CONCEPT_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IConceptSpec = {
        new AmountBasisConSpec(this.code)
    }
}

object AmountBasisConProv {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_AMOUNT_BASIS
}

class AmountBasisConSpec(override val code: ConceptCode, override val path: Array[ArticleCode],
                         override val resultDelegate: Option[ResultFunc]) extends ConceptSpec(code, path, resultDelegate) {
    def this(_code: ConceptCode) = this(_code, constToPathArray(AmountBasisConSpec.specPath), Some(AmountBasisConSpec.conceptEval))
}

object AmountBasisConSpec {
    val specPath = Array[Int](
        ExampleArticleConst.ARTICLE_TIMESHT_WORKING.id,
    )

    def conceptEval(target: ITermTarget, spec: Option[IArticleSpec], period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new AmountBasisResult(target, spec)

        Array(Right(resultsValues))
    }
}

// AmountFixed		AMOUNT_FIXED
class AmountFixedConProv(override val code: ConceptCode) extends ConceptSpecProvider(code) {
    def this() = this(ConceptCode.get(AmountFixedConProv.CONCEPT_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IConceptSpec = {
        new AmountFixedConSpec(this.code)
    }
}

object AmountFixedConProv {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_AMOUNT_FIXED
}

class AmountFixedConSpec(override val code: ConceptCode, override val path: Array[ArticleCode],
                         override val resultDelegate: Option[ResultFunc]) extends ConceptSpec(code, path, resultDelegate) {
    def this(_code: ConceptCode) = this(_code, Array[ArticleCode](), Some(AmountFixedConSpec.conceptEval))
}

object AmountFixedConSpec {
    def conceptEval(target: ITermTarget, spec: Option[IArticleSpec], period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new AmountFixedResult(target, spec)

        Array(Right(resultsValues))
    }
}

// HealthInsbase		HEALTH_INSBASE
class HealthInsbaseConProv(override val code: ConceptCode) extends ConceptSpecProvider(code) {
    def this() = this(ConceptCode.get(HealthInsbaseConProv.CONCEPT_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IConceptSpec = {
        new HealthInsbaseConSpec(this.code)
    }
}

object HealthInsbaseConProv {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_HEALTH_INSBASE
}

class HealthInsbaseConSpec(override val code: ConceptCode, override val path: Array[ArticleCode],
                           override val resultDelegate: Option[ResultFunc]) extends ConceptSpec(code, path, resultDelegate) {
    def this(_code: ConceptCode) = this(_code, Array[ArticleCode](), Some(HealthInsbaseConSpec.conceptEval))
}

object HealthInsbaseConSpec {
    def conceptEval(target: ITermTarget, spec: Option[IArticleSpec], period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new HealthInsbaseResult(target, spec)

        Array(Right(resultsValues))
    }
}

// SocialInsbase		SOCIAL_INSBASE
class SocialInsbaseConProv(override val code: ConceptCode) extends ConceptSpecProvider(code) {
    def this() = this(ConceptCode.get(SocialInsbaseConProv.CONCEPT_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IConceptSpec = {
        new SocialInsbaseConSpec(this.code)
    }
}

object SocialInsbaseConProv {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_SOCIAL_INSBASE
}

class SocialInsbaseConSpec(override val code: ConceptCode, override val path: Array[ArticleCode],
                           override val resultDelegate: Option[ResultFunc]) extends ConceptSpec(code, path, resultDelegate) {
    def this(_code: ConceptCode) = this(_code, Array[ArticleCode](), Some(SocialInsbaseConSpec.conceptEval))
}

object SocialInsbaseConSpec {
    def conceptEval(target: ITermTarget, spec: Option[IArticleSpec], period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new SocialInsbaseResult(target, spec)

        Array(Right(resultsValues))
    }
}

// HealthInspaym		HEALTH_INSPAYM
class HealthInspaymConProv(override val code: ConceptCode) extends ConceptSpecProvider(code) {
    def this() = this(ConceptCode.get(HealthInspaymConProv.CONCEPT_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IConceptSpec = {
        new HealthInspaymConSpec(this.code)
    }
}

object HealthInspaymConProv {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_HEALTH_INSPAYM
}

class HealthInspaymConSpec(override val code: ConceptCode, override val path: Array[ArticleCode],
                           override val resultDelegate: Option[ResultFunc]) extends ConceptSpec(code, path, resultDelegate) {
    def this(_code: ConceptCode) = this(_code, constToPathArray(HealthInspaymConSpec.specPath), Some(HealthInspaymConSpec.conceptEval))
}

object HealthInspaymConSpec {
    val specPath = Array[Int](
        ExampleArticleConst.ARTICLE_HEALTH_INSBASE.id,
    )

    def conceptEval(target: ITermTarget, spec: Option[IArticleSpec], period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new HealthInspaymResult(target, spec)

        Array(Right(resultsValues))
    }
}

// SocialInspaym		SOCIAL_INSPAYM
class SocialInspaymConProv(override val code: ConceptCode) extends ConceptSpecProvider(code) {
    def this() = this(ConceptCode.get(SocialInspaymConProv.CONCEPT_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IConceptSpec = {
        new SocialInspaymConSpec(this.code)
    }
}

object SocialInspaymConProv {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_SOCIAL_INSPAYM
}

class SocialInspaymConSpec(override val code: ConceptCode, override val path: Array[ArticleCode],
                           override val resultDelegate: Option[ResultFunc]) extends ConceptSpec(code, path, resultDelegate) {
    def this(_code: ConceptCode) = this(_code, constToPathArray(SocialInspaymConSpec.specPath), Some(SocialInspaymConSpec.conceptEval))
}

object SocialInspaymConSpec {
    val specPath = Array[Int](
        ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.id,
    )

    def conceptEval(target: ITermTarget, spec: Option[IArticleSpec], period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new SocialInspaymResult(target, spec)

        Array(Right(resultsValues))
    }
}

// TaxingAdvbase		TAXING_ADVBASE
class TaxingAdvbaseConProv(override val code: ConceptCode) extends ConceptSpecProvider(code) {
    def this() = this(ConceptCode.get(TaxingAdvbaseConProv.CONCEPT_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IConceptSpec = {
        new TaxingAdvbaseConSpec(this.code)
    }
}

object TaxingAdvbaseConProv {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_TAXING_ADVBASE
}

class TaxingAdvbaseConSpec(override val code: ConceptCode, override val path: Array[ArticleCode],
                           override val resultDelegate: Option[ResultFunc]) extends ConceptSpec(code, path, resultDelegate) {
    def this(_code: ConceptCode) = this(_code, Array[ArticleCode](), Some(TaxingAdvbaseConSpec.conceptEval))
}

object TaxingAdvbaseConSpec {
    def conceptEval(target: ITermTarget, spec: Option[IArticleSpec], period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new TaxingAdvbaseResult(target, spec)

        Array(Right(resultsValues))
    }
}

// TaxingAdvpaym		TAXING_ADVPAYM
class TaxingAdvpaymConProv(override val code: ConceptCode) extends ConceptSpecProvider(code) {
    def this() = this(ConceptCode.get(TaxingAdvpaymConProv.CONCEPT_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IConceptSpec = {
        new TaxingAdvpaymConSpec(this.code)
    }
}

object TaxingAdvpaymConProv {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_TAXING_ADVPAYM
}

class TaxingAdvpaymConSpec(override val code: ConceptCode, override val path: Array[ArticleCode],
                           override val resultDelegate: Option[ResultFunc]) extends ConceptSpec(code, path, resultDelegate) {
    def this(_code: ConceptCode) = this(_code, constToPathArray(TaxingAdvpaymConSpec.specPath), Some(TaxingAdvpaymConSpec.conceptEval))
}

object TaxingAdvpaymConSpec {
    val specPath = Array[Int](
        ExampleArticleConst.ARTICLE_TAXING_ADVBASE.id,
    )

    def conceptEval(target: ITermTarget, spec: Option[IArticleSpec], period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new TaxingAdvpaymResult(target, spec)

        Array(Right(resultsValues))
    }
}

// IncomeGross		INCOME_GROSS
class IncomeGrossConProv(override val code: ConceptCode) extends ConceptSpecProvider(code) {
    def this() = this(ConceptCode.get(IncomeGrossConProv.CONCEPT_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IConceptSpec = {
        new IncomeGrossConSpec(this.code)
    }
}

object IncomeGrossConProv {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_INCOME_GROSS
}

class IncomeGrossConSpec(override val code: ConceptCode, override val path: Array[ArticleCode],
                         override val resultDelegate: Option[ResultFunc]) extends ConceptSpec(code, path, resultDelegate) {
    def this(_code: ConceptCode) = this(_code, Array[ArticleCode](), Some(IncomeGrossConSpec.conceptEval))
}

object IncomeGrossConSpec {
    def conceptEval(target: ITermTarget, spec: Option[IArticleSpec], period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new IncomeGrossResult(target, spec)

        Array(Right(resultsValues))
    }
}

// IncomeNetto		INCOME_NETTO
class IncomeNettoConProv(override val code: ConceptCode) extends ConceptSpecProvider(code) {
    def this() = this(ConceptCode.get(IncomeNettoConProv.CONCEPT_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IConceptSpec = {
        new IncomeNettoConSpec(this.code)
    }
}

object IncomeNettoConProv {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_INCOME_NETTO
}

class IncomeNettoConSpec(override val code: ConceptCode, override val path: Array[ArticleCode],
                         override val resultDelegate: Option[ResultFunc]) extends ConceptSpec(code, path, resultDelegate) {
    def this(_code: ConceptCode) = this(_code, constToPathArray(IncomeNettoConSpec.specPath), Some(IncomeNettoConSpec.conceptEval))
}

object IncomeNettoConSpec {
    val specPath = Array[Int](
        ExampleArticleConst.ARTICLE_INCOME_GROSS.id,
        ExampleArticleConst.ARTICLE_HEALTH_INSPAYM.id,
        ExampleArticleConst.ARTICLE_SOCIAL_INSPAYM.id,
        ExampleArticleConst.ARTICLE_TAXING_ADVPAYM.id,
    )

    def conceptEval(target: ITermTarget, spec: Option[IArticleSpec], period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new IncomeNettoResult(target, spec)

        Array(Right(resultsValues))
    }
}

