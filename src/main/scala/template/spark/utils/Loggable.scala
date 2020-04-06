package template.spark.utils

import org.apache.log4j.{Level, LogManager, Logger}

trait Loggable {

  @transient private lazy val logger = LogManager.getLogger(this.getClass().toString().stripSuffix("$"))

  def logInfo(msg:String): Unit = logger.info(msg)
  def logDebug(message:String): Unit = logger.debug(message)
  def logWarn(message:String): Unit = logger.warn(message)
  def logError(e:Throwable): Unit = logger.error(e)
  def logError(msg:String,e:Throwable): Unit = logger.error(msg, e)
  def logFatal(e:Throwable): Unit = logger.fatal(e)
}