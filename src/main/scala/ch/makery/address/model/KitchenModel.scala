package ch.makery.address.model

import scalafx.scene.image.{ImageView, Image}
import scalafx.scene.text.Text
import scalafx.scene.media.AudioClip


class KitchenModel {
  val checklistModel = new ChecklistModel()
  
  //Audio
  val pop = new AudioClip(getClass.getResource("pop.mp3").toExternalForm()){
    volume = 0.5
  }
  val pageFlip = new AudioClip(getClass.getResource("pageflip.mp3").toExternalForm()){
    volume = 0.4
  }

  //Kitchen and fridge
  val kitchenBackground = new Image(getClass.getResourceAsStream("kitchenbg.png"))
  val kitchenBackgroundImageView = new ImageView(kitchenBackground)
  val fridge = new Image(getClass.getResourceAsStream("fridgeBottom.png"))
  val fridgeImageView = new ImageView(fridge) {
    x = 25
    y = 327
  }

  val inKitchen = new Text(420, 300, "In Kitchen:") {
    font = checklistModel.fontStyle2
  }

  //Ingredients
  val salt = new Image(getClass.getResourceAsStream("salt.png"))
  val saltImageView = new ImageView(salt) {
    x = 975
    y = 97
  }
  val pepper = new Image(getClass.getResourceAsStream("pepper.png"))
  val pepperImageView = new ImageView(pepper) {
    x = 1005
    y = 97
  }
  val parsley = new Image(getClass.getResourceAsStream("parsley.png"))
  val parsleyImageView = new ImageView(parsley) {
    x = 1130
    y = 65
  }
  val oliveOil = new Image(getClass.getResourceAsStream("oliveoil.png"))
  val oliveOilImageView = new ImageView(oliveOil) {
    x = 930
    y = 247
  }
  val speget = new Image(getClass.getResourceAsStream("speget.png"))
  val spegetImageView = new ImageView(speget) {
    x = 970
    y = 266
  }
  val onion = new Image(getClass.getResourceAsStream("onion.png"))
  val onionImageView = new ImageView(onion) {
    x = 1060
    y = 317
  }
  val garlic = new Image(getClass.getResourceAsStream("garlic.png"))
  val garlicImageView = new ImageView(garlic) {
    x = 1148
    y = 313
  }

  val kitchenIngredients = Map("Salt" -> saltImageView, "Pepper" -> pepperImageView, "Parsley" -> parsleyImageView, "Olive Oil" -> oliveOilImageView, "Spaghetti" -> spegetImageView, "Onion" -> onionImageView, "Garlic" -> garlicImageView)

  var counterClick = 1

  var timer = 0L
  var sceneTimer = 2.0
  var isFinished = false
}