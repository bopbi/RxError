import io.reactivex.subjects.PublishSubject

fun main() {

    val stringEmitter: PublishSubject<String> = PublishSubject.create()

    stringEmitter
            .map {
                if (it.length > 2) {
                    throw IllegalArgumentException()
                }
                it
            }.onErrorReturn {
                "OOps"
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
