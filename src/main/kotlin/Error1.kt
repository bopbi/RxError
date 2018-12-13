import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

fun main() {

    val stringEmitter: PublishSubject<String> = PublishSubject.create()

    stringEmitter
            .map {
                if (it.length > 2) {
                    throw IllegalArgumentException()
                }
                it }
//            .onErrorReturn { "OOps" }
//            .onErrorResumeNext ( Observable.just("OOps") )
//            .onErrorReturnItem("OOps")
            .subscribe({
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
