package ch.makery.address.model

import scalafx.scene.image.{ImageView, Image}
import scalafx.scene.media.AudioClip
import javafx.scene.{media => jfxsm}

class StovetopModel {
  val ingredients = new StovetopIngredients()
  val instructions = new StovetopInstructions()
  val timer = new StovetopTimer()
  val control = new StovetopControls()

  //Audio
  val Indefinite: Int = jfxsm.AudioClip.INDEFINITE
  val stove = new AudioClip(getClass.getResource("stove.mp3").toExternalForm()){
    volume = 0.3
  }
  val boilingWater = new AudioClip(getClass.getResource("boilingwater.mp3").toExternalForm()){
    volume = 0.4
    cycleCount = Indefinite
  }
  val panMove = new AudioClip(getClass.getResource("panmove.mp3").toExternalForm()){
    volume = 0.4
  }
  val sizzling = new AudioClip(getClass.getResource("sizzling.mp3").toExternalForm()){
    volume = 0.3
    cycleCount = Indefinite
  }
  val grate = new AudioClip(getClass.getResource("grate.mp3").toExternalForm()){
    volume = 0.3
  }
  val pouring = new AudioClip(getClass.getResource("pouring.mp3").toExternalForm()){
    volume = 0.4
  }
  val sprinkle = new AudioClip(getClass.getResource("sprinkle.mp3").toExternalForm()){
    volume = 0.5
  }

  //Stove and pointer
  val stoveBackground = new Image(getClass.getResourceAsStream("stovetop.png"))
  val stoveBackgroundImageView = new ImageView(stoveBackground)
  val pointer1 = new Image(getClass.getResourceAsStream("pointer.png"))
  val pointer1ImageView = new ImageView(pointer1) {
    x = 257
    y = 744
  }
  val pointer2 = new Image(getClass.getResourceAsStream("pointer.png"))
  val pointer2ImageView = new ImageView(pointer2) {
    x = 406
    y = 744
  }
  var rotation = pointer1ImageView.getRotate()

  //Items
  val pot = new Image(getClass.getResourceAsStream("pot.png"))
  val potImageView = new ImageView(pot) {
    x = 165
    y = 310
  }
  val pan = new Image(getClass.getResourceAsStream("pan.png"))
  val panImageView = new ImageView(pan) {
    x = 750
    y = 360
  }
  val grater = new Image(getClass.getResourceAsStream("grater.png"))
  val graterImageView = new ImageView(grater) {
    x = 720
    y = 300
  }

  //Fire
  val fire1 = new Image(getClass.getResourceAsStream("Fire1.png"))
  val fire1ImageView = new ImageView(fire1) {
    x = 220
    y = 480
  }
  val fire2 = new Image(getClass.getResourceAsStream("Fire2.png"))
  val fire2ImageView = new ImageView(fire2) {
    x = 230
    y = 480
  }
  val fire3 = new Image(getClass.getResourceAsStream("Fire3.png"))
  val fire3ImageView = new ImageView(fire3) {
    x = 775
    y = 485
  }
  val fire4 = new Image(getClass.getResourceAsStream("Fire4.png"))
  val fire4ImageView = new ImageView(fire4) {
    x = 765
    y = 485
  }

  //Water
  val calmWater = new Image(getClass.getResourceAsStream("calmWater.png"))
  val calmWaterImageView = new ImageView(calmWater) {
    x = 226
    y = 337.5
  }
  val boilingWater1 = new Image(getClass.getResourceAsStream("boilingWater1.png"))
  val boilingWater1ImageView = new ImageView(boilingWater1) {
    x = 226
    y = 225.5
  }
  val boilingWater2 = new Image(getClass.getResourceAsStream("boilingWater2.png"))
  val boilingWater2ImageView = new ImageView(boilingWater2) {
    x = 226
    y = 221.5
  }

  //Smoke Images
  val smoke1 = new Image(getClass.getResourceAsStream("smoke1.png"))
  val smoke1ImageView = new ImageView(smoke1) {
    x = 770
    y = 290
  }
  val smoke2 = new Image(getClass.getResourceAsStream("smoke2.png"))
  val smoke2ImageView = new ImageView(smoke2) {
    x = 770
    y = 290
  }

  
}