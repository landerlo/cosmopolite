package example

import cosmopolite._
import languages.common._

type MyLangs = En & De & Es & Fr

var dynamicLang = "es"

@main
def run(lang: String): Unit =
   def number(n: Int): Messages[MyLangs] = n match
      case 1 => en"one" & fr"un" & de"ein" & es"uno" // & fr"uno" // KO
      case 2 => en"two" & fr"deux" & de"zwei" & es"dos"
      case 3 => en"three" & fr"trois" & de"drei" & es"tres"

   def number2(n: Int): Messages[Pt & It & Fr] = n match
      case 1 => it"uno" & pt"um" & fr"un"

   val msg: Messages[MyLangs] =
      en"This is ${number(1)} in English" &
      de"Das ist ${number(1)} auf Deutsch" &
      es"Es ${number(1)} en español" &
      fr"C'est ${number(1)} en français"

   val msg2: Messages[Pt & It & Fr] =
      pt"This is ${number2(1)} in port" &
      it"Das ist ${number2(1)} it" &
      fr"Das ist ${number2(1)} fr"

   val combined: Messages[MyLangs & Pt & It] = msg & msg2

   val subset: Messages[Fr & En] = msg.subset[Fr | En]

   Language.parse[MyLangs](lang).foreach { lang =>
      given Language[MyLangs] = lang
      println(combined())
   }
