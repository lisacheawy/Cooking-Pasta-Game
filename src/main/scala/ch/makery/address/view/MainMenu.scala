package ch.makery.address.view

import ch.makery.address.MainApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._ //implicit
import scalafx.scene.control._
import scalafx.scene.control.MenuBar

import scala.collection.mutable.ListBuffer

class MainMenu {
  val controller = new MainMenuController()

  def mainMenu(stage: PrimaryStage, menuBar: MenuBar): Unit = {
    val scene = new Scene {
      content = new ListBuffer()
      content += (controller.model.menuBackgroundImageView, controller.model.playButtonImageView)

      controller.model.playButtonImageView.onMousePressed = handle {
        controller.model.click.play()
        MainApp.sceneCategoryCounter += 1
        MainApp.sceneCategory()
      }
      content += menuBar
    }
    stage.setScene(scene)
  }

}