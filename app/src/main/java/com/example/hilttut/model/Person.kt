package com.example.hilttut.model

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Qualifier

/* Allows me to create variations that return the same type */
@Qualifier
annotation class EnglishQualifier

@Qualifier
annotation class SpanishQualiifier

/* Allows me to create an injectable instance from a type without a constructor
Need to use both @Module and @InstallIn for this
 */
@Module
@InstallIn(ActivityComponent::class)
abstract class PersonModule {
    /* @Binds means
    whenever you find a `Person` type then use the parameter here to
    create an object for that type
     */
    @EnglishQualifier
    @Binds
    abstract fun CreateEnglishPeople(englishPerson: EnglishPerson): Person

    @SpanishQualiifier
    @Binds
    abstract fun CreateSpanisHPeople(spanishPerson: SpanishPerson): Person
}

interface Person {
    fun speakLanguage()
}