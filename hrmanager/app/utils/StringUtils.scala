package utils

object StringUtils {
  
  def deleteStrip(quoted: String): String = {
    var result: String = ""
    if (quoted != null) {
      if (quoted.trim().nonEmpty) {
        result = quoted.filter(char => char != '\"')
      }
    }
    result
  }
}