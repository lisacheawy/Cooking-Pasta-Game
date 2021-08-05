package ch.makery.address.model

import scalafx.scene.image.{ImageView, Image}
import scalafx.scene.media.AudioClip

class DishSelectionModel {
  //Audio
  val click= new AudioClip(getClass.getResource("click.mp3").toExternalForm()){
    volume = 0.4
  }
  
  val continueBackground = new Image(getClass.getResourceAsStream("continue.png"))
  val continueBackgroundImageView = new ImageView(continueBackground)

  val carbonara = new Image(getClass.getResourceAsStream("carbonara.png"))
  val carbonaraImageView = new ImageView(carbonara) {
    x = 400
    y = 380
  }

  val continueButton = new Image(getClass.getResourceAsStream("continuebutton.png"))
  val continueButtonImageView = new ImageView(continueButton) {
    x = 380
    y = 610
  }
  val backArrow = new Image(getClass.getResourceAsStream("backarrow.png"))
  val backArrowImageView = new ImageView(backArrow) {
    x = 1100
    y = 770
  }
}