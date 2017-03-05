package commands.entities

import commands.ClientCommands

case class HelpCommand(input: String) extends ClientCommand {

  override val command: String = ClientCommands.HELP

  override def tokens: Set[String] = Set.empty[String]

  def execute(): Unit = {
    println(
        "signIn -u <user-name> -p <password> :   start user authentication process into fun-chat.\n" +
        "signUp -u <user-name> -p <password> :   register a new user to fun-chat.\n" +
        "signOut :   sign-out authenticated user from fun-chat.\n" +
        "exit    :   exit fun-chat demo client.\n" +
        "\n" +
        "who-online :   print all authenticated users to system.\n" +
        "send to List[\"<user-name>\"] message List[\"<content>\"] attachment List[\"<attachment-id>\"]" +
        " :   send message to authenticated user.\n" +
        "\n"
    )
  }
}
