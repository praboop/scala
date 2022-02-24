import java.util.UUID

import com.google.common.cache.{Cache, CacheBuilder, CacheLoader, LoadingCache, RemovalListener, RemovalNotification}
import java.util.concurrent.TimeUnit
import java.util.UUID

import com.google.common.cache.{Cache, CacheBuilder, RemovalListener, RemovalNotification}
import java.util.concurrent.TimeUnit

import com.google.common.cache.RemovalCause.EXPIRED


import scala.collection.mutable.Queue
import scala.concurrent.duration.DurationInt

import scala.concurrent.Future
object GuavaExpiringCacheExample {

  def buildCache(maxLifeOfInteractionSec: Int): Cache[String, Long] =
    CacheBuilder
      .newBuilder()
      .concurrencyLevel(100)
      .expireAfterWrite(12, TimeUnit.SECONDS)
      .removalListener(new RemovalListener[String, Long]() {

        override def onRemoval(notification: RemovalNotification[String, Long]): Unit =
          println("Evicted: " + notification.wasEvicted() + ", key:" + notification.getKey + ", cause:" + notification.getCause)
      })
      .build[String, Long]()

  def main(args: Array[String]): Unit = {

    val cache:Cache[Integer, Long] =
       CacheBuilder.newBuilder
   //      .recordStats()
         .concurrencyLevel(300)
         .expireAfterWrite(1, TimeUnit.SECONDS)
         .removalListener(new RemovalListener[Integer,Long]() {
           override def onRemoval(notification: RemovalNotification[Integer, Long]): Unit = {

             println("Evicted: " + notification.wasEvicted() + ", key:" + notification.getKey + ", cause:" + notification.getCause)

           }
         }).build[Integer, Long]


    cache.put(1, System.currentTimeMillis())
    println("Sleeping...")
    Thread.sleep(3000)
    println("1 present? " + cache.getIfPresent(1))
    cache.put(2, System.currentTimeMillis())
    cache.invalidate(2)
    cache.put(3, System.currentTimeMillis())
    Thread.sleep(1000)
    println("3 present? " + cache.getIfPresent(3))
    cache.put(4, System.currentTimeMillis())
    Thread.sleep(2000)
    (5 to 1000).map(cache.put(_, System.currentTimeMillis()))


  //  cache.cleanUp()


    println("Done. Size: " + cache.size())
    println(cache.stats())
  }

}
