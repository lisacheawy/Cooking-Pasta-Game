package ch.makery.address.model

import scalafx.scene.image.{ImageView, Image}
import scalafx.scene.text.Text
import scalafx.scene.media.AudioClip

class FridgeModel {
  val checklistModel = new ChecklistModel()
  //Audio
  val pop = new AudioClip(getClass.getResource("pop.mp3").toExternalForm()){
    volume = 0.5
  }
  val pageFlip = new AudioClip(getClass.getResource("pageflip.mp3").toExternalForm()){
    volume = 0.4
  }

  //Fridge and Shelves
  val fridgeBackground = new Image(getClass.getResourceAsStream("fridgeOpen.png"))
  val fridgeBackgroundImageView = new ImageView(fridgeBackground)
  val shelf1 = new Image(getClass.getResourceAsStream("Shelf1.png"))
  val shelf1ImageView = new ImageView(shelf1) {
    x = 705
    y = 175
  }
  val shelf2 = new Image(getClass.getResourceAsStream("Shelf2.png"))
  val shelf2ImageView = new ImageView(shelf2) {
    x = 705
    y = 392
  }
  val shelf3 = new Image(getClass.getResourceAsStream("Shelf3.png"))
  val shelf3ImageView = new ImageView(shelf3) {
    x = 705
    y = 593
  }

  val inFridge = new Text(420, 300, "In Fridge:") {
    font = checklistModel.fontStyle2
  }

  //Back button
  val backButton = new Image(getClass.getResourceAsStream("backarrow.png"))
  val backButtonImageView = new ImageView(backButton) {
    fitHeight = 100
    fitWidth = 100
    x = 1100
    y = 770
  }

  //Ingredients
  val whippingCream = new Image(getClass.getResourceAsStream("whippingcream.png"))
  val whippingCreamImageView = new ImageView(whippingCream) {
    x = 1038
    y = 62
  }
  val smokedPorkBelly = new Image(getClass.getResourceAsStream("smokedporkbelly-wrapped.png"))
  val smokedPorkBellyImageView = new ImageView(smokedPorkBelly) {
    x = 48
    y = 115
  }
  val parmesan = new Image(getClass.getResourceAsStream("parmesan-wrapped.png"))
  val parmesanImageView = new ImageView(parmesan) {
    x = 540
    y = 540
  }
  val mushroom = new Image(getClass.getResourceAsStream("mushroom-wrapped.png"))
  val mushroomImageView = new ImageView(mushroom) {
    x = 168
    y = 313
  }

  val fridgeIngredients = Map("Smoked Pork Belly" -> smokedPorkBellyImageView, "Mushrooms" -> mushroomImageView, "Parmesan Cheese" -> parmesanImageView, "Whipping Cream" -> whippingCreamImageView)

  var counterClick = 1

  var timer = 0L
  var sceneTimer = 2.0
  var isFinished = false
}