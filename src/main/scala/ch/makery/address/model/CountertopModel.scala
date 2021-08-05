package ch.makery.address.model

import scalafx.scene.image.{ImageView, Image}
import scalafx.scene.media.AudioClip

class CountertopModel {
  val ingredients = new CountertopIngredients()
  //Audio
  val knifeChop = new AudioClip(getClass.getResource("knifechop.mp3").toExternalForm()){
    volume = 0.5
  }
  val cuttingMeat = new AudioClip(getClass.getResource("cuttingmeat.mp3").toExternalForm()){
    volume = 0.7
  }

  val counterTopBackground = new Image(getClass.getResourceAsStream("countertop.png"))
  val counterTopBackgroundImageView = new ImageView(counterTopBackground)
  //Text Images
  val startText = new Image(getClass.getResourceAsStream("startText.png"))
  val startTextImageView = new ImageView(startText) {
    x = 250
    y = 40
  }
  val clickToChop = new Image(getClass.getResourceAsStream("clickToChop.png"))
  val clickToChopImageView = new ImageView(clickToChop) {
    x = 400
    y = 40
  }
  val holdDrag = new Image(getClass.getResourceAsStream("holdDrag.png"))
  val holdDragImageView = new ImageView(holdDrag) {
    x = 300
    y = 40
  }
  val allPrep = new Image(getClass.getResourceAsStream("allPrep.png"))
  val allPrepImageView = new ImageView(allPrep) {
    x = 330
    y = 40
  }
  val garlicMincedText = new Image(getClass.getResourceAsStream("garlicMincedText.png"))
  val garlicMincedTextImageView = new ImageView(garlicMincedText) {
    x = 365
    y = 50
  }
  val onionMincedText = new Image(getClass.getResourceAsStream("onionMincedText.png"))
  val onionMincedTextImageView = new ImageView(onionMincedText) {
    x = 365
    y = 50
  }
  val porkBellyText = new Image(getClass.getResourceAsStream("porkBellyText.png"))
  val porkBellyTextImageView = new ImageView(porkBellyText) {
    x = 170
    y = 50
  }
  val mushroomText = new Image(getClass.getResourceAsStream("mushroomText.png"))
  val mushroomTextImageView = new ImageView(mushroomText) {
    x = 290
    y = 50
  }

  //Knife
  val knife = new Image(getClass.getResourceAsStream("knife.png"))
  val knifeImageView = new ImageView(knife)

  //Arrow
  val arrow = new Image(getClass.getResourceAsStream("arrow.png"))
  val arrowImageView = new ImageView(arrow) {
    x = 525
    y = 485
  }

  //Timers
  var timer = 0L
  var garlicTimer = 2.0
  var onionTimer = 1.5
  var porkBellyTimer = 1.5
  var porkBellyCutTimer = 1.5
  var mushroomTimer = 1.5
  var allPrepTimer = 1.5
  var nextSceneTimer = 2.0
}