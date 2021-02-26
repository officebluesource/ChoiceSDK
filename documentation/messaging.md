# Messaging 

Messaging consists of two different parts: `Messaging` and `MessagingService`. The difference is that the `Messaging` instance is used to send messages upstream whereas the `MessagingService` handles downstream messages.

## Setup
Be sure to active Huawei Push Kit in AppGallery Connect!

## Usage

Upstream:
```kotlin
MessagingFactory.getMessaging().subscribeToTopic("News")
```

Downstream:
```kotlin
val messageObserver: DisposableObserver<RemoteMessage> = object : DisposableObserver<RemoteMessage>() {
    override fun onNext(result: RemoteMessage) {
        val from = result.from
        val data = result.data
        val title = result.notification?.title
    }

    override fun onError(e: Throwable?) {}
    override fun onComplete() {}
}

MessagingRepositoryFactory.getMessagingService()
    .getMessageReceivedObservable()
    .observeOn(AndroidSchedulers.mainThread())
    .subscribeWith(messageObserver)
```

## Links
- [GMS Messaging](https://firebase.google.com/docs/cloud-messaging)
- [HMS Push Kit](https://developer.huawei.com/consumer/en/hms/huawei-pushkit)