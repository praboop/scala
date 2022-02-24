import java.util.UUID

import com.google.common.cache.{ Cache, CacheBuilder, CacheLoader, LoadingCache, RemovalListener, RemovalNotification }
import java.util.concurrent.TimeUnit

object GuavaExpiringCacheExample {

  def main(args: Array[String]): Unit = {

    val cache: Cache[Integer, Long] =
      CacheBuilder.newBuilder
        .concurrencyLevel(300)
        .expireAfterWrite(1, TimeUnit.SECONDS)
        .removalListener(new RemovalListener[Integer, Long]() {
          override def onRemoval(notification: RemovalNotification[Integer, Long]): Unit =
            println(
              "Evicted: " + notification
                .wasEvicted() + ", key:" + notification.getKey + ", cause:" + notification.getCause
            )

        })
        .build[Integer, Long]

    cache.put(1, System.currentTimeMillis())
    println("Sleeping...")
    Thread.sleep(3000)
    println("Cleaning")
    cache.cleanUp()
    Thread.sleep(3000)
    println("1 present? " + cache.getIfPresent(1))
//    cache.put(2, System.currentTimeMillis())
//    cache.put(3, System.currentTimeMillis())
//    Thread.sleep(1000)
//    println("3 present? " + cache.getIfPresent(3))
//    cache.put(4, System.currentTimeMillis())
//    Thread.sleep(2000)
//    (5 to 1000).map(cache.put(_, System.currentTimeMillis()))

    cache.cleanUp()

    println("Done. Size: " + cache.size())
  }

}
