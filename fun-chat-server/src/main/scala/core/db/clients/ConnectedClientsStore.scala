package core.db.clients

import core.entities.ClientInformation
import core.entities.Defines.UserID

import scala.collection.mutable

/**
  * Stores user's clients information, such as IP address.
  */
class ConnectedClientsStore() {

  private val usersData: mutable.Map[UserID, ClientInformation] = mutable.Map().empty

  def find(userId: UserID): Option[ClientInformation] = {
    usersData.get(userId)
  }

  def update(userId: UserID, info: ClientInformation): Unit = {
    usersData.update(userId, info)
  }

  def remove(userId: UserID): Unit = {
    usersData.remove(userId)
  }

  def isOnline(userId: UserID): Boolean = {
    usersData.contains(userId)
  }
}