package org.hravemzdy.procezor.example

import org.hravemzdy.procezor.interfaces.IConceptSpec
import org.hravemzdy.procezor.registry.factories.{ArticleSpecFactory, ConceptSpecFactory, ProviderRecord, SpecFactory}
import org.hravemzdy.procezor.registry.providers.{ConceptSpec, ConceptSpecProvider, IArticleSpecProvider, IConceptSpecProvider}
import org.hravemzdy.procezor.registry.factories.FactoryTypes.CODE
import org.hravemzdy.procezor.service.types.ConceptCode


class ExampleArticleFactory() extends ArticleSpecFactory() {
    private val providersConfig = Array[ProviderRecord](
        new ProviderRecord(ExampleArticleConst.ARTICLE_TIMESHT_WORKING.id, ExampleConceptConst.CONCEPT_TIMESHT_WORKING.id,
            Array[Int]()),
        new ProviderRecord(ExampleArticleConst.ARTICLE_PAYMENT_SALARY.id, ExampleConceptConst.CONCEPT_AMOUNT_BASIS.id,
            Array[Int](
                ExampleArticleConst.ARTICLE_INCOME_GROSS.id,
                ExampleArticleConst.ARTICLE_HEALTH_INSBASE.id,
                ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.id,
                ExampleArticleConst.ARTICLE_TAXING_ADVBASE.id,
            )),
        new ProviderRecord(ExampleArticleConst.ARTICLE_PAYMENT_BONUS.id, ExampleConceptConst.CONCEPT_AMOUNT_FIXED.id,
            Array[Int](
                ExampleArticleConst.ARTICLE_INCOME_GROSS.id,
                ExampleArticleConst.ARTICLE_HEALTH_INSBASE.id,
                ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.id,
                ExampleArticleConst.ARTICLE_TAXING_ADVBASE.id,
            )),
        new ProviderRecord(ExampleArticleConst.ARTICLE_PAYMENT_BARTER.id, ExampleConceptConst.CONCEPT_AMOUNT_FIXED.id,
            Array[Int](
                ExampleArticleConst.ARTICLE_HEALTH_INSBASE.id,
                ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.id,
                ExampleArticleConst.ARTICLE_TAXING_ADVBASE.id,
            )),
        new ProviderRecord(ExampleArticleConst.ARTICLE_ALLOWCE_HOFFICE.id, ExampleConceptConst.CONCEPT_AMOUNT_FIXED.id,
            Array[Int](
                ExampleArticleConst.ARTICLE_INCOME_NETTO.id,
            )),
        new ProviderRecord(ExampleArticleConst.ARTICLE_HEALTH_INSBASE.id, ExampleConceptConst.CONCEPT_HEALTH_INSBASE.id,
            Array[Int]()),
        new ProviderRecord(ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.id, ExampleConceptConst.CONCEPT_SOCIAL_INSBASE.id,
            Array[Int]()),
        new ProviderRecord(ExampleArticleConst.ARTICLE_HEALTH_INSPAYM.id, ExampleConceptConst.CONCEPT_HEALTH_INSPAYM.id,
            Array[Int]()),
        new ProviderRecord(ExampleArticleConst.ARTICLE_SOCIAL_INSPAYM.id, ExampleConceptConst.CONCEPT_SOCIAL_INSPAYM.id,
            Array[Int]()),
        new ProviderRecord(ExampleArticleConst.ARTICLE_TAXING_ADVBASE.id, ExampleConceptConst.CONCEPT_TAXING_ADVBASE.id,
            Array[Int]()),
        new ProviderRecord(ExampleArticleConst.ARTICLE_TAXING_ADVPAYM.id, ExampleConceptConst.CONCEPT_TAXING_ADVPAYM.id,
            Array[Int]()),
        new ProviderRecord(ExampleArticleConst.ARTICLE_INCOME_GROSS.id, ExampleConceptConst.CONCEPT_INCOME_GROSS.id,
            Array[Int]()),
        new ProviderRecord(ExampleArticleConst.ARTICLE_INCOME_NETTO.id, ExampleConceptConst.CONCEPT_INCOME_NETTO.id,
            Array[Int]()),
    )

    override val providers: Map[CODE, IArticleSpecProvider] =
        ArticleSpecFactory.buildProvidersFromRecords(providersConfig.toArray)
}

class ExampleConceptFactory() extends ConceptSpecFactory() {
    override val providers: Map[CODE, IConceptSpecProvider] =
        SpecFactory.buildProvidersFromAssembly[IConceptSpecProvider, IConceptSpec, ConceptCode]("org.hravemzdy.procezor.example")
}