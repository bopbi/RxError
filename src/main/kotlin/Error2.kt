import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

fun main() {

    val stringEmitter: PublishSubject<String> = PublishSubject.create()

    stringEmitter
            .flatMap {
                Observable.just(simpleFunction(it))
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

fun simpleFunction(value: String): String {
    if (value.length > 2) {
        throw IllegalArgumentException()
    }
    return "through $value"
}
