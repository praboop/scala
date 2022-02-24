val zones = java.util.TimeZone.getAvailableIDs

(1 to 10).grouped(3).toArray

zones.map(s => s.split("/")).filter(_.length > 1).map(_(1))
  .grouped(10).toArray
  .map(_(0))
