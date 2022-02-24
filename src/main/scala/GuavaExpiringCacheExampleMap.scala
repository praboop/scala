import java.util.concurrent.TimeUnit

import com.google.common.cache.{Cache, CacheBuilder, RemovalListener, RemovalNotification}

import scala.collection.concurrent.TrieMap

object GuavaExpiringCacheExampleMap {

  def main(args: Array[String]): Unit = {

    val now = System.currentTimeMillis()

    val cache:Cache[Integer, TrieMap[String, String]] =
      CacheBuilder.newBuilder
        .concurrencyLevel(300)
        .expireAfterWrite(500, TimeUnit.MILLISECONDS)
        .removalListener(new RemovalListener[Integer,TrieMap[String, String]]() {
          override def onRemoval(notification: RemovalNotification[Integer, TrieMap[String, String]]): Unit = {

            println("Time elapsed: (" + (System.currentTimeMillis() - now) + ") Evicted: " + notification.wasEvicted() + ", key:" + notification.getKey + ", cause:" + notification.getCause + " values: " + notification.getValue)

          }
        }).build[Integer, TrieMap[String, String]]


    val firstMap = new TrieMap[String, String]()

    firstMap.put("1a","0")
    cache.put(1, firstMap)
    println("Sleeping...")
    Thread.sleep(400)
    cache.cleanUp()

    firstMap.put("1b","400")
    //val y:TrieMap[String, String] = firstMap.asInstanceOf[TrieMap[String, String]]
    Thread.sleep(400)
    cache.cleanUp()
    val y = firstMap
    y.put("1c","800")
  //  cache.put(1, y)
    Thread.sleep(400)
    cache.cleanUp()

    println("2.Cache size: " + cache.size())

    cache.cleanUp()

    println("Done. Size: " + cache.size())
  }

}
