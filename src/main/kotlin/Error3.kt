import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

fun main() {
    val stringEmitter: PublishSubject<String> = PublishSubject.create()

    stringEmitter
            .flatMap {
                Observable
                        .defer {
                            simpleObservable(it)
                        }
                        .onErrorResumeNext(Observable.just("Oops"))
            }.subscribe({
                println("onNext $it")
            }, {
                println("onError")
            }, {
                println("onComplete")
            })

    stringEmitter.onNext("Hi")
    stringEmitter.onNext("Kotlin")
    stringEmitter.onNext("Rx")
}

fun simpleObservable(value: String): Observable<String> {
    if (value.length > 2) {
        return Observable.error(Throwable())
    }
    return Observable.just("its $value")
}