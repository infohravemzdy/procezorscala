package org.hravemzdy.procezor.example

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.registry.providers.ArticleSpec
import org.hravemzdy.procezor.registry.providers.ArticleSpecProvider
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.ConceptCode
import org.hravemzdy.procezor.service.types.VersionCode

class TimeshtWorkingArtProv(override val code: ArticleCode) extends ArticleSpecProvider(code) {
    def this() = this(ArticleCode.get(TimeshtWorkingArtProv.ARTICLE_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IArticleSpec = {
        new TimeshtWorkingArtSpec(this.code)
    }
}

object TimeshtWorkingArtProv {
    val ARTICLE_CODE = ExampleArticleConst.ARTICLE_TIMESHT_WORKING
}

class TimeshtWorkingArtSpec(override val code: ArticleCode, override val role: ConceptCode, override val sums: Array[ArticleCode]) extends ArticleSpec(code, role, sums) {
    def this(_code: ArticleCode) = this(_code, ConceptCode.get(TimeshtWorkingArtSpec.CONCEPT_CODE.id), Array[ArticleCode]())
}

object TimeshtWorkingArtSpec {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_TIMESHT_WORKING
}

class PaymentSalaryArtProv(override val code: ArticleCode) extends ArticleSpecProvider(code) {
    def this() = this(ArticleCode.get(PaymentSalaryArtProv.ARTICLE_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IArticleSpec = {
        new PaymentSalaryArtSpec(this.code)
    }
}

object PaymentSalaryArtProv {
    val ARTICLE_CODE = ExampleArticleConst.ARTICLE_PAYMENT_SALARY
}

class PaymentSalaryArtSpec(override val code: ArticleCode, override val role: ConceptCode, override val sums: Array[ArticleCode]) extends ArticleSpec(code, role, sums) {
    def this(_code: ArticleCode) = this(_code, ConceptCode.get(PaymentSalaryArtSpec.CONCEPT_CODE.id),
        ArticleSpec.constToSumsArray(Array[Int](
            ExampleArticleConst.ARTICLE_INCOME_GROSS.id,
            ExampleArticleConst.ARTICLE_HEALTH_INSBASE.id,
            ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.id,
            ExampleArticleConst.ARTICLE_TAXING_ADVBASE.id,
        )))
}

object PaymentSalaryArtSpec {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_AMOUNT_BASIS
}

class PaymentBonusArtProv(override val code: ArticleCode) extends ArticleSpecProvider(code) {
    def this() = this(ArticleCode.get(PaymentBonusArtProv.ARTICLE_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IArticleSpec = {
        new PaymentBonusArtSpec(this.code)
    }
}

object PaymentBonusArtProv {
    val ARTICLE_CODE = ExampleArticleConst.ARTICLE_PAYMENT_BONUS
}

class PaymentBonusArtSpec(override val code: ArticleCode, override val role: ConceptCode, override val sums: Array[ArticleCode]) extends ArticleSpec(code, role, sums) {
    def this(_code: ArticleCode) = this(_code, ConceptCode.get(PaymentBonusArtSpec.CONCEPT_CODE.id),
        ArticleSpec.constToSumsArray(Array[Int](
            ExampleArticleConst.ARTICLE_INCOME_GROSS.id,
            ExampleArticleConst.ARTICLE_HEALTH_INSBASE.id,
            ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.id,
            ExampleArticleConst.ARTICLE_TAXING_ADVBASE.id,
        )))
}

object PaymentBonusArtSpec {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_AMOUNT_FIXED
}

class PaymentBarterArtProv(override val code: ArticleCode) extends ArticleSpecProvider(code) {
    def this() = this(ArticleCode.get(PaymentBarterArtProv.ARTICLE_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IArticleSpec = {
        new PaymentBarterArtSpec(this.code)
    }
}

object PaymentBarterArtProv {
    val ARTICLE_CODE = ExampleArticleConst.ARTICLE_PAYMENT_BARTER
}

class PaymentBarterArtSpec(override val code: ArticleCode, override val role: ConceptCode, override val sums: Array[ArticleCode]) extends ArticleSpec(code, role, sums) {
    def this(_code: ArticleCode) = this(_code, ConceptCode.get(PaymentBarterArtSpec.CONCEPT_CODE.id),
        ArticleSpec.constToSumsArray(Array[Int](
            ExampleArticleConst.ARTICLE_HEALTH_INSBASE.id,
            ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.id,
            ExampleArticleConst.ARTICLE_TAXING_ADVBASE.id,
        )))
}

object PaymentBarterArtSpec {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_AMOUNT_FIXED
}

class AllowceHofficeArtProv(override val code: ArticleCode) extends ArticleSpecProvider(code) {
    def this() = this(ArticleCode.get(AllowceHofficeArtProv.ARTICLE_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IArticleSpec = {
        new AllowceHofficeArtSpec(this.code)
    }
}

object AllowceHofficeArtProv {
    val ARTICLE_CODE = ExampleArticleConst.ARTICLE_ALLOWCE_HOFFICE
}

class AllowceHofficeArtSpec(override val code: ArticleCode, override val role: ConceptCode, override val sums: Array[ArticleCode]) extends ArticleSpec(code, role, sums) {
    def this(_code: ArticleCode) = this(_code, ConceptCode.get(AllowceHofficeArtSpec.CONCEPT_CODE.id),
        ArticleSpec.constToSumsArray(Array[Int](
            ExampleArticleConst.ARTICLE_INCOME_NETTO.id,
        )))
}

object AllowceHofficeArtSpec {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_AMOUNT_FIXED
}

class HealthInsbaseArtProv(override val code: ArticleCode) extends ArticleSpecProvider(code) {
    def this() = this(ArticleCode.get(HealthInsbaseArtProv.ARTICLE_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IArticleSpec = {
        new HealthInsbaseArtSpec(this.code)
    }
}

object HealthInsbaseArtProv {
    val ARTICLE_CODE = ExampleArticleConst.ARTICLE_HEALTH_INSBASE
}

class HealthInsbaseArtSpec(override val code: ArticleCode, override val role: ConceptCode, override val sums: Array[ArticleCode]) extends ArticleSpec(code, role, sums) {
    def this(_code: ArticleCode) = this(_code, ConceptCode.get(HealthInsbaseArtSpec.CONCEPT_CODE.id), Array[ArticleCode]())
}

object HealthInsbaseArtSpec {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_HEALTH_INSBASE
}

class SocialInsbaseArtProv(override val code: ArticleCode) extends ArticleSpecProvider(code) {
    def this() = this(ArticleCode.get(SocialInsbaseArtProv.ARTICLE_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IArticleSpec = {
        new SocialInsbaseArtSpec(this.code)
    }
}

object SocialInsbaseArtProv {
    val ARTICLE_CODE = ExampleArticleConst.ARTICLE_SOCIAL_INSBASE
}

class SocialInsbaseArtSpec(override val code: ArticleCode, override val role: ConceptCode, override val sums: Array[ArticleCode]) extends ArticleSpec(code, role, sums) {
    def this(_code: ArticleCode) = this(_code, ConceptCode.get(SocialInsbaseArtSpec.CONCEPT_CODE.id), Array[ArticleCode]())
}

object SocialInsbaseArtSpec {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_SOCIAL_INSBASE
}

class HealthInspaymArtProv(override val code: ArticleCode) extends ArticleSpecProvider(code) {
    def this() = this(ArticleCode.get(HealthInspaymArtProv.ARTICLE_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IArticleSpec = {
        new HealthInspaymArtSpec(this.code)
    }
}

object HealthInspaymArtProv {
    val ARTICLE_CODE = ExampleArticleConst.ARTICLE_HEALTH_INSPAYM
}

class HealthInspaymArtSpec(override val code: ArticleCode, override val role: ConceptCode, override val sums: Array[ArticleCode]) extends ArticleSpec(code, role, sums) {
    def this(_code: ArticleCode) = this(_code, ConceptCode.get(HealthInspaymArtSpec.CONCEPT_CODE.id), Array[ArticleCode]())
}

object HealthInspaymArtSpec {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_HEALTH_INSPAYM
}

class SocialInspaymArtProv(override val code: ArticleCode) extends ArticleSpecProvider(code) {
    def this() = this(ArticleCode.get(SocialInspaymArtProv.ARTICLE_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IArticleSpec = {
        new SocialInspaymArtSpec(this.code)
    }
}

object SocialInspaymArtProv {
    val ARTICLE_CODE = ExampleArticleConst.ARTICLE_SOCIAL_INSPAYM
}

class SocialInspaymArtSpec(override val code: ArticleCode, override val role: ConceptCode, override val sums: Array[ArticleCode]) extends ArticleSpec(code, role, sums) {
    def this(_code: ArticleCode) = this(_code, ConceptCode.get(SocialInspaymArtSpec.CONCEPT_CODE.id), Array[ArticleCode]())
}

object SocialInspaymArtSpec {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_SOCIAL_INSPAYM
}

class TaxingAdvbaseArtProv(override val code: ArticleCode) extends ArticleSpecProvider(code) {
    def this() = this(ArticleCode.get(TaxingAdvbaseArtProv.ARTICLE_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IArticleSpec = {
        new TaxingAdvbaseArtSpec(this.code)
    }
}

object TaxingAdvbaseArtProv {
    val ARTICLE_CODE = ExampleArticleConst.ARTICLE_TAXING_ADVBASE
}

class TaxingAdvbaseArtSpec(override val code: ArticleCode, override val role: ConceptCode, override val sums: Array[ArticleCode]) extends ArticleSpec(code, role, sums) {
    def this(_code: ArticleCode) = this(_code, ConceptCode.get(TaxingAdvbaseArtSpec.CONCEPT_CODE.id), Array[ArticleCode]())
}

object TaxingAdvbaseArtSpec {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_TAXING_ADVBASE
}

class TaxingAdvpaymArtProv(override val code: ArticleCode) extends ArticleSpecProvider(code) {
    def this() = this(ArticleCode.get(TaxingAdvpaymArtProv.ARTICLE_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IArticleSpec = {
        new TaxingAdvpaymArtSpec(this.code)
    }
}

object TaxingAdvpaymArtProv {
    val ARTICLE_CODE = ExampleArticleConst.ARTICLE_TAXING_ADVPAYM
}

class TaxingAdvpaymArtSpec(override val code: ArticleCode, override val role: ConceptCode, override val sums: Array[ArticleCode]) extends ArticleSpec(code, role, sums) {
    def this(_code: ArticleCode) = this(_code, ConceptCode.get(TaxingAdvpaymArtSpec.CONCEPT_CODE.id), Array[ArticleCode]())
}

object TaxingAdvpaymArtSpec {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_TAXING_ADVPAYM
}

class IncomeGrossArtProv(override val code: ArticleCode) extends ArticleSpecProvider(code) {
    def this() = this(ArticleCode.get(IncomeGrossArtProv.ARTICLE_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IArticleSpec = {
        new IncomeGrossArtSpec(this.code)
    }
}

object IncomeGrossArtProv {
    val ARTICLE_CODE = ExampleArticleConst.ARTICLE_INCOME_GROSS
}

class IncomeGrossArtSpec(override val code: ArticleCode, override val role: ConceptCode, override val sums: Array[ArticleCode]) extends ArticleSpec(code, role, sums) {
    def this(_code: ArticleCode) = this(_code, ConceptCode.get(IncomeGrossArtSpec.CONCEPT_CODE.id), Array[ArticleCode]())
}

object IncomeGrossArtSpec {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_INCOME_GROSS
}

class IncomeNettoArtProv(override val code: ArticleCode) extends ArticleSpecProvider(code) {
    def this() = this(ArticleCode.get(IncomeNettoArtProv.ARTICLE_CODE.id))

    override def getSpec(period: IPeriod, version: VersionCode): IArticleSpec = {
        new IncomeNettoArtSpec(this.code)
    }
}

object IncomeNettoArtProv {
    val ARTICLE_CODE = ExampleArticleConst.ARTICLE_INCOME_NETTO
}

class IncomeNettoArtSpec(override val code: ArticleCode, override val role: ConceptCode, override val sums: Array[ArticleCode]) extends ArticleSpec(code, role, sums) {
    def this(_code: ArticleCode) = this(_code, ConceptCode.get(IncomeNettoArtSpec.CONCEPT_CODE.id), Array[ArticleCode]())
}

object IncomeNettoArtSpec {
    val CONCEPT_CODE = ExampleConceptConst.CONCEPT_INCOME_NETTO
}

