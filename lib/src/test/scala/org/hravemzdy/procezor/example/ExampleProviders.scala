package org.hravemzdy.procezor.example

import org.hravemzdy.legalios.interfaces.{IBundleProps, IPeriod}
import org.hravemzdy.procezor.interfaces.BuilderType.{BuilderResultList, ResultFunc}
import org.hravemzdy.procezor.interfaces.{IConceptSpec, ITermResult, ITermTarget}
import org.hravemzdy.procezor.registry.providers.ConceptSpec.constToPathArray
import org.hravemzdy.procezor.registry.providers.{ConceptSpec, ConceptSpecProvider}
import org.hravemzdy.procezor.service.types.{ArticleCode, ConceptCode, ContractCode, MonthCode, PositionCode, TermResult, TermTarget, VariantCode, VersionCode}

class ExampleTermTarget(override val monthCode: MonthCode,
                        override val contract: ContractCode,
                        override val position: PositionCode,
                        override val variant: VariantCode,
                        override val article: ArticleCode,
                        override val concept: ConceptCode,
                        override val targetBasis: Int,
                        override val targetDescr: String) 
  extends TermTarget(monthCode, contract, position, variant, article, concept, targetBasis, targetDescr) {
    def this(_month: MonthCode, _contract: ContractCode, _position: PositionCode, _variant: VariantCode, _article: ArticleCode, _concept: ConceptCode) = 
        this(_month, _contract, _position, _variant, _article, _concept, 0, "")

    override def articleDescr(): String = {
        ExampleArticleConst.getArticleSymbol(article.value)
    }
    override def conceptDescr(): String = {
        ExampleConceptConst.getConceptSymbol(concept.value)
    }
}

class ExampleTermResult(override val target: ITermTarget,
                             override val resultValue: Int,
                             override val resultBasis: Int,
                             override val resultDescr: String) extends TermResult(target, resultValue, resultBasis, resultDescr) {
    override def articleDescr(): String = {
        ExampleArticleConst.getArticleSymbol(article.value)
    }
    override def conceptDescr(): String = {
        ExampleConceptConst.getConceptSymbol(concept.value)
    }
}

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
    def conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new TimeshtWorkingResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

        Array(Right(resultsValues))
    }
}

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

    def conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new AmountBasisResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

        Array(Right(resultsValues))
    }
}

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
    def conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new AmountFixedResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

        Array(Right(resultsValues))
    }
}

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
    def conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new HealthInsbaseResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

        Array(Right(resultsValues))
    }
}

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
    def conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new SocialInsbaseResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

        Array(Right(resultsValues))
    }
}

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

    def conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new HealthInspaymResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

        Array(Right(resultsValues))
    }
}

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

    def conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new SocialInspaymResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

        Array(Right(resultsValues))
    }
}

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
    def conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new TaxingAdvbaseResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

        Array(Right(resultsValues))
    }
}

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

    def conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new TaxingAdvpaymResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

        Array(Right(resultsValues))
    }
}

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
    def conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new IncomeGrossResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

        Array(Right(resultsValues))
    }
}

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

    def conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList = {
        val resultsValues: ITermResult = new IncomeNettoResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

        Array(Right(resultsValues))
    }
}

