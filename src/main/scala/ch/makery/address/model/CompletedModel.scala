package ch.makery.address.model

import scalafx.scene.image.{ImageView, Image}

class CompletedModel {
  val completedBackground = new Image(getClass.getResourceAsStream("completedbg.png"))
  val completedBackgroundImageView = new ImageView(completedBackground)

  val carbonara = new Image(getClass.getResourceAsStream("carbonara.png"))
  val carbonaraImageView = new ImageView(carbonara) {
    x = 420
    y = 450
  }

  //Smoke Images
  val smoke1 = new Image(getClass.getResourceAsStream("smoke1.png"))
  val smoke1ImageView = new ImageView(smoke1) {
    fitWidth = 310.5
    fitHeight = 192
    x = 470
    y = 280
  }
  val smoke2 = new Image(getClass.getResourceAsStream("smoke2.png"))
  val smoke2ImageView = new ImageView(smoke2) {
    fitWidth = 313
    fitHeight = 192
    x = 470
    y = 280
  }

  var timer = 0L
  var smokeTimer = 0.5
  var isSwap = false
}