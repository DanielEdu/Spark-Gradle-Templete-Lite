package template.spark.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone

object DateUtils {

  def getTimestampString(date: String, hours: Int, minutes: Int): String = {
    val dateCalendar = Calendar.getInstance()
    dateCalendar.setTime(new SimpleDateFormat("yyy-MM-dd").parse(date))
    dateCalendar.set(Calendar.HOUR_OF_DAY, hours)
    dateCalendar.set(Calendar.MINUTE, minutes)
    dateCalendar.set(Calendar.SECOND, dateCalendar.getMinimum(Calendar.SECOND))
    dateCalendar.set(Calendar.MILLISECOND, dateCalendar.getMinimum(Calendar.MILLISECOND))
    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateCalendar.getTime())
  }

  def getStartOfDay(day: String): String = {
    val dateCalendar = Calendar.getInstance()
    dateCalendar.setTime(new SimpleDateFormat("yyy-MM-dd").parse(day))
    dateCalendar.set(Calendar.HOUR_OF_DAY, dateCalendar.getMinimum(Calendar.HOUR_OF_DAY))
    dateCalendar.set(Calendar.MINUTE, dateCalendar.getMinimum(Calendar.MINUTE))
    dateCalendar.set(Calendar.SECOND, dateCalendar.getMinimum(Calendar.SECOND))
    dateCalendar.set(Calendar.MILLISECOND, dateCalendar.getMinimum(Calendar.MILLISECOND))
    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(dateCalendar.getTime)
  }

  def getEndOfDay(day: String): String = {
    val dateCalendar = Calendar.getInstance()
    dateCalendar.setTime(new SimpleDateFormat("yyy-MM-dd").parse(day))
    dateCalendar.set(Calendar.HOUR_OF_DAY, dateCalendar.getMaximum(Calendar.HOUR_OF_DAY))
    dateCalendar.set(Calendar.MINUTE, dateCalendar.getMaximum(Calendar.MINUTE))
    dateCalendar.set(Calendar.SECOND, dateCalendar.getMaximum(Calendar.SECOND))
    dateCalendar.set(Calendar.MILLISECOND, dateCalendar.getMaximum(Calendar.MILLISECOND))
    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(dateCalendar.getTime)
  }

  def getSimpleDateFormat(format: String, timeZone: TimeZone): SimpleDateFormat = {
    val sdf = new SimpleDateFormat(format)
    sdf.setTimeZone(timeZone)
    sdf
  }

  def formatDate(date: String, formatInput: String, formatOuput: String): String = {
    val inputFormat = new SimpleDateFormat(formatInput)
    val outputFormat = new SimpleDateFormat(formatOuput)
    outputFormat.format(inputFormat.parse(date)).toString()
  }

}
