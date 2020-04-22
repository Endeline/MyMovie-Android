package com.endeline.mymovie.viewmodels

import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.SearchItemUiModel
import com.endeline.domain.usecase.SearchAllUseCase
import com.endeline.mymovie.di.components.DaggerUseCaseComponent
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject

class SearchViewModel : ViewModel() {
    enum class MediaType {
        person,
        movie,
        tv;

        companion object {
            fun fromString(name: String?) =
                values().first {
                    it.toString() == name
                }
        }

    }

    @Inject
    protected lateinit var searchAllUseCase: SearchAllUseCase

    val personSubject = BehaviorSubject.create<List<SearchItemUiModel>>()
    val movieSubject = BehaviorSubject.create<List<SearchItemUiModel>>()
    val tvSubject = BehaviorSubject.create<List<SearchItemUiModel>>()

    init {
        DaggerUseCaseComponent.builder().build().inject(this)
    }

    fun search(query: String) =
        searchAllUseCase(query)
            .subscribeOn(Schedulers.io())
            .subscribe({
                val personResult = mutableListOf<SearchItemUiModel>()
                val movieResult = mutableListOf<SearchItemUiModel>()
                val tvResult = mutableListOf<SearchItemUiModel>()

                it.results?.forEach {
                    when (MediaType.fromString(it.mediaType)) {
                        MediaType.person -> personResult.add(it)
                        MediaType.movie -> movieResult.add(it)
                        MediaType.tv -> tvResult.add(it)
                    }
                }

                personSubject.onNext(personResult)
                movieSubject.onNext(movieResult)
                tvSubject.onNext(tvResult)

            }, Timber::e)

}
