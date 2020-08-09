package domain.exception

sealed trait Exception extends Throwable

case class ConversionFailureException(value: String = "") extends Exception
class ConvertingException(val value: String)              extends Exception
case class InvalidContentTypeException(value: String)     extends Exception
case class DatabaseException()                            extends Exception
case class PictureNotFoundException()                     extends Exception
