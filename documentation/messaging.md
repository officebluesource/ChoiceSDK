# Messaging (Push Kit)

Messaging consists of two different parts: `Messaging` and `MessagingService`. The difference is that the `Messaging` instance is used to send messages upstream and subscribe to topics, whereas the `MessagingService` handles downstream messages.

## Setup
Be sure to active Huawei Push Kit in AppGallery Connect!

## Usage

Subscribe to token changes:
```kotlin
val tokenObserver = object : DisposableObserver<String>() {
    override fun onNext(token: String) {}
    override fun onError(e: Throwable?) {}
    override fun onComplete() {}
}
MessagingRepositoryFactory.getMessagingService()
    .getNewTokenObservable()
    .subscribeWith(tokenObserver)
```

Request token once in order to get token in `tokenObserver` (see above):
```kotlin
MessagingRepositoryFactory.getMessagingService().requestToken(context)
```

Directly get token via suspending function:
```kotlin
MessagingRepositoryFactory.getMessagingService().getToken(context)
```

Receive messages:
```kotlin
val messageObserver: DisposableObserver<RemoteMessage> = object : DisposableObserver<RemoteMessage>() {
    override fun onNext(remoteMessage: RemoteMessage) {
        val from = remoteMessage.from
        val data = remoteMessage.data
        val title = remoteMessage.notification?.title
    }

    override fun onError(e: Throwable?) {}
    override fun onComplete() {}
}

MessagingRepositoryFactory.getMessagingService()
    .getMessageReceivedObservable()
    .observeOn(AndroidSchedulers.mainThread())
    .subscribeWith(messageObserver)
```

Subscribe to topic:
```kotlin
MessagingFactory.getMessaging().subscribeToTopic("News")
```

Send message:
```kotlin
val message = RemoteMessage
    .Builder(destination)
    .setMessageId(id)
    .addData("my_message", "Hello World")
    .build()
MessagingFactory.getMessaging().send(message)
```

## Links
- [GMS Messaging](https://firebase.google.com/docs/cloud-messaging)
- [HMS Push Kit](https://developer.huawei.com/consumer/en/hms/huawei-pushkit)