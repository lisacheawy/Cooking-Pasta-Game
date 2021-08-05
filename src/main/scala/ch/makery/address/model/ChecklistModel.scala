package ch.makery.address.model

import javafx.scene.{text => jfxst}
import scalafx.scene.image.{ImageView, Image}
import scalafx.scene.text.{Font, Text}

class ChecklistModel {
  //Checklist
  val checklistIcon = new Image(getClass.getResourceAsStream("checklistIcon.png"))
  val checklistIconImageView = new ImageView(checklistIcon) {
    x = 20
    y = 700
  }
  val checklist = new Image(getClass.getResourceAsStream("checklist.png"))
  val checklistImageView = new ImageView(checklist) {
    x = 400
    y = 190
  }
  val fontStyle = new Font(jfxst.Font.font("monospaced", 30))
  val fontStyle2 = new Font(jfxst.Font.font("monospaced", 25))
  val fontStyle3 = new Font(jfxst.Font.font("monospaced", 25))
  val collectText = new Text(425, 230, "Collect Ingredients!") {
    font = fontStyle
  }
  val allCollected = new Image(getClass.getResourceAsStream("allcollectedText.png"))
  val allCollectedImageView = new ImageView(allCollected) {
    x = 330
    y = 350
  }
}