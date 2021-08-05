package ch.makery.address.view

import ch.makery.address.MainApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._ //implicit
import scalafx.scene.control.MenuBar
import scala.collection.mutable.ListBuffer

class DishSelection {
  val controller = new DishSelectionController()

  def dishSelection(stage: PrimaryStage, menuBar: MenuBar): Unit = {
    val scene = new Scene {
      content = new ListBuffer()
      content += (controller.model.continueBackgroundImageView, controller.model.carbonaraImageView, controller.model.continueButtonImageView, controller.model.backArrowImageView)

      controller.model.continueButtonImageView.onMousePressed = handle {
        controller.model.click.play()
        MainApp.sceneCategoryCounter += 1
        MainApp.sceneCategory()
      }

      controller.model.backArrowImageView.onMousePressed = handle {
        MainApp.sceneCategoryCounter -= 1
        MainApp.sceneCategory()
      }
      content += menuBar
    }
    stage.setScene(scene)
  }

}