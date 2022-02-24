import java.util.concurrent.{CompletableFuture, ExecutorService, Executors, Future, TimeUnit}
import scala.collection.concurrent.TrieMap
import scala.collection.mutable.ListBuffer
import scala.util.Random

/**
 * For a given Key-Value pair with tasks as values, demonstrates sequential execution of tasks
 * within a key and parallel execution across keys.
 */
object AsyncThreads {

  val cachedPool: ExecutorService = Executors.newCachedThreadPool
  var initialData: Map[String, ListBuffer[Int]] = Map()
  var processedData: TrieMap[String, ListBuffer[Int]] = TrieMap()
  var runningTasks: TrieMap[String, CompletableFuture[Void]] = TrieMap()

  /**
   * synchronous execution across keys and values
   */
  def processSync(key: String, value: Int, initialSleep: Long) = {
    Thread.sleep(initialSleep)
    if (key.equals("key_0")) {
      println(s"${Thread.currentThread().getName} -> sleep: $initialSleep. Inserting key_0 -> $value")
    }
    processedData.getOrElseUpdate(key, new ListBuffer[Int]).addOne(value)
  }

  /**
   * parallel execution across keys
   */
  def processASync(key: String, value: Int, initialSleep: Long) = {
    val task: Runnable = () => {
      processSync(key, value, initialSleep)
    }

    // 1. Chain the futures for sequential execution within a key
    val prevFuture = runningTasks.getOrElseUpdate(key, CompletableFuture.completedFuture(null))
    runningTasks.put(key, prevFuture.thenRunAsync(task, cachedPool))

    // 2. Parallel execution across keys and values
    // cachedPool.submit(task)
  }

  def process(key: String, value: Int, initialSleep: Int): Unit = {
    //processSync(key, value, initialSleep) // synchronous execution across keys and values
    processASync(key, value, initialSleep) // parallel execution across keys
  }

  def main(args: Array[String]): Unit = {

    checkDiff()

    0.to(9).map(kIndex => {
      var key = "key_" + kIndex
      var values = ListBuffer[Int]()
      initialData += (key -> values)
      1.to(10).map(vIndex => {
        values += kIndex * 10 + vIndex
      })
    })

    println(s"before data:$initialData")

    initialData.foreach(entry => {
      entry._2.foreach(value => {
        process(entry._1, value, Random.between(0, 100))
      })
    })

    cachedPool.awaitTermination(5, TimeUnit.SECONDS)
    println(s"after data:$processedData")

    println("diff: " + (initialData.toSet diff processedData.toSet).toMap)
    cachedPool.shutdown()
  }

  def checkDiff(): Unit = {
    var a1: TrieMap[String, List[Int]] = new TrieMap()
    a1.put("one", List(1, 2, 3, 4, 5))
    a1.put("two", List(11, 12, 13, 14, 15))

    var a2: TrieMap[String, List[Int]] = new TrieMap()
    a2.put("one", List(2, 1, 3, 4, 5))
    a2.put("two", List(11, 12, 13, 14, 15))


    println("a1: " + a1)
    println("a2: " + a2)

    println("check.diff: " + (a1.toSet diff a2.toSet).toMap)
  }
}
