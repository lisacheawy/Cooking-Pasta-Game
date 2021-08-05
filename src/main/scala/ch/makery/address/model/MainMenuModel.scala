package ch.makery.address.model

import scalafx.scene.image.{ImageView, Image}
import scalafx.scene.media.AudioClip

class MainMenuModel {
  //Audio
  val click= new AudioClip(getClass.getResource("click.mp3").toExternalForm()){
    volume = 0.4
  }
  
  val menuBackground = new Image(getClass.getResourceAsStream("brickbg.jpg"))
  val menuBackgroundImageView = new ImageView(menuBackground)

  val playButton = new Image(getClass.getResourceAsStream("playbutton.png"))
  val playButtonImageView = new ImageView(playButton) {
    x = 400
    y = 550
  }
}