package domain.exception

class Exception

class ConversionFailureException(val value: String)
class ConvertingException(val value: String)
case class InvalidContentTypeException(value: String)
